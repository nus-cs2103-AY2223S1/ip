package duke.taskmanager;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.chatbot.commands.exceptions.InvalidDeadlineException;
import duke.chatbot.commands.exceptions.InvalidEventException;
import duke.taskmanager.exceptions.InvalidFormattedStringException;
import duke.taskmanager.exceptions.LoadDataException;
import duke.taskmanager.exceptions.SaveDataException;
import duke.taskmanager.task.DeadlineTask;
import duke.taskmanager.task.EmptyTask;
import duke.taskmanager.task.EventTask;
import duke.taskmanager.task.Task;
import duke.taskmanager.task.ToDoTask;

/**
 * TaskManager class manages a list of task and provide functions to manipulate the list.
 */
public class TaskManager {
    public static final String UPDATE_DELIMITER = " >> ";
    private static final String FILE_PATH = "tasklist.txt";
    private static final String DATE_FORMAT = "dd/MM/yyyy,HHmm";
    private static final String ATTRIBUTE_SEPARATOR = "<>";

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
     * @return date format used by the task manager
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
    private Task processFormattedString(String formattedString) throws InvalidFormattedStringException,
            EmptyTaskException, InvalidDeadlineException, InvalidEventException {
        String[] arguments = formattedString.split(ATTRIBUTE_SEPARATOR);
        String taskType = arguments[0];
        boolean isCompleted = (arguments[1].equals("1"));
        String taskName = arguments[2];

        switch (taskType) {
        case "T":
            return new ToDoTask(taskName, isCompleted);
        case "D":
            try {
                LocalDateTime deadline = LocalDateTime.parse(arguments[3], DateTimeFormatter.ofPattern(DATE_FORMAT));
                return new DeadlineTask(taskName, deadline, isCompleted);
            } catch (DateTimeParseException exception) {
                throw new InvalidDeadlineException(DATE_FORMAT);
            }
        case "E":
            try {
                LocalDateTime eventTime = LocalDateTime.parse(arguments[3], DateTimeFormatter.ofPattern(DATE_FORMAT));
                return new EventTask(taskName, eventTime, isCompleted);
            } catch (DateTimeParseException exception) {
                throw new InvalidEventException(DATE_FORMAT);
            }
        default:
            return new EmptyTask();
        }

    }

    /**
     * List the task currently in its task list.
     *
     * @return string of the list of tasks
     */
    public String listTask() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            stringBuilder.append(i + 1).append(") ").append(taskList.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Adds a new task to the current task list. Responds with the name of the new task added.
     *
     * @param task task to be added to the task list
     * @return name of the new task added
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return task.getTaskName();
    }

    /**
     * Marks a task in the task list to indicate completion. The task to be marked is provided by the user.
     * Responds with an appropriate message indicating the marking of the task depending on the
     * prior completion status of the task
     *
     * @param itemNumber index of the task to be marked as completed
     * @return response message indicating the marking of the task
     */
    public String markTask(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (this.taskList.get(itemNumber - 1).isCompleted()) {
                return "The task is already marked you dummy.\n";
            } else {
                this.taskList.get(itemNumber - 1).setCompleted();
                return "I've marked this task as done. Good Job!\n";
            }
        } else {
            return "There is no such task!!\n";
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
    public String unmarkTask(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (!(this.taskList.get(itemNumber - 1).isCompleted())) {
                return "The task is still not done you idiot.\n";
            } else {
                this.taskList.get(itemNumber - 1).setNotCompleted();
                return "The task has been unmarked.\n";
            }
        } else {
            return "There is no such task!!\n";
        }
    }

    /**
     * Deletes a task in the task list. The task to be deleted is provided by the user.
     * Responds with an appropriate message indicating the task that was deleted.
     *
     * @param itemNumber index of the task to be deleted
     * @return response message indicating the deletion of the task
     */
    public String deleteTask(int itemNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            stringBuilder.append("The following item has been removed.\n");
            stringBuilder.append(this.taskList.remove(itemNumber - 1).toString()).append("\n");
            stringBuilder.append("You have ").append(this.taskList.size()).append(" item(s) remaining.\n");
        } else {
            return "There is no such task!!\n";
        }
        return stringBuilder.toString();
    }

    /**
     * Finds and returns a list of task containing the keyword provided by the user.
     *
     * @param keyword string of the keyword to match in the task list
     * @return a response message listing out the task containing the keyword
     */
    public String findTask(String keyword) {
        int[] keywordList = IntStream.range(0, this.taskList.size())
                .filter(i -> this.taskList.get(i).getTaskName().contains(keyword)).toArray();
        if (keywordList.length == 0) {
            return "You have no tasks in your list with the keyword \"" + keyword + "\".\n";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("I have the matching tasks displayed below:\n");
            for (Integer index : keywordList) {
                stringBuilder.append(index + 1).append(") ").append(this.taskList.get(index)).append("\n");
            }
            return stringBuilder.toString();
        }
    }

    /**
     * Updates a task according to item number and a new task with updated attributes.
     *
     * @param itemNumber index of the task to be updated
     * @param newTask new task with updated attributes
     * @return a response message indicating that the task has been updated
     */
    public String updateTask(int itemNumber, Task newTask) {
        StringBuilder stringBuilder = new StringBuilder();
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            this.taskList.get(itemNumber - 1).update(newTask);
            stringBuilder.append("The following item has been updated.\n");
            stringBuilder.append(itemNumber).append(") ")
                    .append(this.taskList.get(itemNumber - 1).toString()).append("\n");
        } else {
            return "There is no such task!!\n";
        }
        return stringBuilder.toString();
    }

    /**
     * Saves the current task list as a text file in the provided filepath.
     * The text file is saved using a readable format provided by the task.
     * Creates new file if it does not already exist in the filepath.
     *
     * @throws SaveDataException when the file cannot be saved
     */
    public void saveData() throws SaveDataException {
        try {
            File file = new File(FILE_PATH);
            if (!(file.exists())) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : this.taskList) {
                fileWriter.write(task.getFormattedString(ATTRIBUTE_SEPARATOR));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (Exception exception) {
            throw new SaveDataException();
        }
    }

    /**
     * Loads the current task list from a text file in the provided filepath.
     * The text file is read using a readable format provided by the task.
     * Handles exception if file does not exist in the filepath.
     *
     * @throws LoadDataException when the file cannot be loaded
     */
    public void loadData() throws LoadDataException {
        try {
            File file = new File(FILE_PATH);
            if (!(file.exists())) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                Task newTask = processFormattedString(fileScanner.nextLine());
                taskList.add(newTask);
            }
        } catch (Exception exception) {
            throw new LoadDataException();
        }
    }
}
