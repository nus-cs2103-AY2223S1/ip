import java.util.Scanner;

public class Duke {
    static String[] storage = new String[100];
    static Task[] taskList = new Task[100];
    static int index = 0;

    public static String printList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < 100; i++) {
            if (storage[i] != null) {
                list += ((i + 1) + ". " + "[" + taskList[i].getStatusIcon() + "] " + storage[i] + "\n");
            }
        }
        return list;
    }

    public static void markList(int i) {
        taskList[i - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n [" + taskList[i - 1].getStatusIcon() + "] "
                + storage[i - 1] + "\n");
    }

    public static void unMarkList(int i) {
        taskList[i - 1].unMarkTask();
        System.out.println("OK, I've marked this task as not done yet:\n [" + taskList[i - 1].getStatusIcon() + "] "
                + storage[i - 1] + "\n");
    }

    public static void main(String[] args) {
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);
        Scanner sc = new Scanner(System.in);
        while (true) {
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
