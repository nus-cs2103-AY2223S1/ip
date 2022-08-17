import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static String[] keywords = {"bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete"};
    public static String getKeyword(Scanner scanner) throws DukeException {
        String keyword = scanner.next();
        if (Arrays.asList(keywords).contains(keyword)) {
            return keyword;
        } else {
            throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
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
            String text = "";
            try {
                text = getKeyword(scanner);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
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
                try {
                    Todo todo = new Todo(description);
                    todo.add();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.equals("deadline")) {
                String[] sections = scanner.nextLine().split("/by");
                Deadline deadline = new Deadline(sections[0], sections[1]);
                deadline.add();
            } else if (text.equals("event")) {
                String[] sections = scanner.nextLine().split("/at");
                Event event = new Event(sections[0], sections[1]);
                event.add();
            } else if (text.equals("delete")) {
                int index = scanner.nextInt();
                Task.delete(index);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
