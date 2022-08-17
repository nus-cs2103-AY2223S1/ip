import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private ArrayList<Task> storage = new ArrayList<Task>();

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

    private void unmark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to unmark!");
        } else {
            if (input == null) {
                throw new DukeException("No input provided for unmarking");
            }
            String s = input.replaceAll("\\D+", "");
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

    private void mark(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to mark!");
        } else {
            if (input == null) {
                throw new DukeException("No input provided for marking");
            }
            String s = input.replaceAll("\\D+", "");
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

    private void delete(String input) throws DukeException {
        if (this.storage.size() == 0) {
            throw new DukeException("No tasks to delete!");
        }
        if (input == null) {
            throw new DukeException("No input provided for deletion");
        }
        String s = input.replaceAll("\\D+", "");
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

    private void addTask(String[] input) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.
                    ofPattern("yyyy-MM-dd HHmm");
            switch (input[0]) {
                case "todo":
                    if (input.length <= 1) {
                        throw new DukeException("Description of a todo cannot be empty!");
                    }
                    Todo todo = new Todo(input[1].trim());
                    this.storage.add(todo);
                    System.out.printf("added %s\n", todo);
                    break;
                case "deadline":
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
                            LocalDateTime.parse(deadlineInfo[1].trim(), formatter));
                    this.storage.add(deadline);
                    System.out.printf("added %s\n", deadline);
                    break;
                case "event":
                    if (input.length <= 1) {
                        throw new DukeException("Description of an event " +
                                "cannot be empty!");
                    }
                    if (!input[1].contains("/at") ||
                            input[1].indexOf("/at") == input[1].length() - 3) {
                        throw new DukeException("No date inserted for event");

                    }
                    String[] eventInfo = input[1].split("/at", 2);
                    Event event = new Event(eventInfo[0].trim(),
                            LocalDateTime.parse(eventInfo[1].trim(), formatter));
                    this.storage.add(event);
                    System.out.printf("added %s\n", event);
                    break;
                default:
                    throw new DukeException("Invalid task");
            }
            System.out.printf("Now you have %d tasks in the list\n",
                    storage.size());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

    }


    private void handleInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String[] temp = in.nextLine().split(" ", 2);
            String[] next = new String[2];
            for (int i = 0; i < temp.length; i++) {
                next[i] = temp[i].trim();
            }

            next[0] = next[0].toLowerCase();
            String command = next[0];

            try {

                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    case "list":
                        this.list();
                        break;
                    case "unmark":
                        this.unmark(next[1]);
                        break;
                    case "mark":
                        this.mark(next[1]);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        this.addTask(next);
                        break;
                    case "delete":
                        this.delete(next[1]);
                        break;
                    default:
                        throw new DukeException("Invalid command");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
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
        Duke chatBotInstance = new Duke();
        chatBotInstance.handleInput();
    }
}
