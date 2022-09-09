package wanya.task;

import wanya.WanyaException;
import wanya.ui.Ui;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;
    private final static String saveFileCorruptedMessage = "Saved data is corrupted! ";

    /**
     * Initialises an array of Task.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialises an array of Task when given a list of Strings.
     *
     * @param data encoded data from storage.
     */
    public TaskList(List<String> data) {
        this();
        assert tasks.size() == 0: "New task list must be empty!";

        for (String encodedTask : data) {
            String[] inputs = encodedTask.split("\\|");
            assert inputs.length >= 3: saveFileCorruptedMessage + "There is missing information!";

            String taskType = inputs[0];
            assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E")
                    : saveFileCorruptedMessage + "Invalid task type!";
            assert inputs[1].equals("1") || inputs[1].equals("0") : saveFileCorruptedMessage;

            boolean completed = inputs[1].equals("1");
            String description = inputs[2];
            switch(taskType) {
            case "T":
                ToDo toDo = new ToDo(description, completed);
                tasks.add(toDo);
                break;
            case "D":
                assert inputs.length == 4: saveFileCorruptedMessage + "Date required for Deadline task!";
                String dueDate = inputs[3];
                Deadline deadline = new Deadline(description, completed, dueDate);
                tasks.add(deadline);
                break;
            case "E":
                assert inputs.length == 4: saveFileCorruptedMessage + "Date required for Event task!";
                String date = inputs[3];
                Event event = new Event(description, completed, date);
                tasks.add(event);
                break;
            }
        }
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return Boolean value whether task list is empty.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Gets the task from task list with index.
     *
     * @param index Index of the task.
     * @return Task in position index of task list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a ToDo task to the TaskList.
     *
     * @param taskName name of ToDo task.
     * @return String message for adding a ToDo task.
     */
    public String addToDo(String taskName)  {
        Task newTask = new ToDo(taskName);
        return addTask(newTask);
    }

    /**
     * Adds deadline or event task to task list.
     *
     * @param command the first word of the user input.
     * @param commandDescription contains information about the task to be added.
     * @return String message whether task has been added successfully.
     * @throws WanyaException if invalid input is given with command.
     */
    public String addTaskWithDate(String command, String commandDescription) throws WanyaException {
        boolean isDeadline = command.equals("deadline");
        String[] inputs = isDeadline
                          ? commandDescription.split("/by")
                          : commandDescription.split("/at");
        //no date provided
        if (inputs.length == 1 && isDeadline) {
            throw new WanyaException("Deadline must have a due date\n" +
                    "Include '/by' and date with format " +
                    "\"yyyy-mm-dd\" at the back");
        }
        //must be event
        if (inputs.length == 1) {
            throw new WanyaException("Event must have a date\n" +
                    "Include '/at' and date with format " +
                    "\"yyyy-mm-dd\" at the back");
        }

        String taskName = inputs[0];
        String date = inputs[1].trim();
        try {
            Task newTask = isDeadline ? new Deadline(taskName, date) : new Event(taskName, date);
            return addTask(newTask);
        } catch (DateTimeException e) {
             WanyaException exception = isDeadline
                                        ? new WanyaException(Ui.showDateTimeFormat("D"))
                                        : new WanyaException(Ui.showDateTimeFormat("E"));
             throw exception;
        }
    }

    /**
     * Adds the task to the TaskList and display task added.
     *
     * @param task task to be added.
     * @return String message for adding a task.
     */
    private String addTask(Task task) {
        tasks.add(task);
        String addTaskString = "You have added:\n"
                + task
                + getSizeString();
        return addTaskString;
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index the task number to be deleted.
     * @return String message for successfully deleting a task.
     */
    public String deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        String deleteTaskString = "WWaku WWaku!!! Wanya has used her magic powers to remove this task:\n"
                + task
                + getSizeString();
        return deleteTaskString;
    }

    private String getSizeString() {
        String numTaskString = "\nNow you have " + size() + " tasks in your list.";
        return numTaskString;
    }

    /**
     * Displays the list of tasks in TaskList.
     *
     * @return List of tasks in String.
     */
    public String showTasks() {
        if (isEmpty()) {
            String emptyListString = "List is empty! Wheee slack time!";
            return emptyListString;
        }

        StringBuilder taskString = new StringBuilder();
        taskString.append("Your current list now!\n");
        for (int i = 1; i <= size(); i++) {
            Task task = tasks.get(i - 1);
            taskString.append(i + "." + task + "\n");
        }
        return taskString.toString();
    }

    /**
     * Encodes the list of tasks in TaskList.
     *
     * @return List of Strings of the tasks in TaskList to be stored.
     */
    public List<String> saveToStorage() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            Task task = tasks.get(i);
            data.add(task.toStorageString());
        }
        return data;
    }

    /**
     * Finds a list of tasks that contains the keyword and print it.
     *
     * @param keyword word to find matching tasks.
     * @return List of tasks that match keyword if any.
     */
    public String findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task: tasks) {
            String taskName = task.getTaskName();
            if (taskName.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            String noMatchTasks = "Oopsie! There are no matching tasks in your list?\n"
                    + "Maybe you have typed in wrongly..."
            return noMatchTasks;
        }

        StringBuilder matchingTasksString = new StringBuilder();
        matchingTasksString.append("Here are the matching tasks in your list:\n");
        for (Task task: matchingTasks) {
            matchingTasksString.append(task.toString() + '\n');
        }
        return matchingTasksString.toString();
    }
}
