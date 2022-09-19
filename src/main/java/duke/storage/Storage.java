package duke.storage;

import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents a class to store the task list in a text file.
 */
public class Storage {

    private final File DATA_DIRECTORY = new File("/data");
    private final File DATA_FILEPATH = new File("data/duke.txt");

    /**
     * Constructs a storage to store the text file
     *
     * @throws IOException if there is an error in creating the file
     */
    public Storage() {
        if(!DATA_DIRECTORY.exists()) {
            DATA_DIRECTORY.mkdir();
        }

        try {
            DATA_FILEPATH.createNewFile();
        } catch (IOException e) {
            System.out.println("Couldn't create a new file.");
        }
    }

    /**
     * Retrives the task stored in the text file
     * @returns a list of task stored in an arraylist.
     */
    public ArrayList<Task> convertToTaskList() {

        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(DATA_FILEPATH);
            while(sc.hasNextLine()) {

                String task = sc.nextLine();
                String[] description = task.split(" \\| ");

                boolean isDone = description[1].equals("1");

                switch (description[0]) {

                    case "T":
                        taskList.add(new ToDo(description[2], isDone));
                        break;
                    case "D":
                        taskList.add(new Deadline(description[2], isDone, description[3]));
                        break;
                    case "E":
                        taskList.add(new Event(description[2], isDone, description[3]));
                        break;
                    default:
                        break;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("There was an error while opening the file");
        }


        return taskList;

    }

    /**
     * Stores the task in task list in a text file
     * @param taskList the list of tasks in the tasklist array .
     * @throws IOException when there is an error while writing to the text file.
     */
    public void saveTaskList(TaskList taskList) {

        try {
            FileWriter fw = new FileWriter(DATA_FILEPATH);
            fw.write(taskList.taskListFileString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Your data couldn't be saved");
        }

    }




}
