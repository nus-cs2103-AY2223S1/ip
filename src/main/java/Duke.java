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
            String[] split = input.split(" ", 2);
            if (input.equals("list")) { // Prints out items in list
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + "." + list[i].toString());
                }
            } else if (split[0].equals("mark")) { // Checks for mark
                int index = Integer.parseInt(split[1]) - 1;
                list[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:" + "\n" +
                        "\t" + list[index].toString());
            } else if (split[0].equals("unmark")) { // Checks for unmark
                int index = Integer.parseInt(split[1]) - 1;
                list[index].markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:" + "\n" +
                        "\t" + list[index].toString());
            } else if (split[0].equals("todo")) { // Checks for Todo
                list[count] = new Todo(split[1]);
                System.out.println("Got it. I've added this task:" + "\n\t" + list[count].toString());
                count += 1;
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (split[0].equals("deadline")) { // Checks for Deadline
                String[] temp = split[1].split("/by", 2);
                list[count] = new Deadline(temp[0], temp[1]);
                System.out.println("Got it. I've added this task:" + "\n\t" + list[count].toString());
                count += 1;
                System.out.println("Now you have " + count  + " tasks in the list.");
            } else if (split[0].equals("event")) { // Checks for Event
                String[] temp = split[1].split("/at", 2);
                list[count] = new Event(temp[0], temp[1]);
                System.out.println("Got it. I've added this task:" + "\n\t" + list[count].toString());
                count += 1;
                System.out.println("Now you have " + count  + " tasks in the list.");
            } else if (!input.equals("bye")) { // Only adds input to list if it is not "bye"
                System.out.println("Invalid input"); // Prints user input
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // Exits when user types "bye"
    }
}
