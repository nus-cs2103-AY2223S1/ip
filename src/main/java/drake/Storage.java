package drake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import drake.commands.CommandType;
import drake.tasks.Deadline;
import drake.tasks.DoWithinPeriod;
import drake.tasks.Event;
import drake.tasks.Task;
import drake.tasks.Todo;

/**
 * Task list saving and loading functionalities.
 */
public class Storage {

    private static final String TASK_FILE_PATH = "data/tasks.txt";
    private static final String TASK_FILE_DIR = "data";
    private final List<List<String>> tasks;
    private final File taskFile;

    /**
     * Constructor using the default savefile location.
     *
     */
    public Storage() throws IOException, DrakeException {
        taskFile = new File(TASK_FILE_PATH);
        tasks = new ArrayList<>();
        addTasks(fileToList());
    }

    /**
     * Reads tasks from the task file into a list of Tasks.
     *
     * @return A list of Tasks present in the task file.
     */
    public List<Task> fileToList() throws UnknownCommandException {
        ArrayList<Task> list = new ArrayList<>();
        Scanner fileReader;
        try {
            fileReader = new Scanner(taskFile);
        } catch (FileNotFoundException e) {
            return list;
        }
        while (fileReader.hasNext()) {
            String[] taskParts = fileReader.nextLine().split(";");
            switch (taskParts[0]) {
            case "D":
                Deadline deadline = new Deadline(taskParts[1], taskParts[3]);
                if (taskParts[2].equals("X")) {
                    deadline.markAsDone();
                }
                list.add(deadline);
                break;
            case "T":
                Task task = new Todo(taskParts[1]);
                if (taskParts[2].equals("X")) {
                    task.markAsDone();
                }
                list.add(task);
                break;
            case "E":
                Event event = new Event(taskParts[1], taskParts[3]);
                if (taskParts[2].equals("X")) {
                    event.markAsDone();
                }
                list.add(event);
                break;
            case "W":
                DoWithinPeriod doWithinPeriod = new DoWithinPeriod(taskParts[1], taskParts[3], taskParts[4]);
                if (taskParts[2].equals("X")) {
                    doWithinPeriod.markAsDone();
                }
                list.add(doWithinPeriod);
                break;
            default:
                throw new UnknownCommandException();
            }
        }
        return list;
    }

    /**
     * Updates the task in the task list.
     *
     * @param taskNumber The task number of the task to update.
     * @param command The update demanded by the user.
     * @throws DrakeException when saving fails.
     */
    public void updateTask(int taskNumber, CommandType command) throws DrakeException {
        switch (command) {
        case MARK:
            tasks.get(taskNumber - 1).set(2, "X");
            break;

        case UNMARK:
            tasks.get(taskNumber - 1).set(2, " ");
            break;

        case DELETE:
            tasks.remove(taskNumber - 1);
            break;
        default:
            throw new UnknownCommandException();
        }

        updateFile();
    }

    /**
     * Updates the task file to the current state of the task list.
     *
     * @throws DrakeException when saving fails
     */
    private void updateFile() throws DrakeException {
        //Inspired by parnikkapore's PR
        try {
            File fileDir = new File(TASK_FILE_DIR);
            if (!fileDir.isDirectory() && !fileDir.mkdirs()) {
                throw new DrakeException("Higher powers taking a hold on me... I cannot save the task list.");
            }

            FileWriter fileWriter = new FileWriter(TASK_FILE_PATH);
            for (List<String> task : tasks) {
                fileWriter.write(listToCsv(task));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DrakeException(
                    "Higher powers taking a hold on me... I cannot save the task list. This might help: " + e);
        }
    }

    /**
     * Adds a task to the task file.
     *
     * @param addedTask The task to be added to the file.
     * @throws DrakeException when saving fails.
     */
    public void addTask(Task addedTask) throws DrakeException {
        tasks.add(addedTask.toList());
        updateFile();
    }

    private void addTasks(List<Task> addedTasks) throws DrakeException {
        for (Task addedTask : addedTasks) {
            addTask(addedTask);
        }
        updateFile();
    }

    private String listToCsv(List<String> list) {
        StringBuilder csv = new StringBuilder(list.get(0)).append(";");
        for (int i = 1; i < list.size(); i++) {
            csv.append(list.get(i));
            if (i != list.size() - 1) {
                csv.append(";");
            }
        }
        return csv.append("\n").toString();
    }
}
