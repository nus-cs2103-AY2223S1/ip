package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.common.DukeException;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File saveFile;

    public Storage(String filePath) {
        this.saveFile = new File(filePath);
    }

    private void createSaveFile(File saveFile) throws DukeException {
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            } catch (java.io.IOException exception) {
                throw new DukeException("I/O error occured.");
            } catch (SecurityException exception) {
                throw new DukeException("No write access");
            }
        }
    }

    private Scanner getInputScanner(File saveFile) throws DukeException {
        createSaveFile(saveFile);
        try {
            Scanner scanner = new Scanner(saveFile);
            return scanner;
        } catch (java.io.FileNotFoundException exception) {
            throw new DukeException("Save file not found, even after duke.Duke tries to create one :(");
        }
    }

    private PrintWriter getOutputWriter(File saveFile) throws DukeException {
        createSaveFile(saveFile);
        try {
            PrintWriter writer = new PrintWriter(saveFile);
            return writer;
        } catch (java.io.FileNotFoundException exception) {
            throw new DukeException("Save file not found, even after duke.Duke tries to create one :(");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner scanner = getInputScanner(this.saveFile);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            try {
                tasks.add(Task.decode(input));
            } catch (DukeException exception) {
                // ignore invalid line in save file
            }
        }
        return tasks;
    }

    public void save(TaskList taskList) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        PrintWriter writer = getOutputWriter(this.saveFile);
        for (int i = 0; i < tasks.size(); i += 1) {
            Task task = tasks.get(i);
            writer.println(task.encode());
        }
        writer.close();
    }
}
