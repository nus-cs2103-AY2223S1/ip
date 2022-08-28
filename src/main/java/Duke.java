import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static class Task {
        protected String description;
        protected   boolean isDone;

        Task (String description) {
            this.description = description;
            this.isDone = false;
        }
        Task (String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
        }

        public String getDescription() {
            return this.description;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            char mark;
            if (this.isDone) {
                mark = 'X';
            } else {
                mark = ' ';
            }
            return ("[" + mark + "] " + this.getDescription());
        }
    }
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static boolean isEnd = false;

    private static final String UI_LINE_SPACING = "----------------------------------------\n";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";


    public static void main(String[] args) { //runs chatbot UI
        String greeting = "Hello! I'm Duke  \n" + "What can I do for you?\n";
        Scanner userInput = new Scanner(System.in);
        System.out.println(UI_LINE_SPACING + greeting + UI_LINE_SPACING);
        while (!isEnd) {
            String command = userInput.nextLine();

            switch(command) {
            case COMMAND_LIST:
                System.out.println(UI_LINE_SPACING + list() + UI_LINE_SPACING);
                break;
            case COMMAND_BYE:
                System.out.println("Bye! Hope to see you again!");
                isEnd = true;
                break;
            default:
                if (command.contains("mark")) {
                    int taskNum = Integer.parseInt(command.substring(command.length() - 1));
                    if (command.contains("unmark")) {
                        unmark(taskNum);
                    } else {
                        mark(taskNum);
                    }
                } else {
                    tasks.add(new Task(command));
                    System.out.println(UI_LINE_SPACING + "Added: " + command + "\n" + UI_LINE_SPACING);
                }
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

    public static void end() {
        System.out.println(UI_LINE_SPACING + "Bye! Hope to see you again!" + UI_LINE_SPACING);
        isEnd = true;
    }
}
