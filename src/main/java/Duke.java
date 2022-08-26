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
    private boolean tasksEnd = false;

    /**
     * Initialises Duke class with empty TaskList.
     */
    public Duke() {
        taskList = new TaskList();
    }

    /**
     * Runs the program.
     */
    public void run() {

        IOParser.printMsg(String.format("%s%s", LOGO, GREETING));

        Scanner sc = new Scanner(System.in);

        while (!tasksEnd) {
            System.out.print(">> ");
            String input = sc.nextLine();
            try {
                execute(input);
            } catch (DukeException e) {
                IOParser.printMsg(e.getMessage());
            }
        }
    }

    /**
     * Execute the command entered by the user.
     *
     * @param input The command entered by the user.
     * @throws DukeException if command is not valid.
     */
    private void execute(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0].toLowerCase();

        Map<String, String> map = new HashMap<>();
        String description;

        switch (command) {
        case "bye":
            IOParser.printMsg("Bye. Hope to see you again soon!");
            tasksEnd = true;
            break;
        case "list":
            IOParser.printMsg(String.format("Here are the tasks in your list:\n%s",
                    taskList));
            break;
        case "":
            break;
        case "mark":
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                taskList.setDone(index, true);
                IOParser.printMsg(String.format("Nice! I've marked this task as done:\n %s",
                        taskList.get(index)));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
            break;
        case "unmark":
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                taskList.setDone(index, false);
                IOParser.printMsg(String.format("OK, I've marked this task as not done yet:\n %s",
                        taskList.get(index)));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
            break;
        case "todo":
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            description = inputs[1];
            if (description.length() == 0) {
                throw new DukeException("Invalid argument: Description cannot be empty.");
            }
            map.put("description", description);
            taskList.addTask(command, map);
            IOParser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                    taskList.get(taskList.size() - 1),
                    taskList.lengthString()));
            break;
        case "deadline":
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                description = inputs[1].substring(0, inputs[1].indexOf(" /by "));
                if (description.length() == 0) {
                    throw new DukeException("Invalid argument: Description cannot be empty.");
                }
                String deadline = inputs[1].substring(inputs[1].indexOf(" /by ") + 5);
                if (deadline.length() == 0) {
                    throw new DukeException("Invalid argument: Deadline cannot be empty.");
                }
                map.put("description", description);
                map.put("by", deadline);
                taskList.addTask(command, map);
                IOParser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                        taskList.get(taskList.size() - 1),
                        taskList.lengthString()));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Use /by flag to specify the deadline of the task");
            }
            break;
        case "event":
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                description = inputs[1].substring(0, inputs[1].indexOf(" /at "));
                if (description.length() == 0) {
                    throw new DukeException("Invalid argument: Description cannot be empty.");
                }
                String time = inputs[1].substring(inputs[1].indexOf(" /at ") + 5);
                if (time.length() == 0) {
                    throw new DukeException("Invalid argument: Event time cannot be empty.");
                }
                map.put("description", description);
                map.put("at", time);
                taskList.addTask(command, map);
                IOParser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                        taskList.get(taskList.size() - 1),
                        taskList.lengthString()));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Use /at flag to specify the date and time of the event.");
            }
            break;
        default:
            throw new DukeException("Invalid command: Please try again.");
        }
    }
}