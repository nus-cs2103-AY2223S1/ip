import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tasklist {
    static ArrayList<Task> taskList;

    public static void initTaskList() {
        File newFolder = new File("./data");
        if (newFolder.exists()) {
            File dataFile = new File("./data/dukedata.txt");
            if (dataFile.exists()) {
                ArrayList<Task> tempTaskList = new ArrayList<>();
                try {
                    Scanner sc = new Scanner(dataFile);
                    while (sc.hasNext()) {
                        String curr = sc.nextLine();
                        Task addTask = Task.fileStringToTask(curr);
                        if (addTask != null) {
                            tempTaskList.add(addTask);
                        }
                    }
                    taskList = tempTaskList;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    dataFile.createNewFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                taskList = new ArrayList<>();
            }
        } else {
            newFolder.mkdir();
            try {
                new File("./data/dukedata.txt").createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            taskList = new ArrayList<>();
        }
    }

    public static void listToFile() {
        try {
            FileWriter fw = new FileWriter("./data/dukedata.txt");
            for (Task t : taskList) {
                fw.write(t.taskToFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Task get(int index) {
        return taskList.get(index);
    }

    public static void add(Task task) {
        taskList.add(task);
        listToFile();
    }

    public static void remove(int index) {
        taskList.remove(index);
        listToFile();
    }

    public static int size() {
        return taskList.size();
    }

    public static void mark(int index) {
        taskList.get(index).mark();
        listToFile();
    }

    public static void unMark(int index) {
        taskList.get(index).unMark();
        listToFile();
    }

}