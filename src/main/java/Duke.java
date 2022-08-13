import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<String> history = new ArrayList<String>();
    private Scanner commandInput;
    private boolean isClosed = false;

    //The strings that Duke uses for greetings and formatting. Constants.
    private final static String lineBreak1
            = "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
             + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    private final static String lineBreak2
            = "______________________________________________________"
            + "______________________________________________________";
    private final static String logo
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private final static String greeting
            = "Hello! I'm Duke\n"
            + "What can I do for you?";

    private void goodbye() {
        System.out.println(this.lineBreak2);
        System.out.println("Goodbye! See you next time!\n" + this.lineBreak1);
        this.isClosed = true;
    }

    private void addToHistory(String command) {
        System.out.println(this.lineBreak2);
        System.out.println("Adding to Tasks: " + command);
        history.add(command);
        System.out.println(lineBreak1);
    }

    private String taskItem(int i) {
        int taskNum = i + 1;
        return String.valueOf(taskNum) + ". " + history.get(i);
    }

    private void listOut() {
        System.out.println(lineBreak2);
        if (history.size() <= 0) {
            System.out.println("No tasks assigned yet.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println(this.taskItem(i));
            }
        }
        System.out.println(lineBreak1);
    }

    private void inputCommand(String command) {
        if (command.equals("bye")) {
            this.goodbye();
        } else if (command.equals("list")) {
            this.listOut();
        } else {
            this.addToHistory(command);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.logo + "\n" + duke.greeting + "\n" + duke.lineBreak1);
        duke.commandInput = new Scanner(System.in);
        while (duke.commandInput.hasNextLine()) {
            String command = duke.commandInput.nextLine();
            duke.inputCommand(command);
            if (duke.isClosed) {
                duke.commandInput.close();
                break;
            }
        }
    }
}
