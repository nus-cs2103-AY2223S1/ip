package bro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bro.task.Deadline;
import bro.task.Event;
import bro.task.Task;
import bro.task.Todo;

/**
 * Storage Class.
 */
public class Storage {
    private ArrayList<Task> list = new ArrayList<>();
    private String path;

    /**
     * Constructor of the Storage class.
     * @param path String with the file location.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Creates an ArrayList of Task by loading the data from the file.
     * @return ArrayList of Task with previous tasks.
     * @throws BroException If the input for the event and deadline task is invalid.
     */
    public ArrayList<Task> load() throws BroException {
        File f = new File(this.path);
        boolean isCreated = false;
        try {
            isCreated = f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isCreated) {
            Scanner sc = null;
            try {
                sc = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (sc.hasNext()) {
                String input = sc.nextLine();
                this.addTaskToList(input);
            }
        }
        return list;
    }
    /**
     * Adds task to the list.
     * @param input task in String form.
     * @throws BroException If the input for the event and deadline task is invalid.
     */
    public void addTaskToList(String input) throws BroException {
        if (input.startsWith("[T]")) {
            Task t = new Todo(input.substring(6).trim());
            list.add(t);
            if (input.substring(4, 5).equals("X")) {
                t.markAsDone();
            }
        }
        else if (input.startsWith("[D]")) {
            String desc = input.substring(6, input.indexOf(" (by")).trim();
            String by = input.split("by:")[1].replace(')', ' ').trim();
            Task t = new Deadline(desc, by);
            list.add(t);
            if (input.substring(4, 5).equals("X")) {
                t.markAsDone();
            }
        }
        else if (input.startsWith("[E]")) {
            String desc = input.substring(6, input.indexOf(" (at")).trim();
            String at = input.split("at:")[1].replace(')', ' ').trim();
            Task t = new Event(desc, at);
            list.add(t);
            if (input.substring(4, 5).equals("X")) {
                t.markAsDone();
            }
        }
    }

    /**
     * Adds new task to the file
     * @param t Task to be added
     * @throws IOException If the file is not found.
     */
    public static void writeToFile(Task t) throws IOException {
        FileWriter w = new FileWriter("./bro.Bro.txt", true);
        w.write(t.toString() + "\n");
        w.close();
    }

    /**
     * Modifies the file with the given ArrayList of Task.
     * @param li List of the ArrayLisyt of task.
     * @throws IOException If the file is not found.
     */
    public static void modifyTaskFile(ArrayList<Task> li) throws IOException {
        FileWriter w = new FileWriter("./bro.Bro.txt", false);
        for (Task t : li) {
            w.write(t.toString() + "\n");
        }
        w.close();
    }
}

