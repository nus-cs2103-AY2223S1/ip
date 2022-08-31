package duke.taskmanager;

import duke.taskmanager.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static final String FILEPATH = "tasklist.txt";
    private static final String DATE_FORMAT = "dd/MM/yyyy,HHmm";
    private final List<Task> taskList;

    /**
     * Creates a new task manager and initializes a task list.
     */
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the date format to be used to allocate task by the task manager.
     *
     * @return date format used by the task manager.
     */
    public String getDateFormat() {
        return DATE_FORMAT;
    }

    /**
     * Processes strings that are formatted to be readable for task manager.
     * Used to process strings loaded from a save file creates a new task from the string.
     *
     * @param formattedString string in a readable format detailing a task to be added
     * @return a new task with details processed from the string
     * @throws Exception when the formatted string cannot be read
     */
    private Task processFormattedString(String formattedString) throws Exception {
        String[] arguments = formattedString.split(" | ");
        String taskType = arguments[0];
        boolean isCompleted = (arguments[2].equals("1"));
        String taskName = arguments[4];

        switch (taskType) {
        case "T":
            return new ToDoTask(taskName, isCompleted);
        case "D":
            return new DeadlineTask(taskName, arguments[6], isCompleted, DATE_FORMAT);
        case "E":
            return new EventTask(taskName, arguments[6], isCompleted, DATE_FORMAT);
        default:
            return new EmptyTask();
        }
    }

    /**
     * List the task currently in its task list. Responds with an appropriate message
     * depending on the number of items in the list.
     *
     * @return response message detailing the task present in the task list
     */
    public String list() {
        if (taskList.size() == 0) {
            return "\tYou have no tasks in your list.\n";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\tI have your list of tasks displayed below:\n");
            for (int i = 0; i < this.taskList.size(); i++) {
                stringBuilder.append("\t").append(i + 1).append(") ").append(taskList.get(i)).append("\n");
            }
            return stringBuilder.toString();
        }
    }

    /**
     * Adds a new task to the current task list. Responds with a message indicating the
     * addition of the new task.
     *
     * @param task task to be added to the task list
     * @return response message indicating the addition of the new task
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return ("\t> Added: " + task.getTaskName() + "\n");
    }

    /**
     * Marks a task in the task list to indicate completion. The task to be marked is provided by the user.
     * Responds with an appropriate message indicating the marking of the task depending on the
     * prior completion status of the task
     *
     * @param itemNumber index of the task to be marked as completed
     * @return response message indicating the marking of the task
     */
    public String mark(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (this.taskList.get(itemNumber - 1).isCompleted()) {
                return "\tThe task is already marked you dummy.\n";
            } else {
                this.taskList.get(itemNumber - 1).setIsCompleted(true);
                return "\tI've marked this task as done. Good Job!\n";
            }
        } else {
            return "\tThere is no such task!!\n";
        }
    }

    /**
     * Unmarks a marked task in the task list to indicate completion. The task to be marked is provided by the user.
     * Responds with an appropriate message indicating the unmarking of the task depending on the
     * prior completion status of the task
     *
     * @param itemNumber index of the marked task to be unmarked as completed
     * @return response message indicating the unmarking of the task
     */
    public String unmark(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (!(this.taskList.get(itemNumber - 1).isCompleted())) {
                return "\tThe task is still not done you idiot.\n";
            } else {
                this.taskList.get(itemNumber - 1).setIsCompleted(false);
                return "\tThe task has been unmarked.\n";
            }
        } else {
            return "\tThere is no such task!!\n";
        }
    }

    /**
     * Deletes a task in the task list. The task to be deleted is provided by the user.
     * Responds with an appropriate message indicating the task that was deleted.
     *
     * @param itemNumber index of the task to be deleted
     * @return response message indicating the deletion of the task
     */
    public String delete(int itemNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            stringBuilder.append("The following item has been removed.\n");
            stringBuilder.append(this.taskList.remove(itemNumber - 1).toString()).append("\n");
            stringBuilder.append("You have ").append(this.taskList.size()).append(" item(s) remaining.\n");
        } else {
            return "\tThere is no such task!!\n";
        }
        return stringBuilder.toString();
    }

    /**
     * Saves the current task list as a text file in the provided filepath.
     * The text file is saved using a readable format provided by the task.
     * Creates new file if it does not already exist in the filepath.
     */
    public void save() {
        try {
            File file = new File(FILEPATH);
            if (!(file.exists())) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : this.taskList) {
                fileWriter.write(task.getFormattedString());
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Loads the current task list from a text file in the provided filepath.
     * The text file is read using a readable format provided by the task.
     * Handles exception if file does not exist in the filepath.
     */
    public void load() {
        try {
            File file = new File(FILEPATH);
            if (!(file.exists())) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            try {
                while (fileScanner.hasNextLine()) {
                    Task newTask = processFormattedString(fileScanner.nextLine());
                    if (!(newTask.isEmpty())) {
                        taskList.add(newTask);
                    }
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
}