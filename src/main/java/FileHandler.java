import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the saving and loading of data to and from local storage.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class FileHandler {
    private final String FILEPATH;
    private File dataFile;

    /**
     * Constructor for an instance of FileHandler.
     */
    public FileHandler() {
        // Initialise file path
        this.FILEPATH  = "./data/duke.txt";

        // Create an object representing the data file
        this.dataFile = new File(this.FILEPATH);

        // Check if dataFile exists
        if (!dataFile.exists()) {
            // If not, create it
            try {
                dataFile.createNewFile();
            } catch (java.io.IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Saves the given list of Tasks into the data file.
     *
     * @param tasklist List of Tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasklist) {
        try {
            FileWriter writer = new FileWriter(this.FILEPATH);
            for (Task task : tasklist) {
                writer.write(task.encode() + "\n");
            }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Task> getTasks() {
        Scanner reader = null;
        ArrayList<Task> output = new ArrayList<>();
        try {
            reader = new Scanner(this.dataFile);
            while (reader.hasNextLine()) {
                String encodedTask = reader.nextLine();
                output.add(decode(encodedTask));
            }
        } catch (java.io.IOException e) {
            System.out.println(e);
        } finally {
            reader.close();
        }
        return output;
    }

    /**
     * Decodes a line in the data file and returns the Task it represents.
     *
     * @param code The encoded Task saved in the file as a String
     *
     * @return The Task saved in the data file.
     */
    private Task decode(String code) {
        String[] components = code.split(" # ");

        Task result;
        switch (components[0]) {
        case "T":
            result = new Todo(components[2]);
            if (components[1].equals("done")) {
                result.mark();
            }
            return result;
        case "D":
            result = new Deadline(components[2], components[3]);
            if (components[1].equals("done")) {
                result.mark();
            }
            return result;
        case "E":
            result = new Event(components[2], components[3]);
            if (components[1].equals("done")) {
                result.mark();
            }
            return result;
        default:
            return null;
        }
    }
}
