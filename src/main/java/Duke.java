import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static String[] keywords = {"bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete"};
    public enum Keyword {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static Keyword getKeyword(Scanner scanner) throws DukeException {
        String keyword = scanner.next();
        if (Arrays.asList(keywords).contains(keyword)) {
            return Keyword.valueOf(keyword.toUpperCase());
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
        Keyword keyword = null;

        while (true) {
            try {
                keyword = getKeyword(scanner);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                break;
            }
            if (keyword == Keyword.BYE) {
                break;
            } else if (keyword != null) {
                switch (keyword) {
                    case LIST:
                        Task.printList();
                        break;
                    case MARK:
                        int markIndex = scanner.nextInt();
                        Task.mark(markIndex);
                        break;
                    case UNMARK:
                        int unmarkIndex = scanner.nextInt();
                        Task.unmark(unmarkIndex);
                        break;
                    case TODO:
                        String description = scanner.nextLine();
                        try {
                            Todo todo = new Todo(description);
                            todo.add();
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case DEADLINE:
                        String[] deadlineSection = scanner.nextLine().split("/by");
                        Deadline deadline = new Deadline(deadlineSection[0], deadlineSection[1]);
                        deadline.add();
                        break;
                    case EVENT:
                        String[] eventSections = scanner.nextLine().split("/at");
                        Event event = new Event(eventSections[0], eventSections[1]);
                        event.add();
                        break;
                    case DELETE:
                        int deleteIndex = scanner.nextInt();
                        Task.delete(deleteIndex);
                        break;
                }
            } else {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
