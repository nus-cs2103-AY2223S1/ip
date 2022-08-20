import java.util.ArrayList;

/**
 * Represents a Class that manages tasks.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class TaskManager {

    /**
     * Represents an array list of tasks.
     */
    private final ArrayList<Task> arr;

    /**
     * Represents an indentation for replies.
     */
    private static final String INDENTATION = "     ";

    /**
     * Represents an extra indentation for replies.
     */
    private static final String EXTRA_INDENTATION = "  ";

    /**
     * Constructs a formatted reply message.
     *
     * @param message message to be used
     * @return string of well-formatted message
     */
    public static String reply(String message) {
        return "    ____________________________________________________________\n" +
                INDENTATION + message + "\n" +
                "    ____________________________________________________________";
    }

    /**
     * Represents the different action types the manager can handle
     */
    enum Action_type {
        DEADLINE,
        DELETE,
        EVENT,
        LIST,
        MARK,
        TODO,
        UNMARK,
    }

    /**
     * Constructor for task manager
     *
     * @param size size of array
     */
    public TaskManager(int size) {
        this.arr = new ArrayList<>(size);
    }

    /**
     * Crafts a list of tasks.
     *
     * @return String describing the list
     */
    private String craftList() {
        int length = arr.size();
        String result = "Here are the task(s) in your list:";
        for (int x = 0; x < length; x++) {
            Task task = arr.get(x);
            if (task == null) {
                break;
            } else {
                result += "\n" + INDENTATION + (x + 1) + "." + task;
            }
        }
        return result;
    }

    /**
     * Crafts a sentence about the marking and un-marking of tasks.
     *
     * @param task Task being mentioned
     * @param bool boolean describing whether the task is completed
     * @return String representation of message
     */
    private String craftMark(Task task, boolean bool) {
        if (bool) {
            return "Nice! I've marked this task as done:\n" +
                    INDENTATION + EXTRA_INDENTATION + task.toString();
        } else {
            return "OK, I've marked this task as not done yet:\n" +
                    INDENTATION + EXTRA_INDENTATION + task.toString();
        }
    }

    /**
     * Crafts a message about the tasks found in Task.java.
     *
     * @param task Task being described
     * @return String representation of message
     */
    private String craftTask(Task task) {
        return "Got it. I've added this task:\n" +
                INDENTATION + EXTRA_INDENTATION + task + "\n" +
                INDENTATION + "Now you have " + arr.size() + (arr.size() < 2 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Crafts a message about the deletion of tasks.
     *
     * @param task Task being described
     * @return String representation of message
     */
    private String craftDelete(Task task) {
        return "Noted. I've removed the task:\n" +
                INDENTATION + EXTRA_INDENTATION + task.toString() + "\n" +
                INDENTATION + "Now you have " + arr.size() + (arr.size() < 2 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Crafts a message about the tasks found in Task.java.
     *
     * @param message message being sent to the task manager
     * @return String representation of reply to message
     */
    public String errorCheckerThanHandle(String message) throws Exception {
        String[] splitResponse = message.split(" ");
        String keyword = splitResponse[0];
        if (splitResponse.length == 1) {
            if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event") ||
                    keyword.equals("delete") || keyword.equals("mark") || keyword.equals("unmark")) {
                throw new DukeException(keyword);
            } else if (keyword.equals("list")) {
                return handle(Action_type.LIST, null);
            } else {
                throw new DukeException("unknown");
            }
        } else {
            switch (keyword) {
                case "todo":
                    return handle(Action_type.TODO, message.substring(5));
                case "deadline": {
                    String[] tempSplit = message.split(" /by ");
                    if (tempSplit.length == 1) {
                        throw new DukeException("deadline format");
                    } else {
                        return handle(Action_type.DEADLINE, message.substring(9));
                    }
                }
                case "event": {
                    String[] tempSplit = message.split(" /at ");
                    if (tempSplit.length == 1) {
                        throw new DukeException("event format");
                    } else {
                        return handle(Action_type.EVENT, message.substring(6));
                    }
                }
                case "delete":
                case "mark":
                case "unmark":
                    try {
                        switch (keyword) {
                            case "delete":
                                return handle(Action_type.DELETE, splitResponse[1]);
                            case "mark":
                                return handle(Action_type.MARK, splitResponse[1]);
                            case "unmark":
                                return handle(Action_type.UNMARK, splitResponse[1]);
                            default:
                                throw new DukeException("unknown");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("non integer input when deleting");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("index out of bounds");
                    }
                default:
                    throw new DukeException("unknown");
            }
        }
    }

    /**
     * Handles the action being described.
     *
     * @param action  action to be carries out
     * @param message additional details for the message
     * @return String representation of reply
     */
    public String handle(Action_type action, String message) {
        if (action == Action_type.DEADLINE) {
            Task newTask = Task.of(Task.Task_type.DEADLINE, message);
            arr.add(newTask);
            return craftTask(newTask);
        } else if (action == Action_type.DELETE) {
            int location = Integer.parseInt(message) - 1;
            Task task = arr.get(location);
            arr.remove(location);
            return craftDelete(task);
        } else if (action == Action_type.EVENT) {
            Task newTask = Task.of(Task.Task_type.EVENT, message);
            arr.add(newTask);
            return craftTask(newTask);
        } else if (action == Action_type.LIST) {
            return craftList();
        } else if (action == Action_type.MARK) {
//            try {
            int location = Integer.parseInt(message) - 1;
            Task task = arr.get(location);
            task.markComplete();
            return craftMark(task, true);
        } else if (action == Action_type.TODO) {
            Task newTask = Task.of(Task.Task_type.TODO, message);
            arr.add(newTask);
            return craftTask(newTask);
        } else if (action == Action_type.UNMARK) {
            int location = Integer.parseInt(message) - 1;
            Task task = arr.get(location);
            task.markIncomplete();
            return craftMark(task, false);
        } else {
            return null;
        }
    }
}
