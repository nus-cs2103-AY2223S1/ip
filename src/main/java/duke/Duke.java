package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.dukeexception.DateTimeFormatException;
import duke.dukeexception.DukeException;
import duke.dukeexception.IncompleteCommandException;
import duke.dukeexception.NoSuchCommandException;
import duke.dukeexception.TaskCompletionException;
import duke.dukeexception.TaskOutOfBoundException;
import duke.parser.CommandType;
import duke.parser.OutputRedirector;
import duke.parser.Parser;
import duke.storage.Cache;
import duke.storage.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;


/**
 * The main class for personal assistant bot Duke.
 */
public class Duke {
    private static TaskList taskList;
    private Cache cache;

    public Duke() {
        taskList = new TaskList();
    }

    /**
     * Launches the bot and says hello. Reads in the previous task list in cache and take user inputs to.
     * @param args User inputs.
     * @throws IOException
     * @throws DukeException
     */
    public static void main(String[] args) throws IOException, DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Cache cache = new Cache(System.getProperty("user.dir") + "\\data\\duke.txt");
        taskList = cache.printPath();
        cliRun();
        cache.update(taskList);
    }

    /**
     * Executes commands:
     * 1. Greetings on opening.
     * 2. todo [task name]: to add a ToDo Task.
     * 3. deadline [task name] /by yyyy-MM-dd: to add a Deadline task.
     * 4. event [task name] /at yyyy-MM-dd hh:mm: to add a Event task.
     * 5. bye: to exit.
     * 6. mark [index]: to mark done for the task with given index.
     * 7. unmark [index]: to unmark done for the task with given index.
     * 8. delete [index]: to delete the task with given index.
     * 9. find [String]: to find the corresponding task.
     * 10. list: to list all ongoing tasks.
     */
    public static void cliRun() throws IncompleteCommandException, TaskOutOfBoundException,
            NoSuchCommandException, TaskCompletionException, DateTimeFormatException {
        Scanner in = new Scanner(System.in);
        boolean lastCommandOrNot = false;
        Task task;
        String command;
        String suggestion;
        String[] commandList;
        Integer taskIndex = null;
        CommandType type;

        Ui.greet();
        while (!lastCommandOrNot) {
            command = in.nextLine().trim();
            commandList = command.split(" ", 2);
            command = (Parser.hasMultipleVariables(commandList[0])) ? commandList[0] : command;
            type = CommandType.map(command);

            try {
                switch (type) {
                case BYE:
                    Ui.bye();
                    lastCommandOrNot = true;
                    break;

                case MARK:
                case UNMARK:
                    taskIndex = Parser.strToInt(commandList[1]) - 1;
                    task = taskList.get(taskIndex);
                    switch (type) {
                    case MARK:
                        if (!task.isDone()) {
                            Ui.mark(task);
                        } else {
                            System.out.println("     :( OOPS!!! The task specified is already done");
                            throw new TaskCompletionException("");
                        }
                        break;
                    case UNMARK:
                        if (task.isDone()) {
                            Ui.unmark(task);
                        } else {
                            System.out.println("     :( OOPS!!! The task specified is not done yet");
                            throw new TaskCompletionException("");
                        }
                        break;
                    default:
                        throw new NoSuchCommandException(
                                "     :( Unfortunately, but I cannot understand what that means :-(");
                    }
                    break;

                case DELETE:
                    taskIndex = Parser.strToInt(commandList[1]) - 1;
                    task = taskList.get(taskIndex);
                    Ui.delete(task);
                    taskList.remove(task);
                    for (Integer i = taskIndex; i < taskList.countTask(); i++) {
                        taskList.get(i).updateRemoval();
                    }
                    break;

                case LIST:
                    Ui.list(taskList);
                    break;

                case TODO:
                    taskList.add(Ui.addToDo(commandList[1]));
                    break;

                case DEADLINE:
                    taskList.add(Ui.addDeadline(commandList[1]));
                    break;

                case EVENT:
                    taskList.add(Ui.addEvent(commandList[1]));
                    break;

                case FIND:
                    Ui.find(commandList[1].trim(), taskList);
                    break;

                default:
                    throw new NoSuchCommandException(
                            "     :( Unfortunately, but I cannot understand what that means :-(");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (command.equals("mark") || command.equals("unmark")) {
                    System.out.println("     :( OOPS!!! The index of a task cannot be empty.");
                    suggestion = "Try again with a task index";
                } else {
                    System.out.println("     :( OOPS!!! The description of a task cannot be empty.");
                    suggestion = "Try again with a task description that you wanna track";
                }
                throw new IncompleteCommandException(e.getMessage(), suggestion);

            } catch (IndexOutOfBoundsException e) {
                if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                    System.out.println("     :( OOPS!!! You have less than " + (taskIndex + 1) + " task(s).");
                    suggestion = "Try again with a task index";
                    throw new TaskOutOfBoundException(e.getMessage(), suggestion);
                }
                throw new TaskOutOfBoundException(e.getMessage(), "");
            }
        }
    }

    /**
     * Returns a greeting msg after recovering all the previous tasks from cache.
     * @return a string which contains the greeting msg of Duke.
     * @throws DukeException
     * @throws IOException
     */
    public String getResponse() throws DukeException, IOException {
        assert (OutputRedirector.isCaptured() == false) : "The output stream is somehow alr captured. Please check!";
        OutputRedirector.start();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        cache = new Cache(System.getProperty("user.dir") + "\\data\\duke.txt");
        taskList = cache.printPath();
        Ui.greet();
        return OutputRedirector.stop();
    }

    /**
     * Returns a string which contains the response of Duke.
     * @param input the command taken from GUI.
     * @return a string which contains the response of Duke.
     * @throws DukeException
     * @throws IOException
     */
    public String getResponse(String input) throws DukeException, IOException {
        assert (OutputRedirector.isCaptured() == false) : "The output stream is somehow alr captured. Please check!";
        OutputRedirector.start();
        Task task;
        String command;
        String suggestion;
        String[] commandList;
        Integer taskIndex = null;
        CommandType type;

        command = input;
        commandList = command.split(" ", 2);
        command = (Parser.hasMultipleVariables(commandList[0])) ? commandList[0] : command;
        type = CommandType.map(command);

        try {
            switch (type) {
            case BYE:
                Ui.bye();
                cache.update(taskList);
                break;

            case MARK:
            case UNMARK:
                taskIndex = Parser.strToInt(commandList[1]) - 1;
                task = taskList.get(taskIndex);
                switch (type) {
                case MARK:
                    if (!task.isDone()) {
                        Ui.mark(task);
                    } else {
                        System.out.println("     :( OOPS!!! The task specified is already done");
                        throw new TaskCompletionException("");
                    }
                    break;
                case UNMARK:
                    if (task.isDone()) {
                        Ui.unmark(task);
                    } else {
                        System.out.println("     :( OOPS!!! The task specified is not done yet");
                        throw new TaskCompletionException("");
                    }
                    break;
                default:
                    throw new NoSuchCommandException(
                            "     :( Unfortunately, but I cannot understand what that means :-(");
                }
                break;

            case DELETE:
                taskIndex = Parser.strToInt(commandList[1]) - 1;
                task = taskList.get(taskIndex);
                Ui.delete(task);
                taskList.remove(task);
                for (Integer i = taskIndex; i < taskList.countTask(); i++) {
                    taskList.get(i).updateRemoval();
                }
                break;

            case LIST:
                Ui.list(taskList);
                break;

            case TODO:
                taskList.add(Ui.addToDo(commandList[1]));
                break;

            case DEADLINE:
                taskList.add(Ui.addDeadline(commandList[1]));
                break;

            case EVENT:
                taskList.add(Ui.addEvent(commandList[1]));
                break;

            case FIND:
                Ui.find(commandList[1].trim(), taskList);
                break;

            default:
                throw new NoSuchCommandException(
                        "     :( Unfortunately, but I cannot understand what that means :-(");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (command.equals("mark") || command.equals("unmark")) {
                System.out.println("     :( OOPS!!! The index of a task cannot be empty.");
                suggestion = "Try again with a task index";
            } else {
                System.out.println("     :( OOPS!!! The description of a task cannot be empty.");
                suggestion = "Try again with a task description that you wanna track";
            }
            throw new IncompleteCommandException(e.getMessage(), suggestion);

            //IndexOutOfBoundsException mark 1 > totalNumber
            //check mark/ unmark
        } catch (IndexOutOfBoundsException e) {
            if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                System.out.println("     :( OOPS!!! You have less than " + (taskIndex + 1) + " task(s).");
                suggestion = "Try again with a task index";
                throw new TaskOutOfBoundException(e.getMessage(), suggestion);
            }
            throw new TaskOutOfBoundException(e.getMessage(), "");
        }

        String stop = OutputRedirector.stop();
        return stop;
    }
}

