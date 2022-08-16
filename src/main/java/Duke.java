import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String[] validinputs = {"mark", "unmark", "todo", "deadline", "event"};

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while (!input.equals("bye")) {
            try {
                input = scan.nextLine(); // Reads user input
                String[] split = input.split(" ", 2);
                if (input.equals("list")) { // Prints out items in list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + "." + list.get(i).toString());
                    }
                } else if (!input.equals("bye")) { // Only adds input to list if it is not "bye"
                    if (split.length > 0 && Arrays.asList(validinputs).contains(split[0])) {
                        switch (split[0]) {
                            case "mark": { // Checks for mark
                                int index = Integer.parseInt(split[1]) - 1;
                                list.get(index).markAsDone();
                                System.out.println("Nice! I've marked this task as done:" + "\n" +
                                        "\t" + list.get(index).toString());
                                break;
                            }
                            case "unmark": { // Checks for unmark
                                int index = Integer.parseInt(split[1]) - 1;
                                list.get(index).markAsUndone();
                                System.out.println("Ok, I've marked this task as not done yet:" + "\n" +
                                        "\t" + list.get(index).toString());
                                break;
                            }
                            case "todo":  // Checks for Todo
                                if (split.length < 2) {
                                    throw new DukeException("todo");
                                } else {
                                    list.add(new Todo(split[1]));
                                    System.out.println("Got it. I've added this task:" + "\n\t" +
                                            list.get(list.size() - 1).toString());
                                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                                }
                                break;
                            case "deadline":  // Checks for Deadline
                                if (split.length < 2) {
                                    throw new DukeException("deadline");
                                } else {
                                    String[] temp = split[1].split("/by", 2);
                                    list.add(new Deadline(temp[0], temp[1]));
                                    System.out.println("Got it. I've added this task:" + "\n\t" +
                                            list.get(list.size() - 1).toString());
                                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                                }
                                break;
                            case "event":  // Checks for Event
                                if (split.length < 2) {
                                    throw new DukeException("event");
                                } else {
                                    String[] temp = split[1].split("/at", 2);
                                    list.add(new Event(temp[0], temp[1]));
                                    System.out.println("Got it. I've added this task:" + "\n\t" +
                                            list.get(list.size() - 1).toString());
                                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                                }
                                break;
                        }
                    }
                    else {
                        throw new DukeException(); // Invalid input
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getDescription());
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // Exits when user types "bye"
    }
}
