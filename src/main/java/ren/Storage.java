package ren;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ren.task.Deadline;
import ren.task.Event;
import ren.task.Task;
import ren.task.Todo;

/**
 * Storage handles the reading and writing of Tasks to a File.
 */
public class Storage {
    /** Path of the File. */
    private final String filePath;

    /** File that will store the list of Tasks. */
    private final File dataFile;

    /** Array that stores each line of the File as an element. */
    private final ArrayList<String> dataArray = new ArrayList<>();

    /** Counter for number of lines in the File. */
    private int counter = 0;

    /** Indicator for whether an error has occurred in the File. */
    private boolean hasError = false;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path of the File to read from and write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
        init();
    }

    private void init() {
        if (!dataFile.exists()) {
            try {
                if (!dataFile.getParentFile().exists()) {
                    dataFile.getParentFile().mkdir();
                }
                dataFile.createNewFile();
            } catch (IOException e) {
                this.hasError = true;
            }
        } else {
            try {
                Scanner dataSource = new Scanner(dataFile);
                while (dataSource.hasNext()) {
                    dataArray.add(dataSource.nextLine());
                    counter++;
                }
                dataSource.close();
            } catch (FileNotFoundException e) {
                this.hasError = true;
            }
        }
    }

    private void newFile() {
        try {
            dataFile.delete();
            dataFile.createNewFile();
        } catch (IOException e) {
            this.hasError = true;
        }
    }

    private void writeToFile() {
        // Disable writing to File if error occurred during creation of file or reading of file.
        if (hasError) {
            return;
        }
        try {
            FileWriter writer = new FileWriter(filePath);
            StringBuilder contents = new StringBuilder(dataArray.get(0));
            for (int i = 1; i < counter; i++) {
                contents.append(System.lineSeparator());
                contents.append(dataArray.get(i));
            }
            writer.write(contents.toString());
            writer.close();
        } catch (IOException e) {
            this.hasError = true;
        }
    }

    private void appendToFile() {
        // Disable writing to File if error occurred during creation of file or reading of file.
        if (hasError) {
            return;
        }
        try {
            FileWriter writer = new FileWriter(filePath, true);
            StringBuilder contents;
            if (counter == 1) {
                contents = new StringBuilder(dataArray.get(0));
            } else {
                contents = new StringBuilder(System.lineSeparator());
                contents.append(dataArray.get(counter - 1));
            }
            writer.write(contents.toString());
            writer.close();
        } catch (IOException e) {
            this.hasError = true;
        }
    }

    /**
     * Returns a list of Tasks read from File.
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < this.counter; i++) {
            String[] data = dataArray.get(i).split("\\|");
            Task newTask = null;
            switch (data[0]) {
            case "T":
                newTask = Todo.readData(data);
                break;
            case "D":
                newTask = Deadline.readData(data);
                break;
            case "E":
                newTask = Event.readData(data);
                break;
            default:
            }
            if (newTask != null) {
                tasks.add(newTask);
            }
        }
        return tasks;
    }

    /**
     * Writes a new Task to File.
     *
     * @param newTask Task to be written.
     */
    public void addTask(Task newTask) {
        dataArray.add(newTask.writeData());
        counter++;
        appendToFile();
    }

    /**
     * Modifies a Task in File.
     *
     * @param newTask Task that was modified.
     * @param index Index of the modified Task.
     */
    public void updateTask(Task newTask, int index) {
        dataArray.set(index, newTask.writeData());
        writeToFile();
    }

    /**
     * Removes a Task from File.
     *
     * @param index Index of the Task to be removed.
     */
    public void deleteTask(int index) {
        dataArray.remove(index);
        counter--;
        writeToFile();
    }

    /**
     * Removes all Tasks from File.
     */
    public void emptyList() {
        dataArray.clear();
        counter = 0;
        newFile();
    }
}
