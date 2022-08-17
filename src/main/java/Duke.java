import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?\n");
        Task[] lst = new Task[100];
        int count = 0;

        while(true) {
            String input = sc.nextLine();
            String[] words = input.split(" ",2);
            String first = words[0];

            char check = ' ';
            if (first.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else if (first.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println("Here are the tasks in your list: \n" + (i+1) + "." + lst[i]);
                }
            }

            else if (first.equals("mark")) {
                char c = input.charAt(5);
                System.out.println(c);
                int index = Integer.parseInt(String.valueOf(c));
                Task t = lst[index-1];
                t.markAsDone();
                lst[index-1] = t;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(lst[index-1]);

            }

            else if (first.equals("unmark")) {
                char c = input.charAt(7);
                int index = Integer.parseInt(String.valueOf(c));

                Task t = lst[index-1];
                t.unMark();
                lst[index-1] = t;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(lst[index-1]);

            }
            else if (first.equals("deadline")) {
                String s = words[1];
                String[] arr = s.split("/by");
                String desc = arr[0];
                String time = arr[1];
                Deadline t = new Deadline(desc, time);
                lst[count] = t;

                System.out.println("Got it. I've added this task: \n" + t + "\nNow you have " + (count+1) + " tasks in the list.");
                count++;
            }

            else if (first.equals("event")) {
                String s = words[1];
                String[] arr = s.split("/at");
                String desc = arr[0];
                String time = arr[1];
                Event t = new Event(desc, time);
                lst[count] = t;

                System.out.println("Got it. I've added this task: \n" + t + "\nNow you have " + (count+1) + " tasks in the list.");
                count++;
            }

            else if (first.equals("todo")) {
                String desc = words[1];
                ToDo t = new ToDo(desc);
                lst[count] = t;

                System.out.println("Got it. I've added this task: \n" + t + "\nNow you have " + (count+1) + " tasks in the list.");
                count++;
            }

        }
    }
}
