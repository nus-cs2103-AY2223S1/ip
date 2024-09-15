package raiden.task;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import raiden.RaidenException;
import raiden.Storage;

/**
 * Represents a list of Tasks (ToDo, Deadlines, Events) given by the user.
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Checks if the TaskList is empty or not.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    /**
     * Adds Task (ToDo, Deadline, Event) to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully adding a task.
     * @throws RaidenException if the add command is invalid.
     */
    public String addTask(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        String taskType = command[0];
        assert taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")
                : "Add command must be valid, ie. either todo, deadline or event.";
        if (taskType.equals("todo")) {
            return addToDo(command, storage);
        } else if (taskType.equals("deadline")) {
            return addDeadline(command, storage);
        } else if (taskType.equals("event")) {
            return addEvent(command, storage);
        } else {
            return "";
        }
    }

    /**
     * Adds To-do task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully adding a to-do task.
     * @throws RaidenException if the todo command is invalid.
     */
    public String addToDo(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please specify what task you wish to do:\n"
                    + "todo <description>");
        }
        assert command[0].equals("todo") : "command must be todo";
        assert command.length == 2 : "command must consist of 'todo' and task description only.";
        StringBuilder result = new StringBuilder();
        result.append("Got it. Raiden has added this task:\n");
        String description = command[1];
        Task newTask = new ToDo(description);
        result.append(newTask);
        this.taskList.add(newTask);
        int len = this.taskList.size();
        String line = String.format("\nNow you have %d task%s in the list.",
                len, len != 1 ? "s" : "");
        result.append(line);
        // Add task to the save file
        storage.addTaskToSave(newTask);
        return result.toString();
    }

    /**
     * Adds Deadline task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully adding a deadline.
     * @throws RaidenException if the deadline command is invalid.
     */
    public String addDeadline(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please specify what task you wish to do:\n"
                    + "deadline <description> /by <date/time>");
        }
        assert command[0].equals("deadline") : "command must be deadline";
        String[] deadline = command[1].split(" /by ", 2);
        if (deadline.length == 1) {
            throw new RaidenException("Please specify the description and the date/time of this deadline:\n"
                    + "deadline <description> /by <date/time>");
        }
        assert deadline.length == 2 : "command must consist of 'deadline', task description and date/time.";
        try {
            StringBuilder result = new StringBuilder();
            result.append("Got it. Raiden has added this task:\n");
            Task newTask = new Deadline(deadline[0], deadline[1]);
            result.append(newTask);
            this.taskList.add(newTask);
            int len = this.taskList.size();
            String line = String.format("\nNow you have %d task%s in the list.",
                    len, len != 1 ? "s" : "");
            result.append(line);
            // Add task to the save file
            storage.addTaskToSave(newTask);
            return result.toString();
        } catch (DateTimeException e) {
            throw new RaidenException("Please enter your date/time in this format:\n"
                    + "DD MM YYYY HH:mm(optional)");
        }
    }

    /**
     * Adds Event task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully adding an event.
     * @throws RaidenException if the event command is invalid.
     */
    public String addEvent(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please specify what task you wish to do:\n"
                    + "event <description> /at <date/time>");
        }
        assert command[0].equals("event") : "command must be event";
        String[] event = command[1].split(" /at ", 2);
        if (event.length == 1) {
            throw new RaidenException("Please specify the description and the date/time of this event:\n"
                    + "event <description> /at <date/time>");
        }
        assert event.length == 2 : "command must consist of 'event', task description and date/time.";
        try {
            StringBuilder result = new StringBuilder();
            result.append("Got it. Raiden has added this task:\n");
            Task newTask = new Event(event[0], event[1]);
            result.append(newTask);
            this.taskList.add(newTask);
            int len = this.taskList.size();
            String line = String.format("\nNow you have %d task%s in the list.",
                    len, len != 1 ? "s" : "");
            result.append(line);
            // Add task to the save file
            storage.addTaskToSave(newTask);
            return result.toString();
        } catch (DateTimeException e) {
            throw new RaidenException("Please enter your date/time in this format:\n"
                    + "DD MM YYYY HH:mm(optional)");
        }
    }

    /**
     * Prints out the list of the history of tasks
     *
     * @return The String message for successfully listing all the tasks.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");
        if (isEmpty()) {
            return result + "*No tasks! ^_^*";
        }
        assert this.taskList.size() > 0 : "TaskList should not be empty here.";
        for (int i = 0; i < taskList.size(); i++) {
            String line = String.format("%d. %s\n", i + 1, this.taskList.get(i));
            result.append(line);
        }
        return result.toString();
    }

    /**
     * Marks the task of the given id as done.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully marking a task.
     * @throws RaidenException if the mark command is invalid.
     */
    public String markTask(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please specify the task to mark by its id:\n"
                    + "mark <id>");
        }
        assert command[0].equals("mark") : "command must be mark";
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.taskList.size();
            if (id <= 0 || id > len) {
                throw new RaidenException("Invalid task id!");
            }
            // Mark the task in the save file
            storage.markTaskInSave(id - 1);
            return this.taskList.get(id - 1).mark();
        } catch (NumberFormatException e) {
            throw new RaidenException("Please specify the task to mark by its integer id:\n"
                    + "mark <id>");
        } catch (RaidenException e) {
            throw e;
        }
    }

    /**
     * Marks the task of the given id as not done.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully unmarking a task.
     * @throws RaidenException if the unmark command is invalid.
     */
    public String unmarkTask(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please specify the task to unmark by its id:\n"
                    + "unmark <id>");
        }
        assert command[0].equals("unmark") : "command must be unmark";
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.taskList.size();
            if (id <= 0 || id > len) {
                throw new RaidenException("Invalid task id!");
            }
            // Unmark the task in the save file
            storage.unmarkTaskInSave(id - 1);
            return this.taskList.get(id - 1).unmark();
        } catch (NumberFormatException e) {
            throw new RaidenException("Please specify the task to unmark by its integer id:\n"
                    + "mark <id>");
        }
    }

    /**
     * Deletes the task of the given id from the list.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully deleting a task.
     * @throws RaidenException if the delete command is invalid.
     */
    public String deleteTask(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please specify the task to be deleted by its id:\n"
                    + "delete <id>");
        }
        assert command[0].equals("delete") : "command must be delete";
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.taskList.size();
            if (len == 0) {
                throw new RaidenException("Your task list is empty!");
            }
            if (id <= 0 || id > len) {
                throw new RaidenException("Invalid task id!");
            }
            StringBuilder result = new StringBuilder();
            result.append("Understood. I've removed this task:\n");
            result.append(this.taskList.get(id - 1));
            this.taskList.remove(id - 1);
            String line = String.format("\nNow you have %d task%s in the list.",
                    len - 1, len - 1 != 1 ? "s" : "");
            result.append(line);
            // Remove the task from the save file
            storage.deleteTaskFromSave(id - 1);
            return result.toString();
        } catch (NumberFormatException e) {
            throw new RaidenException("Please specify the task to delete by its integer id:\n"
                    + "mark <id>");
        }
    }

    /**
     * Converts the tasks in the current tasks list to StringBuilder
     *
     * @return The StringBuilder of all the tasks in the list.
     */
    public StringBuilder toStringBuilder() {
        StringBuilder stringBuilder = this.taskList.stream()
                .map(s -> s.toCommand() + "\n")
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);
        return stringBuilder;
    }

    /**
     * Loads the given task into the task list.
     *
     * @param line The String representing the task from the save file.
     */
    public void loadFromSave(String line) {
        String[] command = line.split(" \\| ", 3);
        assert command.length == 3 : "command from save file is not valid.";
        String taskType = command[0];
        String completionStatus = command[1];
        String details = command[2];
        switch (taskType) {
        case "T":
            Task todo = new ToDo(details);
            this.taskList.add(todo);
            if (completionStatus.equals("1")) {
                todo.completeTask();
            }
            break;
        case "D":
            String[] deadlineDate = details.split(" /by ", 2);
            Task deadline = new Deadline(deadlineDate[0], deadlineDate[1]);
            this.taskList.add(deadline);
            if (completionStatus.equals("1")) {
                deadline.completeTask();
            }
            break;
        case "E":
            String[] eventDate = details.split(" /at ", 2);
            Task event = new Event(eventDate[0], eventDate[1]);
            this.taskList.add(event);
            if (completionStatus.equals("1")) {
                event.completeTask();
            }
            break;
        default:
            break;
        }
    }

    /**
     * Searches for and lists tasks that contain the keyword in their description.
     *
     * @param command The command represented by an array of Strings.
     * @return The String message for successfully finding a task.
     * @throws RaidenException if the find command is invalid.
     */
    public String findTask(String[] command) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please provide a keyword to search for the task:\n"
                    + "find <keyword>");
        }
        assert command[0].equals("find") : "command must be find.";
        String keyword = command[1];
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.hasKeyword(keyword)) {
                count++;
                String line = String.format("%d. %s\n", count, task);
                result.append(line);
            }
        }
        if (count > 0) {
            return "Here are the matching tasks in your list:\n" + result.toString();
        } else {
            return "Hm...I found no matching task in your list.";
        }
    }

    /**
     * Edits the specified task's description or date/time.
     *
     * @param command The command represented by an array of Strings.
     * @return The String message for successfully editing a task.
     * @throws RaidenException if the edit command is invalid.
     */
    public String editTask(String[] command, Storage storage) throws RaidenException {
        assert command.length > 0 : "String array command must not be empty.";
        if (command.length == 1) {
            throw new RaidenException("Please specify the task to be edited:\n"
                    + "To edit the description: `editD <id> <new_description>`\n"
                    + "To edit the time: `editT <id> <new_date/time>");
        }
        assert command[0].equals("editT") || command[0].equals("editD") : "command must be editT or editD";
        if (command[0].equals("editD")) {
            // Edit the description of the task
            String[] editDescriptionCommand = command[1].split(" ", 2);
            return editTaskDescription(editDescriptionCommand, storage);
        } else if (command[0].equals("editT")) {
            // Edit the date/time of the task (only deadline/event)
            String[] editTimeCommand = command[1].split(" ", 2);
            return editTaskTime(editTimeCommand, storage);
        } else {
            return "";
        }
    }

    /**
     * Edits the specified task's description.
     *
     * @param command The command represented by an array of Strings.
     * @return The String message for successfully editing a task.
     * @throws RaidenException if the edit command is invalid.
     */
    public String editTaskDescription(String[] command, Storage storage) throws RaidenException {
        if (command.length != 2) {
            throw new RaidenException("Please specify the new description for this task!");
        }
        String idString = command[0];
        String newDescription = command[1];
        try {
            int id = Integer.parseInt(idString);
            int len = this.taskList.size();
            if (id <= 0 || id > len) {
                throw new RaidenException("Invalid task id!");
            }
            String result = this.taskList.get(id - 1).changeDescription(newDescription);
            // Update the task in the save file
            storage.editTaskInSave(id - 1, this.taskList.get(id - 1));
            return result;
        } catch (NumberFormatException e) {
            throw new RaidenException("Please specify the task to edit by its integer id:\n"
                    + "editT <id> <new_description");
        } catch (RaidenException e) {
            throw e;
        }
    }

    /**
     * Edits the specified task's date/time.
     *
     * @param command The command represented by an array of Strings.
     * @return The String message for successfully editing a task.
     * @throws RaidenException if the edit command is invalid.
     */
    public String editTaskTime(String[] command, Storage storage) throws RaidenException {
        if (command.length != 2) {
            throw new RaidenException("Please specify the new date/time for this task!");
        }
        String idString = command[0];
        String newDate = command[1];
        try {
            int id = Integer.parseInt(idString);
            int len = this.taskList.size();
            if (id <= 0 || id > len) {
                throw new RaidenException("Invalid task id!");
            }
            String result = this.taskList.get(id - 1).changeDate(newDate);
            // Update the task in the save file
            storage.editTaskInSave(id - 1, this.taskList.get(id - 1));
            return result;
        } catch (NumberFormatException e) {
            throw new RaidenException("Please specify the task to edit by its integer id:\n"
                    + "editT <id> <new_description");
        } catch (DateTimeException e) {
            throw new RaidenException("Please enter your date/time in this format:\n"
                    + "DD MM YYYY HH:mm(optional)");
        } catch (RaidenException e) {
            throw e;
        }
    }
}
