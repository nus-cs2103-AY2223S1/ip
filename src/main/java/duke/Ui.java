package duke;

import duke.task.Task;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETINGS = "Hello! I'm duke.Duke\nWhat can I do for you?\n";

    public Ui() {
//        sc = new Scanner(System.in);
    }

    public String nextLine() {
        return sc.nextLine();
    }

    public void printGreetingsCLI() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETINGS);
    }

    public String printGreetings() {
        return "Hello from\n" + LOGO + "\n" + GREETINGS;
    }

    public String printAddTask(String str) {
        return String.format("Got it. I've added this task:\n" + str);
    }

    public String printArrAsNumberedList(TaskList arr) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            output.append(i + 1).append(". ").append(arr.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    public String bye() {
//        sc.close();
        Platform.exit();
        return "Bye. Hope to see you again soon!\n";
    }

    public String printErrorMessage(Exception exception) {
        return exception.getMessage() + '\n';
    }
}
