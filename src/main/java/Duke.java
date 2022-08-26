import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reply = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        String currReply = reply.nextLine();
        ArrayList<Task> list = new ArrayList<Task>(100);

        while (!currReply.equals("bye")) {
            if (currReply.equals("list")) {
                try {
                    if (list.size() == 0) {
                        throw new DukeException("There are no tasks in your list. :)");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        list.forEach(n -> System.out.println((list.indexOf(n) + 1) + "."
                                + n.toString()));
                        System.out.println();
                        currReply = reply.nextLine();
                        continue;
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    currReply = reply.nextLine();
                    continue;
                }
            } else if (currReply.startsWith("mark")) {
                int index = Integer.parseInt(currReply.substring(5)) - 1;
                try {
                    if (index > list.size() - 1) {
                        throw new DukeException("You have no such tasks.");
                    } else {
                        Task task = list.get(index);
                        task.isMark(true);
                        System.out.println("Nice! I've marked this task as done:\n" +
                                " " + task.toString() + "\n");
                        currReply = reply.nextLine();
                        continue;
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    currReply = reply.nextLine();
                    continue;
                }
            } else if (currReply.startsWith("unmark")) {
                int index = Integer.parseInt(currReply.substring(7)) - 1;
                try {
                    if (index > list.size() - 1) {
                        throw new DukeException("You have no such tasks.");
                    } else {
                        Task task = list.get(index);
                        task.isMark(false);
                        System.out.println("OK, I've marked this task as not done yet:\n" +
                                " " + task.toString() + "\n");
                        currReply = reply.nextLine();
                        continue;
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    currReply = reply.nextLine();
                    continue;
                }
            } else if (currReply.startsWith("delete")) {
                int index = Integer.parseInt(currReply.substring(7)) - 1;
                try {
                    if (index > list.size() - 1) {
                        throw new DukeException("You have no such tasks.");
                    } else {
                        Task task = list.get(index);
                        list.remove(index);
                        System.out.println("Noted. I've removed this task:\n" +
                                " " + task.toString() + "\n" + "Now you have " +
                                list.size() + " tasks in the list.\n");
                        currReply = reply.nextLine();
                        continue;
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    currReply = reply.nextLine();
                    continue;
                }
            } else {
                if (currReply.startsWith("todo")) {
                    try {
                        if (currReply.length() <= 4) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        } else {
                            String actTask = currReply.substring(5);
                            Task currTask = new Todo(actTask);
                            list.add(currTask);
                            System.out.println("Got it. I've added this task:\n" +
                                    " " + currTask.toString() + "\n" +
                                    "Now you have " + list.size() + " tasks in the list.\n");
                            currReply = reply.nextLine();
                            continue;
                        }
                    } catch (DukeException e) {
                        System.out.println(e.toString());
                        currReply = reply.nextLine();
                        continue;
                    }
                } else if (currReply.startsWith("deadline")) {
                    try {
                        if (currReply.length() <= 8) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else {
                            if (!currReply.contains("/")) {
                                System.out.println("There is no date! >:(\n");
                            } else {
                                int slashIndex = currReply.indexOf("/");
                                String actTask = currReply.substring(9, slashIndex - 1);
                                String taskDate = currReply.substring(slashIndex + 4);
                                Task currTask = new Deadline(actTask, taskDate);
                                list.add(currTask);
                                System.out.println("Got it. I've added this task:\n" +
                                        " " + currTask.toString() + "\n" +
                                        "Now you have " + list.size() + " tasks in the list.\n");
                                currReply = reply.nextLine();
                                continue;
                            }
                        }
                    } catch (DukeException e) {
                        System.out.println(e.toString());
                        currReply = reply.nextLine();
                        continue;
                    }
                } else if (currReply.startsWith("event")) {
                    try {
                        if (currReply.length() <= 5) {
                            throw new DukeException("The description of a event cannot be empty.");
                        } else {
                            if (!currReply.contains("/")) {
                                throw new DukeException("There is no date! >:(");
                            } else {
                                int slashIndex = currReply.indexOf("/");
                                String actTask = currReply.substring(6, slashIndex - 1);
                                String taskDate = currReply.substring(slashIndex + 4);
                                Task currTask = new Event(actTask, taskDate);
                                list.add(currTask);
                                System.out.println("Got it. I've added this task:\n" +
                                        " " + currTask.toString() + "\n" +
                                        "Now you have " + list.size() + " tasks in the list.\n");
                                currReply = reply.nextLine();
                                continue;
                            }
                        }
                    } catch (DukeException e) {
                        System.out.println(e.toString());
                        currReply = reply.nextLine();
                        continue;
                    }
                } else {
                    try {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e.toString());
                        currReply = reply.nextLine();
                        continue;
                    }
                }
                currReply = reply.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
