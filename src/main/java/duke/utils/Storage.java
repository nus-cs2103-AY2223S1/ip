package duke.utils;

import duke.exceptions.UnrecognisedDateException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for persisting the list of tasks
 * to the disk.
 */
public class Storage {

    private File saveFile;

    public Storage(File givenSaveFile) {
        saveFile = givenSaveFile;
    }

    /**
     * Loads the list of tasks from the save file.
     * @return List of tasks.
     */
    public List<Task> loadFromFile() {
        checkFileExists();
        return parseSaveFile();
    }

    /**
     * Persists the list of tasks to the save file.
     * @param taskList List of tasks in use by the app.
     */
    public void saveToFile(List<Task> taskList) {
        checkFileExists();
        writeSaveFile(taskList);
    }

    private void checkFileExists() {
        boolean isSaveFileCreated = saveFile.exists();
        if (!isSaveFileCreated) {
            try {
                isSaveFileCreated = saveFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating save file: " + e);
            }
        }

        if (!isSaveFileCreated) {
            System.out.println("Unable to find or create save file, exiting...");
            System.exit(1);
        }
    }

    private List<Task> parseSaveFile() {
        assert saveFile.exists();
        List<Task> taskList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));
            taskList.clear();
            reader.lines().forEach((s) -> {
                Task t = stringToTask(s);
                if (t != null) {
                    taskList.add(stringToTask(s));
                }
            });
        } catch (IOException e) {
            System.out.println("Error while loading from file: " + e);
        }
        return taskList;
    }

    private void writeSaveFile(List<Task> taskList) {
        assert saveFile.exists();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
            for (Task task : taskList) {
                writer.write(task.toDataString());
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e);
        }
    }

    private Task stringToTask(String dataString) {
        String[] arr = splitDataString(dataString);
        return parseSplitString(arr);
    }

    private String[] splitDataString(String dataString) {
        String[] arr = dataString.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

    private Task parseSplitString(String[] arr) {
        Task ret;
        try {
            switch(arr[0]) {
            case "[T]":
                ret = new Todo(arr[2]);
                break;
            case "[D]":
                ret = new Deadline(arr[2], DateParser.stringToDate(arr[3]));
                break;
            case "[E]":
                ret = new Event(arr[2], DateParser.stringToDate(arr[3]));
                break;
            default:
                return null;
            }
        } catch (UnrecognisedDateException e) {
            return null;
        }

        if ("1".equals(arr[1])) {
            ret.mark();
        }

        return ret;
    }

}
