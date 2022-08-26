package Duke;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws DukeException{
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.getParentFile().mkdir();
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

        public void writeTasks(TaskList tl) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < tl.size(); i++) {
                Task task = tl.get(i);
                String str = task.fileFormat();
                printWriter.println(str);
            }
           printWriter.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Failed to save your file!.");
        }
    }
}