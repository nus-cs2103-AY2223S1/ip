import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.text.WordUtils;

public class Duke {

    private static final String LINE = "──────────────────────────────────────────\n";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";

    /**
     * Pretty prints an output string
     *
     * @param output The string representing the output.
     */
    public static void printMsg(String output) {
        String[] lines = output.split("\n");
        String newStr = Arrays.stream(lines).map(line ->
                String.format("\t %s%s\n", line.replace(line.stripLeading(), ""),
                        WordUtils.wrap(line, 40, "\n\t ", false)))
                .reduce("", String::concat);
        System.out.printf("\t%s%s\t%s%n", LINE, newStr, LINE);
    }

    private final TaskList taskList;

    /**
     * Initialises Duke class with empty TaskList.
     */
    public Duke() {
        this.taskList = new TaskList();
    }

    /**
     * Runs the program.
     */
    public void run() {

        printMsg(String.format("%s%s", LOGO, GREETING));

        Scanner sc = new Scanner(System.in);
        boolean hasExited = false;

        while (!hasExited) {
            System.out.print(">> ");
            String input = sc.nextLine();
            switch (input.toLowerCase()) {
            case "bye":
                printMsg("Bye. Hope to see you again soon!");
                hasExited = true;
                break;
            case "list":
                printMsg(this.taskList.toString());
                break;
            default:
                this.taskList.addTask(input);
                printMsg(String.format("Added task: %s", input));
            }
        }
    }
}