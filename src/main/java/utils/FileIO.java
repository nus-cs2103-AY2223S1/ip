package utils;

import org.json.JSONArray;
import org.json.JSONObject;
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

    public Task[] readTaskList() {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(Files.readString(Paths.get(saveFilePath)));
        } catch (FileNotFoundException e) {
            jsonArray = new JSONArray();
        } catch (IOException e) {
            jsonArray = new JSONArray();
        }

        Task[] objArray = new Task[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i ++) {
            objArray[i] = Task.fromJSONObject((JSONObject) jsonArray.get(i));
        }

        return objArray;
    }

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
