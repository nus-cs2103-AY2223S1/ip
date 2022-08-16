import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static boolean END;
    //storing all user inputs
    private static ArrayList<Task> storage;

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return this.description;
        }

        public void markasDone() {
            this.isDone = true;
        }

        public void markasNotDone() {
            this.isDone = false;
        }
    }

    /*
    Decides on what the chatbox will response with based on user input.
     */
    public void Response(String input) {
        if (input.equals("bye")) {
            END = true;
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            String output = "";
            for (int i = 0; i < storage.size(); i ++) {
                Task current = storage.get(i);
                output = output + String.valueOf(i + 1) + ".[" + current.getStatusIcon() + "] "
                        + current.getDescription() + "\n";
            }
            System.out.println(output);
        } else if (input.substring(0, 4).equals("mark")) {
            String indexString = input.substring(5);
            int index = Integer.valueOf(indexString) - 1;
            Task current = storage.get(index);
            current.markasDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + "[" + current.getStatusIcon() + "] " + current.getDescription());
        } else if (input.substring(0, 6).equals("unmark")) {
            String indexString = input.substring(7);
            int index = Integer.valueOf(indexString) - 1;
            Task current = storage.get(index);
            current.markasNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + "[" + current.getStatusIcon() + "] " + current.getDescription());
        } else {
            Task task = new Task(input);
            storage.add(task);
            System.out.println("added: " + input);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        END = false;
        storage = new ArrayList<Task>();
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (!END) {
            duke.Response(myScanner.nextLine());
        }
        myScanner.close();
    }
}
