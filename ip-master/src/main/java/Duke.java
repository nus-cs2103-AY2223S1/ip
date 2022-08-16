import java.util.ArrayList;
import java.util.Scanner;

/*
Duke IP for CS2103T by Yuvaraj Kumaresan AY2022/23
 */
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

        public void setIsDone(Boolean done) {
            this.isDone = done;
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
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        if (text.equalsIgnoreCase("list")) {
            if (storage.size() == 0) {
                System.out.println("No items have been added to the list");
            } else {
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println((i + 1) + ". " + "[" + storage.get(i).getStatusIcon() + "] " + storage.get(i).description);
                }
            }
            list();
        } else if (text.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!\n");
        } else if (text.startsWith("mark")) {
            storage.get(text.charAt(5) - 49).setIsDone(true);
            System.out.println("Nice! I've marked this task as done \n" + "[" + storage.get(text.charAt(5) - 49).getStatusIcon() + "] " + storage.get(text.charAt(5) - 49).description);
            list();
        } else if (text.startsWith("unmark")) {
            storage.get(text.charAt(7) - 49).setIsDone(false);
            System.out.println("Ok, I've marked this task as not done yet \n" + "[" + storage.get(text.charAt(7) - 49).getStatusIcon() + "] " + storage.get(text.charAt(7) - 49).description);
            list();
        } else {
            System.out.println("Added:" + text);
            storage.add(new Task(text));
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

