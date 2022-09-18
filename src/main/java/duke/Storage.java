package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads data if any and saves user input in the same file.
 */
public class Storage {
    private static final String BREAK_LINE = "    ____________________________________________________________";
    private String path;

    /**
     * Storage Constructor
     * @param path file path where the .txt file is stored
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads data in a .txt file into an ArrayList of tasks
     * @return ArrayList of Task stored in a previously saved file if any or a new ArrayList.
     * @throws DukeException If the type format in the .txt file is invalid.
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File taskFile = new File(this.path);
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] inputArr = input.split(" \\| ");
                String type = inputArr[0];
                Task task;
                switch (type) {
                    case "T":
                        task = new Todo(inputArr[2]);
                        tasks.add(task);
                        if (inputArr[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    case "D":
                        task = new Deadline(inputArr[2], inputArr[3]);
                        tasks.add(task);
                        if (inputArr[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    case "E":
                        task = new Event(inputArr[2], inputArr[3]);
                        tasks.add(task);
                        if (inputArr[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    default:
                        throw new DukeException("It is an invalid type!");
                }
            }
            sc.close();
        } catch (FileNotFoundException err) {
            printError(err);
        } catch (DukeException err) {
            printError(err);
        }
        return tasks;
    }

    /**
     * Saves an ArrayList of Task in a .txt file
     * @param tasks TaskList object consists of a list of task inputted by user
     */
    public void saveData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.path);
            for(int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toSave());
            }
            fw.close();
        } catch (IOException err) {
            printError(err);
        }
    }

    private static void printError(Exception err) {
        System.out.println(BREAK_LINE + "\n" + "     ☹ OOPS!!! "
                + err + "\n" + BREAK_LINE + "\n");
    }
}