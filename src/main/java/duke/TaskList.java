package duke;


import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int count;
    public TaskList(File f){
        list = new ArrayList<>();

        if (f.exists()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    String[] task = line.split(",");
                    if (task[0].equals("T")) {
                        list.add(new Todo(task[1].equals("X"), task[2]));
                    }
                    if (task[0].equals("E")) {
                        list.add(new Event(task[1].equals("X"), task[2], task[3]));
                    }
                    if (task[0].equals("D")) {
                        list.add(new Deadline(task[1].equals("X"), task[2], LocalDate.parse(task[3])));
                    }
                    line = br.readLine();
                }

                this.count = list.size();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void list() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    public void mark(int index) {
        list.get(index).mark();
        System.out.println("I have marked this task as done:");
        System.out.println(list.get(index));
    }

    public void add(Event e) {
        try {
            list.add(e);
            count = list.size();
            System.out.println("Added Task");
            System.out.println(list.get(count - 1));
            System.out.println("Now you have " + count + " tasks in the list");
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println("Invalid Time");
        }
    }

    public void add(Todo t) {
        list.add(t);
        count = list.size();
        System.out.println("Added Task");
        System.out.println(list.get(count - 1));
        System.out.println("Now you have " + count + " tasks in the list");
    }

    public void add(Deadline d) {
        try {
            list.add(d);
            count = list.size();
            System.out.println("Added Task");
            System.out.println(list.get(count - 1));
            System.out.println("Now you have " + count + " tasks in the list");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Time");
        }
    }

    public void delete(int index) {
        Task item = list.get(index);
        list.remove(index);
        count = list.size();
        System.out.println("Noted. I have removed this task: \n" + item);
        System.out.println("You now have " + count + " tasks left in the list");
    }

    public void unMark(int index) {
        list.get(index).unmark();
        System.out.println("I have marked this task as not done:");
        System.out.println(list.get(index));
    }
}
