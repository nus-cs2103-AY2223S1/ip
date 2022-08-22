import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner sc;
    private TaskList taskList;
    private enum Commands {
        TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE, SAVE
    }
    private final HashMap<String, Commands> commandMap = new HashMap<>(Map.of(
            "todo", Commands.TODO,
            "deadline", Commands.DEADLINE,
            "event", Commands.EVENT,
            "list", Commands.LIST,
            "bye", Commands.BYE,
            "mark", Commands.MARK,
            "unmark", Commands.UNMARK,
            "delete", Commands.DELETE,
            "save", Commands.SAVE
    ));

    public Duke() {
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
        reply(LOGO + "Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public void reply(String msg) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("    ");
        stringBuilder.append(HORIZONTAL_LINE);
        String[] response = msg.split("\n");
        for (String line : response) {
            stringBuilder.append("     ");
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        stringBuilder.append("    ");
        stringBuilder.append(HORIZONTAL_LINE);
        System.out.println(stringBuilder);
    }

    public String add(Commands type) {
        String[] arguments;
        String msg = "";
        try {
            switch (type) {
                case TODO:
                    String input = sc.nextLine();
                    if (input.isBlank()) {
                        throw new DukeException("Something went wrong! Could not read TODO.");
                    }
                    msg = this.taskList.addTask(new ToDo(input));
                    break;
                case DEADLINE:
                    arguments = sc.nextLine().split("/by");
                    if (arguments.length < 2 || arguments[0].isBlank() || arguments[1].isBlank()) {
                        throw new DukeException("Something went wrong! Could not read DEADLINE.");
                    }
                    msg = this.taskList.addTask(new Deadline(arguments[0], arguments[1]));
                    break;
                case EVENT:
                    arguments = sc.nextLine().split("/at");
                    if (arguments.length < 2 || arguments[0].isBlank() || arguments[1].isBlank()) {
                        throw new DukeException("Something went wrong! Could not read EVENT.");
                    }
                    msg = this.taskList.addTask(new Event(arguments[0], arguments[1]));
                    break;
            }
            return msg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void loadData() {
        try {
            File f = new File("data/duke.txt");
            Scanner sc = new Scanner(f);
            reply("Reading duke.txt ...");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] inputs = line.split(",");
                System.out.println(Arrays.toString(inputs));
                switch (inputs[0]) {
                    case "T":
                        this.taskList.addTask(new ToDo(inputs[2]));
                        break;
                    case "D":
                        this.taskList.addTask(new Deadline(inputs[2], inputs[3]));
                        break;
                    case "E":
                        this.taskList.addTask(new Event(inputs[2], inputs[3]));
                        break;
                    default:
                        throw new DukeException("Invalid input from file.");
                }
            }
            sc.close();
            reply("Successfully loaded saved contents.\n" + this.taskList.toString());
        } catch (FileNotFoundException fileError) {
            reply("Error in loading data. File not found.");
        } catch (DukeException dukeError) {
            reply(dukeError.getMessage());
        }
    }

    public String saveData() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
            Iterator<Task> it = this.taskList.toSave();
            while (it.hasNext()) {
                fw.write(it.next().toStringSaveFormat());
            }
            fw.close();
            return "Successfully saved contents into duke.txt" ;
        } catch (IOException e) {
            return "Error in saving data.";
        }
    }

    public void commandHandler() {
        while (sc.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                String input = sc.next();
                if (!commandMap.containsKey(input)) {
                    sc.nextLine(); // Move scanner to next line
                    throw new DukeException("I'm sorry, but I don't understand that.");
                }
                Commands type = commandMap.get(input);
                switch (type) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        reply(add(type));
                        break;
                    case LIST:
                        reply(this.taskList.toString());
                        break;
                    case MARK:
                        stringBuilder.append("Nice! I've marked this task as done:\n");
                        stringBuilder.append(this.taskList.markDone(sc.nextInt() - 1));
                        reply(stringBuilder.toString());
                        break;
                    case UNMARK:
                        stringBuilder.append("OK, I've marked this task as not done yet:\n");
                        stringBuilder.append(this.taskList.unmarkDone(sc.nextInt() - 1));
                        reply(stringBuilder.toString());
                        break;
                    case DELETE:
                        reply(this.taskList.removeTask(sc.nextInt() - 1));
                        break;
                    case SAVE:
                        reply(saveData());
                        break;
                    case BYE:
                        reply("Bye. Hope to see you again soon!");
                        sc.close();
                        break;
                }
                if (type == Commands.BYE) {
                    break;
                }
            } catch (DukeException e) {
                reply(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.loadData();
        dk.commandHandler();
    }
}
