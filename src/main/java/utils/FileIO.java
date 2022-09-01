package utils;

import org.json.JSONArray;
import task_classes.Task;

import java.io.*;
import java.util.ArrayList;

public class FileIO {

    private static String saveFilePath = "/data/saveFile.json";

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

    public void saveList(ArrayList<JSONParsable> list) {
        JSONArray saveData = new JSONArray();
        for (JSONParsable obj : list) {
            saveData.put(obj.toJSONObject());
        }
        try {
            FileWriter file = new FileWriter(saveFilePath);
            file.write(saveData.toString(4));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
