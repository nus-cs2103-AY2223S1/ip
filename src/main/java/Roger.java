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
        String logo = "██████╗░░█████╗░░██████╗░███████╗██████╗░ \n"
                    + "██╔══██╗██╔══██╗██╔════╝░██╔════╝██╔══██╗ \n"
                    + "██████╔╝██║░░██║██║░░██╗░█████╗░░██████╔╝ \n"
                    + "██╔══██╗██║░░██║██║░░╚██╗██╔══╝░░██╔══██╗ \n"
                    + "██║░░██║╚█████╔╝╚██████╔╝███████╗██║░░██║ \n"
                    + "╚═╝░░╚═╝░╚════╝░░╚═════╝░╚══════╝╚═╝░░╚═╝ ";
        System.out.println("Hello its \n" + logo);
        System.out.println("What you wan? What you wan?");
    }

    private void list() {
        if (this.tasks.isEmpty()) {
            System.out.println("No tasks. Nephew must be a failure.");
            return;
        }

        System.out.println("Nephew got a lot of things to do:");

        int i = 1;
        for (Task task: tasks) {
            System.out.println(String.valueOf(i) + ". " + task.toString());
            ++i;
        }
    }

    private void add(String taskName) {
        Task task = new Task(taskName);
        this.tasks.add(task);
        System.out.println("Nephew got new task to do:");
        System.out.println(task);
        System.out.println("Nephew now have " + Integer.toString(this.tasks.size()) + " tasks in the list.");
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

    public static void main(String[] args) {
        Roger roger = new Roger();
        roger.sayHello();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                roger.sayGoodbye();
                break;
            } else if (input.equals("list")) {
                roger.list();
            } else if (input.startsWith("mark")) {
                int idx;
                try {
                    idx = Integer.parseInt(input.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Nephew must tell me which task to mark!");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Nephew must give me the task number!");
                    continue;
                }

                roger.markAsDone(idx);
            } else if (input.startsWith("unmark")) {
                int idx;
                try {
                    idx = Integer.parseInt(input.substring(7));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Nephew must tell me which task to unmark!");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Nephew must give me the task number!");
                    continue;
                }

                roger.unmarkAsDone(idx);
            } else if (input.startsWith("todo")) {
                String taskName = input.substring(5);
                roger.addToDo(taskName);
            } else if (input.startsWith("deadline")) {
                int dateIdx = input.indexOf("/by");
                if (dateIdx == -1) {
                    System.out.println("Nephew must tell me when is the deadline, with /by");
                    continue;
                }

                String taskName = input.substring(9, dateIdx - 1);
                String date = input.substring(dateIdx + 4);
                roger.addDeadline(taskName, date);
            } else if (input.startsWith("event")) {
                int periodIdx = input.indexOf("/at");
                if (periodIdx == -1) {
                    System.out.println("Nephew must tell me when is the event, with /at");
                    continue;
                }

                String taskName = input.substring(6, periodIdx - 1);
                String period = input.substring(periodIdx + 4);
                roger.addEvent(taskName, period);
            } else {
                // Legacy Task type
                roger.add(input);
            }

        }

    }
}
