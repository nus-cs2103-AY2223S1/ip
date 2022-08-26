package data;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private File db;

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

    public TaskList load() {
        TaskList taskList = new TaskList(this);
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    taskList.addTaskFromDb(line);
                } catch (DukeException e) {
                    System.out.println("Corrupted data found!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return taskList;
    }

}
