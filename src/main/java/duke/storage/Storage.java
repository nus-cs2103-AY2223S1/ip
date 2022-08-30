package duke.storage;

import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.*;
public class Storage {

    private final static Path FOLDER_PATH = Paths.get(System.getProperty("user.dir") + "/data");

    private final static Path FILE_PATH = Paths.get(FOLDER_PATH + "/duke.txt");


    public Storage() throws IOException {

    }

    public static TaskList load() throws  IOException {
        boolean directoryExists = java.nio.file.Files.exists(FOLDER_PATH);
        boolean fileExists = java.nio.file.Files.exists(FILE_PATH);
        TaskList taskList = new TaskList();

        if(!directoryExists) {
            Files.createDirectories(FOLDER_PATH);
        }

        if(!fileExists) {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            fw.close();
        }
//        return readFile();

        File f = new File(FILE_PATH.toString()); // create a File for the given file path
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
//            System.out.println(taskType);
            if (taskType == 'T') {
                String description = line.substring(7);
                taskList.addTaskWithoutPrinting(new ToDo(description));
            } else if (taskType == 'D') {
                String descriptionAndDate = line.substring(7);
                String[] arguments  = descriptionAndDate.split("\\(");
                String description = arguments[0];
                LocalDate date = LocalDate.parse(removeLastChar(arguments[1]));
                taskList.addTaskWithoutPrinting(new Deadline(description, date));
            }else if (taskType == 'E') {
                String descriptionAndDate = line.substring(7);
                String[] arguments  = descriptionAndDate.split("\\(");
                String description = arguments[0];
                LocalDate date = LocalDate.parse(removeLastChar(arguments[1]));
                taskList.addTaskWithoutPrinting(new Event(description, date));
            }
        }
        return  taskList;
    }

    public static void save(ArrayList<Task> taskListToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH.toString());
        String fullText = "";
        int ListLength = taskListToAdd.size();
        for (int i = 0; i < ListLength; i++) {
            fullText = fullText + taskListToAdd.get(i).getDescription() + System.lineSeparator();
        }

        fw.write(fullText);
        fw.close();
    }

    public static String removeLastChar(String s) {
        s = s.substring(0, s.length()-1);
        return s;
    }

//    public static Scanner readFile() throws FileNotFoundException {
//        File f = new File(FILEPATH.toString()); // create a File for the given file path
//        Scanner s = new Scanner(f);
//        return s;
//    }

}
