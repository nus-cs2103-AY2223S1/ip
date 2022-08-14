import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while (!input.equals("bye")) {
            input = scan.nextLine(); // Reads user input
            String[] split = input.split(" ", 0);
            if (input.equals("list")) { // Prints out items in list
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
            } else if (split.length == 2 && split[0].equals("mark")) { // Checks for mark
                int index = Integer.parseInt(split[1]) - 1;
                list[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:" + "\n" +
                        "\t[" + list[index].getStatusIcon() + "] " + list[index].getDescription());
            } else if (split.length == 2 && split[0].equals("unmark")) { // Checks for unmark
                int index = Integer.parseInt(split[1]) - 1;
                list[index].markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:" + "\n" +
                        "\t[" + list[index].getStatusIcon() + "] " + list[index].getDescription());
            } else if (!input.equals("bye")) { // Only adds input to list if it is not "bye"
                list[count] = new Task(input);
                count += 1;
                System.out.println("\tadded: " + input); // Prints user input
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // Exits when user types "bye"
    }
}
