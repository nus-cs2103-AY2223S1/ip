import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected ArrayList<Task> tasks = new ArrayList<Task>();

    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public Command getCommand(String str) throws DukeException {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry! I don't know what that means :(");
        }
    }

    public void greet() {
        System.out.println("Hello! I'm Pip :)\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Goodbye and see you again soon!");
    }

    public void list() {
        if (!tasks.isEmpty()) {
            System.out.println("Task list:");
            for (int i = 0; i < tasks.size(); i++) {
                int taskNum = i + 1;
                Task task = tasks.get(i);
                System.out.println(taskNum + "." + task);
            }
        } else {
            System.out.println("Your task list is empty!");
        }
    }

    protected static int getTaskNumber(String[] splitInputArray) throws DukeException {
        if (splitInputArray.length > 1) {
            try {
                return Integer.parseInt(splitInputArray[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a task number!");
            }
        } else {
            throw new DukeException("Please specify a task number!");
        }
    }

    public void changeTaskStatus(String[] splitInputArray) throws DukeException {
        int taskNum = getTaskNumber(splitInputArray);
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            if (splitInputArray[0].equals("mark")) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }
        } else {
            throw new DukeException("No such task!");
        }
    }

    public void addTask(String[] splitInputArray) throws DukeException {
        String command = splitInputArray[0];
        boolean isToDo = command.equals("todo");
        if (splitInputArray.length < 2) {
            throw new DukeException("Please provide a task description" + (isToDo ? "!" : " and a date / time!"));
        }

        Task task;
        String details = splitInputArray[1];
        if (isToDo) {
            task = new ToDo(details);
        } else {
            boolean isDeadline = command.equals("deadline");
            int pos = details.indexOf(isDeadline ? " /by " : " /at ");
            if (pos > 0 && details.length() > pos + 5) {
                String description = details.substring(0, pos);
                String by = details.substring(pos + 5);
                task = isDeadline ? new Deadline(description, by) : new Event(description, by);
            } else {
                throw new DukeException("Please provide a task description and a date / time!");
            }
        }
        tasks.add(task);
        System.out.println("Task added:\n\t" + task);
    }

    public void deleteTask(String[] splitInputArray) throws DukeException {
        int taskNum = getTaskNumber(splitInputArray);
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(taskNum - 1);
            System.out.println("Task removed:\n\t" + task);
        } else {
            throw new DukeException("No such task!");
        }
    }

    public void displayNumOfTasks() {
        System.out.println("You have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner in = new Scanner(System.in);
        
        String input;
        do {
            input = in.nextLine();
            String[] splitInputArray = input.split(" ", 2);
            String firstWord = splitInputArray[0];
            try {
                switch (duke.getCommand(firstWord)) {
                    case BYE:
                        duke.exit();
                        break;
                    case LIST:
                        duke.list();
                        break;
                    case MARK:
                    case UNMARK:
                        duke.changeTaskStatus(splitInputArray);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        duke.addTask(splitInputArray);
                        duke.displayNumOfTasks();
                        break;
                    case DELETE:
                        duke.deleteTask(splitInputArray);
                        duke.displayNumOfTasks();
                        break;
                    default:
                        throw new DukeException("Sorry! I don't know what that means :(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } while (!input.equals("bye"));

        in.close();
    }
}
