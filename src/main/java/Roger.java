import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.NumberFormatException;
import java.lang.StringIndexOutOfBoundsException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Roger {
    private List<Task> tasks = new ArrayList<>();

    private static void sayGoodbye() {
        System.out.println("Bye bye niece and nephew.");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    private static void sayHello() {
        String logo = "  ____                                \n" +
                      " |  _ \\    ___     __ _    ___   _ __ \n" +
                      " | |_) |  / _ \\   / _` |  / _ \\ | '__|\n" +
                      " |  _ <  | (_) | | (_| | |  __/ | |   \n" +
                      " |_| \\_\\  \\___/   \\__, |  \\___| |_|   \n" +
                      "                  |___/               ";
        System.out.println("Hello its \n" + logo);
        System.out.println("What you wan? What you wan?");
    }

    private void list() {
        if (this.tasks.isEmpty()) {
            System.out.println("No tasks. Nephew must be a failure.");
            return;
        }

        System.out.println("Nephew got a lot of things to do:");

        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            System.out.println(String.valueOf(i+1) + ". " + task.toString());
        }
    }

    private void list(LocalDate date) {
        System.out.println("Nephew have to do these things on " + date.toString() + ":");

        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isOnDate(date)) {
                    System.out.println(String.valueOf(i+1) + ". " + task.toString());
                }
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isOnDate(date)) {
                    System.out.println(String.valueOf(i+1) + ". " + task.toString());
                }
            }
        }
    }

    private void add(String taskName) {
        Task task = new Task(taskName);
        this.tasks.add(task);
        System.out.println("Nephew got new task to do:");
        System.out.println(task);
        System.out.println("Nephew now have " + Integer.toString(this.tasks.size()) + " tasks in the list.");
    }

    private void add(Event event) {
        this.tasks.add(event);
    }

    private void addToDo(String taskName) {
        ToDo toDo = new ToDo(taskName);
        this.tasks.add(toDo);
        System.out.println("Nephew got new to-do:");
        System.out.println("  " + toDo.toString());
        System.out.println("Nephew now have " + Integer.toString(this.tasks.size()) + " tasks in the list.");
    }

    private void addDeadline(String taskName, LocalDate date) {
        Deadline deadline = new Deadline(taskName, date);
        this.tasks.add(deadline);
        System.out.println("Nephew got new deadline, hurry up before I beat you:");
        System.out.println("  " + deadline);
        System.out.println("Nephew now have " + Integer.toString(this.tasks.size()) + " tasks in the list.");
    }

    private void addEvent(String taskName, LocalDate date) {
        Event event = new Event(taskName, date);
        this.tasks.add(event);
        System.out.println("Nephew so busy, got new event:");
        System.out.println("  " + event);
        System.out.println("Nephew now have " + Integer.toString(this.tasks.size()) + " tasks in the list.");
    }

    private void markAsDone(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.markAsDone();
        System.out.println("Fuiyoh, nephew so efficient! Finished this task:");
        System.out.println(task);
    }

    private void unmarkAsDone(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.unmarkAsDone();

        System.out.println("Haven't done yet, mark what mark? Unmarked this task:");
        System.out.println(task);
    }

    private void deleteTask(int taskNum) {
        Task task = this.tasks.remove(taskNum - 1);

        System.out.println("Haiya so lazy. Deleted this task:");
        System.out.println(task);
        System.out.println("Nephew now have " + Integer.toString(this.tasks.size()) + " tasks in the list.");
    }

    private void handleList(String arguments) throws RogerInvalidInputException {
        LocalDate date;

        String firstArg = arguments.split(" ")[0];
        if (firstArg.isBlank()) {
            this.list();
            return;
        }

        try {
            date = LocalDate.parse(firstArg);
        } catch (DateTimeParseException e) {
            throw new RogerInvalidInputException("Nephew, what you doing? List tasks with `list` or `list <yyyy-mm-dd>`");
        }

        this.list(date);
    }

    private void handleAddToDo(String arguments) throws RogerInvalidInputException {
        String taskName = arguments;

        if (taskName.equals("")) {
            throw new RogerInvalidInputException("Nephew, what you doing? Add to-do with `todo <name>`");
        }

        this.addToDo(taskName);
    }

    private void handleAddDeadline(String arguments) throws RogerInvalidInputException {
        String taskName;
        LocalDate date;

        int dateKeywordIndex = arguments.indexOf("/by");
        if (dateKeywordIndex < 0) {
            throw new RogerInvalidInputException("Nephew, what you doing? Add deadlines with `deadline <name> /by <yyyy-mm-dd>`");
        }

        try {
            taskName = arguments.substring(0, dateKeywordIndex - 1);
            date = LocalDate.parse(arguments.substring(dateKeywordIndex + 4));
        } catch (AssertionError | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new RogerInvalidInputException("Nephew, what you doing? Add deadlines with `deadline <name> /by <yyyy-mm-dd>`");
        }

        this.addDeadline(taskName, date);
    }

    private void handleAddEvent(String arguments) throws RogerInvalidInputException {
        String taskName;
        LocalDate date;

        int dateKeywordIndex = arguments.indexOf("/at");
        if (dateKeywordIndex < 0) {
            throw new RogerInvalidInputException("Nephew, what you doing? Add events with `event <name> /at <yyyy-mm-dd>`");
        }

        try {
            taskName = arguments.substring(0, dateKeywordIndex - 1);
            date = LocalDate.parse(arguments.substring(dateKeywordIndex + 4));
        } catch (AssertionError | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new RogerInvalidInputException("Nephew, what you doing? Add event with `event <name> /at <yyyy-mm-dd>`");
        }

        this.addEvent(taskName, date);
    }

    private void handleMark(String arguments) throws RogerInvalidInputException {
        int idx;

        try {
            String firstArg = arguments.split(" ")[0];
            idx = Integer.parseInt(firstArg);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new RogerInvalidInputException("Nephew, what you doing? Mark tasks as done with `mark <task number>`");
        }

        if (idx < 1 || this.tasks.size() < idx) {
            throw new RogerInvalidInputException("Task " + String.valueOf(idx) + " doesn't exist, just like my love for Aunty Helen.");
        }

        this.markAsDone(idx);
    }

    private void handleUnmark(String arguments) throws RogerInvalidInputException {
        int idx;

        try {
            String firstArg = arguments.split(" ")[0];
            idx = Integer.parseInt(firstArg);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new RogerInvalidInputException("Nephew, what you doing? Unmark tasks with `unmark <task number>`");
        }

        if (idx < 1 || this.tasks.size() < idx) {
            throw new RogerInvalidInputException("Task " + String.valueOf(idx) + " doesn't exist, just like my love for Aunty Helen.");
        }

        this.unmarkAsDone(idx);
    }

    private void handleDelete(String arguments) throws RogerInvalidInputException {
        int idx;

        try {
            String firstArg = arguments.split(" ")[0];
            idx = Integer.parseInt(firstArg);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new RogerInvalidInputException("Nephew, what you doing? Delete tasks with `delete <task number>`");
        }

        if (idx < 1 || this.tasks.size() < idx) {
            throw new RogerInvalidInputException("Task " + String.valueOf(idx) + " doesn't exist, just like my love for Aunty Helen.");
        }

        this.deleteTask(idx);
    }

    private void handleUnknownInput() {
        System.out.println("Uncle really don't understand.");
    }

    public static void main(String[] args) {
        /**
         * Logic for Roger program. Takes user input and matches it
         * with various commands (list, mark, unmark, todo, deadline, event, bye).
         * 'bye' shuts Roger down.
         */
        Roger roger = new Roger();
        Scanner scanner = new Scanner(System.in);

        roger.sayHello();

        repl: while (true) {

            String input = scanner.nextLine();
            int i = input.indexOf(" ");
            String command = i < 0 ? input : input.substring(0, i);
            String arguments = i < 0 ? "" : input.substring(i + 1);

            try {
                switch (command) {
                    case "bye":
                        Roger.sayGoodbye();
                        break repl;
                    case "list":
                        roger.handleList(arguments);
                        break;
                    case "mark":
                        roger.handleMark(arguments);
                        break;
                    case "unmark":
                        roger.handleUnmark(arguments);
                        break;
                    case "todo":
                        roger.handleAddToDo(arguments);
                        break;
                    case "deadline":
                        roger.handleAddDeadline(arguments);
                        break;
                    case "event":
                        roger.handleAddEvent(arguments);
                        break;
                    case "delete":
                        roger.handleDelete(arguments);
                        break;
                    default:
                        roger.handleUnknownInput();
                }
            } catch (RogerInvalidInputException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}
