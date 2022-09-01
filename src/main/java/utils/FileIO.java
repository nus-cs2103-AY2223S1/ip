package utils;

import task_classes.Task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FileIO {

    private static String saveFilePath = "/data/saveFile.json";

    private static FileIO instance;

    // This is a singleton class
    private FileIO(){

    };

    public static FileIO getInstance() {
        if (instance == null) {
            instance = new FileIO();
        }
        return instance;
    }

    public Task[] readTaskList() {
        try {
            FileReader saveFileReader = new FileReader(saveFilePath);
        } catch (FileNotFoundException e) {
            return null;
        }

    }

    public void writeTaskList(ArrayList<Task> taskList) {

    }
}
