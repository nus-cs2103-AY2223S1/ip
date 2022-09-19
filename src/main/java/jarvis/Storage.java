package jarvis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.task.ToDo;

/**
 * Storage stores the File Path and handles loading and writing to the file.
 */
public class Storage {
    private String filePath;

    /**
     * Return new Storage Object with the specified filePath of data.
     * @param filePath Specified file path of data stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from data file into a task list.
     * @return List of tasks.
     * @throws IOException if error occurs when reading file.
     */
    public List<Task> load() throws IOException {
        File myFile = new File(filePath);
        List<Task> taskList = new ArrayList<>();
        if (!myFile.createNewFile()) {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String next = sc.nextLine();
                int divisor = next.lastIndexOf("|");

                if (next.charAt(0) == 'D') {
                    readDeadline(next, divisor, taskList);
                }
                if (next.charAt(0) == 'E') {
                    readEvent(next, divisor, taskList);
                }
                if (next.charAt(0) == 'T') {
                    readToDo(next, taskList);
                }
            }
        }
        return taskList;
    }

    /**
     * Stores task list in data file in given file path.
     * @param tasks Task List to be stored.
     * @throws IOException if error occurs when writing to data file.
     */
    public void write(TaskList tasks) throws IOException {
        File myFile = new File(filePath);
        myFile.createNewFile();
        FileWriter myWriter = new FileWriter(myFile);
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task curr = tasks.getList().get(i);
            if (curr instanceof Deadline) {
                writeDeadline(curr, myWriter);
            } else if (curr instanceof Event) {
                writeEvent(curr, myWriter);
            } else {
                writeToDo(curr, myWriter);
            }
        }
        myWriter.close();
    }

    private static void writeDeadline(Task curr, FileWriter myWriter) throws IOException {
        if (curr.getDone()) {
            myWriter.write("D" + " | 1 | " + curr.getDescription() + " | " + ((Deadline) curr).getBy() + "\n");
        } else {
            myWriter.write("D" + " | 0 | " + curr.getDescription() + " | " + ((Deadline) curr).getBy() + "\n");
        }
    }

    private static void writeEvent(Task curr, FileWriter myWriter) throws IOException {
        if (curr.getDone()) {
            myWriter.write("E" + " | 1 | " + curr.getDescription() + " | " + ((Event) curr).getAt() + "\n");
        } else {
            myWriter.write("E" + " | 0 | " + curr.getDescription() + " | " + ((Event) curr).getAt() + "\n");
        }
    }

    private static void writeToDo(Task curr, FileWriter myWriter) throws IOException {
        if (curr.getDone()) {
            myWriter.write("T" + " | 1 | " + curr.getDescription() + "\n");
        } else {
            myWriter.write("T" + " | 0 | " + curr.getDescription() + "\n");
        }
    }

    private static void readDeadline(String next, int divisor, List<Task> taskList) {
        String description = next.substring(8, divisor - 1);
        String date = next.substring((divisor + 2));
        taskList.add(new Deadline(description, date));

        if (next.charAt(4) == '1') {
            taskList.get(taskList.size() - 1).mark();
        }
    }

    private static void readEvent(String next, int divisor, List<Task> taskList) {
        String description = next.substring(8, divisor - 1);
        String date = next.substring((divisor + 2));
        taskList.add(new Event(description, date));

        if (next.charAt(4) == '1') {
            taskList.get(taskList.size() - 1).mark();
        }
    }

    private static void readToDo(String next, List<Task> taskList) {
        String description = next.substring(8);
        taskList.add(new ToDo(description));

        if (next.charAt(4) == '1') {
            taskList.get(taskList.size() - 1).mark();
        }
    }
}
