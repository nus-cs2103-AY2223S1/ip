import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";

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

        IOParser.printMsg(String.format("%s%s", LOGO, GREETING));

        Scanner sc = new Scanner(System.in);
        boolean hasExited = false;

        while (!hasExited) {
            System.out.print(">> ");
            String input = sc.nextLine();
            switch (input.toLowerCase()) {
            case "bye":
                IOParser.printMsg("Bye. Hope to see you again soon!");
                hasExited = true;
                break;
            case "list":
                IOParser.printMsg(this.taskList.toString());
                break;
            default:
                String[] inputs = input.split(" ", 2);
                String command = inputs[0].toLowerCase();
                if (command.equals("mark") && inputs.length == 2 && inputs[1].matches("\\d+")) {
                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index < this.taskList.size() && index >= 0) {
                        this.taskList.setDone(index, true);
                        IOParser.printMsg(String.format("Nice! I've marked this task as done:\n %s",
                                this.taskList.get(index)));
                        break;
                    }
                } else if (command.equals("unmark") && inputs.length == 2 && inputs[1].matches("\\d+")) {
                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index < this.taskList.size() && index >= 0) {
                        this.taskList.setDone(index, false);
                        IOParser.printMsg(String.format("OK, I've marked this task as not done yet:\n %s",
                                this.taskList.get(index)));
                        break;
                    }
                }
                this.taskList.addTask(input);
                IOParser.printMsg(String.format("Added task:\n %s", this.taskList.get(this.taskList.size() - 1)));
            }
        }
    }
}