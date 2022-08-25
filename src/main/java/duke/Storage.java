package duke;

import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Contains the processes that store data and manipulates the data file of the application
 */
public class Storage {
    /** list that stores the tasks */
    private ArrayList<Task> list = new ArrayList<>();

    /** Keeps track of the number of tasks */
    private int count = 0;

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
        } catch(IOException e){
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
                String[] parts = line.split("##");
                switch(parts[0]) {
                    case("[E]"):
                        Event event = new Event(parts[2], parts[3]);
                        if(parts[1] == "[X]") {
                            event.setDone();
                        }
                        list.add(event);
                        break;
                    case("[D]"):
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
                        LocalDate date = LocalDate.parse(parts[3], formatter);
                        Deadline deadline = new Deadline(parts[2], date);
                        if(parts[1] == "[X]") {
                            deadline.setDone();
                        }
                        list.add(deadline);
                        break;
                    case("[T]"):
                        Todo todo = new Todo(parts[2]);
                        if(parts[1] == "[X]") {
                            todo.setDone();
                        }
                        list.add(todo);
                        break;
                }


            }
        } catch(IOException e) {
            System.out.println("File not found");
        }
    }
    /**
     * writes the data into the data file from the list
     */
    public void writeToFile(){
        try {
            new FileWriter("data/duke.txt", false).close();
            for (int i = 0; i < list.size(); i++) {
                FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
                fw.write(list.get(i).getFileLine());
                fw.write(System.lineSeparator());
                fw.close();
            }
        } catch(IOException e) {
            System.out.println("File not found");
        }
    }

    public ArrayList<Task> getList() {
        return this.list;
    }



}
