package ip;

import java.util.LinkedList;
import java.util.Scanner;

import ip.exception.*;
import ip.task.Deadline;
import ip.task.Event;
import ip.task.Task;
import ip.task.ToDo;

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
    /** Stream of input from the user. */
    private static Scanner input = new Scanner(System.in);
    /** The latest command entered by the user. */
    private static Command lastCommand = null;
    /** Commands that MrRobot can execute. */
    private static enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void main(String[] args) {
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
                            } catch (NoDeadline | BadDeadline e) {
                                System.out.println("ADD DEADLINE BY APPENDING TASK DESCRIPTION WITH \"/by [date]\"");
                                System.out.println("[date] MUST HAVE THE FORMAT OF <d/M/yyyy HHmm>");
                                System.out.println("EXAMPLE: \"4/11/1977 0240\"");
                            } catch (NoPeriod e) {
                                System.out.println("ADD PERIOD BY APPENDING TASK DESCRIPTION WITH \"/at [date]\"");
                            }
                        }
                }
            } else {
                System.out.println("NO INPUT DETECTED. TERMINATING...");
                break;
            }
        }
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