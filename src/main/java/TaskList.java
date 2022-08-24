import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    static ArrayList<Task> taskList = new ArrayList<>();
    static File dataFile = new File("data.txt");
    
    public static void initFile() {
        if (dataFile.exists()) {
            loadFromFile();
        } else {
            createFile();
        }
    }
    
    public static void createFile() {
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void loadFromFile() {
        taskList.clear();
        try {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task task = Task.decodeFromString(line);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task t : taskList) {
                fw.write(t.encodeToString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    public static Task get(int index) {
        return taskList.get(index);
    }

    public static int size() {
        return taskList.size();
    }
    
    //to save
    public static void add(Task t) {
        taskList.add(t);
        saveToFile();
    }
    
    public static void delete(int index) {
        taskList.remove(index);
        saveToFile();
    }
    
    public static void mark(int index) {
        taskList.get(index).mark();
        saveToFile();
    }
    
    public static void unmark(int index) {
        taskList.get(index).unmark();
        saveToFile();
    }
    
}
