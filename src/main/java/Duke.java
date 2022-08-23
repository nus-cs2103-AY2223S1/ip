import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;

public class Duke {

    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, INVALID
    }

    private static ArrayList<Task> addedTask = new ArrayList<>(100);

    private static int getSize() {
        return Duke.addedTask.size();
    }

    private static void reportSize() {
        if (Duke.getSize() == 1) {
            System.out.println("There is " + Duke.getSize() + " task in your list.");
        } else {
            System.out.println("There are " + Duke.getSize() + " tasks in your list.");
        }
    }

    private static void addTask(Task task) {
        Duke.addedTask.add(task);
        TaskSaver.saveToDirectory(Duke.addedTask);
        System.out.println("Task added:");
        System.out.println(task);
        reportSize();
    }

    private static void deleteTask(int index) throws TaskNotFoundException {
        if (index > Duke.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        System.out.println("Task successfully removed:");
        System.out.println(Duke.addedTask.remove(index - 1));
        TaskSaver.saveToDirectory(Duke.addedTask);
        reportSize();
    }

    private static void listTask() {
        System.out.println("Listing your current tasks:");
        for (int i = 0; i < Duke.getSize(); ++i) {
            System.out.println((i + 1) + "." + Duke.addedTask.get(i));
        }
    }

    private static void markTask(int index) throws TaskNotFoundException {
        if (index > Duke.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Duke.addedTask.get(index - 1).mark();
        TaskSaver.saveToDirectory(Duke.addedTask);
    }

    private static void unmarkTask(int index) throws TaskNotFoundException {
        if (index > Duke.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Duke.addedTask.get(index - 1).unmark();
        TaskSaver.saveToDirectory(Duke.addedTask);
    }

    private static LocalDateTime parseDateTime(String dateTime) throws IllegalDateTimeException {
        // abide by format dd-MM-yyyy-HH-mm (e.g. 23-04-2000-23-04)
        String[] dateTimeData = dateTime.split("-");
        if (dateTimeData.length != 5) {
            throw new IllegalDateTimeException(dateTime);
        } else {
            try {
                int[] dateTimeDataInt = new int[5];
                for (int i = 0; i < dateTimeData.length; ++i) {
                    dateTimeDataInt[i] = Integer.parseInt(dateTimeData[i]);
                }
                LocalDateTime localDateTime = LocalDateTime.of(
                        dateTimeDataInt[2], dateTimeDataInt[1],
                        dateTimeDataInt[0], dateTimeDataInt[3],
                        dateTimeDataInt[4]);
                return localDateTime;
            } catch (NumberFormatException e) {
                throw new IllegalDateTimeException(dateTime);
            } catch (DateTimeException e) {
                throw new IllegalDateTimeException(dateTime);
            }
        }
    }

    private static Command parseCommand(String command) 
            throws CommandNotFoundException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
    }

    private static void processCommand(String command)
            throws DukeException {
        String[] commandArgs = command.split(" ", 2);
        Command keyword = Duke.parseCommand(commandArgs[0]);
        switch (keyword) {
            case BYE:
                System.out.println("Come again soon!");
                System.exit(0);
            case LIST:
                Duke.listTask();
                break;
            case MARK:
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Duke.markTask(Integer.parseInt(commandArgs[1]));
                break;
            case UNMARK:
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Duke.unmarkTask(Integer.parseInt(commandArgs[1]));
                break;
            case DELETE:
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Duke.deleteTask(Integer.parseInt(commandArgs[1]));
                break;
            case TODO:
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                Todo todo = new Todo(commandArgs[1]);
                Duke.addTask(todo);
                break;
            case DEADLINE:
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                String[] deadlineDetails = commandArgs[1].split(" /by ");
                if (deadlineDetails.length == 1) {
                    throw new IllegalCommandException(commandArgs[0]);
                }
                Duke.addTask(new Deadline(deadlineDetails[0],
                        parseDateTime(deadlineDetails[1])));
                break;
            case EVENT:
                if (commandArgs.length == 1) {
                    throw new EmptyCommandException(commandArgs[0]);
                }
                String[] eventDetails = commandArgs[1].split(" /at ");
                if (eventDetails.length <= 1) {
                    throw new IllegalCommandException(commandArgs[0]);
                }
                Duke.addTask(new Event(eventDetails[0],
                        parseDateTime(eventDetails[1])));
                break;
            default:
                throw new CommandNotFoundException(command);
        }
    }

    public static void greet() {
        System.out.println("Hi, my name is Duke and it's power hour!");
        System.out.println("Please input all dates and times in the following format:");
        System.out.println("dd-MM-yyyy-HH-mm (e.g. 23-04-2000-23-04)");
        System.out.println("***********************");
    }

    public static void main(String[] args) {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                Duke.processCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();   
    }
}
