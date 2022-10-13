package sally.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import sally.exception.SallyException;
import sally.task.Deadline;
import sally.task.Event;
import sally.task.Task;
import sally.task.TaskList;
import sally.task.ToDo;

/**
 * Storage class to store tasks into task list, reads tasks from files, and saves tasks to files.
 *
 * @author liviamil
 */

public class Storage {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private String filePath = "data/Sally.txt";

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
                    loadToDo(splitInput, tasks);
                    break;

                case "D":
                    loadDeadline(splitInput, tasks);
                    break;

                case "E":
                    loadEvent(splitInput, tasks);
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
     * Loads Todo tasks to Parser.
     *
     * @param splitInput data input read from text file.
     * @param tasks tasks to be updated.
     */
    public void loadToDo(String[] splitInput, ArrayList<Task> tasks) {
        ToDo todo = new ToDo(splitInput[2]);
        if (Integer.parseInt(splitInput[1]) == 1) {
            todo.markAsDone();
        }
        tasks.add(todo);
    }

    /**
     * Loads Deadline tasks to Parser.
     *
     * @param splitInput data input read from text file.
     * @param tasks tasks to be updated.
     */
    public void loadDeadline(String[] splitInput, ArrayList<Task> tasks) {
        System.out.println("splitInput[2]: " + splitInput[2]);
        System.out.println("splitInput[3]: " + splitInput[3]);
        Deadline deadline;
        String date = splitInput[3];
        if (isDate(date)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
            String []splitDate = date.split("-");
            String stringDate = splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0];
            LocalDate byDate = LocalDate.parse(stringDate, formatter);
            deadline = new Deadline(splitInput[2], byDate);
        } else {
            deadline = new Deadline(splitInput[2], date);
        }

        if (Integer.parseInt(splitInput[1]) == 1) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    /**
     * Returns true if deadline entered is date, returns false otherwise.
     *
     * @param by deadline parsed in
     * @return true if deadline parsed is valid date.
     */
    protected static boolean isDate(String by) {
        // Expected input date format: d-MM-yyyy
        String[] splitBy = by.split("-");
        if (splitBy.length != 3) {
            return false;
        }
        return true;
    }

    /**
     * Loads Event tasks to Parser.
     *
     * @param splitInput data input read from text file.
     * @param tasks tasks to be updated.
     */
    public void loadEvent(String[] splitInput, ArrayList<Task> tasks) {
        Event event = new Event(splitInput[2], splitInput[3]);
        if (Integer.parseInt(splitInput[1]) == 1) {
            event.markAsDone();
        }
        tasks.add(event);
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
