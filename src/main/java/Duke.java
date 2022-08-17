import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?\n");
        Task[] lst = new Task[100];
        int count = 0;

        while(true) {
            String input = sc.nextLine();
            char check = ' ';
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + ". " + "[" + lst[i].getStatusIcon() + "] " + lst[i]);
                }
            }

            else if (input.length() == 6 && (input.substring(0,4)).equals("mark")) {
                char c = input.charAt(5);
                System.out.println(c);
                int index = Integer.parseInt(String.valueOf(c));
                Task t = lst[index-1];
                t = t.markAsDone();
                lst[index-1] = t;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + lst[index-1].getStatusIcon() + "] " + lst[index-1]);

            }

            else if (input.length() == 8 && (input.substring(0,6)).equals("unmark")) {
                char c = input.charAt(7);
                int index = Integer.parseInt(String.valueOf(c));

                Task t = lst[index-1];
                t = t.unMark();
                lst[index-1] = t;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + lst[index-1].getStatusIcon() + "] " + lst[index-1]);

            }
            else {

                Task t = new Task(input);
                lst[count] = t;

                System.out.println("added: " + input);
                count++;
            }
        }
    }
}
