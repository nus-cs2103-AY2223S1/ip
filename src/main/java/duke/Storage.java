package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulate the Storage of the program.
 */

public class Storage {

    private String filePath;

    private File dataFile;

    /**
     * Class Constructor of Storage class.
     *
     * @param filePath path to store data file.
     */
    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            File folder = new File(filePath);
            folder.mkdir();
            this.dataFile = new File(filePath + "/duke.txt");
            if (!this.dataFile.exists()) {
                this.dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in file creation");
        }
    }

    /**
     * Rewrites the list of tasks into the data file.
     *
     * @param taskList list of existing tasks.
     */
    public void update(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter myWriter = new FileWriter(filePath + "/duke.txt");
            for (Task task : taskList) {
                myWriter.write(task.toStorageString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("There is something wrong with storage file!");
        }
    }

    /**
     * Loads task list data from data file and returns a
     * TaskList with all the task in the data file.
     *
     * @return TaskList with all the saved tasks from previous session.
     * @throws DukeException if file not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> memory = new ArrayList<>();
        Scanner myReader;
        try {
            myReader = new Scanner(this.dataFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("Storage file not found at specified path!");
        }
        while (myReader.hasNextLine()) {
            String taskString = myReader.nextLine();
            String[] taskDataArray = taskString.split(" \\| "); // since | is a special character
            Task currTask = null;
            switch (taskDataArray[0]) {
            case "T":
                currTask = new ToDo(taskDataArray[2]);
                memory.add(currTask);
                break;
            case "E":
                currTask = new Event(taskDataArray[2], taskDataArray[3]);
                memory.add(currTask);
                break;
            case "D":
                currTask = new Deadline(taskDataArray[2], taskDataArray[3]);
                memory.add(currTask);
                break;
            }
            if (taskDataArray[1].equals("1")) {
                assert currTask != null;
                currTask.markAsDone();
            }
        }
        return memory;
    }
}
