package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {

    private static final String DIR = System.getProperty("user.dir");
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                String cmd;
                LocalDate d;
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
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        } else {
            File parent = new File(DIR + "/data");
            boolean isFolderCreated = parent.mkdir();
            boolean isFileCreated = target.createNewFile();
        }
        return tasks;
    }

    public void store(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.get(i);
            String status = curr.getStatusIcon().equals("X") ? "1" : "0";
            if (curr instanceof Todo) {
                writer.write("T | " + status + " | " + curr.getDescription() + "\n");
            } else if (curr instanceof Deadline) {
                writer.write("D | " + status + " | " + curr.getDescription() + " | "
                        + ((Deadline) curr).getBy().toString() + "\n");
            } else if (curr instanceof Event) {
                writer.write("E | " + status + " | " + curr.getDescription() + " | "
                        + ((Event) curr).getAt().toString() + "\n");
            }
        }
        writer.close();
    }

}
