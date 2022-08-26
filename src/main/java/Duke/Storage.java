package Duke;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String txt = sc.nextLine();
                String[] parts = txt.split("\\|");
                if (parts.length == 2) {
                    Task todo = new Todo(parts[1]);
                    list.add(todo);
                } else if (txt.startsWith("D")) {
                    Task dl = new Deadline(parts[2], parts[4], parts[5]);
                    list.add(dl);
                } else if (txt.startsWith("E")) {
                    Task event = new Event(parts[2], parts[3], parts[5], parts[6]);
                    list.add(event);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeTasks(ArrayList<Task> list) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Task task: list) {
                String str = task.fileFormat();
                printWriter.print(str + System.lineSeparator());
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Failed to save your file!.");
        }
    }

}