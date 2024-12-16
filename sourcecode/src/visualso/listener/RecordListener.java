package visualso.listener;

import visualso.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JButton;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RecordListener implements ActionListener {

    private boolean isRecording = false;
    private ByteArrayOutputStream audioData;
    private TargetDataLine microphone;
    private String sortType;
    private String array;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
    }

    private void startRecording() {
        try {
            AudioFormat format = new AudioFormat(16000, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Microphone not supported.");
                return;
            }

            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            audioData = new ByteArrayOutputStream();

            isRecording = true;
            System.out.println("Recording started...");

            Thread recordingThread = new Thread(() -> {
                byte[] buffer = new byte[1024];
                microphone.start();
                while (isRecording) {
                    int bytesRead = microphone.read(buffer, 0, buffer.length);
                    audioData.write(buffer, 0, bytesRead);
                }
            });

            recordingThread.start();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    private void stopRecording() {
        isRecording = false;
        microphone.stop();
        microphone.close();
        System.out.println("Recording stopped.");

        // Process the recorded audio (e.g., save it as a file)
        byte[] audioBytes = audioData.toByteArray();
        saveAudioToFile(audioBytes);

        processAudioCommand(audioBytes);
        VoiceToText();
        GetSortType();

    }

    private void saveAudioToFile(byte[] audioBytes) {
        try {
            File assetsDir = new File("assets");
            if (!assetsDir.exists()) {
                assetsDir.mkdir();
            }
            File audioFile = new File(assetsDir, "recorded_audio.wav");
            AudioFormat format = new AudioFormat(16000, 16, 1, true, true);
            try (AudioInputStream audioInputStream = new AudioInputStream(
                    new ByteArrayInputStream(audioBytes),
                    format,
                    audioBytes.length / format.getFrameSize())) {
                AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
                System.out.println("Audio saved to " + audioFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processAudioCommand(byte[] audioBytes) {
        // Implement your logic to convert audio to text and handle commands
        System.out.println("Processing audio command...");
    }

    public static void VoiceToText() {
        try {
            String wavFilePath = "D:\\Documents\\AlgoVisualiser\\sourcecode\\src\\visualso\\assets\\recorded_audio.wav";

            String pythonCommand = "python3";
            String pythonScriptPath = "script.py";

            ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand, pythonScriptPath, wavFilePath);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println("Python Output: " + line);
                output.append(line).append("\n");
            }

            process.waitFor();

            String outputFilePath = "D:\\Documents\\AlgoVisualiser\\sourcecode\\src\\visualso\\assets\\output.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write(output.toString());
                System.out.println("Output has been written to: " + outputFilePath);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void GetSortType() {
        String outputFilepath = "D:\\Documents\\AlgoVisualiser\\sourcecode\\src\\visualso\\assets\\output.txt";

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(outputFilepath));

            sortType = lines.get(0); // First line
            array = lines.get(1); // Second line

            System.out.println("Sort type:" + sortType);
            System.out.println("Array: " + array);

            // Write to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Documents\\OOP_projectv2\\visualso\\visualso\\assets\\sortType.txt"))) {
                writer.write(sortType);
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Documents\\OOP_projectv2\\visualso\\visualso\\assets\\array.txt"))) {
                writer.write(array);
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }

        } catch (IOException e1) {
            System.out.println("An error occurred while reading the file: " + e1.getMessage());
        }
    }

    public void displaySortScreen(ActionEvent e) {
        if (sortType == null) {
            System.err.println("sortType is null. Cannot display sort screen.");
            return;
        }

        switch (sortType) {
            case "Bubble Sort":
                new MergeSortScreen().setVisible(true);
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
                break;
            case "Counting Sort":
                new CountingSortScreen().setVisible(true);
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
                break;
            case "Insertion Sort":
//                new InsertionSortScreen().setVisible(true); Uncomment when InsertionSortScreen is completed
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
                break;
            case "Merge Sort":
                new MergeSortScreen().setVisible(true);
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
            case "Quick Sort":
//                new QuickSortScreen().setVisible(true); Uncomment
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
            case "Radix Sort":
//                new RadixSortScreen().setVisible(true); Uncomment
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
            case "Selection Sort":
//                new SelectionSortScreen().setVisible(true); Uncomment
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
            case "Shell Sort":
//                new ShellSortScreen().setVisible(true); Uncomment
                SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
            default:
                System.err.println("Invalid sort type: " + sortType);
        }

    }

}
