/**
 * Project done by Hong Jin.
 */
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * TaskList class to store the list of Tasks.
 *
 * Also allows user to mark and unmark the task in list.
 */
public class TaskList {
    private List<Task> memo;

    public TaskList() {
        this.memo = new ArrayList<>();
        loadfromFile();
    }

    public String addTask(Task t) {
        memo.add(t);
        String note = "Now you have " + memo.size() + " tasks in the list.";
        return "Got it, I've added this task:\n      " + t.getTask()
                + "\n    " + note;
    }

    public String enumerate() {
        int count = 0;
        String str = "Here are the tasks in your list:";
        for (Task k : memo) {
            count ++;
            str += "\n    " + count + ". " + k.getTask();
        }
        return str;
    }

    public String updateMark(int index) {
        this.memo.get(index - 1).mark();
        return "Nice! I've marked this task as done :)\n      "
                + this.memo.get(index - 1).getTask();
    }

    public String updateUnmark(int index) {
        this.memo.get(index - 1).unmark();
        return "okay I mark this task as not done yet...\n      "
                + this.memo.get(index - 1).getTask();
    }

    public String deleteTask(int index) {
        String temp = this.memo.get(index - 1).getTask();
        this.memo.remove(index - 1);
        String noteUpdated = "Now you have " + memo.size() + " tasks in the list.";
        return "Noted. I've deleted this task:\n      " + temp
                + "\n    " + noteUpdated;
    }

    public void loadfromFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("tasks.txt"));
            String row = reader.readLine();

            while (row != null) {
                //Example of line: [T] [X] read book
                String[] parse = row.split(" ", 3);
                Task task = null;

                task = new Task(parse[2], parse[0]);

                if (parse[1].equals("[X]")) {
                    task.mark();
                }
                memo.add(task);
                row = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void savetoFile() {
        String path = "tasks.txt";
        FileWriter writer = null;

        try {
            File file = new File(path);
            writer = new FileWriter(file);

            for (int i = 0; i < memo.size(); i++) {
                Task t = memo.get(i);
                writer.write(String.format("%s\n", t.getTask()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
