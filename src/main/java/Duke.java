import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static boolean isEnd = false;

    private static final String UI_LINE_SPACING = "----------------------------------------\n";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke  \n" + "What can I do for you?\n";
        Scanner userInput = new Scanner(System.in);
        System.out.println(UI_LINE_SPACING + greeting + UI_LINE_SPACING);
        while (!isEnd) {
            String userStatement = userInput.nextLine();
            String splitUserStatement[] = userStatement.split(" ", 2);
            String command = splitUserStatement[0];
            String userArgs = "";
            if (splitUserStatement.length > 1) {
                userArgs = splitUserStatement[1];
            }
            switch(command) {
            case COMMAND_LIST:
                System.out.println(chat("Here are the tasks in your list: /n" + list()));
                break;
            case COMMAND_BYE:
                System.out.println(chat("Bye! Hope to see you again!"));
                isEnd = true;
                break;
            case COMMAND_TODO:
                ToDo taskToAdd = new ToDo(command);
                tasks.add(taskToAdd);
                System.out.println(chat("Got it, I've added this task:\n " + taskToAdd + "\n" + outputNumOfTasks()));
                break;
            case COMMAND_MARK:
                mark(Integer.parseInt(userArgs));
                break;
            case COMMAND_UNMARK:
                unmark(Integer.parseInt(userArgs));
                break;
            default:
                System.out.println(chat("Not a command, just wanna chat?"));
            }
        }
    }

    public static String list() {
        String output = "";
        int count = 1;
        for (Task task : tasks) {
            output += String.valueOf(count) + ". " + task + "\n";
            count += 1;
        }
        return output;
    }

    public static void mark(int taskNum) {
        Task targetTask = tasks.get(taskNum-1);
        targetTask.mark();
        System.out.println(UI_LINE_SPACING + "Nice! I've marked this task as done:\n" + targetTask + "\n" + UI_LINE_SPACING);

    }

    public static void unmark(int taskNum) {
        Task targetTask = tasks.get(taskNum-1);
        targetTask.unmark();
        System.out.println(UI_LINE_SPACING + "OK, I've marked this task as not done yet:\n" + targetTask + "\n" + UI_LINE_SPACING);
    }

    public static String outputNumOfTasks() {
        return String.format("Now you have %d tasks in the list \n", tasks.size());
    }

    public static String chat(String message) {
        return UI_LINE_SPACING + message + UI_LINE_SPACING;
    }

    public static void end() {
        System.out.println(UI_LINE_SPACING + "Bye! Hope to see you again!" + UI_LINE_SPACING);
        isEnd = true;
    }
}
