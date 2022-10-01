package duke.storage;


import duke.taskList.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * reads file from duke.txt and
 * writes to duke.txt once programme terminates
 * has a Scanner object to read from duke.txt
 * has a PrintWriter to write to duke.txt
 */
public class Storage {
    // C:/Unu_Stuff/Y3S1/CS2103-CS2103T/Lab/Lab 2/
    private static final String FILE_PATH = "src/data/duke.txt";
    private Path myPath;
    public Storage() {
        this.myPath = Paths.get(FILE_PATH);
        }
    /**
     * Reads the files from duke.txt and stores them in TaskList
     * @param tasks tasks Arraylist from TaskList
     * @param curr no of tasks in taskList
     * @return new Taskist with updated tasks
     */
    public TaskList readDuke(List<Task> tasks, int curr) {
        try {
            List<String> userInput = Files.readAllLines(myPath);
            for (String line : userInput) {
                String[] userInputArray = line.split(" \\| "); //special chara
                Task task;
                boolean done = userInputArray[1].equals("1");
                switch (userInputArray[0]) {
                    case "T":
                        task = new ToDo(userInputArray[2], done, "");
                        curr += 1;
                        System.out.println("here");
                        tasks.add(curr, task);
                        break;
                    case "E":
                        task = new Event(userInputArray[2], done, dateReader(userInputArray[3]));
                        curr += 1;
                        tasks.add(curr, task);
                        break;
                    case "D":
                        task = new Deadline(userInputArray[2], done, dateReader(userInputArray[3]));
                        curr += 1;
                        System.out.println("here");
                        tasks.add(curr, task);
                        break;
                    case "":
                        break; //edge case
                }
            }
            tasks.remove(0);
            return new TaskList(tasks, curr, "");
        } catch (Exception e) {
            return new TaskList(tasks, curr, "Hello, I cannot real your files.");
        }
    }

    /**
     *
     * @param tasks takes in arrayList of TaskList, list of tasks
     * @param curr the no of tasks in TaskList
     */
    public void writerToDuke(List<Task> tasks, int curr) {
        try {
            PrintWriter writer = new PrintWriter(FILE_PATH);
            for (int i = 0; i < curr; i++) {
                writer.println(tasks.get(i).toText());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            final String errorMessage = "Hi, u got error in storage file not exist";
            System.out.println(errorMessage);
            e.printStackTrace();
        }

    }

    public static String dateReader(String str) {
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        myMap.put("JANUARY", 1);
        myMap.put("FEBRUARY", 2);
        myMap.put("MARCH", 3);
        myMap.put("APRIL", 4);
        myMap.put("MAY", 5);
        myMap.put("JUNE", 6);
        myMap.put("JULY", 7);
        myMap.put("AUGUST", 8);
        myMap.put("SEPTEMBER", 9);
        myMap.put("OCTOBER", 10);
        myMap.put("NOVEMBER", 11);
        myMap.put("DECEMBER", 12);

        String[] arr = str.split(" ");
        arr[0] = String.format("%02d", Integer.parseInt(arr[0]));
        arr[1] = String.format("%02d", myMap.get(arr[1]));
        arr[3] = arr[3].substring(0, 2) + arr[3].substring(3);
        return String.format("%s/%s/%s %s", arr[0], arr[1], arr[2], arr[3]);
    }

    /**
     * creats the necessary files if it is not found
     * @throws IOException unable to create new File in directory
     */
    public static String createFiles() throws IOException {
        String[] arr = FILE_PATH.split("/");
        StringBuilder sb = new StringBuilder();
        java.nio.file.Path path = java.nio.file.Paths.get("");
        for (int i = 0; i < arr.length - 1; i++) {
            path = java.nio.file.Paths.get(String.valueOf(path),arr[i]);
            boolean directoryExists = java.nio.file.Files.exists(path);
            if(!directoryExists) {
                new File(String.valueOf(path)).mkdirs();
                sb.append("hi, made new directory");
            }
        }
        java.nio.file.Path filePath = java.nio.file.Paths.get(FILE_PATH);
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if(!directoryExists) {
            filePath.toFile().createNewFile();
        }
        return sb.toString();
    }
}
