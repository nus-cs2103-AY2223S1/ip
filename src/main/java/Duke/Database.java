package Duke;

import java.io.*;
import java.util.Scanner;

public class Database {

    public String directory;
    public TaskList taskList;

    public Database(String directory, TaskList taskList) {
        assert directory != "": "directory should not empty";
        this.directory = directory;
        this.taskList = taskList;
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(directory));
            String tasks = "";
            for (int i = 0; i < taskList.size(); i++) {
                tasks += taskList.get(i).toString() + "\n";
            }
            writer.write(tasks);
            writer.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void load() throws IOException {
        createFile();
        File file = new File(directory);
        Scanner sc = new Scanner(file);
        Task curr = null;

        while (sc.hasNext()) {
            String data = sc.nextLine();
            String type = data.substring(1,2);
            String status = data.substring(4,5);

            String substring = data.substring(data.indexOf("(") + 5, data.length() - 1);
            switch (type) {
                case "T":
                    String todoDescription = data.substring(7);
                    curr = new ToDo(todoDescription);
                    break;
                case "D":
                    String deadlineDescription = data.substring(7, data.indexOf("("));
                    String by = substring;
                    curr = new Deadline(deadlineDescription, by);
                    break;
                case "E":
                    String eventDescription = data.substring(7, data.indexOf("("));
                    String at = substring;
                    curr = new Deadline(eventDescription, at);
                    break;
                default:
                    System.out.println("The file may be corrupted");
                    break;
            }
            if (status.equals("X")) {
                curr.markAsDone();
            }
            taskList.add(curr);
        }
        sc.close();
    }

    public void createFile() {
        try {
            File file = new File(directory);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

