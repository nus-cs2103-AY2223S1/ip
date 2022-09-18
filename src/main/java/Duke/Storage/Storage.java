package Duke.Storage;
import Duke.Exception.DukeException;
import Duke.Tasks.Deadline;
import Duke.Tasks.TaskList;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;
import Duke.Tasks.Task;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores files and read files
 */
public class Storage {
    private File file;
    private final String filePath;

    /**
     * Constructor for Storage
     *
     */
    public Storage() {
        this.filePath = "/Users/yiye/Desktop/cs2103Projects/ip/Data/duke.txt";;
    }

    /**
     * Reads the file and store the tasks in file to the list
     *
     * @return ArrayList of type Task
     * @throws DukeException when argument is invalid
     */
    public ArrayList<Task> loadTasks() throws DukeException{
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
                    Task dl = new Deadline(parts[2], parts[3], parts[4]);
                    list.add(dl);
                } else if (txt.startsWith("E")) {
                    String[] dateTime = parts[4].split(" ");
                    Task event = new Event(parts[2], parts[3], parts[0], parts[1]);
                    list.add(event);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Writes current task to file
     *
     * @param tl list of tasks that is to be stored in file
     */
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