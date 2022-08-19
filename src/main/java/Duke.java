import java.util.Scanner;
import java.util.ArrayList; 

public class Duke {

    private ArrayList<Task> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public enum Commands {
        bye, list, mark, unmark, todo, deadline, events
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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greeting();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command below:");
            String input = scanner.nextLine();

            if (input.contains("bye")) {
                break;
                
            } else if (input.contains("list")) {
                duke.printList();

            } else if (input.contains("unmark")) {
                int index = Integer.parseInt(input.replace("unmark", "").trim()) - 1;
                duke.unmarkTask(index);

            } else if (input.contains("mark")) {
                int index = Integer.parseInt(input.replace("mark", "").trim()) - 1;
                duke.markTask(index);

            } else if (input.contains("todo")) {
                duke.addToDo(input.replace("todo", "").trim());

            } else if (input.contains("deadline")) {
                String[] split = input.replace("deadline", "").split(" by ");
                duke.addDeadline(split[0].trim(), split[1].trim());

            } else if (input.contains("event")) {
                String[] split = input.replace("event", "").split(" on ");
                duke.addEvent(split[0].trim(), split[1].trim());

            } else {
                duke.addToDo(input);
            }
        }
        scanner.close();
        duke.goodbye();
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
        this.list.get(index).unmarkComplete();
        System.out.println("\t____________________________________________________________");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + list.get(index).toString());
        System.out.println("\t____________________________________________________________");
    }

    public void markTask(int index) {
        this.list.get(index).markComplete();
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

    public void addToDo(String content) {
        ToDo task = new ToDo(content);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size() + 1));
        System.out.println("\t____________________________________________________________");
        this.list.add(task);
    }

    public void addDeadline(String content, String by) {
        Deadline task = new Deadline(content, by);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size() + 1));
        System.out.println("\t____________________________________________________________");
        this.list.add(task);
    }

    public void addEvent(String content, String time) {
        Event task = new Event(content, time);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println(String.format("\t  %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size() + 1));
        System.out.println("\t____________________________________________________________");
        this.list.add(task);
    }

}
