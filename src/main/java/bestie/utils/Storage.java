package bestie.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bestie.tasks.Deadline;
import bestie.tasks.Event;
import bestie.tasks.Task;
import bestie.tasks.Todo;

/**
 * A class which handles the interaction of the Bestie with the file storing the ToDo List items.
 */
public class Storage {
    private String filePath;
    private String dirPath;

    /**
     * Constructs a storage object.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
    }

    /**
     * Loads data from the specified file into an ArrayList, which can be used for the ToDo List object.
     * If the file is not found, an empty ArrayList is returned.
     *
     * @return An ArrayList containing the tasks in the specified file, or an empty ArrayList if there is no
     *     file or the file is empty.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(this.filePath);
        if (directory.exists() && !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(LanguageBank.IOEXCEPTION_ERROR_MESSAGE);
            }
        }

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] parsedInput = input.split(" \\| ");
                Task task = null;

                switch (parsedInput[0]) {
                case "T":
                    task = new Todo(parsedInput[2]);
                    break;
                case "D":
                    task = new Deadline(parsedInput[2], parsedInput[3]);
                    break;
                case "E":
                    task = new Event(parsedInput[2], parsedInput[3]);
                    break;
                default:
                    System.err.println(LanguageBank.FILE_LOADING_ERROR_MESSAGE);
                    break;
                }

                // checking if the task is done
                if (Integer.parseInt(parsedInput[1]) == 1) {
                    assert task != null : "Storage.java: task is null";
                    task.markDone();
                }

                list.add(task);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
            return list;
        }
        return list;
    }

    /**
     * Takes in an ArrayList of tasks and stores it into the specified file.
     *
     * @param list An ArrayList containing the Tasks in the ToDo list object.
     */
    public void store(ArrayList<Task> list) {
        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(this.filePath);
        if (directory.exists() && !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(LanguageBank.IOEXCEPTION_ERROR_MESSAGE);
            }
        }

        try {
            boolean isAppendMode = false;
            FileWriter fw = new FileWriter(this.filePath, isAppendMode);

            for (Task task : list) {
                assert task != null : "Storage.java: task is null";
                fw.append(task.toStorageFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println(LanguageBank.FILE_LOADING_ERROR_MESSAGE);
        }
    }
}
