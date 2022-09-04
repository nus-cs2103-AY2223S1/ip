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

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "C:/Unu_Stuff/Y3S1/CS2103-CS2103T/Lab/Lab 2/src/data/duke.txt";
    private Scanner myReader;
    private PrintWriter writer;
    public Storage() {
        File myObj = new File(FILE_PATH);
        try {
            this.myReader = new Scanner(myObj);
            this.writer = new PrintWriter(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Hi, u got error in storage file not exist");
            e.printStackTrace();
        }
    }

    public TaskList readDuke(List<Task> tasks, int curr) {
        while (myReader.hasNextLine()) {
            String userInput = myReader.nextLine();
            String[] userInputArray = userInput.split(" \\| "); //special chara
            Task task;
            boolean done = userInputArray[1].equals("1");
            switch (userInputArray[0]) {
                case "T":
                    task = new ToDo(userInputArray[2], done, "");
                    tasks.add(curr++, task);
                    break;
                case "E":
                    task = new Event(userInputArray[2], done, dateReader(userInputArray[3]));
                    tasks.add(curr++, task);
                    break;
                case "D":
                    task = new Deadline(userInputArray[2], done, dateReader(userInputArray[3]));
                    tasks.add(curr++, task);
                    break;
                case "":
                    break; //edge case
            }
        }
        myReader.close();
        writer.print("");
        return new TaskList(tasks, curr);
    }

    public void writerToDuke(List<Task> tasks, int curr) {
        for (int i = 0; i < curr; i++) {
            writer.println(tasks.get(i).toText());
        }
        writer.close();
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

    public static void createFiles() throws IOException {
        String[] arr = FILE_PATH.split("/");
        java.nio.file.Path path = java.nio.file.Paths.get("");
        for (int i = 0; i < arr.length - 1; i++) {
            path = java.nio.file.Paths.get(String.valueOf(path),arr[i]);
            boolean directoryExists = java.nio.file.Files.exists(path);
            if(!directoryExists) {
                new File(String.valueOf(path)).mkdirs();
                System.out.println("hi, made new directory");
            }
        }
        java.nio.file.Path filePath = java.nio.file.Paths.get(FILE_PATH);
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if(!directoryExists) {
            filePath.toFile().createNewFile();
        }
    }
}
