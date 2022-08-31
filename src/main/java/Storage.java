import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static ArrayList<Task> load() throws FileNotFoundException {
        File newFile = new File(filePath);
        Scanner scan = new Scanner(newFile);
        ArrayList<Task> result = new ArrayList<>();
        while (scan.hasNext()) {
            String taskString = scan.nextLine();
            String[] inputArray = taskString.split(" / ");
            Task task = new Task("");
            boolean isDone = inputArray[1].equals("X");

            switch (inputArray[0]) {
                case "T":
                    task = new Todo(inputArray[2], isDone);
                    break;
                case "D":
                    task = new Deadline(inputArray[2], LocalDate.parse(inputArray[3]), isDone);
                    break;
                case "E":
                    task = new Event(inputArray[2], LocalDate.parse(inputArray[3]), LocalTime.parse(inputArray[4]),
                            LocalTime.parse(inputArray[5]), isDone);
                    break;
                default:
                    continue;
            }

            result.add(task);
        }
        return result;
    }

    public static void save(TaskList tasks) {
        try {
            File newFile = new File(filePath);
            newFile.createNewFile();
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(tasks.saveToFile());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
