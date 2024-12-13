package visualso.listener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import visualso.view.BubbleSortScreen;
import visualso.view.InsertionSortScreen;
import visualso.view.CountingSortScreen;
import visualso.view.MergeSortScreen;
import visualso.view.QuickSortScreen;
import visualso.view.RadixSortScreen;
import visualso.view.SelectionSortScreen;

public class VoiceSortListener implements ActionListener {
    String name;

    public VoiceSortListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = "D:\\Documents\\AlgoVisualiser\\sourcecode\\src\\visualso\\assets\\output.txt";

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