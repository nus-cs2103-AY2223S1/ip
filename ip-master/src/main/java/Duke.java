import java.util.ArrayList;
import java.util.Scanner;

/*
Duke IP for CS2103T by Yuvaraj Kumaresan AY2022/23
 */
public class Duke {
    /*
    Class Task
    Description: An encapsulation of what a completable task is in the context of Duke.
    Has description and isDone field to describe and mark completion status of a task.

    @param String description Textual description of the task.
     */
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

        public void setIsDone(Boolean done) {
            this.isDone = done;
        }

        public boolean getIsDone() {
            return this.isDone;
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.getDescription();
        }
    }

    // Deadline a child class of Task has the same functionality
    // but adds on with a by field which allows users to set a deadline
    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    // Event a child class of Task has the same functionality
    // but adds on with an at field which allows users to set an event timing.
    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }
    // ToDo class a child class of the task and has similar functionality.

    public static class ToDo extends Task {


        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static ArrayList<Task> storage = new ArrayList<Task>();

    /*
    Method list
    Description: Asks for user input using the scanner utility,
                 Adds input to memory if input is not bye or list or mark or unmark,
                 if input is bye exit message is displayed and program exits,
                 if input is list program lists out the stored inputs,
                 if input is mark <number> program marks the task as complete and displays it,
                 if input is unmark <number> program marks the task as incomplete and displays it.

     */
    public static void list() {
        try {
            Scanner input = new Scanner(System.in);
            String text = input.nextLine();
            if (text.equalsIgnoreCase("list")) {
                if (storage.size() == 0) {
                    System.out.println("No items have been added to the list");
                } else {
                    System.out.println("Here are the tasks in your list : ");
                    for (int i = 0; i < storage.size(); i++) {
                        System.out.println((i + 1) + ". " + storage.get(i).toString());
                    }
                }
                list();
            } else if (text.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
            } else if (text.startsWith("todo")) {
                ToDo item = new ToDo(text.replace("todo ", ""));
                storage.add(item);
                System.out.println("Got it. I've added this task. \n" + item.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                list();
            } else if (text.startsWith("deadline")) {
                try {
                    String[] description = text.replace("deadline ", "").split("/by ");
                    Deadline item = new Deadline(description[0], description[1]);
                    storage.add(item);
                    System.out.println("Got it. I've added this task. \n" + item.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                } catch (ArrayIndexOutOfBoundsException error) {
                    System.out.println("Please provide a deadline and a by time e.g. deadline <description of the deadline> /by <time of the deadline>");
                }
                list();
            } else if (text.startsWith("event")) {
                try {
                    String[] description = text.replace("event ", "").split("/at ");
                    Event item = new Event(description[0], description[1]);
                    storage.add(item);
                    System.out.println("Got it. I've added this task. \n" + item.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                } catch (ArrayIndexOutOfBoundsException error) {
                    System.out.println("Please provide a event and an at time e.g. event <description of the event> /at <time of the event>");
                }
                list();
            } else if (text.startsWith("mark")) {
                if (Integer.parseInt(text.replace("mark ", "")) - 1 < storage.size() && Integer.parseInt(text.replace("mark ", "")) > 0) {
                    if (!storage.get(Integer.parseInt(text.replace("mark ", "")) - 1).getIsDone()) {
                        storage.get(Integer.parseInt(text.replace("mark ", "")) - 1).setIsDone(true);
                        System.out.println("Nice! I've marked this task as done \n" +
                                storage.get(Integer.parseInt(text.replace("mark ", "")) - 1).toString() +
                                "\n Now you have " + storage.size() + " tasks in the list");
                    } else {
                        System.out.println("This task is already marked done");
                    }
                } else {
                    System.out.println("Such an item does not exist");
                }
                list();
            } else if (text.startsWith("unmark")) {
                if (Integer.parseInt(text.replace("unmark ", "")) - 1 < storage.size() && Integer.parseInt(text.replace("unmark ", "")) > 0) {
                    if (storage.get(Integer.parseInt(text.replace("unmark ", "")) - 1).getIsDone()) {
                        storage.get(Integer.parseInt(text.replace("unmark ", "")) - 1).setIsDone(false);
                        System.out.println("Ok, I've marked this task as not done yet \n" +
                                storage.get(Integer.parseInt(text.replace("unmark ", "")) - 1).toString() +
                                "\n Now you have " + storage.size() + " tasks in the list");
                    } else {
                        System.out.println("This task has already been marked not done");
                    }
                } else {
                    System.out.println("Such an item does not exist");
                }
                list();
            } else {
                System.out.println("Please provide a Todo or a deadline or an event to add to the list formats are as follows \n" +
                        "Todo : todo <description of the task> \n" +
                        "Deadline : deadline <description of the deadline> /by <time of the deadline> \n" +
                        "Event : event <description of the event> /at <time of the event> \n");
                list();
            }
        } catch (NumberFormatException error) {
            System.out.println("Invalid command please add a space between mark/unmark and the list item you would like to interact with.");
            list();
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am \n" + logo + "\n What can I do for you?\n");
        list();
    }
}

