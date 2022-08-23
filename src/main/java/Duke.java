import java.util.HashMap;
import java.util.Map;
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
            String[] inputs = input.split(" ", 2);
            String command = inputs[0].toLowerCase();

            switch (command) {
            case "bye":
                IOParser.printMsg("Bye. Hope to see you again soon!");
                hasExited = true;
                break;
            case "list":
                IOParser.printMsg(String.format("Here are the tasks in your list:\n%s",
                        this.taskList));
                break;
            case "":
                break;
            case "mark":
                if (inputs.length == 2 && inputs[1].matches("\\d+")) {
                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index < this.taskList.size() && index >= 0) {
                        this.taskList.setDone(index, true);
                        IOParser.printMsg(String.format("Nice! I've marked this task as done:\n %s",
                                this.taskList.get(index)));
                    }
                }
                break;
            case "unmark":
                if (inputs.length == 2 && inputs[1].matches("\\d+")) {
                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index < this.taskList.size() && index >= 0) {
                        this.taskList.setDone(index, false);
                        IOParser.printMsg(String.format("OK, I've marked this task as not done yet:\n %s",
                                this.taskList.get(index)));
                    }
                }
                break;
            case "todo":
                if (inputs.length == 2) {
                    Map<String, String> map = new HashMap<>();
                    String description = inputs[1];
                    map.put("description", description);
                    this.taskList.addTask(command, map);
                    IOParser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                            this.taskList.get(this.taskList.size() - 1),
                            this.taskList.lengthString()));
                }
                break;
            case "deadline":
                if (inputs.length == 2) {
                    Map<String, String> map = new HashMap<>();
                    String description = inputs[1].substring(0, inputs[1].indexOf("/by "));
                    String deadline = inputs[1].substring(inputs[1].indexOf("/by ") + 4);
                    map.put("description", description);
                    map.put("by", deadline);
                    this.taskList.addTask(command, map);
                    IOParser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                            this.taskList.get(this.taskList.size() - 1),
                            this.taskList.lengthString()));
                }
                break;
            case "event":
                if (inputs.length == 2) {
                    Map<String, String> map = new HashMap<>();
                    String description = inputs[1].substring(0, inputs[1].indexOf("/at "));
                    String time = inputs[1].substring(inputs[1].indexOf("/at ") + 4);
                    map.put("description", description);
                    map.put("at", time);
                    this.taskList.addTask(command, map);
                    IOParser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                            this.taskList.get(this.taskList.size() - 1),
                            this.taskList.lengthString()));
                }
                break;
            default:
                Map<String, String> map = new HashMap<>();
                map.put("description", input);
                this.taskList.addTask("other", map);
                IOParser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                        this.taskList.get(this.taskList.size() - 1),
                        this.taskList.lengthString()));
            }
        }
    }
}