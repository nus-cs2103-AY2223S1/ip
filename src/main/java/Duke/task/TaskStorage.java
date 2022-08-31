package Duke.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Duke.DukeException;

/**
 * Represents the class responsible for saving and
 * loading list of tasks to and from the Tasks.txt file
 */
public class TaskStorage {
    private String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks into the Tasks.txt file
     *
     * @param taskList
     */
    public void saveTask(TaskList taskList) {

        try {
            File original = new File(filePath);
            FileWriter taskUpdater = new FileWriter(original);
            for (int i = 0; i < taskList.getLength(); i++) {
                Task temp = taskList.getTask(i);
                String completed = temp.getDone();
                if (temp.isTodo()) {
                    String task = "T" + "|" + completed
                            + "|" + temp.getDescription();
                    taskUpdater.write(task + "\n");
                }

                if (temp.isDeadline()) {
                    Deadline temp1 = (Deadline) temp;
                    String task = "D" + "|" + completed
                            + "|" + temp1.getDescription()
                                + "|" + temp1.getDeadline();
                    taskUpdater.write(task + "\n");
                }
                if (temp.isEvent()) {
                    Event temp1 = (Event) temp;
                    String task = "D" + "|" + completed
                            + "|" + temp1.getDescription()
                                + "|" + temp1.getTime();
                    taskUpdater.write(task + "\n");
                }
            }
            taskUpdater.close();
        } catch (IOException e) {
            System.out.println("Error occurred while updating Task Storage)");
        }
    }

    /**
     * Reads the Tasks.txt file and loads a list of tasks to be managed by Duke
     *
     * @return a TaskList
     */
    public TaskList loadTask() {
        ArrayList<Task> tempList = new ArrayList<>();
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File taskFile = new File(filePath);

            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                String[] taskInfo = task.split("\\|");
                if (taskInfo[0].equals("T")) {
                    Todo temp = new Todo(taskInfo[2], (taskInfo[1].equals("1")));
                    tempList.add(temp);
                }
                if (taskInfo[0].equals("D")) {
                    Deadline temp = new Deadline(taskInfo[2], taskInfo[3], (taskInfo[1].equals("1")));
                    tempList.add(temp);
                }
                if (taskInfo[0].equals("E")) {
                    Event temp = new Event(taskInfo[2], taskInfo[3], (taskInfo[1].equals("1")));
                    tempList.add(temp);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (DukeException e) {
            System.out.println("An error occurred while loading Tasks from the Task Storage file");
        }
        TaskList result = new TaskList(tempList);
        return result;
    }
}
