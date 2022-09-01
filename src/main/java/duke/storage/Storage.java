package duke.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.data.TaskList;

/**
 * Reads and writes data to the hard disk.
 */
public class Storage {

    /** Relative file path of the data file */
    private String dataFilePath;


    /**
     * Creates a new Storage object that stores data at the specified data path.
     * 
     * @param dataFilePath Relative file path of the data file
     */
    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }


    /**
     * Serializes (write to file) the specified TaskList object to the data file.
     * Method adapted from https://www.tutorialspoint.com/java/java_serialization.htm
     * 
     * @param tasks TaskList object to serialize.
     */
    public void writeToFile(TaskList tasks) {

        try {
            FileOutputStream outputFile = new FileOutputStream(this.dataFilePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);

            outputStream.writeObject(tasks);

            outputFile.close();
            outputStream.close();
            

        } catch (FileNotFoundException e) {
            System.out.println("Error, cannot open file");
            // System.out.println(e);

        } catch (IOException e) {
            System.out.println("Error when writing to file");
            // System.out.println(e);
        }

    }


    /**
     * Returns the deserialized (read from file) TaskList object from the data file.
     * Method adapted from https://www.tutorialspoint.com/java/java_serialization.htm
     * 
     * @return TaskList object that was deserialized from the data file.
     */
    public TaskList readFromFile() {
        
        try {
            FileInputStream inputFile = new FileInputStream(this.dataFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);

            TaskList tasks = (TaskList) inputStream.readObject();

            inputFile.close();
            inputStream.close();
            return tasks;

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find data.ser file. Creating new task list instead.");
            // System.out.println(e);

        } catch (IOException e) {
            System.out.println("Error when reading file");
            // System.out.println(e);

        } catch (ClassNotFoundException e) {
            System.out.println("Error, serialized object can't be deserialized");
            // System.out.println(e);
        }

        // Some error occured when reading from file so return an empty task list instead
        return new TaskList();
    }
    
}
