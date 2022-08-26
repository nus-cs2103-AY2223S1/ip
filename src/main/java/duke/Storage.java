package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FOLDER_PATH = "./data/";
    private static final String FILE_NAME = "PloopyDatabase.txt";
    private static final String FILE_PATH = FOLDER_PATH + FILE_NAME;
    private static File folder, file;
    private UI ui;

    public Storage(UI ui) throws PloopyException {
        folder = new File(FOLDER_PATH);
        file = new File(FILE_PATH);
        this.ui = ui;
        //this.taskList = taskList;
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (SecurityException e) {
                throw new PloopyException("IO");
            }
        }

        try {
            if (!file.createNewFile()) {
                ui.createFilesMessage();
            }
        } catch (IOException e) {
            throw new PloopyException("IO");
        }
    }

    public String formatLineToWrite(Task task) {
        final String sep = "_";
        String type = task.getType();
        String done = task.isDone() ? "1" : "0";
        String name = task.getName();
        String date = task.getDateForFileWrite();
        return String.format("%s%s%s%s%s%s%s", type, sep, done, sep, name, sep, date);

    }

    public void loadFile(TaskList taskList) throws PloopyException {
        try {
            Scanner fileReader = new Scanner(file);
            ui.addingFilesMessage();
            while (fileReader.hasNext()) {
                taskList.addTasksFromFile(fileReader.nextLine());
            }
        } catch (IOException e) {
            throw new PloopyException("IO");
        }
    }

    public void rewriteFile(ArrayList<Task> taskList) throws PloopyException {
        try {
            FileWriter fileDelete = new FileWriter(FILE_PATH, false);
            fileDelete.write("");
            fileDelete.close();
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            for (Task task : taskList) {
                fileWriter.write(formatLineToWrite(task) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new PloopyException("IO");
        }
    }

    public void writeToFile(Task task) throws PloopyException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            fileWriter.write(formatLineToWrite(task) + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new PloopyException("IO");
        }
    }
}
