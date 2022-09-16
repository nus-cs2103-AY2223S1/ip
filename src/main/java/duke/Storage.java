package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.task.Activity;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles the loading and saving of tasks in the save file.
 *
 * @author Justin Peng
 */
public class Storage {
    /** Path to {@code .txt} save file */
    private String saveFilePath = "data.txt";

    protected Storage() {}

    protected Storage(String saveFilePath) {
        if (validateFilePath(saveFilePath)) {
            this.saveFilePath = saveFilePath;
        }
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    /**
     * Checks whether the given file path points to a {@code .txt} file.
     *
     * @param filePath The file path.
     * @return {@code true} if the file path is valid, else {@code false}.
     */
    private static boolean validateFilePath(String filePath) {
        try {
            Paths.get(filePath);
            if (!filePath.endsWith(".txt")) {
                return false;
            }
            File saveFile = new File(filePath);
            if (saveFile.getParentFile() != null) {
                saveFile.getParentFile().mkdirs();
            }
            saveFile.createNewFile();
            return saveFile.exists();
        } catch (InvalidPathException | IOException e) {
            return false;
        }
    }


    /**
     * Loads the tasks from the save file into a task list in the specified timezone.
     *
     * @param timeZone The timezone to display the tasks in.
     * @return A list of loaded tasks.
     */
    protected ArrayList<Task> loadTasks(ZoneId timeZone) {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File saveFile = new File(saveFilePath);
            Scanner saveSc = new Scanner(saveFile);

            while (saveSc.hasNextLine()) {
                String[] dataArr = saveSc.nextLine().split(" \\| ");
                char taskType = dataArr[0].charAt(0);
                boolean isDone = Boolean.parseBoolean(dataArr[1]);
                String taskDescription = dataArr[2];
                Task newTask;

                switch (taskType) {
                case 'A':
                    newTask = new Activity(taskDescription, Duration.parse(dataArr[3]));
                    break;
                case 'D':
                    newTask = new Deadline(taskDescription,
                            ZonedDateTime.parse(dataArr[3]).withZoneSameInstant(timeZone));
                    break;
                case 'E':
                    newTask = new Event(taskDescription,
                            ZonedDateTime.parse(dataArr[3]).withZoneSameInstant(timeZone));
                    break;
                case 'T':
                    newTask = new Todo(taskDescription);
                    break;
                default:
                    System.out.println("The following task could not be loaded from memory:\n"
                            + Arrays.toString(dataArr));
                    continue;
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                tasks.add(newTask);
            }

            System.out.println(tasks.size() + "task(s) successfully loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("No tasks to load.");
        }

        return tasks;
    }

    /**
     * Saves the current tasks from the given task list to the save file
     *
     * @param tasks A list of tasks to save.
     */
    protected String updateSaveFile(ArrayList<Task> tasks) {
        try {
            FileWriter saveFileWriter = new FileWriter(saveFilePath);
            for (Task task : tasks) {
                System.out.println(task);
                String saveMsg = String.format("%c | %s | %s", task.getType(), task.getIsDone(), task.getDescription());
                if (task instanceof Deadline) {
                    saveMsg += " | " + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    saveMsg += " | " + ((Event) task).getAt();
                } else if (task instanceof Activity) {
                    System.out.println("activity");
                    saveMsg += " | " + ((Activity) task).getDuration();
                }
                saveFileWriter.write(saveMsg + "\n");
            }
            saveFileWriter.close();
            return "Tasks saved successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while saving your tasks.";
        }
    }
}
