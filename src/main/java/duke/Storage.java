package duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Storage {

    private String path;
    private TaskList taskList;

    /**
     * Constructor for Storage.
     *
     * @param path     Relative path of the file to store data in.
     * @param taskList
     */
    public Storage(String path, TaskList taskList) {
        this.path = path;
        this.taskList = taskList;
    }

    /**
     * Saves data in current taskList to local storage.
     */
    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            String tasks = "";
            for (int i = 0; i < taskList.size(); i++) {
                tasks += taskList.get(i).toString() + "\n";
            }
            writer.write(tasks);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads data from local storage into Duke.
     * Only run on initial startup of Duke.
     *
     * @throws IOException Thrown when there is an IOException.
     */
    public void load() throws IOException {
        File file = new File(path);
        Task current = null;
        try {
            if (!file.exists()) {
                if (file.getParentFile().mkdirs()) {
                    System.out.println("Directory is created");
                } else {
                    System.out.println("Directory cannot be created");
                }
                if (file.createNewFile()) {
                    System.out.println("File created");
                }
            } else {
                System.out.println("File exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                String type = data.substring(1, 2);
                String status = data.substring(4, 5);

                switch (type) {
                case "T":
                    String todoDescription = data.substring(7);
                    current = new ToDo(todoDescription);
                    break;
                case "D":
                    String deadlineDescription = data.substring(7, data.indexOf("(") - 1);
                    String by = data.substring(data.indexOf("(") + 5, data.length() - 1);
                    current = new Deadline(deadlineDescription, by);
                    break;
                case "E":
                    String eventDescription = data.substring(7, data.indexOf("(") - 1);
                    String at = data.substring(data.indexOf("(") + 5, data.length() - 1);
                    current = new Deadline(eventDescription, at);
                    break;
                default:
                    System.out.println("The file may be corrupted");
                    break;
                }
                if (status.equals("X")) {
                    current.markAsDone();
                }
                taskList.add(current);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createFile() {
        try {
            File file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
