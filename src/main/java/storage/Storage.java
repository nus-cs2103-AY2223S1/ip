package storage;

import common.DukeException;
import task.Task;
import task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File dataFile;

    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }

    private void createDataFile() throws DukeException {
        if (!dataFile.exists()) {
            try {
                dataFile.getParentFile()
                        .mkdirs();
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("I/O error occurred");
            }
        }
    }

    public void saveTask(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataFile, true); // create a FileWriter in append mode
            fw.write(task.encode() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS!!! I'm sorry, but I can't write to the file.");
        }
    }

    public void saveAllTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataFile);
            fw.write(taskList.encode());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\tOOPS!!! I'm sorry, but I can't write to the file.");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        createDataFile();
        Scanner scanner;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file not found");
        }
        while (scanner.hasNext()) {
            String l = scanner.nextLine();
            tasks.add(Task.decode(l));
        }
        scanner.close();

        return tasks;
    }
}
