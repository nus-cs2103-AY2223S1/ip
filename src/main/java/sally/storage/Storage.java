package sally.storage;

import sally.exception.SallyException;
import sally.task.Deadline;
import sally.task.Event;
import sally.task.Task;
import sally.task.TaskList;
import sally.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to store tasks into task list, reads tasks from files, and saves tasks to files.
 *
 * @author liviamil
 */

public class Storage {
    private String filePath = "data/Sally.txt";
    private static final String DIRECTORY = System.getProperty("user.dir");


    /**
     * Constructor for Storage class
     */
    public Storage() {
        this.filePath = DIRECTORY + "/" + filePath;
    }

    /**
     * Loads tasks from storage
     *
     * @return inputs in the form of List of String.
     */
    public ArrayList<Task> load() throws SallyException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                File parent = new File(DIRECTORY + "/data");
                boolean isDirectoryCreated = parent.mkdir();
                boolean isFileCreated = file.createNewFile();
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitInput = input.split(" \\| ");

                switch (splitInput[0]) {
                    case "T":
                        ToDo todo = new ToDo(splitInput[2]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;

                    case "D":
                        Deadline deadline = new Deadline(splitInput[2], splitInput[3]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;

                    case "E":
                        Event event = new Event(splitInput[2], splitInput[3]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;

                    default:
                        throw new SallyException("Oops! Please enter a valid task type (i.e. todo, deadline, event).");
                }
            }
        } catch (IOException e) {
            throw new SallyException("Oops! An IOException occurred. load()");
        } catch (NumberFormatException e) {
            throw new SallyException("Oops! Please enter 0/1 to mark the tasks.");
        }

        return tasks;
    }

    /**
     * Saves task list to file.
     *
     * @param tasks task list to be saved.
     */
    public void savesFile(TaskList tasks) throws SallyException {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : tasks.getAllTasks()) {
                fw.write(task.getOutput() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new SallyException("Oops! An IOException has occurred. savesFile");
        }
    }

}