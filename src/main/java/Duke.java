import java.util.*;
public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String getDescription() {
            return this.description;
        }
    }
    public static void run(Task[] lst) {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        Scanner sc2 = new Scanner(choice);
        String call = sc2.next();

        if (choice.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return;
        } else if (choice.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < lst.length; i++) {
                if (lst[i] == null) {
                    break;
                }
                int curr = i + 1;
                System.out.println(curr + ". [" + lst[i].getStatusIcon() + "] " + lst[i].getDescription());
            }
        } else if (call.equals("mark")) {
            int pos = sc2.nextInt() - 1;
            lst[pos].mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + lst[pos].getStatusIcon() + "] " + lst[pos].getDescription());
        } else if (call.equals("unmark")) {
            int pos = sc2.nextInt() - 1;
            lst[pos].unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + lst[pos].getStatusIcon() + "] " + lst[pos].getDescription());
        } else {
            Task task = new Task(choice);
            for (int i = 0; i < lst.length; i++) {
                if (lst[i] == null) {
                    lst[i] = task;
                    break;
                }
            }
            System.out.println("added: " + task.getDescription());
        }
        run(lst);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] lst = new Task[100];
        System.out.println("Hello! I'm Justin, your personal helper.");
        System.out.println("What can I do for you?");
        run(lst);
    }
}
