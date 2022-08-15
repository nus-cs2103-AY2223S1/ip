import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Task[] storage = new Task[100];
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        int count = 0;

        while (true) {
            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                if (count == 0) {
                    System.out.println("List is empty!");
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.print("\t");
                        System.out.print(i + 1);
                        System.out.print(". " + storage[i] + "\n");
                    }
                }
            } else if (text.equals("mark")) {
                int index = scanner.nextInt();
                Task task = storage[index - 1];
                task.setDone(true);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t\t" + task);
            } else if (text.equals("unmark")) {
                int index = scanner.nextInt();
                Task task = storage[index - 1];
                task.setDone(false);
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t\t" + task);
            } else {
                System.out.println("\tadded: " + text);
                storage[count] = new Task(text);
                count++;
            }
            scanner = new Scanner(System.in);
            text = scanner.next();
        }
        
        System.out.println("Bye. Hope to see you again soon!");
    }
}
