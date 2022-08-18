import java.util.LinkedList;
import java.util.Scanner;

public class MrRobot {
    private static LinkedList<Task> list = new LinkedList<>();
    private static Scanner input = new Scanner(System.in);
    private static Command lastCommand = null;
    private static enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void main(String[] args) {
        // Greet
        System.out.println("HELLO HUMAN. TELL ME WHAT'S ON YOUR MIND?");
        while(true) {
            /**** Parse Input ****/ 
            // Step 1: Get first token - to check if it is a valid command.
            if (input.hasNext()) {
                String firstToken = input.next();
                switch (firstToken) {
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
                        System.out.println(firstToken + " IS NOT A VALID COMMAND. YOU DUMMY!");
                        continue; // Skip remaining command execution logic.
                }
                // Step 2: Execute non-optioned commands (list)
                if (lastCommand == Command.BYE) {
                    System.out.println("FAREWELL HUMAN.");
                    break;
                } else if (lastCommand == Command.LIST) {
                    show();
                } else { // Commands executed here require options. (E.g. index, descriptions)
                    Scanner options = new Scanner(input.nextLine());
                        if (lastCommand == Command.MARK ||
                                lastCommand == Command.UNMARK ||
                                lastCommand == Command.DELETE) {
                            // Get index
                            if (options.hasNextInt()) {
                                int index = options.nextInt();
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
                            // Create new task object with given options.
                            // Pass all options as a scanner object to the constructor.
                            try {
                                switch (lastCommand) {
                                    case TODO:
                                        list.add(new ToDo(options));
                                        break;
                                    case DEADLINE:
                                        list.add(new Deadline(options));
                                        break;
                                    case EVENT:
                                        list.add(new Event(options));
                                        break;
                                }
                            } catch (NoDescription e) {
                                System.out.println("PLEASE ADD DESCRIPTION TO YOUR TASK");
                            } catch (NoDeadline e) {
                                System.out.println("ADD DEADLINE BY APPENDING TASK WITH \"/by [date]\"");
                            } catch (NoPeriod e) {
                                System.out.println("ADD PEROID BY APPENDING TASK WITH \"/at [date]\"");
                            }
                        }
                }
            } else {
                System.out.println("NO INPUT DETECTED. TERMINATING...");
                break;
            }
        }
    }

    private static void show() {
        int i = 1;
        System.out.println("===== TASK LIST =====");
        for (Task task : list) {
            System.out.println(i + ". " + task);
            i++;
        }
        System.out.println("=====================");
    }

    private static void mark(int index) throws NoTaskFound {
        if (index < 1 || index > list.size()) throw new NoTaskFound();
        list.get(index - 1).mark();
        System.out.println("MARKED TASK AT INDEX " + index + " AS DONE.");
    }

    private static void unmark(int index) throws NoTaskFound {
        if (index < 1 || index > list.size()) throw new NoTaskFound();
        list.get(index - 1).unmark();
        System.out.println("MARKED TASK AT INDEX " + index + " AS NOT DONE.");
    }

    private static void delete(int index) throws NoTaskFound {
        if (index < 1 || index > list.size()) throw new NoTaskFound();
        list.remove(index - 1);
        System.out.println("REMOVED TASK AT INDEX " + index);
    }

}