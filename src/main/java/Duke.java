import java.util.Scanner;

public class Duke {
    static String[] storage = new String[100];
    static Task[] taskList = new Task[100];
    static int index = 0;

    public static String printList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < 100; i++) {
            if (storage[i] != null) {
                list += ((i + 1) + "." + taskList[i].toString() + "\n");
            }
        }
        return list;
    }

    public static void markList(int i) {
        taskList[i - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + taskList[i - 1].toString() + "\n");
    }

    public static void unMarkList(int i) {
        taskList[i - 1].unMarkTask();
        System.out.println("OK, I've marked this task as not done yet:\n " + taskList[i - 1].toString() + "\n");
    }

    public static void main(String[] args) {
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
                storage[index] = toDo;
                taskList[index] = new Todo(toDo);
                System.out.println("Got it. I've added this task:\n " + taskList[index].toString() +
                        "\nNow you have " + (index + 1) + " tasks in the list.\n");
                index += 1;
            } else if (input.equals("deadline")) {
                String activity = "";
                String word = sc.next();
                while(!word.equals("/by")) {
                    activity += (" " + word);
                    word = sc.next();
                }
                String deadline = sc.nextLine();
                storage[index] = deadline;
                taskList[index] = new Deadline(activity, deadline);
                System.out.println("Got it. I've added this task:\n " + taskList[index].toString() +
                        "\nNow you have " + (index + 1) + " tasks in the list.\n");
                index += 1;
            } else if (input.equals("event")) {
                String activity = "";
                String word = sc.next();
                while(!word.equals("/at")) {
                    activity += (" " + word);
                    word = sc.next();
                }
                String event = sc.nextLine();
                storage[index] = event;
                taskList[index] = new Event(activity, event);
                System.out.println("Got it. I've added this task:\n " + taskList[index].toString() +
                        "\nNow you have " + (index + 1) + " tasks in the list.\n");
                index += 1;
            } else {
                input += sc.nextLine();
                System.out.println("added: " + input + "\n");
                storage[index] = input;
                taskList[index] = new Task(input);
                index += 1;
            }
        }
    }
}
