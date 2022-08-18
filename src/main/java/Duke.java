import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected ArrayList<Task> tasks = new ArrayList<Task>();

    protected static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public enum Command {
        MARK, UNMARK
    }

    public enum Type {
        TODO, DEADLINE, EVENT
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

    public void changeTaskStatus(String[] splitInputArray, Command command) throws DukeException {
        if (splitInputArray.length < 2 || !isNumber(splitInputArray[1])) {
            throw new DukeException("Please specify a task number!");
        }

        int taskNum = Integer.parseInt(splitInputArray[1]);
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            if (command == Command.MARK) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }
        } else {
            throw new DukeException("No such task!");
        }
    }

    public void addTask(String[] splitInputArray, Type type) throws DukeException {
        boolean isToDo = type == Type.TODO;
        if (splitInputArray.length < 2) {
            throw new DukeException("Please provide a task description" + (isToDo ? "!" : " and a date / time!"));
        }

        Task task;
        String details = splitInputArray[1];
        if (isToDo) {
            task = new ToDo(details);
        } else {
            boolean isDeadline = type == Type.DEADLINE;
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
        if (splitInputArray.length < 2 || !isNumber(splitInputArray[1])) {
            throw new DukeException("Please specify a task number!");
        }

        int taskNum = Integer.parseInt(splitInputArray[1]);
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(taskNum - 1);
            System.out.println("Task removed:\n\t" + task);
        } else {
            throw new DukeException("No such task!");
        }
    }

    public void displayNumOfTasks() {
        System.out.println("You have " + tasks.size() + " task" + (tasks.size() != 1 ? "s" : "") + " in the list.");
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
                if (input.equals("bye")) {
                    duke.exit();
                } else if (input.equals("list")) {
                    duke.list();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    duke.changeTaskStatus(splitInputArray, Command.valueOf(firstWord.toUpperCase()));
                } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                    duke.addTask(splitInputArray, Type.valueOf(firstWord.toUpperCase()));
                    duke.displayNumOfTasks();
                } else if (firstWord.equals("delete")) {
                    duke.deleteTask(splitInputArray);
                    duke.displayNumOfTasks();
                } else {
                    throw new DukeException("Sorry! I don't know what that means :(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } while (!input.equals("bye"));

        in.close();
    }
}
