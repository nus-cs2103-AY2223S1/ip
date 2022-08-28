package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private String saveFilePath = "data.txt";

    public String getSaveFilePath() {
        return saveFilePath;
    }

    protected void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

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

                if (taskType == 'D') {
                    newTask = new Deadline(taskDescription,
                            ZonedDateTime.parse(dataArr[3]).withZoneSameInstant(timeZone));
                } else if (taskType == 'E') {
                    newTask = new Event(taskDescription,
                            ZonedDateTime.parse(dataArr[3]).withZoneSameInstant(timeZone));
                } else if (taskType == 'T') {
                    newTask = new Todo(taskDescription);
                } else {
                    System.out.println("The following task could not be loaded from memory:\n"
                            + Arrays.toString(dataArr));
                    continue;
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                tasks.add(newTask);
            }

            if (tasks.isEmpty()) {
                System.out.println("No tasks to load.");
            } else {
                System.out.println("Tasks successfully loaded!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No tasks to load.");
        }

        return tasks;
    }

    protected void updateSaveFile(ArrayList<Task> tasks) {
        try {
            System.out.println(saveFilePath);
            FileWriter saveFileWriter = new FileWriter(saveFilePath);
            for (Task task : tasks) {
                String saveMsg = String.format("%c | %s | %s", task.getType(), task.getIsDone(), task.getDescription());
                if (task instanceof Deadline) {
                    saveMsg += " | " + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    saveMsg += " | " + ((Event) task).getAt();
                }
                try {
                    saveFileWriter.write(saveMsg + "\n");
                } catch (IOException e) {
                    System.out.println("An error occurred while saving your tasks.");
                    e.printStackTrace();
                }
            }
            System.out.println("Tasks saved successfully!");
            saveFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
        }
    }
}
