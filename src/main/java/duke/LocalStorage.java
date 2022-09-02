package duke;

import duke.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class LocalStorage {

    private String saveFilePath;
    private String saveFileDir;

    public LocalStorage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
        this.saveFileDir = saveFilePath.substring(0, saveFilePath.lastIndexOf("/"));
    }

    /**
     * Load the task array from the given save data file.
     * @return a Task array.
     */
    public Task[] load() {
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

    /**
     * Write the current task data into the save file.
     * @param list a TaskList object to be saved.
     */
    public void save(TaskList list) {
        JSONArray saveData = new JSONArray();
        for (Iterator<Task> it = list.getIterator(); it.hasNext(); ) {
            Task obj = it.next();
            saveData.put(obj.toJsonObject());
        }

        try {
//            Create dir if it does not exist
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
