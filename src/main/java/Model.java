import java.util.ArrayList;
import java.util.List;

public class Model {
    private static List<Task> memo = new ArrayList<>();

    public static Enum getCommand(String message) throws InvalidCommandException {
        String[] strArr = message.trim().split(" ", 2);
        Enum command = null;

        switch (strArr[0]) {
            case "bye":
                command = dukeCommands.BYE;
                break;
            case "list":
                command = dukeCommands.LIST;
                break;
            case "todo":
                command = dukeCommands.TODO;
                break;
            case "deadline":
                command = dukeCommands.DEADLINE;
                break;
            case "event":
                command = dukeCommands.EVENT;
                break;
            case "mark":
                command = dukeCommands.MARK;
                break;
            case "unmark":
                command = dukeCommands.UNMARK;
                break;
            case "delete":
                command = dukeCommands.DELETE;
                break;
            default:
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    public static String getDescription(String message) throws EmptyDescriptionException {
        String[] strArr = message.trim().split(" ", 2);
        String description = null;
        if (strArr.length == 1) {
            throw new EmptyDescriptionException("OOPS!!! The description of a command cannot be empty.");
        } else {
            description = strArr[1];
        }
        return description;
    }

    /**
     * Parse the string message to the index that is readable by memo
     *
     * @param message Integer of String type starting from "1"
     * @return
     */
    public static Integer getIndex(String message) throws InvalidIndexException {
        int index = -1;
        //TODO unsure
        if (message.matches(String.format("[1-%d]", memo.size()))) {
            index = Integer.parseInt(message);
        } else {
            //TODO To improve user experience
            throw new InvalidIndexException("OOPS!!! The index is illegal.");
        }
        return index - 1;
    }

    public static Integer getNumOfRemainingTasks() {
        return memo.size();
    }

    public static void terminate() {
        System.exit(0);
    }

    public static List<Task> list() {
        return memo;
    }

    public static Task markTask(Integer index) {
        Task task = memo.get(index);
        task.markAsDone();
        return task;
    }

    public static Task unmarkTask(Integer index) {
        Task task = memo.get(index);
        task.unMark();
        return task;
    }

    public static Task deleteTask(int index) {
        Task task = memo.get(index);
        memo.remove(index);
        return task;
    }

    public static Task addTodoTask(String description) throws EmptyDescriptionException {
        if (description == null) {
            throw new EmptyDescriptionException("OOPS!!! The description of a command cannot be empty.");
        }
        Task todoTask = new Todo(description);
        memo.add(todoTask);
        return todoTask;
    }

    //TODO improve the code
    public static Task addDeadlineTask(String description) throws EmptyDescriptionException {
        if (description == null) {
            throw new EmptyDescriptionException("OOPS!!! The description of a command cannot be empty.");
        }
        String[] splittedStr = description.split("/");
        Task deadlineTask = new Deadline(splittedStr[0], splittedStr[1].substring(3));
        memo.add(deadlineTask);
        return deadlineTask;
    }

    //TODO improve the code
    public static Task addEventTask(String description) throws EmptyDescriptionException {
        if (description == null) {
            throw new EmptyDescriptionException("OOPS!!! The description of a command cannot be empty.");
        }
        String[] splittedStr = description.split("/");
        Task eventTask = new Event(splittedStr[0], splittedStr[1].substring(3));
        memo.add(eventTask);
        return eventTask;
    }
}
