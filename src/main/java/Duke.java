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

    public static void main(String[] args) {
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

            } else {
                    print("added: " + input);
                    tasks.add(new Task(input));
            }
        }
    }
}
