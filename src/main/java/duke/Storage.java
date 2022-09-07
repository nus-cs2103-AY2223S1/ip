package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to handle the saving and loading of tasks into/from a file.
 */
public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Saves TaskList to a file.
     *
     * @param tasks A TaskList object containing lists of tasks.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.saveTaskAsString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file to an ArrayList of tasks.
     *
     * @return An ArrayList of tasks loaded from previously saved file (if there is saved file),
     * or a new ArrayList of tasks (if there is no saved file).
     * @throws IOException If there is error in creating directory.
     * @throws DukeException If task type is not supported.
     */
    public ArrayList<Task> loadSaveData() throws DukeException, IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        if (!this.file.exists()) {
            System.out.println("No existing save file found. Creating 'duke.txt' for you!");
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException err) {
                System.out.println("Problem trying to create directory!");
            }
        } else {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String taskString = scan.nextLine();

                // split and organise task data
                String[] taskSplit = taskString.split(" \\| ");
                String taskType = taskSplit[0];
                boolean isTaskDone = taskSplit[1].equals("1");
                String taskDescription = taskSplit[2];

                Task task;
                switch (taskType) {
                case "T": {
                    task = new ToDo(taskDescription);
                    break;
                }
                case "D": {
                    assert taskSplit.length > 3 : "deadline task not split correctly";
                    String taskDate = taskSplit[3];
                    task = new Deadline(taskDescription, taskDate);
                    break;
                }
                case "E": {
                    String taskDate = taskSplit[3];
                    task = new Event(taskDescription, taskDate);
                    break;
                }
                default:
                    throw new DukeException("Task type is not supported!");
                }
                if (isTaskDone) {
                    task.markAsDone();
                }
                loadedTasks.add(task);
            }
        }
        return loadedTasks;
    }

}
