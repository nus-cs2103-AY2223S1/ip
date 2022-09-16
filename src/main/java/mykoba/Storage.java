package mykoba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import exception.KobaException;
import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * This class deals with reading tasks from the file to Duke, and also saving tasks from Duke to the file.
 */
public class Storage {
    //this is the physical file saving the items
    private final File storageFile;

    /**
     * Constructs a storage object.
     *
     * @param filepath file path of the storage file.
     */
    public Storage(File filepath) {
        storageFile = filepath;
    }

    /**
     * Reads the data from the file and stores it into the given taskList.
     *
     * @param taskLists the taskList you want to store the data in.
     */
    public void loadFromFile(TaskList taskLists) throws KobaException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(storageFile));
            String currentLine;
            boolean isTaskCompleted;
            while ((currentLine = reader.readLine()) != null) {
                //type of task
                String type = currentLine.substring(0, 1);
                //whether the task is completed
                isTaskCompleted = currentLine.substring(4, 5).equals("T");
                //getting index of where the date starts
                int divider = currentLine.substring(8).indexOf("|") + 8;
                switch (type) {
                case "T":
                    taskLists.saveTask(new ToDo(currentLine.substring(8), isTaskCompleted));
                    break;
                case "D":
                    taskLists.saveTask(new Deadline(currentLine.substring(8, divider - 1),
                            isTaskCompleted, LocalDateTime.parse(currentLine.substring(divider + 2))));
                    break;
                case "E":
                    taskLists.saveTask(new Event(currentLine.substring(8, divider - 1),
                            isTaskCompleted, currentLine.substring(divider + 2)));
                    break;
                default:
                    throw new KobaException("Error reading from file, data stored in file in wrong format!");
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    /**
     * Stores the data from the taskList into the file.
     *
     * @param taskLists given taskList.
     */
    public void saveToFile(TaskList taskLists) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile));
            for (int i = 0; i < taskLists.getNumOfTask(); i++) {
                writer.write(taskLists.getTask(i).getDescription());
            }
            writer.close();
        } catch (IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }
}
