package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** Represents a Storage class. Handles reading and writing to a Save File */
public class Storage {

    private final String directory;
    private final String fileName;

    /**
     * Creates a Storage class which saves and reads from input save file name
     * @param saveFile name of save file
     */
    public Storage(String saveFile) {
        this.directory = System.getProperty("user.dir");
        this.fileName = saveFile;
    }

    /**
     * Writes the Tasks ArrayList input to the specified save file
     * @param  array Tasks ArrayList
     */
    public void save(ArrayList<Task> array) {

        try {
            FileWriter fileWriter = new FileWriter(directory + "/" + fileName, false);

            for (Task task : array) {
                String taskString = task.toStoredString();
                fileWriter.write(taskString + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem encountered when writing, file not found");
        } catch (IOException e) {
            System.out.println("Problem encountered when writing, cannot initialise stream");
        }
    }

    /**
     * Returns a Tasks ArrayList retrieved from the specified save file
     * @return Tasks ArrayList
     */
    public ArrayList<Task> read() {
        try {
            File readFile = new File(directory, fileName);
            boolean fileExists = readFile.createNewFile();
            if (!fileExists) {
                System.out.println("No file detected, new file created");
            }
        } catch (IOException e) {
            System.out.println("Problem encountered when reading, file not created");
            e.printStackTrace();
        }

        try {
            String currLine;
            ArrayList<Task> returnVal = new ArrayList<Task>();
            BufferedReader reader = new BufferedReader(new FileReader(directory + "/" + fileName));

            while ((currLine = reader.readLine()) != null) {
                Task currTask = parse(currLine);
                returnVal.add(currTask);
            }

            return returnVal;
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        } catch (IOException e) {
            return new ArrayList<Task>();
        }
    }

    private Task parse(String input) {
        String[] arguments = input.split("/");
        String type = arguments[0];
        String isCompleted = arguments[1];
        String description = arguments[2];
        String dateString;
        Task currTask;

        switch (type) {
        case "T":
            currTask = new Todo(description);
            break;
        case "D":
            dateString = arguments[3];
            currTask = new Deadline(description, dateString);
            break;
        case "E":
            dateString = arguments[3];
            currTask = new Event(description, dateString);
            break;
        default:
            throw new IllegalStateException();
        }

        if (isCompleted.equals("T")) {
            currTask.toggleComplete();
        }

        return currTask;
    }
}
