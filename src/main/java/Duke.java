import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "____________________________________________________________\n";

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
        for (Task t : tasklist) {
            System.out.println(counter + "." + t.toString());
            counter++;
        }
    }
    public static void unmarkTask(ArrayList<Task> taskList, int index) {
        System.out.println(LINE + "OK, I've marked this task as not done yet:");
        Task task = taskList.get(index);
        task.markAsUndone();
        System.out.println(task.toString() + "\n" + LINE);
    }

    public static void markTask(ArrayList<Task> taskList, int index) {
        System.out.println(LINE + "Nice! I've marked this task as done:");
        Task task = taskList.get(index);
        task.markAsDone();
        System.out.println(task.toString() + "\n" + LINE);
    }

    public static void addTask(ArrayList<Task> taskList, Task task) {
        int count = taskList.size() + 1;
        taskList.add(task);
        System.out.println(LINE + "Got it. I've added this task:\n" + task.toString());
        System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);
    }

    public static void deleteTask(ArrayList<Task> taskList, int index) {
        System.out.println(LINE + "Noted. I've removed this task:");
        Task task = taskList.remove(index);
        System.out.println(task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list.\n" + LINE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        ArrayList<Task> list = new ArrayList<>();
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
                    System.out.println(LINE + "Here are the tasks in your list:");
                    listTasks(list);
                    System.out.println(LINE);
                } else if (first.equals("unmark")) {
                    String taskNumber = input.substring(7);
                    int number = Integer.parseInt(taskNumber);

                    if (number <= 0 || number > count) {
                        throw new DukeException(LINE + "☹ OOPS!!! Sorry, I can't mark this as" +
                                " undone if it does not exist\n" + LINE);
                    } else {
                        unmarkTask(list, number - 1);
                    }
                } else if (first.equals("mark")) {
                    String taskNumber = input.substring(5);
                    int number = Integer.parseInt(taskNumber);

                    if (number <= 0 || number > count) {
                        throw new DukeException(LINE + "☹ OOPS!!! Sorry," +
                                " I can't mark this as done if it does not exist :(\n" + LINE);
                    } else {
                        markTask(list, number - 1);
                    }
                } else if (first.equals("todo")) {
                    String[] taskArray = input.split(" ", 2);

                    if (taskArray.length < 2 || taskArray[1].isEmpty()) {
                        throw new DukeException(LINE + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                                + LINE);
                    } else {
                        Task todo = new ToDo(taskArray[1]);
                        addTask(list, todo);
                        count++;
                    }
                } else if (first.equals("deadline")) {
                        String[] deadline = input.split("deadline", 2);
                        String taskBy = deadline[1];
                        String[] task = taskBy.split("/by", 2);

                    if (deadline.length < 2 || deadline[1].isEmpty()) {
                        throw new DukeException(LINE + "☹ OOPS!!! The description of a deadline cannot be empty.\n"
                                + LINE);
                    }

                    if (task.length < 2 || task[1].isEmpty()) {
                        throw new DukeException(LINE + "☹ OOPS!!! Please include a /by for your deadline. " +
                                "E.g /by Aug 6th.\n"
                                + LINE);
                    } else {
                        Deadline d = new Deadline(task[0].substring(1), task[1]);
                        addTask(list, d);
                        count++;
                    }
                } else if (first.equals("event")) {
                    String[] event = input.split("event", 2);
                    String taskAt = event[1];
                    String[] task = taskAt.split("/at", 2);

                    if (event.length < 2 || event[1].isEmpty()) {
                        throw new DukeException(LINE + "☹ OOPS!!! The description of a event cannot be empty.\n"
                                + LINE);
                    }

                    if (task.length < 2 || task[1].isEmpty()) {
                        throw new DukeException(LINE + "☹ OOPS!!! Please include a /at for your deadline. " +
                                "E.g /at 2-4pm.\n"
                                + LINE);
                    } else {
                        Event e = new Event(task[0].substring(1), task[1]);
                        addTask(list, e);
                        count++;
                    }
                } else if (first.equals("delete")) {
                    String deletionIndex = input.substring(7);
                    int index = Integer.parseInt(deletionIndex);

                    if (index <= 0 || index > count) {
                        throw new DukeException(LINE + "☹ OOPS!!! I can't remove this if it does not exist\n"
                                + LINE);
                    } else {
                        deleteTask(list, index - 1);
                        count--;
                    }
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
