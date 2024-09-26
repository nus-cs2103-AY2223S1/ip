package froggy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import froggy.exception.FroggyException;
import froggy.parser.Parser;
import froggy.task.Task;
import froggy.task.TaskList;

/**
 * The Storage class handles the creation of a file specified by the duke.txt file.
 */
public class Storage {
    /* The filepath that the file should be created or read from. */
    protected String filePath;

    /**
     * Creates a Storage object.
     *
     * @param filePath The filePath of whether the file should be created or read from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Returns an ArrayList Task after reading the contents of the file specified by the filePath variable.
     */
    public ArrayList<Task> load() throws FroggyException {
        // Reused from https://www.w3schools.com/java/java_files_create.asp
        // Reused from https://www.w3schools.com/java/java_files_read.asp

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                return tasks;
            } else {
                return readFile(myObj, tasks);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Saves the data from the TaskList object into a file specified by the filePath.
     */
    public void writeToFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            ArrayList<Task> tasksArrayList = tasks.getAllTasks();
            for (Task task : tasksArrayList) {
                myWriter.write(task.toFileString() + System.getProperty("line.separator"));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Returns an ArrayList Task object after reading the contents of the file.
     *
     * @param myObj A file object.
     * @param tasks An array with which the contents of the file read would be written to.
     * @return An ArrayList containing the tasks read from file.
     * @throws FroggyException If there is an error found when reading the file.
     */
    private ArrayList<Task> readFile(File myObj, ArrayList<Task> tasks) throws FroggyException {
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tasks.add(Parser.readLine(data));
            }
        } catch (FileNotFoundException e) {
            throw new FroggyException("An error occurred.");
        }
        return tasks;
    }
}
