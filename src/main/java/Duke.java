import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static boolean isEnd = false;

    private static final String UI_LINE_SPACING = "----------------------------------------\n";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke  \n" + "What can I do for you?\n";
        Scanner userInput = new Scanner(System.in);
        chat(greeting);
            while (!isEnd) {
                try {
                    String[] parsedUserInput = parseCommand(userInput.nextLine());
                    String command = parsedUserInput[0];
                    String arg1 = parsedUserInput[1];
                    String arg2 = parsedUserInput[2];
                    switch(command) {
                    case COMMAND_LIST:
                        chat("Here are the tasks in your list: \n" + list());
                        break;
                    case COMMAND_BYE:
                        chat("Bye! Hope to see you again!\n");
                        isEnd = true;
                        userInput.close();
                        break;
                    case COMMAND_TODO:
                        if (arg1.equals("")) {
                            error("ToDo task requires a description");
                            break;
                        }
                        ToDo toDoToAdd = new ToDo(arg1);
                        tasks.add(toDoToAdd);
                        chat("Got it, I've added this task:\n " + toDoToAdd + "\n" + outputNumOfTasks());
                        break;
                    case COMMAND_DEADLINE:            
                        Deadline deadlineToAdd = new Deadline(arg1, arg2);
                        tasks.add(deadlineToAdd);
                        chat("Got it, I've added this task:\n " + deadlineToAdd + "\n" + outputNumOfTasks());
                        break;
                    case COMMAND_EVENT:          
                        Event eventToAdd = new Event(arg1, arg2);
                        tasks.add(eventToAdd);
                        chat("Got it, I've added this task:\n " + eventToAdd + "\n" + outputNumOfTasks());
                        break;
                    case COMMAND_MARK:
                        mark(Integer.parseInt(arg1));
                        break;
                    case COMMAND_UNMARK:
                        unmark(Integer.parseInt(arg1));
                        break;
                    case COMMAND_DELETE:
                        delete(Integer.parseInt(arg1));
                        break;
                    default:
                        error("No or invalid command given, are you just looking to chat? :D");
                        break;
                    }
                } catch (MissingArgumentException e) {
                    chat(e.getLocalizedMessage() + "\n");
                } catch (InvalidCommandException e) {
                    chat(e.getLocalizedMessage() + "\n");
                }
            }
        }

    public static String[] parseCommand(String userCommand) throws InvalidCommandException {
        String[] parsedCommand = {"","",""};
        String splitUserStatement[] = userCommand.split(" ", 2);
        if (!isValidCommand(splitUserStatement[0])) {
            throw new InvalidCommandException("Thats not an available command.");
        }
        String command = splitUserStatement[0];
        parsedCommand[0] = command;
        String userArgs = "";
        if (splitUserStatement.length > 1) {
            userArgs = splitUserStatement[1];
        }
        String[] splitUserArgs = {"",""};
        if (command.equals(COMMAND_DEADLINE)) {
            splitUserArgs = userArgs.split("/by", 2);
        }
        else if (command.equals(COMMAND_EVENT)) {
            splitUserArgs = userArgs.split("/at", 2);
        } else {
            splitUserArgs = userArgs.split("/", 2);
        }
        parsedCommand[1] = splitUserArgs[0];
        if (splitUserArgs.length > 1) {
            parsedCommand[2] = splitUserArgs[1];
        }
        return parsedCommand;

    }

    public static boolean isValidCommand(String command) {
        switch(command) { //no breaks as all cases lead to return
            case COMMAND_LIST:
                return true;
            case COMMAND_BYE:
                return true;
            case COMMAND_TODO:
                return true;
            case COMMAND_DEADLINE:            
                return true;
            case COMMAND_EVENT:          
                return true;
            case COMMAND_MARK:
                return true;
            case COMMAND_UNMARK:
                return true;
            case COMMAND_DELETE:
                return true;
            default:
                return false;
            }
    }

    public static String list() {
        String output = "";
        int count = 1;
        if (tasks.size() == 0) {
            return "No tasks! Yay!";
        }
        for (Task task : tasks) {
            output += String.valueOf(count) + ". " + task + "\n";
            count += 1;
        }
        return output;
    }

    public static void mark(int taskNum) {
        Task targetTask = tasks.get(taskNum-1);
        targetTask.mark();
        chat("Nice! I've marked this task as done:\n" + targetTask + "\n");
    }

    public static void delete(int taskNum) {
        Task removedTask = tasks.get(taskNum-1);
        tasks.remove(taskNum-1);
        chat(String.format("Noted. I've removed this task:\n %s Now you have %d tasks in the list.\n", removedTask, tasks.size()));
    }

    public static void unmark(int taskNum) {
        Task targetTask = tasks.get(taskNum-1);
        targetTask.unmark();
        chat("OK, I've marked this task as not done yet:\n" + targetTask + "\n");
    }

    public static String outputNumOfTasks() {
        return String.format("Now you have %d tasks in the list \n", tasks.size());
    }

    public static void chat(String message) {
        System.out.println(UI_LINE_SPACING + message + UI_LINE_SPACING);
    }

    public static void error(String message) {
        chat(":( OOPS: " + message + "\n");
    }

    public static void end() {
        chat("Bye! Hope to see you again!");
        isEnd = true;
    }
}
