import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String taskListTxt = "duke.txt";

    private final TaskList taskList;
    private boolean tasksEnd = false;

    /**
     * Initialises Duke class with empty {@code TaskList}.
     */
    public Duke() {
        taskList = new TaskList();
    }

    /**
     * Runs the program.
     */
    public void run() {

        Parser.printMsg(String.format("%s%s", LOGO, GREETING));

        Scanner sc = new Scanner(System.in);
        load();

        while (!tasksEnd) {
            System.out.print(">> ");
            String input = sc.nextLine();
            try {
                execute(input);
                write(taskList);
            } catch (DukeException e) {
                Parser.printMsg(e.getMessage());
            }
        }
    }

    /**
     * Writes {@code TaskList} taskList into data/duke.txt
     * Overwrites the whole file with current taskList
     * @param taskList the {@code TaskList} we are saving
     */
    public void write(TaskList taskList) {
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                Parser.printMsg("Could not create /data directory");
            }
        }
        try {
            FileWriter fw = new FileWriter(String.format("./data/%s", taskListTxt));
            for (Task task : taskList) {
                fw.write(task.toData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Parser.printMsg(String.format("Failed to write file at ./data/%s", taskListTxt));
        }
    }

    /**
     * Loads {@code TaskList} taskList from data/duke.txt
     */
    public void load() {
        File f = new File(String.format("./data/%s", taskListTxt));
        try {
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    taskList.add(Parser.parseTask(sc.nextLine()));
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            Parser.printMsg(e.getMessage());
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
        CommandType command = CommandType.fromString(inputs[0].toUpperCase());

        if (command == null) {
            throw new DukeException("Invalid command: Please try again.");
        }

        Map<String, String> map = new HashMap<>();
        String description;

        switch (command) {
        case BYE:
            Parser.printMsg("Bye. Hope to see you again soon!");
            tasksEnd = true;
            break;
        case LIST:
            Parser.printMsg(String.format("Here are the tasks in your list:\n%s",
                    taskList));
            break;
        case EMPTY:
            break;
        case MARK:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                taskList.setDone(index, true);
                Parser.printMsg(String.format("Nice! I've marked this task as done:\n %s",
                        taskList.get(index)));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
            break;
        case UNMARK:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                taskList.setDone(index, false);
                Parser.printMsg(String.format("OK, I've marked this task as not done yet:\n %s",
                        taskList.get(index)));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
            break;
        case TODO:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            description = inputs[1];
            if (description.length() == 0) {
                throw new DukeException("Invalid argument: Description cannot be empty.");
            }
            map.put("description", description);
            taskList.add(new TodoTask(map, false));
            Parser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                    taskList.get(taskList.size() - 1),
                    taskList.lengthString()));
            break;
        case DEADLINE:
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
                taskList.add(new DeadlineTask(map, false));
                Parser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                        taskList.get(taskList.size() - 1),
                        taskList.lengthString()));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Use /by flag to specify the deadline of the task");
            }
            break;
        case EVENT:
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
                taskList.add(new EventTask(map, false));
                Parser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                        taskList.get(taskList.size() - 1),
                        taskList.lengthString()));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Use /at flag to specify the date and time of the event.");
            }
            break;
        case DELETE:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                Task task = taskList.remove(index);
                Parser.printMsg(String.format("Noted. I've removed this task:\n %s",
                        task));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
            break;
        default:
            throw new DukeException("Invalid command: Please try again.");
        }
    }

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}