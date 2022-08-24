import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The main class that executes the Duke chatbot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Duke {
    /** List of tasks. */
    protected ArrayList<Task> taskList = new ArrayList<>();
    protected final String DIRECTORY = System.getProperty("user.home") + "/DukeData";
    protected final String FILE_PATH = DIRECTORY + "/Duke.txt";

    /** Ui that prints statements for the bot. */
    protected static Ui ui = new Ui();

    public File initialize() throws DukeException{
        try {
            File parentDirectory = new File(DIRECTORY);

            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }

            File dukeFile = new File(FILE_PATH);

            if(!dukeFile.exists()) {
                dukeFile.createNewFile();
            }

            return dukeFile;

        } catch (IOException e) {
            throw new DukeException("OOPS!! A new file cannot be created.");
        }
    }

    public void readFile(File dukeFile) throws DukeException {
        try {
            Scanner sc = new Scanner(dukeFile);
            while (sc.hasNextLine()) {
                String commands = sc.nextLine();
                String typeOfTask = commands.substring(1, 2);
                String marked = commands.substring(4, 5);
                String description = commands.substring(7);

                switch (typeOfTask) {
                case "T":
                    Task toDo = new ToDo(description);
                    if (marked == "X") {
                        toDo.markAsDone();
                    }
                    taskList.add(toDo);
                    break;
                case "D":
                    String[] deadlineDescription = description.split("by:");
                    String deadlineName = deadlineDescription[0]
                            .substring(0, deadlineDescription[0].length() - 1)
                            .trim();
                    String by = deadlineDescription[1].substring(1, deadlineDescription[1].length() - 1).trim();
                    LocalDate deadlineDate = LocalDate.parse(by);
                    Task deadline = new Deadline(deadlineName, deadlineDate);
                    if (marked == "X") {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    String[] eventDescription = description.split("at:");
                    String eventName = eventDescription[0]
                            .substring(0, eventDescription[0].length() - 1)
                            .trim();
                    String at = eventDescription[1].substring(0, eventDescription[1].length() - 1).trim();
                    Task event = new Event(eventName, at);
                    if (marked == "X") {
                        event.markAsDone();
                    }
                    taskList.add(event);
                    break;
                }
            }
        } catch (IOException e) {
            throw new DukeException("OOPS!! Failed to read file.");
        }
    }

    public void writeAndSaveToFile(File dukeFile) throws DukeException {
        try {
            FileWriter writer = new FileWriter(dukeFile);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!! Failed to write to file.");
        }
    }

    /**
     * Determines whether a command line inputted by
     * the user is a valid command.
     *
     * @param command Command line that is inputted by the user.
     * @return A boolean value that states whether the command exists or not.
     */
    public boolean doesCommandExist(String command) {
        return command.equals("list") || command.equals("mark")
                || command.equals("unmark") || command.equals("deadline") || command.equals("event")
                || command.equals("todo") || command.equals("delete") || command.equals("bye");
    }

    /**
     * Detects the commands inputted by a user; if the command exists,
     * it will execute the given command. Otherwise, the ui will
     * print a message saying that the command does not exist.
     *
     * @param typeOfCommandLine The type of command line inputted by the user.
     * @param userInput The whole string inputted by the user.
     */
    public void detectCommand(CommandLine typeOfCommandLine, String userInput, File dukeFile) {
        try {
            switch (typeOfCommandLine) {
            case BYE:
                ui.exit();
                break;
            case LIST:
                ui.list(taskList);
                break;
            case MARK:
                Commands.mark(userInput, taskList);
                break;
            case UNMARK:
                Commands.unmark(userInput, taskList);
                break;
            case DEADLINE:
                Commands.deadline(userInput, taskList);
                break;
            case EVENT:
                Commands.event(userInput, taskList);
                break;
            case TODO:
                Commands.toDo(userInput, taskList);
                break;
            case DELETE:
                Commands.delete(userInput, taskList);
                break;
            default:
                ui.commandDoesNotExist();
                break;
            }
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }

    /**
     * Executes the bot.
     *
     * @param args Main arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        ui.greet();

        try {
            File dukeFile = duke.initialize();
            duke.readFile(dukeFile);
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();

            while (true) {
                String commandLine = userInput.split(" ")[0];
                if (duke.doesCommandExist(commandLine)) {
                    CommandLine typeOfCommandLine = CommandLine.valueOf(commandLine.toUpperCase());
                    duke.detectCommand(typeOfCommandLine, userInput, dukeFile);
                    if (typeOfCommandLine == CommandLine.BYE) {
                        duke.writeAndSaveToFile(dukeFile);
                        sc.close();
                        return;
                    }
                } else {
                    ui.commandDoesNotExist();
                }
                userInput = sc.nextLine();
            }

        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }

    /**
     * Enum class to represent the command lines that exist.
     */
    enum CommandLine {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE,
    }
}
