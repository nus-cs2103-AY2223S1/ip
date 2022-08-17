import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reply = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        class Task {
            protected String description;
            protected boolean isDone;
            protected int type;
            protected String date;

            public Task(String description, int type) {
                this.description = description;
                this.isDone = false;
                this.type = type;
            }

            public void isMark(boolean bool) {
                this.isDone = bool;
            }

            public String getType() {
                if (this.type == 0) {
                    return "[T]";
                } else if (this.type == 1) {
                    return "[D]";
                } else {
                    return "[E]";
                }
            }

            public String getDescription() {
                return this.description;
            }

            public void setDate(String str) {
                this.date = str;
            }

            public String getDate() {
                if (this.type == 0) {
                    String str = "There is no date.";
                    return str;
                }
                return this.date;
            }

            public String getStatusIcon() {
                return (isDone ? "X" : " "); // mark done task with X
            }

            public String toString() {
                if (type == 0) {
                    return "[T][" + this.getStatusIcon() + "] " +
                            this.description;
                } else if (type == 1) {
                    return "[D][" + this.getStatusIcon() + "] " +
                            this.description + " (by: " + this.date + ")";
                } else {
                    return "[E][" + this.getStatusIcon() + "] " +
                            this.description + " (at: " + this.date + ")";
                }
            }
        }

        String currreply = reply.nextLine();
        ArrayList<Task> list = new ArrayList<>(100);

        while (!currreply.equals("bye")) {
            if (currreply.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("There are no tasks in your list. :)");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    list.forEach(n -> System.out.println((list.indexOf(n) + 1) + "."
                            + n.toString()));
                    System.out.println();
                }
                currreply = reply.nextLine();
            } else if (currreply.startsWith("mark")) {
                int index = Integer.parseInt(currreply.substring(5)) - 1;
                if (index > list.size() - 1) {
                    System.out.println("You have no such tasks.\n");
                } else {
                    Task task = list.get(index);
                    task.isMark(true);
                    System.out.println("Nice! I've marked this task as done:\n" +
                            " " + task.getType() + "[X] " + task.getDescription() + "\n");
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
                            " " + task.getType() + "[ ] " + task.getDescription() + "\n");
                }
                currreply = reply.nextLine();
            } else if (currreply.startsWith("delete")) {
                int index = Integer.parseInt(currreply.substring(7)) - 1;
                if (index > list.size() - 1) {
                    System.out.println("You have no such tasks.\n");
                } else {
                    Task task = list.get(index);
                    list.remove(index);
                    System.out.println("Noted. I've removed this task:\n" +
                            " " + task.toString() + "\n" + "Now you have " +
                            list.size() + " tasks in the list.\n");
                }
                currreply = reply.nextLine();
            } else {
                if (currreply.startsWith("todo")) {
                    if (currreply.length() <= 4) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.\n");
                    } else {
                        String acttask = currreply.substring(5);
                        Task currtask = new Task(acttask, 0);
                        currtask.setDate(null);
                        list.add(currtask);
                        System.out.println("Got it. I've added this task:\n" +
                                " " + currtask.toString() + "\n" +
                                "Now you have " + list.size() + " tasks in the list.\n");
                    }
                } else if (currreply.startsWith("deadline")) {
                    if (currreply.length() <= 8) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.\n");
                    } else {
                        if (!currreply.contains("/")) {
                            System.out.println("There is no date! >:(\n");
                        } else {
                            int slashindex = currreply.indexOf("/");
                            String acttask = currreply.substring(9, slashindex - 1);
                            Task currtask = new Task(acttask, 1);
                            currtask.setDate(currreply.substring(slashindex + 4));
                            list.add(currtask);
                            System.out.println("Got it. I've added this task:\n" +
                                    " " + currtask.toString() + "\n" +
                                    "Now you have " + list.size() + " tasks in the list.\n");
                        }
                    }
                } else if (currreply.startsWith("event")) {
                    if (currreply.length() <= 5) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.\n");
                    } else {
                        if (!currreply.contains("/")) {
                            System.out.println("There is no date! >:(\n");
                        } else {
                            int slashindex = currreply.indexOf("/");
                            String acttask = currreply.substring(6, slashindex - 1);
                            Task currtask = new Task(acttask, 2);
                            currtask.setDate(currreply.substring(slashindex + 4));
                            list.add(currtask);
                            System.out.println("Got it. I've added this task:\n" +
                                    " " + currtask.toString() + "\n" +
                                    "Now you have " + list.size() + " tasks in the list.\n");
                        }
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
                currreply = reply.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
