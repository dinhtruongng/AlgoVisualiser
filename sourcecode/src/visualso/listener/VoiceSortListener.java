package visualso.listener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import visualso.view.*;

public class VoiceSortListener implements ActionListener {
    String name;

    public VoiceSortListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = new File("sourcecode\\assets").getAbsolutePath() + "\\output.txt";

        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            name = lines.get(0);
        } catch (IOException e1) {
            System.err.println("An error occurred while reading the file: " + e1.getMessage());
        } catch (IndexOutOfBoundsException e1) {
            System.err.println("The file is empty or does not have enough lines.");
        }
        switch (name) {
            case "Merge Sort":
                new MergeSortScreen();
                SwingUtilities.windowForComponent(((JButton) e.getSource())).dispose();
                break;
            case "Bubble Sort":
                new BubbleSortScreen();
                SwingUtilities.windowForComponent(((JButton) e.getSource())).dispose();
                break;
            case "Insertion Sort":
                new InsertionSortScreen();
                SwingUtilities.windowForComponent(((JButton) e.getSource())).dispose();
                break;
            case "Shell Sort":
                new CountingSortScreen();
                SwingUtilities.windowForComponent(((JButton) e.getSource())).dispose();
                break;
            case "Selection Sort":
                new SelectionSortScreen();
                SwingUtilities.windowForComponent(((JButton) e.getSource())).dispose();
                break;
            case "Quick Sort":
                new QuickSortScreen();
                SwingUtilities.windowForComponent(((JButton) e.getSource())).dispose();
                break;
            case "Radix Sort":
                new RadixSortScreen();
                SwingUtilities.windowForComponent(((JButton) e.getSource())).dispose();
                break;

        }
    }
}