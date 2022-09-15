package duke;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that creates Storage object to do file manipulation
 * @author amresh A0235398R
 */
public class Storage {

    protected String filePath;
    protected ArrayList<Task> storeLists = new ArrayList<>();

    /**
     * Constructor that creates Storage object
     * @param filePath Location where file is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads file at the beginning of the program
     * @return ArrayList<Task> that will return when method runs
     */
    public ArrayList<Task> loadFile() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            if (file.length() == 0) {
                throw new DukeException ("File is Empty!");

            } else {
                while (scanner.hasNextLine()) {
                    String task = scanner.nextLine();
                    renderStringAsTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println("File is empty");
        }
        return storeLists;
    }

    /**
     * Method that parses strings and converts them to Tasks to be added to storeList
     * @param taskStr String that will be passed into method to convert
     */
    private void renderStringAsTask(String taskStr) {
        String taskType = String.valueOf(taskStr.charAt(3));
        assert taskType.equals("T") || taskType.equals("E") || taskType.equals("D");

        String taskDescription = taskStr.split("] ", 2)[1];
        if (taskType.equals("T")) {
            Todo t = new Todo(taskDescription);
            storeLists.add(t);
        } else if (taskType.equals("D")) {
            String duration = taskDescription.split("y: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Deadline d = new Deadline(taskDescription, duration);
            storeLists.add(d);
        } else {
            String duration = taskDescription.split("t: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Event e = new Event(taskDescription, duration);
            storeLists.add(e);
        }
    }

    /**
     * Writes any additions, removals or changes to tasks to file
     * @param textToAlter text that needs to be added or removed or changed
     */
    public void writeToFile(ArrayList<Task> textToAlter) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("File does not exist.");
            }

            FileWriter fwriter = new FileWriter(filePath);
            String output = "";
            for (Task item : textToAlter) {
                output += (textToAlter.indexOf(item) + 1) + "." + item  + "\n";
            }

            fwriter.write(output);
            fwriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
