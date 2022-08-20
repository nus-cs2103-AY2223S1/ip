/**
 * Project done by Hong Jin.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/uuuu");

    public TaskList() {
        this.memo = new ArrayList<>();
        loadfromFile();
    }

    public String addTask(Task t) {
        memo.add(t);
        String note = "Now you have " + memo.size() + " tasks in the list.";
        return "Got it, I've added this task:\n      " + t.toString()
                + "\n    " + note;
    }

    public String enumerate() {
        int count = 0;
        String str = "Here are the tasks in your list:";
        for (Task k : memo) {
            count ++;
            str += "\n    " + count + ". " + k.toString();
        }
        return str;
    }

    public String updateMark(int index) {
        this.memo.get(index - 1).mark();
        return "Nice! I've marked this task as done :)\n      "
                + this.memo.get(index - 1).toString();
    }

    public String updateUnmark(int index) {
        this.memo.get(index - 1).unmark();
        return "okay I mark this task as not done yet...\n      "
                + this.memo.get(index - 1).toString();
    }

    public String deleteTask(int index) {
        String temp = this.memo.get(index - 1).toString();
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
                //[D] [ ] Assignment 1 /by 11 09 2022
                String[] parse = row.split(" ", 3);
                Task task = null;

                if (parse[0].equals("[T]")) {
                    task = new Todo(parse[2]);
                } else if (parse[0].equals("[E]")) {
                    String[] parse2 = parse[2].split("/at", 2);
                    LocalDate localDate1 = LocalDate.parse(parse2[1], formatter);
                    task = new Event(parse[2], localDate1);
                } else {
                    String[] parse3 = parse[2].split("/by", 2);
                    LocalDate localDate2 = LocalDate.parse(parse3[1], formatter);
                    task = new Deadline(parse3[2], localDate2);
                }

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
                writer.write(String.format("%s\n", t.toString()));
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
