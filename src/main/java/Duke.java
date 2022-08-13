import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "_________________________________________________\nHello! I'm Duke" +
                "\nWhat can I do for you?\n_________________________________________________";
        System.out.println(greetings);

        ArrayList<String> tasks = new ArrayList<>();
        //int count = 1;

        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while (!Objects.equals(echo, "bye")) {
            if (Objects.equals(echo, "list")) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks available!");
                }
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
            } else {
                System.out.println("_________________________________________________\nadded: " + echo + "\n" +
                        "_________________________________________________\n");
                tasks.add(echo);
                //count++;
            }
            echo = sc.nextLine();
        }
        System.out.println("_________________________________________________\nBye. Hope to see you again soon!\n" +
                "_________________________________________________\n");
        /*
        String greetings = "_________________________________________________\nHello! I'm Duke" +
                "\nWhat can I do for you?\n_________________________________________________";
        System.out.println(greetings);

        String[] tasks = new String[100];
        int count = 1;

        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while (!Objects.equals(echo, "bye")) {
            if (Objects.equals(echo, "list")) {
                for (int i = 1; i < tasks.length; i++) {
                    System.out.println(i + ". " + tasks[i]);
                }
            }
            System.out.println("_________________________________________________\nadded: " + echo + "\n" +
                    "_________________________________________________\n");
            tasks[count] = echo;
            count++;
            echo = sc.nextLine();
        }
        System.out.println("_________________________________________________\nBye. Hope to see you again soon!\n" +
                "_________________________________________________\n");
        */
    }
}
