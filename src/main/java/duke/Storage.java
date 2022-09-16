package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * Contains the processes that store data and manipulates the data file of the application
 */
public class Storage {
    /** list that stores the tasks */
    private ArrayList<Task> list = new ArrayList<>();

    /** Keeps track of the number of tasks */
    private int count = 0;

    /**
     * Creates storage object
     */
    public Storage() {
        try {
            if (!new File("data").exists()) {
                new File("data").mkdirs();
                File tmp = new File("data", "duke.txt");
                tmp.createNewFile();
            } else if (!new File("data/duke.txt").exists()) {
                File tmp = new File("data", "duke.txt");
                tmp.createNewFile();
            } else {
                this.loadData();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    };

    /**
     * loads the data into the list from the data file
     */
    public void loadData() {
        try {
            File f = new File("data/duke.txt"); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            while (s.hasNext()) {
                String line = s.nextLine();
                addToList(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     * adds tasks from the line to the list according to the type of task
     * @param line the line from the storage file
     */
    private void addToList(String line) {
        String[] parts = line.split("##");
        assert parts.length != 0 : "line incompatible i storage file";
        switch(parts[0]) {
        case("[T]"):
            addTodoToList(parts);
            break;
        case("[D]"):
            addDeadlineToList(parts);
            break;
        case("[E]"):
            addEventToList(parts);
            break;
        default:
            break;
        }
    }

    /**
     * adds deadline task to the list
     * @param parts line separated into  to the components of the deadline
     */
    private void addDeadlineToList(String[] parts) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(parts[3], formatter);
        Deadline deadline = new Deadline(parts[2], date);
        if (parts[1] == "[X]") {
            deadline.setDone();
        }
        list.add(deadline);
        this.count++;
    }

    /**
     * adds To do task to the list
     * @param parts line separated into  to the components of the to do task
     */
    private void addTodoToList(String[] parts) {
        Todo todo = new Todo(parts[2]);
        if (parts[1] == "[X]") {
            todo.setDone();
        }
        list.add(todo);
        this.count++;
    }

    /**
     * adds event task to the list
     * @param parts line separated into  to the components of the event
     */
    private void addEventToList(String[] parts) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(parts[3], formatter);
        Event event = new Event(parts[2], date);
        if (parts[1] == "[X]") {
            event.setDone();
        }
        list.add(event);
        this.count++;
    }


    /**
     * writes the data into the data file from the list
     */
    public void writeToFile() {
        try {
            new FileWriter("data/duke.txt", false).close();
            for (int i = 0; i < list.size(); i++) {
                FileWriter fw = new FileWriter("data/duke.txt", true);
                fw.write(list.get(i).getFileLine());
                fw.write(System.lineSeparator());
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int getCount() {
        return this.count;
    }
}
