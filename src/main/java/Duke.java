import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * The main class to run Duke.
 */
public class Duke {
    /** Boolean to check if code should stop */
    private static boolean done;

    /** Scanner Object */
    private static final Scanner echo = new Scanner(System.in);
    /** To check if the string is an intger */
    private static Pattern checkString = Pattern.compile("-?\\d+");

    enum Update {
        MARK,
        UNMARK
    }

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /** Store list */
    private static ArrayList<Task> storage = new ArrayList<Task>();

    /**
     * To check if the String is an integer.
     *
     * @param strNum  String for comparison
     * @return True if it's an integer, otherwise, flase
     */
    private static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        return checkString.matcher(strNum).matches();
    }

    /**
     * Add task to list
     *
     * @param task
     */
    private static void addTask(Task task) {
        storage.add(task);
        System.out.println("-----------------------------------------------");
        System.out.println("added: " + task.getDescription());
        System.out.println("-----------------------------------------------");
    }

    /**
     * mark task with an X
     *
     * @param task
     */
    private static void markString(Task task) {
        task.mark();
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Unmark task by removing the X.
     *
     * @param task
     */
    private static void unMarkString(Task task) {
        task.unMark();
        System.out.println("-----------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("-----------------------------------------------");
    }

    /**
     * The number in the list to be mark or unmark.
     *
     * @param update   To mark or unmark.
     * @param parts    The array that contains the action and the number to determine which task
     * @throws DukeException
     */
    private static void updateTask(Update update, String[] parts ) throws DukeException {
        String part2;
        switch (update){
            case MARK:
                /** when there is no number declared */
                if(parts.length <= 1) {
                    throw new DukeException("Please tell me what to mark!");
                }
                part2 = parts[1];
                if (isInteger(part2)) {
                    int number = Integer.parseInt(part2);
                    /** To check if the integer is valid */
                    if(storage.size() < number || number <= 0) {
                        throw new DukeException("There's no such task to mark!");
                    } else {
                        markString(storage.get(number - 1));
                    }
                } else {
                    throw new DukeException("I don't know which to mark!");
                }
                break;
            case UNMARK:
                /** when there is no number declared */
                if(parts.length <= 1) {
                    throw new DukeException("Please tell me what to unmark!");
                }
                part2 = parts[1];
                if (isInteger(part2)) {
                    int number = Integer.parseInt(part2);
                    /** To check if the integer is valid */
                    if(storage.size() < number || number <= 0) {
                        throw new DukeException("There's no such task to unmark!");
                    } else {
                        unMarkString(storage.get(number - 1));
                    }
                } else {
                    throw new DukeException("I don't know which to unmark!");
                }
                break;
        }
    }

    /**
     * Add task to the list.
     *
     * @param task
     */
    private static void addDetailedTask(Task task) {
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + storage.size() +" tasks in the list.");
        System.out.println("-----------------------------------------------");
    }

    /**
     * add the right type of task to the list.
     * @param type     Type of task.
     * @param parts    The array that contains the action and the name of the task.
     * @throws DukeException
     */
    private static void addTaskType(Type type, String[] parts) throws DukeException{
        String part2;
        switch (type) {
            case DEADLINE:
                /** no task declared */
                if(parts.length <= 1) {
                    throw new DukeException("There's no deadline task!");
                }
                part2 = parts[1];
                String[] deadlineParts =  part2.split("/by ", 2);
                /** no declaration of deadline time */
                if (deadlineParts.length <= 1) {
                    throw new DukeException("You didn't specify the deadline! Please use /by.");
                }
                DeadlineTask deadline = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
                storage.add(deadline);
                addDetailedTask(deadline);
                break;
            case TODO:
                /** no task declared */
                if(parts.length <= 1) {
                    throw new DukeException("There's no todo task!");
                }
                part2 = parts[1];
                TodoTask todo = new TodoTask(part2);
                storage.add(todo);
                addDetailedTask(todo);
                break;
            case EVENT:
                /** no task declared */
                if(parts.length <= 1) {
                    throw new DukeException("There's no event task!");
                }
                part2 = parts[1];
                String[] eventParts = part2.split("/at ", 2);
                /** no declaration of event time */
                if (eventParts.length <= 1) {
                    throw new DukeException("You didn't specify the event time! Please use /at.");
                }
                EventTask event = new EventTask(eventParts[0], eventParts[1]);
                storage.add(event);
                addDetailedTask(event);
                break;
            default:
                Task task = new Task(parts[0]);
                addTask(task);
        }
    }

    /**
     * Delete task from the list
     * @param number   The number in the list to be removed.
     */
    private static void deleteTask(int number) {
        Task temp = storage.get(number - 1);
        storage.remove(number - 1);
        System.out.println("-----------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + storage.size() +" tasks in the list.");
        System.out.println("-----------------------------------------------");
    }

    /**
     * The reply after taking in an input.
     *
     * @param response         The input.
     * @throws DukeException
     */
    private static void reply(String response) throws DukeException {
        String[] parts = response.split(" ", 2);
        /** the action */
        String part1 = parts[0];
        String part2;
        switch (part1) {
            case "bye":
                System.out.println("-----------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------------------");
                storage.clear();
                echo.close();
                done = true;
                break;
            case "list":
                if (storage.size() == 0) {
                    throw new DukeException("there's nothing!");
                }
                System.out.println("-----------------------------------------------");
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println((i + 1) + "." + storage.get(i).toString());
                }
                System.out.println("-----------------------------------------------");
                break;
            case "mark":
                updateTask(Update.MARK, parts);
                break;
            case "unmark":
                updateTask(Update.UNMARK, parts);
                break;
            case "deadline":
                addTaskType(Type.DEADLINE, parts);
                break;
            case "todo":
                addTaskType(Type.TODO, parts);
                break;
            case "event":
                addTaskType(Type.EVENT, parts);
                break;
            case "delete":
                /** no task declared */
                if(parts.length <= 1) {
                    throw new DukeException("Please tell me what to delete!");
                }
                part2 = parts[1];
                if (isInteger(part2)) {
                    int number = Integer.parseInt(part2);
                    if(storage.size() < number || number <= 0) {
                        throw new DukeException("There's no such task to delete!");
                    } else {
                        deleteTask(number);
                    }
                } else {
                    throw new DukeException("I don't know which to delete!");
                }
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        String response;
        done = false;
        while (!done) {
            try {
                response = echo.nextLine();
                reply(response);
            } catch (DukeException e) {
                System.out.println("-----------------------------------------------");
                System.out.println(e);
                System.out.println("-----------------------------------------------");
            }
        }
    }
}
