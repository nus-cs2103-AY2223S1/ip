import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SmartBot\nWhat can I do for you?");
        Task[] lst = new Task[100];
        int counter = 0;
        while(true) {
            Scanner sc = new Scanner(System.in);
            String first = sc.nextLine();
            if(first.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(first.equals("list")) {
                for (int i = 1; i < counter + 1; i++) {
                    System.out.println((i) + ". [" + lst[i - 1].getStatusIcon() + "] " +
                            lst[i - 1].description);
                }
            }
            else if(first.length() == 6) {
                if (first.substring(0, 4).equals("mark")) {
                    char index = first.charAt(5);
                    int number = Integer.parseInt(String.valueOf(index));
                    Task t = lst[number - 1];
                    t.mark();
                    System.out.println("Nice! I've marked this task as done:\n  [X] " + t.description);
                }
            }
            else if(first.length() == 8) {
                if(first.substring(0, 6).equals("unmark")) {
                    char index = first.charAt(7);
                    int number = Integer.parseInt(String.valueOf(index));
                    Task t = lst[number - 1];
                    t.unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + t.description);
                }
            }
            else {
                System.out.println("added: " + first);
                Task t = new Task(first);
                lst[counter] = t;
                counter++;
            }
        }
    }

}
