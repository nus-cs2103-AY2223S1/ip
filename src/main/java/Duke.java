import java.util.*;
import java.util.List;

public class Duke {
    private List<Task> storage = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke curr = new Duke();
        curr.handleInput();


    }

    private void handleInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String next = in.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                if (this.storage.size() == 0) {
                    System.out.println("List is currently empty");
                }
                for (int i = 0; i < this.storage.size(); i++) {
                    System.out.printf("%d.%s\n", i+1, storage.get(i).toString());
                }
            } else if (next.contains("unmark")){
                Character item = next.charAt(next.length()-1);
                if (Character.isDigit(item)) {
                    int idx = item - '0';
                    if (idx > storage.size() + 1 || idx < 1) {
                        System.out.println("Invalid selection");
                    } else {
                        Task t = storage.get(idx - 1);
                        t.markAsNotDone();
                    }
                } else {
                    System.out.println("Invalid selection");
                }

            } else if (next.contains("mark")){
                Character item = next.charAt(next.length()-1);
                if (Character.isDigit(item)) {
                    int idx = item - '0';
                    if (idx > storage.size() + 1 || idx < 1) {
                        System.out.println("Invalid selection");
                    } else {
                        Task t = storage.get(idx - 1);
                        t.markAsDone();
                    }
                } else {
                    System.out.println("Invalid selection");
                }

            } else {

                if (next.contains("todo")) {
                    Todo todo = new Todo(next.substring(5));
                    storage.add(todo);
                    System.out.printf("added %s\n", todo);
                } else if (next.contains("deadline")) {
                    int idxDate = next.indexOf("/by");
                    Deadline deadline = new Deadline(next.substring(9, idxDate),
                            next.substring(idxDate + 4));
                    storage.add(deadline);
                    System.out.printf("added %s\n", deadline);
                } else if (next.contains("event")) {
                    int idxDate = next.indexOf("/at");
                    Event event = new Event(next.substring(6, idxDate),
                            next.substring(idxDate + 4));
                    storage.add(event);
                    System.out.printf("added %s\n", event);
                } else {
                    System.out.println("Invalid action");
                }
                System.out.printf("Now you have %d tasks in the list\n",
                        this.storage.size());
            }
        }
    }
}
