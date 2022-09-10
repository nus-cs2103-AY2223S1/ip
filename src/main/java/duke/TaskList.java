package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents the list of tasks that the user has keyed in.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int count;

    /**
     * Constructor Method for the TaskList, if a file is given to it
     *
     * @param f Save File
     */
    public TaskList(File f) {
        tasks = new ArrayList<>();

        if (f.exists()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    String[] task = line.split(",");
                    if (task[0].equals("T")) {
                        tasks.add(new Todo(task[1].equals("X"), task[2]));
                    }
                    if (task[0].equals("E")) {
                        tasks.add(new Event(task[1].equals("X"), task[2], task[3]));
                    }
                    if (task[0].equals("D")) {
                        tasks.add(new Deadline(task[1].equals("X"), task[2], LocalDate.parse(task[3])));
                    }
                    line = br.readLine();
                }

                this.count = tasks.size();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Constructor Method for the TaskList if no save file is given
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Prints out the contents of the list
     */
    public String list() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += (i + 1 + ". " + tasks.get(i) + "\n");
        }
        return s;
    }

    /**
     * Marks the content at index as done on the list
     *
     * @param index
     */
    public String mark(int index) {
        String s = "";
        assert index >= 0;
        assert index < tasks.size();
        tasks.get(index).mark();
        s += "I have marked this task as done: \n";
        s += tasks.get(index) + "\n";
        return s;
    }

    /**
     * Adds a new Event to the list
     *
     * @param e
     */
    public String add(Event e) {
        String s = "";
        try {
            tasks.add(e);
            count = tasks.size();
            s += ("Added Task \n");
            s += tasks.get(count - 1);
            s += "Now you have " + count + " tasks in the list";
            return s;
        } catch (ArrayIndexOutOfBoundsException err) {
            return "Invalid Time";
        }
    }

    /**
     * Adds a new Todo to the list
     *
     * @param t
     */
    public String add(Todo t) {
        tasks.add(t);
        count = tasks.size();
        String s = "";
        s += "Added Task \n";
        s += tasks.get(count - 1) + "\n";
        s += "Now you have " + count + " tasks in the list\n";
        return s;
    }

    /**
     * Adds a new Deadline to the list
     *
     * @param d
     */
    public String add(Deadline d) {
        try {
            tasks.add(d);
            count = tasks.size();
            String s = "";
            s += "Added Task\n";
            s += tasks.get(count - 1) + "\n";
            s += "Now you have " + count + " tasks in the list" + "\n";
            return s;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Invalid Time";
        }
    }

    /**
     * Removes the task at index from the list
     *
     * @param index
     */
    public String delete(int index) {
        assert index >= 0;
        assert index < tasks.size();

        Task item = tasks.get(index);
        tasks.remove(index);
        count = tasks.size();
        String s = ("Noted. I have removed this task: \n" + item);
        return s;
    }

    /**
     * Marks the task at index as not done
     *
     * @param index
     */
    public String unMark(int index) {
        assert index >= 0;
        assert index < tasks.size();
        tasks.get(index).unmark();
        String s = "";
        s += "I have marked this task as not done:\n";
        s += tasks.get(index);
        return s;
    }

    /**
     * Finds any task that contains the string s in its description and prints it out.
     *
     * @param s
     */
    public String find(String s) {
        String result = "";
        for (Task task : tasks) {
            if (task.contains(s)) {
                result += task + "\n";
            }
        }
        return result;
    }

    /**
     * Finds any task that is due one week from now and returns them as a list
     * @return
     */
    public String dueSoon() {
        String result = "";
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDate soon = LocalDate.now().plusWeeks(1);
                LocalDate dueDate = deadline.getDeadline();
                if (dueDate.isBefore(soon)) {
                    result += deadline + "\n";
                }
            }
        }
        return result;
    }
}
