package dan;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import java.util.Scanner;


public class Dan {
    private TaskListReader tlr;
    private TaskList tasks;
    private Parser parser;
    
    public Dan(String fileName) {
        tlr = new TaskListReader(fileName);
        try {
            tasks = new TaskList(tlr.readTaskListFromFile());
            parser = new Parser(tasks);
        } catch (NoSuchFileException e) {
            try {
                Ui.printIndent("Task list data not found, creating new data file...");
                tlr.createFile();
                Ui.printIndent(String.format("Data file created at %s\n Please start me again.", tlr.getPath()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                Ui.printIndent("Error when creating new data file");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            Ui.printIndent("Error when reading current data file");
        }
    }

    public static void main(String[] args) {
        new Dan("TestData1.txt").run();
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        Ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = sc.nextLine().strip();
                isExit = parser.parse(input);
                tlr.writeTaskListToFile(tasks);
            } catch (IOException ioe) {
                Ui.printIndent(ioe.getMessage() + "Error when creating saving to data file");
            }
        }
        // writes list every iteration
    }
}
