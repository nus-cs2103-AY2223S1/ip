package Duke.storage;

import Duke.tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the file used to store the TaskList
 */
public class Storage {

    /** Default file path used if the user does not provide the file name. */
    public static final String DEFAULT_FILEPATH = "/Users/iz/Documents/Github/ip/src/main/data/tasks.txt";

    private String filePath = "";

    public Storage() {
        this.filePath = DEFAULT_FILEPATH;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void store(TaskList toStore) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(toStore);
            for (String task : encodedTaskList) {
                writer.write("\n" + task);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Task> load() {
        ArrayList<Task> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            List<String> encodedTaskList= new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                encodedTaskList.add(line);
            }
            reader.close();
            result =  TaskListDecoder.decodeTaskList(encodedTaskList);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
