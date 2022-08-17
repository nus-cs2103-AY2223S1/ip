import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reply = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        class Task {
            protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public void isMark(boolean bool) {
                this.isDone = bool;
            }

            public String getDescription() {
                return this.description;
            }

            public String getStatusIcon() {
                return (isDone ? "X" : " "); // mark done task with X
            }
        }

        String currreply = reply.nextLine();
        Task currtask = new Task(currreply);
        ArrayList<Task> list = new ArrayList<>(100);

        while (!currreply.equals("bye")) {
            if (currreply.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                list.forEach(n -> System.out.println((list.indexOf(n) + 1) + ".["
                        + n.getStatusIcon() + "] " + n.getDescription()));
                System.out.println();
                currreply = reply.nextLine();
            } else if (currreply.startsWith("mark")) {
                int index = Integer.parseInt(currreply.substring(5)) - 1;
                if (index > list.size() - 1) {
                    System.out.println("You have no such tasks.\n");
                } else {
                    Task task = list.get(index);
                    task.isMark(true);
                    System.out.println("Nice! I've marked this task as done:\n" +
                            " [X] " + task.getDescription() + "\n");
                }
                currreply = reply.nextLine();
            } else if (currreply.startsWith("unmark")) {
                int index = Integer.parseInt(currreply.substring(7)) - 1;
                if (index > list.size() - 1) {
                    System.out.println("You have no such tasks.\n");
                } else {
                    Task task = list.get(index);
                    task.isMark(false);
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            " [ ] " + task.getDescription() + "\n");
                }
                currreply = reply.nextLine();
            } else {
                list.add(currtask);
                System.out.println("added: " + currreply + "\n");
                currreply = reply.nextLine();
                currtask = new Task(currreply);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
