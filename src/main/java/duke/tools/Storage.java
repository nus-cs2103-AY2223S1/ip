package duke.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Storage for list of tasks.
 */
public class Storage {
    private File data;
    private File dir;
    private FileWriter writer;
    private String PATH;

    /**
     * Creates a Storage object to save tasks to memory.
     * @param filePath Path to txt file where tasks will be saved.
     */
    public Storage(String filePath) {
        this.PATH = filePath;
        this.dir = new File(PATH);
        this.data = new File(PATH + "tasks.txt");

        try {
            if (!this.dir.exists()) {
                dir.mkdir();
            }
            if (this.data.createNewFile()) {
                System.err.println("File created " + this.data.getName());
            } else {
                System.err.println("File already exists at " + this.data.getPath());
            }
        } catch (IOException e) {
            System.out.println("I cannot save all your tasks in a file! Please try again.");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Saves tasks to memory on computer, in a txt file.
     * @param tasks List of tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        try {
            this.writer = new FileWriter(this.data);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                this.writer.write(task.toString() + "\n");
                System.err.println("Successfully saved: " + task.toString());
            }
            this.writer.close();

        } catch (IOException e) {
            System.out.println("I cannot save the task.");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns a list of tasks read from existing data file.
     * @return A list of tasks.
     */
    public TaskList load() {
        TaskList dataTasks = new TaskList();
        try {
            Scanner fileScanner = new Scanner(this.data);
            while (fileScanner.hasNextLine()) {
                String input = fileScanner.nextLine();
                System.out.println(input);
                String[] keywords = input.replace('|', '/').split("/", 2);
                dataTasks.loadTask(keywords);
            }

        } catch (FileNotFoundException e) {
            System.out.println("I... I can't find the file :(");
            System.err.println("Error: " + e.getMessage());
        } finally {
            return dataTasks;
        }
    }
}
