import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "____________________________________________________________\n";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int count = 0;

    public static void greeting() {
        String initMessage = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(LINE + initMessage + LINE);
    }

    public static void exit() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + goodbyeMessage + LINE);
    }

    public static void listTasks(ArrayList<Task> tasklist) {
        int counter = 1;
        System.out.println(LINE + "Here are the tasks in your list:");
        for (Task t : tasklist) {
            System.out.println(counter + "." + t.toString());
            counter++;
        }
        System.out.println(LINE);
    }
    public static void unmarkTask(String input) throws DukeException {
        String taskNumber = input.substring(7);
        int number = Integer.parseInt(taskNumber);

        if (number <= 0 || number > taskList.size()) {
            throw new DukeException(LINE + "☹ OOPS!!! Sorry, I can't mark this as" +
                    " undone if it does not exist\n" + LINE);
        }
        Task task = taskList.get(number - 1);
        task.markAsUndone();
        System.out.println(LINE + "OK, I've marked this task as not done yet:");
        System.out.println(task.toString() + "\n" + LINE);
    }

    public static void markTask(String input) throws DukeException {
        String taskNumber = input.substring(5);
        int number = Integer.parseInt(taskNumber);

        if (number <= 0 || number > taskList.size()) {
            throw new DukeException(LINE + "☹ OOPS!!! Sorry," +
                    " I can't mark this as done if it does not exist :(\n" + LINE);
        }
        Task task = taskList.get(number - 1);
        task.markAsDone();
        System.out.println(LINE + "Nice! I've marked this task as done:");
        System.out.println(task.toString() + "\n" + LINE);
    }
    public static void addTask(String input) throws DukeException {
        String[] taskArray = input.split(" ", 2);
        String task = taskArray[0];
        switch (task) {
        case "todo":
            if (taskArray.length < 2 || taskArray[1].isEmpty()) {
                throw new DukeException(LINE + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                        + LINE);
            } else {
                Task todo = new ToDo(taskArray[1]);
                int index = taskList.size() + 1;
                taskList.add(todo);
                count++;
                System.out.println(LINE + "Got it. I've added this task:\n" + todo.toString());
                System.out.println("Now you have " + index + " tasks in this list.\n" + LINE);
                break;
            }
        case "deadline":
            String[] deadlineArray = input.split("deadline", 2);
            String taskBy = deadlineArray[1];
            String[] taskDeadline = taskBy.split("/by", 2);

            if (deadlineArray.length < 2 || deadlineArray[1].isEmpty()) {
                throw new DukeException(LINE + "☹ OOPS!!! The description of a deadline cannot be empty.\n"
                        + LINE);
            }

            if (taskDeadline.length < 2 || taskDeadline[1].isEmpty()) {
                throw new DukeException(LINE + "☹ OOPS!!! Please include a /by for your deadline. " +
                        "E.g /by Aug 6th.\n"
                        + LINE);
            } else {
                Deadline d = new Deadline(taskDeadline[0].substring(1), " " + taskDeadline[1].substring(1));
                int index = taskList.size() + 1;
                taskList.add(d);
                count++;
                System.out.println(LINE + "Got it. I've added this task:\n" + d.toString());
                System.out.println("Now you have " + index + " tasks in this list.\n" + LINE);
                break;
            }
        case "event":
            String[] eventArray = input.split("event", 2);
            String taskAt = eventArray[1];
            String[] taskEvent = taskAt.split("/at", 2);

            if (eventArray.length < 2 || eventArray[1].isEmpty()) {
                throw new DukeException(LINE + "☹ OOPS!!! The description of a event cannot be empty.\n"
                        + LINE);
            }

            if (taskEvent.length < 2 || taskEvent[1].isEmpty()) {
                throw new DukeException(LINE + "☹ OOPS!!! Please include a /at for your deadline. " +
                        "E.g /at 2-4pm.\n"
                        + LINE);
            } else {
                Event e = new Event(taskEvent[0].substring(1), " " + taskEvent[1].substring(1));
                int index = taskList.size() + 1;
                taskList.add(e);
                count++;
                System.out.println(LINE + "Got it. I've added this task:\n" + e.toString());
                System.out.println("Now you have " + index + " tasks in this list.\n" + LINE);
                break;
            }
        default:
            System.out.println("I can't add this task. Sorry :(");
        }
    }

    public static void deleteTask(String input) throws DukeException {
        String deletionIndex = input.substring(7);
        int index = Integer.parseInt(deletionIndex);

        if (index <= 0 || index > taskList.size()) {
            throw new DukeException(LINE + "☹ OOPS!!! I can't remove this if it does not exist\n"
                    + LINE);
        }
        Task task = taskList.remove(index - 1);
        count--;
        System.out.println(LINE + "Noted. I've removed this task:");
        System.out.println(task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.\n" + LINE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greeting();

        while (true) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            String first = inputArray[0];
            try {
                if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.equals("list")) {
                    listTasks(taskList);
                } else if (first.equals("unmark")) {
                    unmarkTask(input);
                } else if (first.equals("mark")) {
                    markTask(input);
                } else if (first.equals("todo")) {
                    addTask(input);
                } else if (first.equals("deadline")) {
                    addTask(input);
                } else if (first.equals("event")) {
                    addTask(input);
                } else if (first.equals("delete")) {
                        deleteTask(input);
                } else {
                    throw new DukeException(LINE + "☹ OOPS!!! I'm sorry, but I don't know what that means :(\n"
                            + LINE);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(LINE + "☹ OOPS!!! Please make sure you include the proper index.\n" + LINE);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(LINE + "☹ OOPS!!! Please make sure you include the proper index.\n" + LINE);
            }
        }
    }
}
