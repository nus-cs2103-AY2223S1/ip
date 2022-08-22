import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    public static ArrayList<Task> parseFile(File file) {
        ArrayList<Task> retreivedList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                Task task = taskParser(reader.nextLine());
                if (task != null) {
                    retreivedList.add(task);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The storage for the list is not found.");
        }
        return retreivedList;
    }

    private static Task taskParser(String dataline) {
        String[] data = dataline.split("\\|");
        try {
            String taskType = data[0];
            boolean isMarked = data[1] == "Y" ? true : false;
            switch (taskType) {
            case "T":
                return new ToDoTask(data[2], isMarked);
            case "D":
                return new DeadlineTask(data[2], data[3], isMarked);
            case "E":
                return new EventTask(data[2], data[3], isMarked);
            default:
                System.out.println("I do not recognise this:\n[Line]\t" + dataline);
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Some data might be missing!:\n[Line]\t" + dataline);
            return null;
        }   
    }
}
