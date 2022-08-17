import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Edric \nWhat can I do for you?");

        Task[] data = new Task[100];
        int dataIdx = 0;
        Scanner sc = new Scanner(System.in);
        // while loop keeps grabbing input from user, unless user inputs bye to break the loop
        while (true) {
            String input = sc.nextLine();
            // bye keyword: exit
            if (input.equals("bye")) {
                break;
            }
            String[] words = input.split(" ");
            // list keyword: prints list of stored text
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i < dataIdx; i++) {
                    Task curr = data[i];
                    System.out.format("%d.[%s] %s\n", i + 1, curr.getStatusIcon(), curr.getDescription());
                }
            }
            // mark keyword: checks the box of an item in the list
            else if (words[0].equals("mark")) {
                String numString = words[1];
                int idx = Integer.parseInt(numString) - 1;
                Task curr = data[idx];
                curr.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t[X] " + curr.getDescription());
            }
            // unmark keyword: unchecks the box of an item in the list
            else if (words[0].equals("unmark")) {
                String numString = words[1];
                int idx = Integer.parseInt(numString) - 1;
                Task curr = data[idx];
                curr.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t[ ] " + curr.getDescription());
            }
            else {
                data[dataIdx++] = new Task(input);
                System.out.println("added: " + input);
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
