package duke.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Storage class handles the storing of tasks so that we can save our tasks.
 * Then, they can be loaded in the next session.
 */
public class Storage {
    private final String filePath;
    private File db;

    /**
     * Constructor of the Storage.
     * @param filePath the file path of where the txt file is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        // creates a file if file did not exist
        try {
            db = new File(filePath);
            db.createNewFile();
        } catch (IOException e) {
            System.out.printf("Failed to create file at %s%n", this.filePath);
        }
    }

    /**
     * Writes taskList into duke.txt
     * Overwrites the whole file with current taskList
     * @param taskList the taskList we are saving
     */
    public void write(ArrayList<Task> taskList) {
        try {
            FileWriter dbWriter = new FileWriter(this.filePath);
            for (Task task : taskList) {
                dbWriter.write(task.dbRepresentation() + "\n");
            }
            dbWriter.close();
        } catch (IOException e) {
            System.out.printf("Failed to write file at %s%n", this.filePath);
        }
    }

    /**
     * Loads taskList from duke.txt
     * @return the loaded taskList.
     */
    public TaskList load() {
        TaskList taskList = new TaskList(this);
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    taskList.addTaskFromDb(line);
                } catch (DukeException e) {
                    System.out.println("Corrupted duke.data found!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return taskList;
    }

}
