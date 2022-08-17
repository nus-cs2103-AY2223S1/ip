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
        Duke chatBotInstance = new Duke();
        chatBotInstance.handleInput();
    }

    public void list() {
        if (storage.size() == 0) {
            System.out.println("List is currently empty");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < storage.size(); i++) {
                System.out.printf("%d.%s\n", i + 1,
                        storage.get(i).toString());
            }
        }
    }

    public void unmark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to unmark!");
        } else {
            if (input == null) {
                throw new DukeException("No input provided for unmarking");
            }
            String s = input.replaceAll("\\D+", "" );
            if (!s.equals("")) {
                int idx = Integer.parseInt(s);
                if (idx - 1 >= this.storage.size() || idx < 1) {
                    throw new DukeException("Invalid selection for unmarking");
                } else {
                    Task t = this.storage.get(idx - 1);
                    t.markAsNotDone();
                }
            } else {
                throw new DukeException("Invalid selection for unmarking");
            }
        }
    }

    public void mark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to mark!");
        } else {
            if (input == null) {
                throw new DukeException("No input provided for marking");
            }
            String s = input.replaceAll("\\D+", "" );
            if (!s.equals("")) {
                int idx = Integer.parseInt(s);
                if (idx - 1 >= this.storage.size() || idx < 1) {
                    throw new DukeException("Invalid selection for marking");
                } else {
                    Task t = this.storage.get(idx - 1);
                    t.markAsDone();
                }
            } else {
                throw new DukeException("Invalid selection for marking");
            }
        }
    }

    public void delete(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to delete!");
        }
        if (input == null) {
            throw new DukeException("No input provided for deletion");
        }
        String s = input.replaceAll("\\D+", "" );
        if (!s.equals("")) {
            int idx = Integer.parseInt(s);
            if (idx - 1 >= this.storage.size() || idx < 1) {
                throw new DukeException("Invalid selection for marking");
            } else {
                Task t = this.storage.get(idx - 1);
                this.storage.remove(idx - 1);
                System.out.println("Removed the following task:\n" +
                        t);
            }
        } else {
            throw new DukeException("Invalid selection for deletion");
        }
    }

    public void addTask(String[] input) throws DukeException {
        if (input[0].equals("todo")) {
            if (input.length <= 1) {
                throw new DukeException("Description of a todo cannot be empty!");
            }
            Todo todo = new Todo(input[1].trim());
            storage.add(todo);
            System.out.printf("added %s\n", todo);
        } else if (input[0].equals("deadline")) {
            if (input.length <= 1) {
                throw new DukeException("Description of a deadline " +
                        "cannot be empty!");
            }
            if (!input[1].contains("/by") ||
                    input[1].indexOf("/by") == input[1].length() - 3) {
                throw new DukeException("No date inserted for deadline");
            }
            String[] deadlineInfo = input[1].split("/by", 2);
            Deadline deadline = new Deadline(deadlineInfo[0].trim(),
                    deadlineInfo[1].trim());
            storage.add(deadline);
            System.out.printf("added %s\n", deadline);
        } else if (input[0].equals("event")) {
            if (input.length <= 1) {
                throw new DukeException("Description of an event " +
                        "cannot be empty!");
            }
            if (!input[1].contains("/at") ||
                    input[1].indexOf("/at") == input[1].length() - 3) {
                throw new DukeException("No date inserted for event");

            }
            String[] eventInfo = input[1].split("/at", 2);
            Event event = new Event(eventInfo[0].trim(), eventInfo[1].trim());
            storage.add(event);
            System.out.printf("added %s\n", event);
        } else {
            throw new DukeException("Invalid task");
        }
        System.out.printf("Now you have %d tasks in the list\n",
                this.storage.size());
    }


    public void handleInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String[] temp = in.nextLine().split(" ", 2);
            String[] next = new String[2];
            for (int i = 0; i < temp.length; i++) {
                next[i] = temp[i].trim();
            }

            String command = next[0];

            try {

                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (command.equals("list")) {
                    this.list();
                } else if (command.equals("unmark")) {
                    this.unmark(next[1]);
                } else if (command.equals("mark")) {
                    this.mark(next[1]);
                } else if (command.equals("todo") || command.equals("deadline")
                        || command.equals("event")) {
                    this.addTask(temp);
                } else if (command.equals("delete")) {
                    this.delete(next[1]);
                } else {
                    throw new DukeException("Invalid command");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
