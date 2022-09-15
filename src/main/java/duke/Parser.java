package duke;

import java.time.format.DateTimeParseException;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Class that handles parsing and execution of command strings for Duke Bot commands.
 */
public class Parser {
    private static String[] commandList = new String[] {"deadline {task_name} /by {YYYY-mm-dd}",
                                                        "event {task_name} /at {YYYY-mm-dd}",
                                                        "todo {task_name}",
                                                        "mark {task_index}",
                                                        "unmark {task_index}",
                                                        "delete {task_index}",
                                                        "find {keyword}",
                                                        "list",
                                                        "bye",
                                                        "help"};

    private TaskList tasks;
    private Chick chick;
    private Ui ui;
    private int parseErrorCount;

    /**
     * Class constructor for Parser.
     *
     * @param taskList TaskList instance tracking tasks in Duke Bot.
     * @param chickInstance Duke instance representing Duke Bot.
     * @param uiInstance Ui instance representing user interface for Duke Bot.
     */
    public Parser(TaskList taskList, Chick chickInstance, Ui uiInstance) {
        tasks = taskList;
        chick = chickInstance;
        ui = uiInstance;
        parseErrorCount = 0;
    }

    /**
     * Parses and executes a command for Duke Bot.
     * Verbose argument can be used to control output of Ui.
     *
     * @param command Command string for parsing and execution.
     * @param isVerbose Boolean to indicate verbosity of Ui.
     * @return Response string from Duke Bot.
     * @throws ChickException If command cannot be parsed or is invalid.
     */
    public String parse(String command, boolean isVerbose) throws ChickException {
        ui.setVerbose(isVerbose);
        if (command.startsWith("deadline ") || command.startsWith("event ") || command.startsWith("todo ")) {
            return parseTask(command);
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            return parseTwo(command);
        } else if (command.startsWith("find")) {
            return parseFind(command);
        } else if (command.startsWith("list") || command.startsWith("bye") || command.startsWith("help")) {
            return parseOne(command);
        } else {
            parseErrorCount++;
            String response;
            if (parseErrorCount > 5) {
                response = "oi";
            } else if (parseErrorCount > 3) {
                response = "stop it";
            } else if (command.length() > 10) {
                response = "bruh";
            } else {
                String[] chickResponses = new String[] {"??", "lmao"};
                int responseIndex = (int) Math.round(Math.random() * (chickResponses.length - 0.5));
                response = chickResponses[responseIndex];
            }
            throw new ChickException(response);
        }
    }

    /**
     * Parses and executes task commands for Duke Bot.
     * Task commands are in the format "task description separator time".
     *
     * @param command Task command string.
     * @return Response string from Duke Bot.
     * @throws ChickException If command cannot be parsed or is invalid.
     */
    public String parseTask(String command) throws ChickException {
        parseErrorCount = 0;
        Task t = null;
        try {
            if (command.startsWith("deadline")) {
                t = new DeadlineTask(command);
            } else if (command.startsWith("event")) {
                t = new EventTask(command);
            } else if (command.startsWith("todo")) {
                t = new TodoTask(command);
            }
            assert t != null;
            return tasks.addTask(t);
        } catch (DateTimeParseException e) {
            System.out.println("wrong date: " + e);
            return "wrong date: " + e;
        }
    }

    /**
     * Parses and executes commands that are two parts long for Duke Bot.
     * Two part long commands contains one whitespace after command.
     * Two part long commands are in the format "command description".
     *
     * @param command Two part long command.
     * @return Response string from Duke Bot.
     * @throws ChickException If command cannot be parsed or is invalid.
     */
    public String parseTwo(String command) throws ChickException {
        parseErrorCount = 0;
        String[] commandSplit = command.split(" ", 2);
        String commandName = commandSplit[0];
        int id = tasks.getSize();
        if (commandSplit.length > 1) {
            try {
                id = Integer.parseInt(commandSplit[1]);
            } catch (NumberFormatException e) {
                throw new ChickException("Please input a valid number.");
            }
        } else if (id == 0) {
            throw new ChickException("Task list is empty.");
        }

        if (commandName.equals("delete")) {
            return tasks.deleteTask(id - 1);
        } else if (commandName.equals("mark")) {
            return tasks.markTask(id - 1);
        } else if (commandName.equals("unmark")) {
            return tasks.unmarkTask(id - 1);
        } else {
            throw new ChickException("Unknown command");
        }
    }

    /**
     * Parses and executes find command for Duke Bot.
     *
     * @param command Find command.
     * @return Response string from Duke Bot.
     * @throws ChickException If command cannot be parsed or is invalid.
     */
    public String parseFind(String command) throws ChickException {
        parseErrorCount = 0;
        String[] commandSplit = command.split(" ", 2);
        if (commandSplit.length < 2) {
            throw new ChickException("Please input string for find.");
        }
        return tasks.findTask(commandSplit[1]);
    }

    /**
     * Parses and executes commands that are one part long for Duke Bot.
     * One part long commands are in the format "command".
     *
     * @param command One part long command.
     * @return Response string from Duke Bot.
     * @throws ChickException If command cannot be parsed or is invalid.
     */
    public String parseOne(String command) throws ChickException {
        parseErrorCount = 0;
        if (command.startsWith("bye")) {
            String response = tasks.saveTasks();
            chick.terminate();
            return response;
        } else if (command.startsWith("list")) {
            return tasks.generateList();
        } else if (command.startsWith("help")) {
            String commandString = "Commands:\n";
            for (String commandName: commandList) {
                commandString += commandName;
                commandString += "\n";
            }
            return commandString;
        } else {
            throw new ChickException("Invalid command format.");
        }
    }
}
