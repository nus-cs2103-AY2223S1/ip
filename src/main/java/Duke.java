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

    private void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void list() {
        if (this.storage.size() == 0) {
            System.out.println("List is currently empty");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < this.storage.size(); i++) {
                System.out.printf("%d.%s\n", i + 1,
                        this.storage.get(i).toString());
            }
        }
    }

    private void unmark(String command) {
        if (this.storage.size() == 0) {
            System.out.println("No tasks to unmark");
        }
        Character item = command.charAt(0);
        if (Character.isDigit(item)) {
            int idx = item - '0';
            if (idx > this.storage.size() + 1 || idx < 1) {
                System.out.println("Invalid selection");
            } else {
                Task t = this.storage.get(idx - 1);
                t.markAsNotDone();
            }
        } else {
            System.out.println("Invalid selection");
        }
    }

    private void mark(String command) {
        if (this.storage.size() == 0) {
            System.out.println("No tasks to mark");
        }
        Character item = command.charAt(0);
        if (Character.isDigit(item)) {
            int idx = item - '0';
            if (idx > this.storage.size() + 1 || idx < 1) {
                System.out.println("Invalid selection");
            } else {
                Task t = this.storage.get(idx - 1);
                t.markAsDone();
            }
        } else {
            System.out.println("Invalid selection");
        }
    }

    private void addTask(String[] command) {
        if (command[0].equals("todo")) {
            if (command.length <= 1) {
                System.out.println("Description of a " +
                        "todo cannot be empty");
            }
            Todo todo = new Todo(command[1].trim());
            storage.add(todo);
            System.out.printf("added %s\n", todo);
        } else if (command[0].equals("deadline")) {
            if (command.length <= 1) {
                System.out.println("Description of a " +
                        "deadline cannot be empty");
            }
            if (!command[1].contains("/by") ||
                    command[1].indexOf("/by") == command[1].length() - 3) {
                System.out.println("Please insert " +
                        "date for deadline");
            }
            String[] deadlineInfo = command[1].split("/by", 2);
            Deadline deadline = new Deadline(deadlineInfo[0].trim(),
                    deadlineInfo[1].trim());
            storage.add(deadline);
            System.out.printf("added %s\n", deadline);
        } else if (command[0].equals("event")) {
            if (command.length <= 1) {
                System.out.println("Description of a event " +
                        "cannot be empty");
            }
            if (!command[1].contains("/at") ||
                    command[1].indexOf("/at") == command[1].length() - 3) {
                System.out.println("Please insert " +
                        "date for event");
            }
            String[] eventInfo = command[1].split("/at", 2);
            Event event = new Event(eventInfo[0].trim(), eventInfo[1].trim());
            storage.add(event);
            System.out.printf("added %s\n", event);
        } else {
            System.out.println("I'm sorry, but I don't know " +
                    "what that means");
            return;
        }
        System.out.printf("Now you have %d tasks in the list\n",
                this.storage.size());
    }


    private void handleInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String[] temp = in.nextLine().split(" ", 2);
            String[] next = new String[2];
            for (int i = 0; i < temp.length; i++) {
                next[i] = temp[i].trim();
            }

            if (next[0].equals("bye")) {
                bye();
            } else if (next[0].equals("list")) {
                list();
            } else if (next[0].equals("unmark")){
                unmark(next[1]);
            } else if (next[0].equals("mark")){
                mark(next[1]);
            } else {
                addTask(next);
            }
        }
    }
}
