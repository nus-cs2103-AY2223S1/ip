import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void mark() {
            this.isDone = true;
        }
        public void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            String s = "[%s] %s";
            return String.format(s, getStatusIcon(), description);
        }
    }
    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[T]" + s;
        }
    }
    public static class Deadline extends Task {
        protected String time;
        public Deadline(String description, String time) {
            super(description);
            this.time = time;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[D]" + s + " (by: " + time +")";
        }
    }
    public static class Event extends Task {
        protected String time;
        public Event(String description, String time) {
            super(description);
            this.time = time;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[E]" + s + " (at: " + time +")";
        }
    }
    enum Command {
        list,
        bye,
        mark,
        unmark,
        delete,
        deadline,
        todo,
        event
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        System.out.println("Oi, What u want?");
        ArrayList<Task> list = new ArrayList<>();

        while (in.hasNext()) {
            String next = in.nextLine();
            String[] command = next.split(" ", 2);
            Command cmd = Command.valueOf(command[0]);
            int count;
            int index;
            switch (cmd) {
                case bye:
                    System.out.println("Bye! Don't Come back!");
                    break;
                case list:
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    break;
                case mark:
                    index = Integer.parseInt(command[1]) - 1;
                    list.get(index).mark();
                    System.out.println("I have marked this task as done:");
                    System.out.println(list.get(index));
                    break;

                case event:
                    String[] desc = command[1].split("/at", 2);
                    list.add(new Event(command[0], desc[1]));
                    count = list.size();
                    System.out.println("Added Task");
                    System.out.println(list.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list");
                    break;
                case todo:
                    if (command.length < 2) {
                        throw new DukeException("oops the description of a todo cannot be empty!");
                    }
                    list.add(new Todo(command[1]));
                    count = list.size();
                    System.out.println("Added Task");
                    System.out.println(list.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list");
                    break;
                case delete:
                    if (command.length < 2) {
                        throw new DukeException("please specify which item to delete");
                    }
                    index = Integer.parseInt(command[1]) - 1;
                    Task item = list.get(index);
                    list.remove(index);
                    count = list.size();
                    System.out.println("Noted. I have removed this task: \n" + item);
                    System.out.println("You now have " + count + " tasks left in the list");
                    break;
                case unmark:
                    index = Integer.parseInt(next.split(" ", 2)[1]) - 1;
                    list.get(index).unmark();
                    System.out.println("I have marked this task as not done:");
                    System.out.println(list.get(index));
                    break;
                case deadline:
                    String[] dl = command[1].split("/by", 2);
                    list.add(new Deadline(command[0], dl[1]));
                    count = list.size();
                    System.out.println("Added Task");
                    System.out.println(list.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list");
                    break;
                default:
                    throw new DukeException("oops! I do not know what that means!");
            }

        }


    }
}

 class DukeException extends RuntimeException{
    public DukeException(String message) {
        super(message);
    }
}

