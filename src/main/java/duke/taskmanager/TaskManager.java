package duke.taskmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import duke.chatbot.commands.exceptions.InvalidDeadlineException;
import duke.chatbot.commands.exceptions.InvalidEventException;
import duke.taskmanager.exceptions.InvalidFormattedStringException;
import duke.taskmanager.exceptions.LoadDataException;
import duke.taskmanager.exceptions.SaveDataException;
import duke.taskmanager.task.DeadlineTask;
import duke.taskmanager.task.EventTask;
import duke.taskmanager.task.Task;
import duke.taskmanager.task.ToDoTask;

/**
 * TaskManager class manages a list of task and provide functions to manipulate the list.
 */
public class TaskManager {
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
     * Returns the task type of a task in the task list.
     *
     * @return task type of the task indicated by the item number
     */
    public String getTaskType(int itemNumber) {
        return this.taskList.get(itemNumber - 1).getTaskType();
    }

    /**
     * Returns a length of the task list
     *
     * @return length of the task list
     */
    public int getListSize() {
        return this.taskList.size();
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
            InvalidDeadlineException, InvalidEventException {
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
            throw new InvalidFormattedStringException();
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
     * Responds with a confirmation indicating if the marking of task is successful.
     *
     * @param itemNumber index of the task to be marked as completed
     * @return whether the marking of task is successful
     */
    public boolean markTask(int itemNumber) {
        if (!(this.taskList.get(itemNumber - 1).isCompleted())) {
            this.taskList.get(itemNumber - 1).setCompleted();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Unmarks a marked task in the task list to indicate completion. The task to be marked is provided by the user.
     * Responds with a confirmation indicating if the unmarking of task is successful.
     *
     * @param itemNumber index of the task to be marked as not completed
     * @return whether the unmarking of task is successful
     */
    public boolean unmarkTask(int itemNumber) {
        if (this.taskList.get(itemNumber - 1).isCompleted()) {
            this.taskList.get(itemNumber - 1).setNotCompleted();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a task in the task list. The task to be deleted is provided by the user.
     * Responds with the task that was deleted.
     *
     * @param itemNumber index of the task to be deleted
     * @return the deleted task
     */
    public String deleteTask(int itemNumber) {
        return this.taskList.remove(itemNumber - 1).toString();
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
     * Responds with the string of the updated task.
     *
     * @param itemNumber index of the task to be updated
     * @param arguments the updated attributes
     * @return string of the updated task
     */
    public String updateTask(int itemNumber, String... arguments) {
        this.taskList.get(itemNumber - 1).update(arguments);
        return this.taskList.get(itemNumber - 1).toString();
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
        } catch (IOException exception) {
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
