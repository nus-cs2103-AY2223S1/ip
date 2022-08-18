import main.java.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> ls = new ArrayList<>();
        String line = "-----------------------------";
        String answer;

        // Duke's self-intro
        System.out.println(line + "\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you\n" +
                line + "\n");

        while (true) {
            answer = sc.nextLine();
            if (answer.equals("bye")) {
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" +
                        line + "\n");
                break;
            }
            else if (answer.equals("list")) { // Task 2
                System.out.println(line + "\n");
                list(ls);
                System.out.println(line + "\n");
            }
            else if (answer.startsWith("mark")) { // Task 3: mark
                int idx = Integer.parseInt(answer.substring(5)) - 1;
                Task t = ls.get(idx);
                t.mark();
                System.out.println(line + "\n" + "Nice! I've marked this task as done:" + "\n" +
                        "[X] " + t + "\n" +
                        line + "\n");
            }
            else if (answer.startsWith("unmark")) { // Task 3: unmark
                int idx = Integer.parseInt(answer.substring(7)) - 1;
                Task t = ls.get(idx);
                t.unmark();
                System.out.println(line + "\n" + "OK, I've marked this task as not done yet:" + "\n" +
                        "[ ] " + t + "\n" +
                        line + "\n");
            }
            else { // for normal actions
                System.out.println(line + "\n" + "added: " + answer + "\n" +
                        line + "\n");
                ls.add(new Task(answer));
            }
        }
    }

    // For Level-3
    private static void list(ArrayList ls) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            Task t = (Task) ls.get(i);
            System.out.println((i + 1) + ". [" + t.getStatusIcon() + "] " + t);
        }
    }

    /*
    // For Level-2
    private static void list(ArrayList ls) {
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + ls.get(i));
        }
    }
    */
}
