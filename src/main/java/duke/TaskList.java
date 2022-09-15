package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList class to model list of Tasks and relevant operations.
 */
public class TaskList {
    /**
     * TaskEnum includes all the possible types of Task instance.
     */
    public enum TaskEnum {
        Todo,
        Deadline,
        Event
    }
    static final String INVALID_COMMAND_MESSAGE = "Invalid Command";
    private ArrayList<Task> taskArrayList;


    /**
     * Constructs an instance of TaskList and initializes a new array list.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Gets the array list stored in this instance.
     *
     * @return an array list of tasks
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    // Todo: include more support for date string
    private static String parseDateTimeString(String arg) {
        return arg;
    }

    private static LocalDate parseString2LocalDate(String arg) {
        // current supported format: "2009-10-12"
        String dateString = parseDateTimeString(arg);
        return LocalDate.parse(dateString);
    }

    private void setIsDoneAndAddTask(Task task, boolean isDone) {
        task.setDone(isDone);
        this.addTask(task);
    }

    /**
     * Reads pre-created records from the given line from the file.
     *
     * @param line string of pre-created record
     */
    public void readPreCreatedTask(String line) {
        String separator = " \\| ";
        Task curr;
        boolean isDone;
        LocalDate localDate;
        String description;
        isDone = !(line.charAt(4) == '0');
        switch (line.substring(0, 1)) {
        case "T":
            // example: T | 0 | sleep1
            description = line.split(separator, 3)[2];
            curr = new Todo(description);
            setIsDoneAndAddTask(curr, isDone);
            break;
        case "E":
            // example: E | 0 | sleep2 | 2019-10-10
            description = line.split(separator, 4)[2];
            localDate = LocalDate.parse(line.split(separator, 4)[3]);
            curr = new Event(description, localDate);
            setIsDoneAndAddTask(curr, isDone);
            break;
        case "D":
            //example: D | 1 | sleep3 | 2019-10-10
            description = line.split(separator, 4)[2];
            localDate = LocalDate.parse(line.split(separator, 4)[3]);
            curr = new Deadline(description, localDate);
            setIsDoneAndAddTask(curr, isDone);
            break;
        default:
            // unrecognized pre-created task
        }
    }

    private String createEvent(TaskEnum taskEnum, String command) throws DukeException {
        String[] args;
        Task taskToCreate;
        String separator = "/";
        String invalidInputMessage = "Invalid Input";
        String outputMessage = "Got it. I've added this task:" + System.lineSeparator();
        String emptyTodoDescMessage = "Empty description for Todo task";

        switch (taskEnum) {
        case Todo:
            // example: borrow book
            if (command.equals("")) {
                throw new DukeException(emptyTodoDescMessage);
            }
            taskToCreate = new Todo(command);
            break;

        case Deadline:
            // example: return book /by Sunday
            args = command.split(separator, 2);
            if (args.length != 2) {
                throw new DukeException(invalidInputMessage);
            }
            taskToCreate = new Deadline(args[0].trim(), TaskList.parseString2LocalDate(args[1].substring(3)));
            break;

        case Event:
            // example: project meeting /at Mon 2-4pm
            args = command.split(separator, 2);
            if (args.length != 2) {
                throw new DukeException(invalidInputMessage);
            }
            taskToCreate = new Event(args[0].trim(), TaskList.parseString2LocalDate(args[1].substring(3)));
            break;

        default:
            throw new DukeException(invalidInputMessage);
        }
        this.addTask(taskToCreate);
        return Ui.formatPrint(outputMessage + taskToCreate);
    }

    private String handleDelete(String indexStr) throws DukeException {
        String[] outputMessage = new String[]{
            "Noted. I've removed this task:" + System.lineSeparator(),
            System.lineSeparator() + "Now you have ",
            " tasks in the list."
        };
        int index;
        try {
            index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_COMMAND_MESSAGE);
        }
        Task curr = this.taskArrayList.get(index);
        this.remove(index);
        return Ui.formatPrint(outputMessage[0] + curr.toString()
                + outputMessage[1] + this.taskArrayList.size() + outputMessage[2]);
    }

    private void addTask(Task t) {
        this.taskArrayList.add(t);
    }

    private void remove(int index) {
        ArrayList<Task> arr = this.taskArrayList;
        ArrayList<Task> newArr = new ArrayList<>();
        if (index < 0 || index > arr.size()) {
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            if (i == index) {
                continue;
            }
            newArr.add(arr.get(i));
        }
        this.taskArrayList = newArr;
    }

    private String handleMarkDoneUndone(String[] command) throws DukeException {
        String[] actions = new String[] {"mark", "unmark"};
        int index = Integer.parseInt(command[1]) - 1;
        if (index < 0 || index > this.taskArrayList.size() - 1) {
            throw new DukeException(INVALID_COMMAND_MESSAGE);
        }
        Task curr = this.taskArrayList.get(index);
        if (command[0].equals(actions[0])) { // mark
            if (!curr.isDone) {
                curr.isDone = true;
            }
            return Ui.taskStateChangePrint(curr, true);
        } else if (command[0].equals(actions[1])) { // unmark
            if (curr.isDone) {
                curr.isDone = false;
            }
            return Ui.taskStateChangePrint(curr, false);
        } else {
            throw new DukeException(INVALID_COMMAND_MESSAGE);
        }
    }

    private TaskList findSimilarItems(String arg) {
        TaskList res = new TaskList();
        for (Task t : this.taskArrayList) {
            if (t.description.contains(arg)) {
                res.addTask(t);
            }
        }
        return res;
    }

    /**
     * Parses instructions from the given string
     * @param args string of command in the relevant formats
     * @throws DukeException the exception containing the error message when encountered error
     */
    protected String parseInstructions(String args) throws DukeException {
        String[] arguments = args.split(" ", 2);
        try {
            switch (arguments[0]) {
            case "mark":
                // fall through
            case "unmark":
                return this.handleMarkDoneUndone(arguments);
            case "delete":
                return this.handleDelete(arguments[1]);
            case "deadline":
                return this.createEvent(TaskEnum.Deadline, arguments[1]);
            case "todo":
                return this.createEvent(TaskEnum.Todo, arguments[1]);
            case "event":
                return this.createEvent(TaskEnum.Event, arguments[1]);
            case "find":
                TaskList res = this.findSimilarItems(arguments[1]);
                if (res.taskArrayList.size() >= 1) {
                    return Ui.listPrint(res);
                } else {
                    return Ui.processUnfoundResult();
                }
            default:
                throw new DukeException(INVALID_COMMAND_MESSAGE);
            }
        } catch (DukeException e) {
            throw new DukeException(INVALID_COMMAND_MESSAGE);
        }
    }
}
