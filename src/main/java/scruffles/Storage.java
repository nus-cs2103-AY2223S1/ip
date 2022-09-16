package scruffles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for the storing and loading of previously saved data
 *
 * @author Shamus Tan
 */
public class Storage {

    private static final String filePath = "scruffles.txt";

    public Storage() {}

    /**
     * Loads the existing file from the filepath
     *
     * @return the TaskList that was saved in the file
     */
    public static TaskList load() {
        try {
            File newFile = new File(filePath);
            Scanner scan = new Scanner(newFile);
            ArrayList<Task> result = new ArrayList<>();

            while (scan.hasNext()) {
                String taskString = scan.nextLine();
                String[] inputs = taskString.split(" / ");
                Task task = new Task("");
                boolean isDone = inputs[1].equals("X");

                switch (inputs[0]) {
                case "T":
                    task = new Todo(inputs[2], isDone);
                    break;
                case "D":
                    task = new Deadline(inputs[2], LocalDate.parse(inputs[3]), isDone);
                    break;
                case "E":
                    task = new Event(inputs[2], LocalDate.parse(inputs[3]), LocalTime.parse(inputs[4]),
                            LocalTime.parse(inputs[5]), isDone);
                    break;
                case "P":
                    task = new DoWithinPeriod(inputs[2], LocalDate.parse(inputs[3]), LocalDate.parse(inputs[4]),
                            isDone);
                    break;
                default:
                    continue;
                }

                result.add(task);
            }
            return new TaskList(result);
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Saves the TaskList into the filepath
     */
    public static void save(TaskList tasks) {
        try {
            File newFile = new File(filePath);
            newFile.createNewFile();
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.write(tasks.saveToFile());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
