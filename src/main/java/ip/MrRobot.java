package ip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Task;
import ip.task.ToDo;

import ip.exception.InvalidCommand;
import ip.exception.NoDeadline;
import ip.exception.NoDescription;
import ip.exception.NoPeriod;
import ip.exception.NoTaskFound;

/**
 * <h1>Task management program<h2>.
 * Record to-do's, deadlines, and events.
 * Mark them as done or not.
 * Delete them when no longer needed!
 * 
 * @author Jonathan Lam
 * @since 2022-08-25
 */
public class MrRobot {
    /** List of all tasks. */
    private static LinkedList<Task> tasks = new LinkedList<>();
    private final static String path = "src/main/java/ip/data/taskData.txt";
    /** File to load and save task data to. */
    private static File taskDataFile = new File(path);
    /** Stream of input from the user. */
    private static Scanner input = new Scanner(System.in);
    /** The latest command entered by the user. */
    private static Command lastCommand = null;
    /** Commands that MrRobot can execute. */
    private static enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void main(String[] args) {
        // TODO
        // Load existing task data (if it exists)
        // If task data does not exist, create the file.
        loadSavedData(path);
        // Greeting Message
        System.out.println("HELLO HUMAN. TELL ME WHAT'S ON YOUR MIND?");
        // Primary Program Loop
        while(true) {
            if (input.hasNext()) {
                String firstToken = input.next();
                try {
                    setCommand(firstToken);
                } catch (InvalidCommand e) {
                    System.out.println(firstToken + " IS NOT A VALID COMMAND. YOU DUMMY!");
                    // Clear line if first token is not a command.
                    input.nextLine();
                    continue;
                }
                // End program if exit command entered.
                if (lastCommand == Command.BYE) {
                    // Farewell Message
                    System.out.println("FAREWELL HUMAN.");
                    break;
                } else if (lastCommand == Command.LIST) {
                    show();
                // Execute commands that require options.
                } else {
                    // Store all subsequent text as options.
                    Scanner options = new Scanner(input.nextLine());
                        if (lastCommand == Command.MARK ||
                                lastCommand == Command.UNMARK ||
                                lastCommand == Command.DELETE) {
                            if (options.hasNextInt()) {
                                int index = options.nextInt();
                                // Try executing the command.
                                try {
                                    switch (lastCommand) {
                                    case MARK:
                                        mark(index);
                                        break;
                                    case UNMARK:
                                        unmark(index);
                                        break;
                                    case DELETE:
                                        delete(index);
                                        break;
                                    default:
                                        break;
                                    }
                                } catch (NoTaskFound e) {
                                    System.out.println("There is no task at index " + index);
                                }
                            } else {
                                System.out.println("PLEASE ENTER VALID TASK INDEX NUMBER.");
                            }
                        } else if (lastCommand == Command.TODO ||
                                lastCommand == Command.DEADLINE ||
                                lastCommand == Command.EVENT) {
                            try {
                                switch (lastCommand) {
                                    case TODO:
                                        tasks.add(new ToDo(options));
                                        break;
                                    case DEADLINE:
                                        tasks.add(new Deadline(options));
                                        break;
                                    case EVENT:
                                        tasks.add(new Event(options));
                                        break;
                                    default:
                                        break;
                                }
                            } catch (NoDescription e) {
                                System.out.println("PLEASE ADD DESCRIPTION TO YOUR TASK");
                            } catch (NoDeadline e) {
                                System.out.println("ADD DEADLINE BY APPENDING TASK WITH \"/by [date]\"");
                            } catch (NoPeriod e) {
                                System.out.println("ADD PERIOD BY APPENDING TASK WITH \"/at [date]\"");
                            }
                        }
                    // TODO
                    // After execution of commands that edit the tasklist, save changes to file.
                }
            } else {
                System.out.println("NO INPUT DETECTED. TERMINATING...");
                break;
            }
        }
    }

    private static void loadSavedData(String dataFilePath) {
        System.out.println("SEARCHING FOR EXISTING TASK DATA...");
        try {
            if (taskDataFile.createNewFile()) {
                System.out.println("EXISTING TASK DATA NOT FOUND. INITIALIZING...");
                System.out.println("TASK DATABASE SUCCESSFULLY INITIALIZED: " + taskDataFile.getName());
            } else {
                System.out.println("FILE ALREADY EXISTS. LOADING DATA...");
                // Parse data from taskDataFile.txt into ArrayList Tasks.
                /* Sample text data for tasks:
                t|0|Do the dishes||
                d|1|Attend ceremony|Today|
                e|1|Eat with grandma|Next Tuesday, 2-3pm|

                 */
                String data = new String(Files.readAllBytes(Path.of(path)));
                String[] lines = data.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
                    String[] props = line.split("\\|");
                    String taskType = props[0];
                    switch (taskType) {
                    case "t":
                        tasks.add(new ToDo(props));
                        break;
                    case "d":
                        tasks.add(new Deadline(props));
                        break;
                    case "e":
                        tasks.add(new Event(props));
                        break;
                    default:
                        System.out.println("INVALID TASK FORMAT DETECTED. SKIPPING LINE.");
                    }
                }
                show();
            }
        } catch (IOException e) {
            System.out.println("IO ERROR!!!");
            e.printStackTrace();
        }
    }

    private static void updateTask(int index) {

    }

    /**
     * Outputs formatted task list.
     */
    private static void show() {
        int i = 1;
        System.out.println("===== TASK LIST =====");
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
        System.out.println("=====================");
    }

    /**
     * Marks specified task as complete.
     * 
     * @param index Index of specified task.
     * @throws NoTaskFound If there is no task of that index.
     */
    private static void mark(int index) throws NoTaskFound {
        if (index < 1 || index > tasks.size()) throw new NoTaskFound();
        tasks.get(index - 1).mark();
        System.out.println("MARKED TASK AT INDEX " + index + " AS DONE.");
    }

    /** 
     * Marks the specified task as incomplete.
     * 
     * @param index Index of specified task.
     * @throws NoTaskFound If there is no task of that index.
     */
    private static void unmark(int index) throws NoTaskFound {
        if (index < 1 || index > tasks.size()) throw new NoTaskFound();
        tasks.get(index - 1).unmark();
        System.out.println("MARKED TASK AT INDEX " + index + " AS NOT DONE.");
    }

    /**
     * Deletes the specified task.
     * 
     * @param index Index of specified task.
     * @throws NoTaskFound If there is no task of that index.
     */
    private static void delete(int index) throws NoTaskFound {
        if (index < 1 || index > tasks.size()) throw new NoTaskFound();
        tasks.remove(index - 1);
        System.out.println("REMOVED TASK AT INDEX " + index);
    }

    /**
     * Sets the lastCommand to the given command.
     * 
     * @param command The command to set lastCommand to.
     * @throws InvalidCommand If the given command is not supported.
     */
    private static void setCommand(String command) throws InvalidCommand {
        switch (command) {
        case "bye":
            lastCommand = Command.BYE;
            break;
        case "list":
            lastCommand = Command.LIST;
            break;
        case "mark":
            lastCommand = Command.MARK;
            break;
        case "unmark":
            lastCommand = Command.UNMARK;
            break;
        case "todo":
            lastCommand = Command.TODO;
            break;
        case "deadline":
            lastCommand = Command.DEADLINE;
            break;
        case "event":
            lastCommand = Command.EVENT;
            break;
        case "delete":
            lastCommand = Command.DELETE;
            break;
        default:
            throw new InvalidCommand();
        }
    }

}