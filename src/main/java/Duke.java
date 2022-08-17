import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            boolean exit = false;
            System.out.println("Hello! I'm Milk");
            System.out.println("What can I do for you?");
            Scanner sc = new Scanner(System.in);
            while (!exit) {
                String userReply = sc.nextLine();
                String[] splitReply = userReply.split(" ");
                if (userReply.equals("bye")) {
                    exit = true;
                } else if (userReply.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + "." + list.get(i));
                    }
                    // need to replace contains with regex
                } else if (splitReply[0].equals("mark") && splitReply.length == 2) {
                    int index = Integer.parseInt(userReply.replaceAll("[^0-9]", ""));
                    index--;
                    list.get(index).finished();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index));
                } else if (splitReply[0].equals("unmark") && splitReply.length == 2) {
                    int index = Integer.parseInt(userReply.replaceAll("[^0-9]", ""));
                    index--;
                    list.get(index).notFinished();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(index));
                } else if (splitReply[0].equals("todo")){
                    if (splitReply.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String[] descriptionArray = Arrays.copyOfRange(splitReply,1, splitReply.length);
                    String description = String.join(" ", descriptionArray);
                    Task task = new ToDos(description);
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (splitReply[0].equals("deadline")) {
                    int by = -1;
                    for (int i = 0; i < splitReply.length; i++) {
                        if (splitReply[i].equals("/by")) {
                            by = i;
                            break;
                        }
                    }
                    String[] descriptionArray = Arrays.copyOfRange(splitReply,1, by);
                    String[] durationArray = Arrays.copyOfRange(splitReply, by + 1, splitReply.length );
                    String description = String.join(" ", descriptionArray);
                    String duration = String.join(" ", durationArray);
                    Task task = new Deadline(description, duration);
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");

                } else if (splitReply[0].equals("event")) {
                    int by = -1;
                    for (int i = 0; i < splitReply.length; i++) {
                        if (splitReply[i].equals("/at")) {
                            by = i;
                            break;
                        }
                    }
                    String[] descriptionArray = Arrays.copyOfRange(splitReply,1, by);
                    String[] durationArray = Arrays.copyOfRange(splitReply, by + 1, splitReply.length);
                    String description = String.join(" ", descriptionArray);
                    String duration = String.join(" ", durationArray);
                    Task task = new Events(description, duration);
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (splitReply[0].equals("delete") && splitReply.length == 2) {
                    int index = Integer.parseInt(userReply.replaceAll("[^0-9]", ""));
                    index--;
                    Task task = list.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            System.out.println("Bye. Hope to see you again soon!");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
