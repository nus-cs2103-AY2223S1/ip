import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Action greet = new Greet();
        System.out.println(greet);
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while(!s.equals("bye")) {
            String parts[] = s.split(" ", 0);
            if (s.equals("list")) {
                String listOutput = "Here are the tasks in your list:\n";
                int index = 1;
                for (Task t : taskList) {
                    listOutput += index + "."+ t;
                    index++;
                }
                System.out.println(listOutput);
            }
            else if (parts.length >= 1 && (parts[0].equals("mark") || parts[0].equals("unmark"))) {
                try {
                    if (parts[0].equals("mark")) {
                        int pos = Integer.parseInt(parts[1]) - 1;
                        Task markedTask = taskList.get(pos).mark();
                        taskList.set(pos, markedTask);
                        System.out.println("Nice! I've marked this task as done:\n " + markedTask);
                    } else if (parts[0].equals("unmark")) {
                        int pos = Integer.parseInt(parts[1]) - 1;
                        Task unmarkedTask = taskList.get(pos).unmark();
                        taskList.set(pos, unmarkedTask);
                        System.out.println("OK, I've marked this task as not done yet:\n " + unmarkedTask);
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! You did not specify which task to be marked/unmarked.");
                }
                catch (IndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! Your list only has " + taskList.size() + " tasks.");
                }
            }
            else if (parts.length >= 1 && (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event"))) {
                try {
                    if (parts.length == 1) {
                        throw new EmptyTaskException();
                    }
                    String taskName = "";
                    boolean hasBracket = false;
                    for (int i = 1; i < parts.length; i++) {
                        if (parts[i].charAt(0) != '/') {
                            taskName += " " + parts[i];
                        } else {
                            taskName += " (" + parts[i].substring(1) + ":";
                            hasBracket = true;
                        }
                    }
                    if (hasBracket) {
                        taskName += ")";
                    }

                    Task task = new Task("DummyTask");
                    if (parts[0].equals("todo")) {
                        task = new ToDo(taskName);
                    } else if (parts[0].equals("deadline")) {
                        task = new Deadline(taskName);
                    } else if (parts[0].equals("event")) {
                        task = new Event(taskName);
                    }
                    taskList.add(task);
                    System.out.println("Got it. I've added this task:\n" + task + "Now you have " + taskList.size() + " tasks in the list.\n");
                }
                catch (EmptyTaskException ex){
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else if (parts.length >= 1 && (parts[0].equals("delete"))) {
                try {
                    int pos = Integer.parseInt(parts[1]) - 1;
                    Task removedTask = taskList.get(pos);
                    taskList.remove(pos);
                    System.out.println("Noted. I've removed this task:\n " + removedTask + "Now you have " + taskList.size() + " tasks in the list.\n");
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! You did not specify which task to be delete.");
                }
                catch (IndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! Your list only has " + taskList.size() + " tasks.");
                }
            }
            else {
                try {
                    throw new InvalidCommandException();
                }
                catch (InvalidCommandException ex) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            s = in.nextLine();
        }
        Action bye = new Bye();
        System.out.println(bye);
    }
}
