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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.next();
            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                Task.printList();
            } else if (text.equals("mark")) {
                int index = scanner.nextInt();
                Task.mark(index);
            } else if (text.equals("unmark")) {
                int index = scanner.nextInt();
                Task.unmark(index);
            } else if (text.equals("todo")) {
                String description = scanner.nextLine();
                Todo todo = new Todo(description);
                todo.add();
            } else if (text.equals("deadline")) {
                String[] sections = scanner.nextLine().split("/by");
                Deadline deadline = new Deadline(sections[0], sections[1]);
                deadline.add();
            } else if (text.equals("event")) {
                String[] sections = scanner.nextLine().split("/at");
                Event event = new Event(sections[0], sections[1]);
                event.add();
            } else {
                System.out.println("\tTask type not indicated.");
                text = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
