package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class contains the methods required to load from
 * and save to the save file.
 */
public class Storage {
    private File log;
    private ArrayList<Task> list;
    private String filepath;

    /**
     * Constructor for Storage.
     * @param filepath Filepath of the save file.
     */
    public Storage(String filepath) {
        log = new File(filepath);
        list = new ArrayList<>();
        this.filepath = filepath;
    }

    /**
     * Creates a save file at filepath.
     */
    public void createLog() {
        log.mkdirs();
        System.out.println("Save file created");
    }

    /**
     * Loads from the save file at filepath.
     * @return Tasks saved in the save file.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner readFile = new Scanner(log);
        while (readFile.hasNext()) {
            String taskString = readFile.nextLine();
            String[] split = taskString.split(" \\| ");
            switch (split[0]) {
            case "T": // Checks for Todo
                list.add(new Todo(split[2]));
                break;
            case "D": // Checks for Deadline
                list.add(new Deadline(split[2], split[3]));
                break;
            case "E": // Checks for Event
                list.add(new Event(split[2], split[3]));
                break;
            default: // Default case
                System.out.println("Wrong input");
            }
            if (split[1] == "X") {
                list.get(list.size() - 1).markAsDone();
            }
        }
        return list;
    }

    /**
     * Writes to the save file based off a given TaskList list.
     * @param list The given TaskList used to write to the save file.
     */
    public void writeToFile(TaskList list) {
        String retString = "";
        FileWriter copyTasks = null;
        try {
            copyTasks = new FileWriter(filepath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Task value : list.getList()) {
            String logTask = "";
            if (value instanceof Todo) {
                Todo task = (Todo) value;
                logTask = String.format("T | %s | %s", task.getStatusIcon(), task.getDescription());
            } else if (value instanceof Deadline) {
                Deadline task = (Deadline) value;
                logTask = String.format("D | %s | %s | %s", task.getStatusIcon(), task.getDescription(), task.by);
            } else {
                Event task = (Event) value;
                logTask = String.format("E | %s | %s | %s", task.getStatusIcon(), task.getDescription(), task.when);
            }
            retString = retString + logTask + System.lineSeparator();
        }

        try {
            copyTasks.write(retString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            copyTasks.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
