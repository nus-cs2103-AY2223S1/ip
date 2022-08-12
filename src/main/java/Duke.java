import java.awt.*;
import java.util.*;
import java.util.List;

public class Duke {
    private List<Task> storage = new ArrayList<Task>();

    private class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            if (this.isDone) {
                System.out.println("Task already marked as done\n" + this);
            } else {
                this.isDone = true;
                System.out.println("Nice! I've marked this task as done\n" +
                        this);
            }
        }

        public void markAsNotDone() {
            if (!this.isDone) {
                System.out.println("Task already marked as done\n" + this);
            } else {
                this.isDone = false;
                System.out.println("Ok! I've unmarked this task\n" + this);
            }
        }

        private String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
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
        Duke curr = new Duke();
        curr.handleInput();


    }

    private void handleInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String next = in.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                if (this.storage.size() == 0) {
                    System.out.println("List is currently empty");
                }
                for (int i = 0; i < this.storage.size(); i++) {
                    System.out.printf("%d.%s\n", i+1, storage.get(i).toString());
                }
            } else if (next.contains("unmark")){
                Character item = next.charAt(next.length()-1);
                if (Character.isDigit(item)) {
                    int idx = item - '0';
                    if (idx > storage.size() + 1 || idx < 1) {
                        System.out.println("Invalid selection");
                    } else {
                        Task t = storage.get(idx - 1);
                        t.markAsNotDone();
                    }
                } else {
                    System.out.println("Invalid selection");
                }

            } else if (next.contains("mark")){
                Character item = next.charAt(next.length()-1);
                if (Character.isDigit(item)) {
                    int idx = item - '0';
                    if (idx > storage.size() + 1 || idx < 1) {
                        System.out.println("Invalid selection");
                    } else {
                        Task t = storage.get(idx - 1);
                        t.markAsDone();
                    }
                } else {
                    System.out.println("Invalid selection");
                }

            } else {
                Task t = new Task(next);
                storage.add(t);
                System.out.printf("added %s\n", next);
            }
        }
    }
}
