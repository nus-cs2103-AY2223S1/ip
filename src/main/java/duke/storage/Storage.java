package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.TaskList;
import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;



/**
 * Storage class to handle file operations
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if saved folder exists.
     */
    public void run() {
        try {
            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                System.out.println("hello");
            } else {
                //File already exists
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function to load data into the application from saved file
     * @return A list of tasks
     */
    public List<Task> loadData() {
        List<Task> result = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("\\| ", 4);
                switch (split[0]) {
                case "T ":
                    result.add(new Todo(split[2]));
                    break;
                case "D ":
                    result.add(new Deadline(split[2], LocalDate.parse(split[3])));
                    break;
                case "E ":
                    result.add(new Event(split[2], LocalDate.parse(split[3])));
                    break;
                default:
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Write task to file
     * @param text
     */
    public void write(String text) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(text + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Some actions like update and delete require a rewrite of the
     * entire text file
     * @param tasks
     */
    public void rewrite(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            PrintWriter pw = new PrintWriter(filePath);
            for (int i = 0; i < tasks.getSize() - 1; i++) {
                myWriter.write(tasks.getTask(i).toString());
            }
            pw.close();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
