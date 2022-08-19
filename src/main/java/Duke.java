import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String greetings = "Hello! I'm Ekud \n" + "What can I do for you?";
    private static String banner = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void print(String msg) {
        System.out.println(banner);
        System.out.println(msg);
        System.out.println(banner);
    }

    private static String getTaskName(String[] msg) {
        String input = "";
        for (int i = 1; i < msg.length; i++) {
            input += msg[i];
            if (i < msg.length - 1) input += " ";
        }
        return input;
    }

    private static void printAddTask(String msg) {
        print("Got it. I've added this task:\n" + msg + "\nNow you have " + tasks.size() +  " tasks in the list.");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        print(greetings);

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                print("Cya!");
            } else if (input.equals("list")) {

                String list = "";
                for (int i = 0; i < tasks.size(); i++) {
                    list += (i + 1) + "." + tasks.get(i);
                    if (i != tasks.size() - 1) list += "\n";
                }
                print(list);

            } else if (input.startsWith("mark")) {

                String[] msg = input.split(" ");
                if (msg.length < 2) {
                    print("nothing to mark!");
                    continue;
                }
                int index = Integer.valueOf(msg[1]) - 1;
                tasks.get(index).mark();
                print("I've marked this task as done: \n" + tasks.get(index));

            } else if (input.startsWith("unmark")) {

                String[] msg = input.split(" ");
                if (msg.length < 2) {
                    print("nothing to unmark!");
                    continue;
                }
                int index = Integer.valueOf(msg[1]) - 1;
                tasks.get(index).unmark();
                print("I've marked this task as undone: \n" + tasks.get(index));

            } else if (input.startsWith("todo")) {

                String[] msg = input.split(" ");
                if (msg.length < 2) {
                    print("nothing to add!");
                    continue;
                }
                input = getTaskName(msg);
                tasks.add(new Todo(input));
                printAddTask(input);

            } else if (input.startsWith("deadline")) {
                String[] msg = input.split("/");
                if (msg.length < 2) {
                    print("no date specified!");
                    continue;
                }
                String[] tmp = msg[0].split(" ");
                if (msg.length < 2) {
                    print("nothing to add!");
                    continue;
                }
                input = getTaskName(tmp);
                tasks.add(new Deadline(input, msg[1]));
                printAddTask(input);
            } else if (input.startsWith("event")) {
                String[] msg = input.split("/");
                if (msg.length < 2) {
                    print("no date specified!");
                    continue;
                }
                String[] tmp = msg[0].split(" ");
                if (msg.length < 2) {
                    print("nothing to add!");
                    continue;
                }
                input = getTaskName(tmp);
                tasks.add(new Event(input, msg[1]));
                printAddTask(input);
            } else {
                throw(new Exception("I do not understand!"));
            }
        }
    }
}
