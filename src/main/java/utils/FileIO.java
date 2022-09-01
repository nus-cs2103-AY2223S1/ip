package utils;

import org.json.JSONArray;
import task_classes.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    private static String saveFilePath = "./data/saveFile.json";
    private static String saveFileDir = "./data";

    private static FileIO instance;

    // This is a singleton class
    private FileIO(){

    }

    public static FileIO getInstance() {
        if (instance == null) {
            instance = new FileIO();
        }
        return instance;
    }

//    public Task[] readTaskList() {
//        try {
//            FileReader saveFileReader = new FileReader(saveFilePath);
//        } catch (FileNotFoundException e) {
//            return null;
//        }
//        return
//    }

    public void saveList(List<? extends JSONParsable> list) {
        JSONArray saveData = new JSONArray();
        for (JSONParsable obj : list) {
            saveData.put(obj.toJSONObject());
        }
        try {
//            Create dire if it does not exist
            Files.createDirectories(Paths.get(saveFileDir));
            FileWriter file = new FileWriter(saveFilePath);
            file.write(saveData.toString(4));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
