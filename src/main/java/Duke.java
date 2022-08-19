import java.util.Scanner;
import java.util.ArrayList; 

public class Duke {

    private ArrayList<Task> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public enum Commands {
        bye, list, mark, unmark, todo, deadline, event
    }

    private abstract class Task {
        protected String content;
        protected Boolean status;

        protected Task(String content) {
            this.content = content;
            this.status = false;
        }

        public void markComplete() {
            this.status = true;
        }

        public void unmarkComplete() {
            this.status = false;
        }

        @Override
        public String toString() {
            if (status) {
                return String.format("[x] %s", content);
            } else {
                return String.format("[ ] %s", content);
            }
        }
    }

    private class ToDo extends Task {
        public ToDo(String content) {
            super(content);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", super.toString());
        }
    }

    private class Deadline extends Task {
        private String by;

        public Deadline(String content, String by) {
            super(content);
            this.by = by;
        }

        @Override 
        public String toString() {
            return String.format("[D]%s (by: %s)", super.toString(), by);
        }
    }

    private class Event extends Task {
        private String time;

        public Event(String content, String time) {
            super(content);
            this.time = time;
        }

        @Override 
        public String toString() {
            return String.format("[E]%s (at: %s)", super.toString(), time);
        }
    }

    private class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greeting();
        Boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        while (!quit) {
            System.out.println("Enter a command below:");
            String input = scanner.nextLine();
            try {
                quit = duke.FeedDuke(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                quit = false;
            }
        }
        scanner.close();
        duke.goodbye();
    }

    public Boolean FeedDuke(String input) throws DukeException {
        if (input.contains("bye")) {
            return true;
            
        } else if (input.contains("list")) {
            this.printList();
            return false;

        } else if (input.contains("unmark")) {
            int index = Integer.parseInt(input.replace("unmark", "").trim()) - 1;
            this.unmarkTask(index);
            return false;

        } else if (input.contains("mark")) {
            int index = Integer.parseInt(input.replace("mark", "").trim()) - 1;
            this.markTask(index);
            return false;

        } else if (input.contains("todo")) {
            try {
                this.addToDo(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        
        } else if (input.contains("deadline")) {
            try {
                this.addDeadline(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;

        } else if (input.contains("event")) {
            try {
                this.addEvent(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;

        }
        throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void greeting() {
        String logo = "\t\t\t  ____        _        \n"
        + "\t\t\t |  _ \\ _   _| | _____ \n"
        + "\t\t\t | | | | | | | |/ / _ \\\n"
        + "\t\t\t | |_| | |_| |   <  __/\n"
        + "\t\t\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public void goodbye() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public void unmarkTask(int index) {
        try {
            this.list.get(index).unmarkComplete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be unmarked does not exist.");
            return;
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + list.get(index).toString());
        System.out.println("\t____________________________________________________________");
    }

    public void markTask(int index) {
        try {
            this.list.get(index).markComplete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be marked does not exist.");
            return;
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + list.get(index).toString());
        System.out.println("\t____________________________________________________________");
    }

    public void printList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, list.get(i).toString()));
        }
        System.out.println("\t____________________________________________________________");
    }

    public void addToDo(String input) throws DukeException{
        String content = input.replace("todo", "").trim();
        if (content.length() == 0) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo task = new ToDo(content);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size() + 1));
        System.out.println("\t____________________________________________________________");
        this.list.add(task);
    }

    public void addDeadline(String input) throws DukeException{
        if (!input.contains(" by ")) {
            throw new DukeException("\t☹ OOPS!!! Formatting of deadline is incorrect.");
        }
        String[] split = input.replace("deadline", "").split(" by ");
        String content = split[0].trim();
        String by = split[1].trim();
        if (content.length() == 0 || by.length() == 0) {
            throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Deadline task = new Deadline(content, by);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size() + 1));
        System.out.println("\t____________________________________________________________");
        this.list.add(task);
    }

    public void addEvent(String input) throws DukeException{
        if (!input.contains(" on ")) {
            throw new DukeException("\t☹ OOPS!!! Formatting of event is incorrect.");
        }
        String[] split = input.replace("event", "").split(" on ");
        String content = split[0].trim();
        String time = split[1].trim();
        if (content.length() == 0 || time.length() == 0) {
            throw new DukeException("\t☹ OOPS!!! The description of an event cannot be empty.");
        }
        Event task = new Event(content, time);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size() + 1));
        System.out.println("\t____________________________________________________________");
        this.list.add(task);
    }

    
}
