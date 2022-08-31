package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static final String DIR = System.getProperty("user.dir");
    private String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath The relative path of the location to store the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the saved tasks from filePath.
     *
     * @return ArrayList containing the saved tasks.
     * @throws IOException If the target is not found.
     */
    public ArrayList<Task> load() throws IOException {
        File target = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (target.exists()) {
            Scanner sc = new Scanner(target);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] components = line.split(Pattern.quote(" | "));
                String taskType = components[0];
                boolean isDone = components[1].equals("1");
                String desc = components[2];
                switch (taskType) {
                // TODO
                case "T":
                    Todo todo = new Todo(desc);
                    tasks.add(todo);
                    break;

                // DEADLINE
                case "D":
                    String dl = components[3];
                    Deadline deadline = new Deadline(desc, dl);
                    tasks.add(deadline);
                    break;

                // EVENT
                case "E":
                    String time = components[3];
                    Event event = new Event(desc, time);
                    tasks.add(event);
                    break;
                default:
                    break;
                }
                if (isDone) {
                    tasks.get(tasks.size() - 1).markTask();
                }
            }
        } else {
            File parent = new File(DIR + "/data");
            boolean isFolderCreated = parent.mkdir();
            boolean isFileCreated = target.createNewFile();
        }
        return tasks;
    }

    /**
     * Stores the tasks after closing the program.
     *
     * @param tasks The list of tasks the user has inputted.
     * @throws IOException If the named file is not a file (e.g. directory).
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.get(i);
            String status = curr.getStatusIcon().equals("X") ? "1" : "0";
            if (curr instanceof Todo) {
                writer.write("T | " + status + " | " + curr.getDescription() + System.lineSeparator());
            } else if (curr instanceof Deadline) {
                writer.write("D | " + status + " | " + curr.getDescription() + " | "
                        + ((Deadline) curr).getBy().toString() + System.lineSeparator());
            } else if (curr instanceof Event) {
                writer.write("E | " + status + " | " + curr.getDescription() + " | "
                        + ((Event) curr).getAt().toString() + System.lineSeparator());
            }
        }
        writer.close();
    }

}
