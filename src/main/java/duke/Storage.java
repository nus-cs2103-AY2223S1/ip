package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Consists of the logic to interact with the disk storage.
 */
public class Storage {

    private final String path;

    /**
     * Creates a storage object linked to a file specified by path.
     * A new storage file is created if the file doesn't exist.
     * @param path The path for the storage file
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Saves whatever data user writes into storage
     */
    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            StringBuilder tasks = new StringBuilder();
            for (Task task : TaskList.taskList) {
                tasks.append(task.storeToString()).append("\n");
            }
            writer.write(tasks.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads whatever data user has written
     */
    public void load() throws IOException {

        File file = new File(this.path);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String event = sc.nextLine();
            String type = event.substring(0, 1);
            String status = event.substring(2, 3);
            Task cur = null;

            switch (type) {
            case "T":
                cur = new ToDo(event.substring(4));
                break;
            case "D":
                int indexOfDeadline = event.lastIndexOf("|") + 1;
                String deadline = event.substring(indexOfDeadline);
                String deadlineDescription = event.substring(4, indexOfDeadline - 1);
                cur = new Deadline(deadlineDescription, deadline);
                break;
            case "E":
                int indexOfDate = event.lastIndexOf("|") + 1;
                String date = event.substring(indexOfDate);
                String eventDescription = event.substring(4, indexOfDate - 1);
                cur = new Event(eventDescription, date);
                break;
            default:
                System.out.println("error");
                break;
            }
            if (status.equals("1")) {
                cur.markAsDone();
            }
            System.out.println(cur.toString());
            TaskList.taskList.add(cur);
        }
        sc.close();
    }
}

