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
            try {
                if (input.equals("bye")) {
                    exit();
                    break;

                } else if (input.equals("list")) {
                    System.out.println(LINE + "Here are the tasks in your list:");
                    listTasks(list);
                    System.out.println(LINE);

                } else if (input.contains("unmark")) {
                    char taskNumber = input.charAt(7);
                    int number = Character.getNumericValue(taskNumber);

                    if (number <= 0 || number > count) {
                        throw new DukeException(LINE + "☹ OOPS!!! Sorry, I can't mark this as" +
                                " undone if it does not exist\n" + LINE);
                    } else {
                        unmarkTask(list, number - 1);
                    }

                } else if (input.contains("mark")) {
                    char taskNumber = input.charAt(5);
                    int number = Character.getNumericValue(taskNumber);

                    if (number <= 0 || number > count) {
                        throw new DukeException(LINE + "☹ OOPS!!! Sorry, I can't mark this as " +
                                "done if it does not exist :(\n" + LINE);
                    } else {
                        markTask(list, number - 1);
                    }

                } else if (input.contains("todo")) {
                    String task = input.substring(4);
                    ToDo todo = new ToDo(task);// get the task
                    if (task.isEmpty() || input.substring(5).isEmpty()) {
                        throw new DukeException(LINE + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                                + LINE);
                    } else {
                        list.add(count, todo);
                        count++;
                        System.out.println(LINE + "Got it. I've added this task:\n" + todo.toString());
                        System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);
                    }

                } else if (input.contains("deadline")) {
                        String[] deadline = input.split("deadline ", 2);
                        String taskBy = deadline[1];
                        String[] task = taskBy.split("/by", 2);//Split task into its description and deadline
                        Deadline d = new Deadline(task[0], task[1]);
                        list.add(d);
                        count++;
                        System.out.println(LINE + "Got it. I've added this task:\n" + d.toString());
                        System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);

                } else if (input.contains("event")) {
                    String[] event = input.split("event ", 2);
                    String taskAt = event[1];
                    String[] task = taskAt.split("/at", 2);// Split task into its description and timeline
                    Event e = new Event(task[0], task[1]);
                    list.add(e);
                    count++;
                    System.out.println(LINE + "Got it. I've added this task:\n" + e.toString());
                    System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);
                } else if (input.contains("delete")) {
                    String[] deletion = input.split("delete ", 2);
                    char deletionIndex = input.charAt(7);
                    int index = Character.getNumericValue(deletionIndex);

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
            }
        }
    }
}
