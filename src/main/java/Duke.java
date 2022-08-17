import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskArray = new ArrayList<>(100);
    static int index = 0;

    public static String printList() {
        String list = "Here are the tasks in your list:\n";
        int size = taskArray.size();
        for (int i = 0; i < size; i++) {
            if (taskArray.get(i) != null) {
                list += ((i + 1) + "." + taskArray.get(i).toString() + "\n");
            }
        }
        return list;
    }

    public static void markList(int i) {
        taskArray.get(i - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + taskArray.get(i - 1).toString() + "\n");
    }

    public static void unMarkList(int i) {
        taskArray.get(i - 1).unMarkTask();
        System.out.println("OK, I've marked this task as not done yet:\n " + taskArray.get(i - 1).toString() + "\n");
    }

    public static void main(String[] args) throws DukeException {
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println(printList());
            } else if (input.equals("mark")) {
                int number = Integer.parseInt(sc.next());
                markList(number);
            } else if (input.equals("unmark")) {
                int number = Integer.parseInt(sc.next());
                unMarkList(number);
            } else if (input.equals("todo")) {
                String toDo = sc.nextLine();
                if (toDo.equals("")) {
                    DukeException toDoError = new DukeException("☹ OOPS!!! " +
                            "The description of a todo cannot be empty.\n");
                    System.out.println(toDoError.toString());
                } else {
                    taskArray.add(index, new Todo(toDo));
                    System.out.println("Got it. I've added this task:\n " + taskArray.get(index).toString() +
                            "\nNow you have " + (index + 1) + " tasks in the list.\n");
                    index += 1;
                }
            } else if (input.equals("deadline")) {
                String deadlineTask = sc.nextLine();
                if (deadlineTask.equals("")) {
                    DukeException deadLineError = new DukeException("☹ OOPS!!! " +
                            "The description of a deadline cannot be empty.\n");
                    System.out.println(deadLineError.toString());
                } else {
                    int integer = deadlineTask.indexOf("/by");
                    String description = deadlineTask.substring(0, integer - 1);
                    String by = deadlineTask.substring(integer + 4);
                    taskArray.add(index, new Deadline(description, by));
                    System.out.println("Got it. I've added this task:\n " + taskArray.get(index).toString() +
                            "\nNow you have " + (index + 1) + " tasks in the list.\n");
                    index += 1;
                }
            } else if (input.equals("event")) {
                String event = sc.nextLine();
                if (event.equals("")) {
                    DukeException deadLineError = new DukeException("☹ OOPS!!! " +
                            "The description of a event cannot be empty.\n");
                    System.out.println(deadLineError.toString());
                } else {
                    int integer = event.indexOf("/at");
                    String description = event.substring(0, integer - 1);
                    String at = event.substring(integer + 4);
                    taskArray.add(index, new Event(description, at));
                    System.out.println("Got it. I've added this task:\n " + taskArray.get(index).toString() +
                            "\nNow you have " + (index + 1) + " tasks in the list.\n");
                    index += 1;
                }
            } else if (input.equals("delete")) {
                int deleteIndex = sc.nextInt();
                Task task = taskArray.get(deleteIndex - 1);
                taskArray.remove(deleteIndex - 1);
                index -= 1;
                System.out.println("Noted. I've removed this task:\n " + task.toString() + "\nNow you have "
                        + index + " tasks in the list.\n");
            } else {
                input += sc.nextLine();
                DukeException incorrectInput = new DukeException("☹ OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(\n");
                System.out.println(incorrectInput.toString());

            }
        }

    }

}