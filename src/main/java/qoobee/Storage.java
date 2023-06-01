package qoobee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage to be saved in the hard disk.
 */
public class Storage {

    private String filePath;
    private List<Task> list;

    /**
     * Creates a storage given a filepath.
     * @param filepath The directory of the storage.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
        this.list = new ArrayList<>();
    }

    /**
     * Loads a file from the hard disk. If file is not available, creates one.
     * @throws QoobeeException if error in saving task list.
     */
    public void loadFile() throws QoobeeException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Scanner myReader = new Scanner(reader);
            while (myReader.hasNextLine()) {
                String curr = myReader.nextLine();
                String[] stringArray = curr.split(" \\| ");
                String taskName = stringArray[0];
                String taskStatus = stringArray[1];
                String taskDescription = stringArray[2];
                Task currTask = null;
                if (taskName.equals("T")) {
                    currTask = new ToDo(taskDescription);
                } else if (taskName.equals("D")) {
                    String taskBy = stringArray[3];
                    currTask = new Deadline(taskDescription, DateTimeParser.getDateTime(taskBy));
                } else if (taskName.equals("E")) {
                    String taskAt = stringArray[3];
                    currTask = new Event(taskDescription, taskAt);
                }
                if (taskStatus.equals("1")) {
                    currTask.markAsDone();
                }
                currTask.setPriorityLevel(stringArray[stringArray.length - 1]);
                this.list.add(currTask);
                save(list);
            }
        } catch (FileNotFoundException e) {
            // Create the empty file if there is no existing file.
            File file = new File(filePath);
        } catch (QoobeeException e) {
            System.out.println("Unable to load task");
        }
    }

    /**
     * Saves the tasklist into the hard disk.
     * @param taskList The tasklist to be saved.
     * @throws QoobeeException if there is an error saving the file.
     */
    public void save(List<Task> taskList) throws QoobeeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                writer.write(task.storageToString());
            }
            writer.close();
        } catch (IOException e) {
            throw new QoobeeException("Something went wrong!!");
        }
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public List<Task> getList() {
        return this.list;
    }

}
