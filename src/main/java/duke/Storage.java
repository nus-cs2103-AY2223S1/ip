package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with loading tasks from the file and storing tasks in the file.
 */
public class Storage {
    private String path;

    /**
     * Creates new Storage object.
     *
     * @param path Path to folder storing data.
     */
    public Storage(String path) {
        this.path = path;
    }
    /**
     * Returns list containing all tasks stored in the file.
     *
     * @return List of tasks.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            File dir = new File("./data");
            dir.mkdir();

            File file = new File(this.path);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(this.path);
            BufferedReader reader = new BufferedReader(fileReader);

            String next = reader.readLine();

            while (next != null) {
                String[] section = next.split(" \\| ");
                boolean isDone = section[1].equals("1");
                String type = section[0];

                if (type.equals("T")) {
                    try {
                        Todo todo = new Todo(section[2], isDone);
                        if (section.length > 3) {
                            todo.setTag(section[3]);
                        }
                        tasks.add(todo);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equals("D")) {
                    Deadline deadline = new Deadline(section[2], isDone, section[3]);
                    if (section.length > 4) {
                        deadline.setTag(section[4]);
                    }
                    tasks.add(deadline);
                } else {
                    Event event = new Event(section[2], isDone, section[3]);
                    if (section.length > 4) {
                        event.setTag(section[4]);
                    }
                    tasks.add(event);
                }
                next = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes tasks from list into file.
     *
     * @param list List of tasks to be stored in the file.
     */
    public void updateTasks(List<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(this.path, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            list.forEach((task) -> {
                try {
                    writer.write(task.getStorageString());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
