import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static String line = "------------------------------------------";
    private static boolean END;
    //storing all user inputs
    private static ArrayList<Task> storage;

    private class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        public String toString() {
            return this.description;
        }

        public void markasDone() {
            this.isDone = true;
        }

        public void markasNotDone() {
            this.isDone = false;
        }
    }

    private class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.getStatusIcon() + " " + super.toString();
        }
    }

    private class Deadline extends Task {
        private String deadline;

        public Deadline(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + super.getStatusIcon() + " " + super.toString() + "(by: " + this.deadline + ")";
        }
    }

    private class Event extends Task {
        private String duration;

        public Event(String description, String duration) {
            super(description);
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "[E]" + super.getStatusIcon() + " " + super.toString() + "(at: " + this.duration + ")";
        }
    }
    /*
    Wraps the text with lines on top and below
     */
    public String wrapper(String content) {
        return line + "\n" + content + "\n" + line;
    }
    /*
    Decides on what the chatbox will respond with based on user input.
     */
    public void Response(String input) {
        String done = "Got it. I've added this task:\n";
        if (input.equals("bye")) {
            END = true;
            System.out.println(wrapper("Bye. Hope to see you again soon!"));
        } else if(input.length() < 4) {
            System.out.println(wrapper("Error! Please input your message again!"));
        } else if (input.equals("list")) {
            String output = "";
            for (int i = 0; i < storage.size(); i ++) {
                Task current = storage.get(i);
                output = output + String.valueOf(i + 1) + "." + current.toString();
                if (i != storage.size() - 1) {
                    output = output + "\n";
                }
            }
            System.out.println(wrapper(output));
        } else if (input.substring(0, 4).equals("mark")) {
            String indexString = input.substring(5);
            int index = Integer.valueOf(indexString) - 1;
            if (index > -1 && index < storage.size()) {
                Task current = storage.get(index);
                current.markasDone();
                String content = "Nice! I've marked this task as done:\n" + current.toString();
                System.out.println(wrapper(content));
            } else {
                System.out.println(wrapper("Error! Index out of Bound\nPlease Try Again!"));
            }
        } else if (input.substring(0, 6).equals("unmark")) {
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            if (index > -1 && index < storage.size()) {
                Task current = storage.get(index);
                current.markasNotDone();
                String content = "OK, I've marked this task as not done yet:\n" + current.toString();
                System.out.println(wrapper(content));
            } else {
                System.out.println(wrapper("Error! Index out of Bound\nPlease Try Again!"));
            }
        } else if (input.substring(0, 4).equals("todo")) {
            Task todo = new ToDo(input.substring(5));
            storage.add(todo);
            String message = done + "  " + todo.toString() +
                    "\n" + "Now you have " + String.valueOf(storage.size()) + " tasks in the list.";
            System.out.println(wrapper(message));
        } else if (input.substring(0, 8).equals("deadline")) {
            int divider = input.indexOf("/");
            Task deadline = new Deadline(input.substring(9, divider), input.substring(divider + 4));
            storage.add(deadline);
            String message = done + "  " + deadline.toString() +
                    "\n" + "Now you have " + String.valueOf(storage.size()) + " tasks in the list.";
            System.out.println(wrapper(message));
        } else if (input.substring(0, 5).equals("event")) {
            int divider = input.indexOf("/");
            Task event = new Event(input.substring(6, divider), input.substring(divider + 4));
            storage.add(event);
            String message = done + "  " + event.toString() +
                    "\n" + "Now you have " + String.valueOf(storage.size()) + " tasks in the list.";
            System.out.println(wrapper(message));
        } else {
            System.out.println(wrapper("Error! Please input your message again!"));
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        END = false;
        storage = new ArrayList<Task>();
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println(duke.wrapper("Hello! I'm Duke\nWhat can I do for you?"));
        while (!END) {
            duke.Response(myScanner.nextLine());
        }
        myScanner.close();
    }
}
