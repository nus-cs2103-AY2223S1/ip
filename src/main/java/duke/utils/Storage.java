package duke.utils;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private List<Task> taskList;

    private File saveFile;

    public Storage(File givenSaveFile) {
        taskList = new ArrayList<>();
        saveFile = givenSaveFile;
    }
    public List<Task> loadFromFile() {
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

        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));
            taskList.clear();
            reader.lines().forEach((s) -> taskList.add(fromDataString(s)));
        } catch (IOException e) {
            System.out.println("Error while loading from file: " + e);
        }
        return taskList;
    }

    public void saveToFile(List<Task> givenTaskList) {
        taskList = givenTaskList;
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

    private static Task fromDataString(String dataString) {
        String[] arr = dataString.split("\\|");
        Task ret;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }

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

        if ("1".equals(arr[1])) {
            ret.mark();
        }

        return ret;
    }

}
