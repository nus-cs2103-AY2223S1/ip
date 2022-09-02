package jarvis.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jarvis.JarvisException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.task.ToDo;

/**
 * Storage - Loads and stores tasks locally.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor.
     *
     * @param filePath file path of where tasks.txt is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from local tasks.txt file.
     *
     * @return the list of tasks stored from the previous session.
     * @throws JarvisException exception for error when loading tasks.
     */
    public List<Task> loadTasks() throws JarvisException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                Task task;

                while (sc.hasNextLine()) {
                    String[] split = sc.nextLine().split("\\|");
                    switch (split[0]) {
                    case "T":
                        task = new ToDo(split[2]);
                        break;
                    case "D":
                        task = new Deadline(split[2], split[3]);
                        break;
                    case "E":
                        task = new Event(split[2], split[3]);
                        break;
                    default:
                        throw new JarvisException("Failed to load task\n");
                    }

                    if (split[1].equals("1")) {
                        task.setIsDone(true);
                    }

                    tasks.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return tasks;
    }

    /**
     * Saves tasks into local tasks.txt file.
     *
     * @param tasks the list of tasks to be stored.
     */
    public void saveTasks(TaskList tasks) {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();

        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task: tasks.getTasks()) {
                StringBuilder taskString = new StringBuilder();
                if (task instanceof ToDo) {
                    taskString.append("T|");
                } else if (task instanceof Deadline) {
                    taskString.append("D|");
                } else if (task instanceof Event) {
                    taskString.append("E|");
                }

                if (task.getIsDone()) {
                    taskString.append("1|");
                } else {
                    taskString.append("0|");
                }

                taskString.append(task.getDescription());

                if (task instanceof Deadline) {
                    taskString.append("|");
                    taskString.append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    taskString.append("|");
                    taskString.append(((Event) task).getAt());
                }

                taskString.append(System.lineSeparator());
                fileWriter.write(taskString.toString());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
