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

    private String path;

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
            String tasks = "";
            for (Task task : TaskList.taskList) {
                tasks += task.storeToString() + "\n";
            }
            writer.write(tasks);
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
                String deadlinedescription = event.substring(4, indexOfDeadline - 1);
                cur = new Deadline(deadlinedescription, deadline);
                break;
            case "E":
                int indexofdate = event.lastIndexOf("|") + 1;
                String date = event.substring(indexofdate);
                String eventdescription = event.substring(4, indexofdate - 1);
                cur = new Event(eventdescription, date);
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

