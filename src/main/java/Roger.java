import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.NumberFormatException;
import java.lang.StringIndexOutOfBoundsException;

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

    private void addDeadline(String taskName, String date) {
        Deadline deadline = new Deadline(taskName, date);
        this.tasks.add(deadline);
        System.out.println("Nephew got new deadline, hurry up before I beat you:");
        System.out.println("  " + deadline);
        System.out.println("Nephew now have " + Integer.toString(this.tasks.size()) + " tasks in the list.");
    }

    private void addEvent(String taskName, String period) {
        Event event = new Event(taskName, period);
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

    private void handleAddToDo(String input) throws RogerInvalidInputException {
        String taskName;

        try {
            taskName = input.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new RogerInvalidInputException("Nephew must tell me the to-do name!");
        }

        this.addToDo(taskName);
    }

    private void handleAddDeadline(String input) throws RogerInvalidInputException {
        int dateIdx = input.indexOf("/by");

        if (dateIdx == -1) {
            if (input.length() <= 9) {
                throw new RogerInvalidInputException("Nephew must tell me the deadline name!");
            } else {
                throw new RogerInvalidInputException("Nephew must tell me when is the deadline, with /by");
            }
        }

        String taskName = input.substring(9, dateIdx - 1);
        String date = input.substring(dateIdx + 4);

        if (taskName.isBlank()) {
            throw new RogerInvalidInputException("Nephew must tell me the deadline name!");
        }

        this.addDeadline(taskName, date);
    }

    private void handleAddEvent(String input) throws RogerInvalidInputException {
        int periodIdx = input.indexOf("/at");

        if (periodIdx == -1) {
            if (input.length() <= 6) {
                throw new RogerInvalidInputException("Nephew must tell me the event name!");
            } else {
                throw new RogerInvalidInputException("Nephew must tell me when is the event, with /at");
            }
        }

        String taskName = input.substring(6, periodIdx - 1);
        String period = input.substring(periodIdx + 4);

        if (period.isBlank()) {
            throw new RogerInvalidInputException("Nephew must tell me the event name!");
        }

        this.addEvent(taskName, period);
    }


    private void handleMark(String input) throws RogerInvalidInputException {
        int idx;

        try {
            idx = Integer.parseInt(input.substring(5));
        } catch (StringIndexOutOfBoundsException e) {
            throw new RogerInvalidInputException("Nephew must tell me which task to mark!");
        } catch (NumberFormatException e) {
            throw new RogerInvalidInputException("Nephew must give me the task number!");
        }

        if (idx < 1 || this.tasks.size() < idx) {
            throw new RogerInvalidInputException("Task " + String.valueOf(idx) + " doesn't exist, just like my love for Aunty Helen.");
        }

        this.markAsDone(idx);
    }


    private void handleUnmark(String input) throws RogerInvalidInputException {
        int idx;

        try {
            idx = Integer.parseInt(input.substring(7));
        } catch (StringIndexOutOfBoundsException e) {
            throw new RogerInvalidInputException("Nephew must tell me which task to unmark!");
        } catch (NumberFormatException e) {
            throw new RogerInvalidInputException("Nephew must give me the task number!");
        }

        if (idx < 1 || this.tasks.size() < idx) {
            throw new RogerInvalidInputException("Task " + String.valueOf(idx) + " doesn't exist, just like my love for Aunty Helen.");
        }

        this.unmarkAsDone(idx);
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

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    roger.sayGoodbye();
                    break;
                } else if (input.equals("list")) {
                    roger.list();
                } else if (input.startsWith("mark")) {
                    roger.handleMark(input);
                } else if (input.startsWith("unmark")) {
                    roger.handleUnmark(input);
                } else if (input.startsWith("todo")) {
                    roger.handleAddToDo(input);
                } else if (input.startsWith("deadline")) {
                    roger.handleAddDeadline(input);
                } else if (input.startsWith("event")) {
                    roger.handleAddEvent(input);
                } else {
                    roger.handleUnknownInput();
                }
            } catch (RogerInvalidInputException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}
