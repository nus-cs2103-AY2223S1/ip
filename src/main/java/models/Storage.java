package models;

import exceptions.DukeException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String taskDirectoryName = "../data/";
    private static final String taskFileName = "tasklist.txt";
    private static final String taskFilePath = taskDirectoryName + "/" + taskFileName;

    private static Task parseTextToTask(String text) {
        return new Task("");
    }

    public static List<Task> loadTasksFromDisk() throws DukeException {
        try {
            File dir = new File(taskDirectoryName);
            if (!dir.exists()) dir.mkdirs();
            File myFile = new File(taskFilePath);
            myFile.createNewFile();
            Scanner fileReader = new Scanner(myFile);

            List<Task> taskList = new ArrayList<>();
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                taskList.add(parseTextToTask(data));
            }
            fileReader.close();
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Cannot create file to save tasks!");
        }
    }
}
