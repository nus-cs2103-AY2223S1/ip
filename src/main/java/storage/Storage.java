package storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import filedata.FileData;
import task.Task;
import task.TaskList;

public class Storage {
    protected String filePath;
    protected FileData fileData;
    protected TaskList tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileData = new FileData(filePath);
        this.tasks = new TaskList(this.fileData.storeArray());
    }

    public ArrayList<Task> load() throws DukeException {
        this.fileData.toPrint();
        return tasks.toArray();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void storeData(String textToStore) {
        String file = this.filePath;
        try {
            appendToFile(file, textToStore + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void updateData(TaskList taskList) {
        fileData.updateData(taskList.toArray());
    }

    public void save() throws DukeException {
        if (!this.fileData.exists()) {
            throw new DukeException("The file does not exist!");
        } else {
            assert this.f.exists();
            for (int i = 0; i < tasks.size(); i++) {
                try {
                    if (i == 0) {
                        writeToFile(this.filePath, tasks.get(i).toString() + System.lineSeparator());
                    } else {
                        storeData(tasks.get(i).toString());
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }
    }

}
