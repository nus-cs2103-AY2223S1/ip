import java.util.ArrayList;
import java.util.Scanner;

public class Fred {
    public static void list(ArrayList<Task> arrayList) {
        int counter = 1;
        for (Task t : arrayList) {
            System.out.println("Fred: " + counter++ + "." + t.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> storage = new ArrayList<>();

        System.out.println("Fred: Hello! I'm Fred!");
        System.out.println("Fred: What can I do for you?");

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Fred: Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    if (storage.size() == 0) {
                        throw new FredException("There are no items in your list!");
                    }
                    System.out.println("Fred: Here are the tasks in your list:");
                    Fred.list(storage);
                } else if (input.startsWith("mark")) {
                    if (input.trim().equals("mark")) {
                        throw new FredException("The input of mark cannot be empty!");
                    }
                    int index = Integer.parseInt(input.substring(5));
                    if (index > storage.size()) {
                        throw new FredException("Your list has only " + storage.size() + " items!");
                    }
                    storage.get(index - 1).setStatus(true);
                    System.out.println("Fred: Nice! I've marked this task as done:");
                    System.out.println("Fred: " + storage.get(index - 1).toString());
                } else if (input.startsWith("unmark")) {
                    if (input.trim().equals("unmark")) {
                        throw new FredException("The input of unmark cannot be empty!");
                    }
                    int index = Integer.parseInt(input.substring(7));
                    if (index > storage.size()) {
                        throw new FredException("Your list has only " + storage.size() + " items!");
                    }
                    storage.get(index - 1).setStatus(false);
                    System.out.println("Fred: OK, I've marked this task as not done yet:");
                    System.out.println("Fred: " + storage.get(index - 1).toString());
                } else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new FredException("The description of a todo cannot be empty.");
                    }
                    storage.add(new ToDo(input.substring(5)));
                    System.out.println("Fred: Got it. I've added this task:");
                    System.out.println("Fred: " + storage.get(storage.size() - 1).toString());
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else if (input.startsWith("event")) {
                    if (input.trim().equals("event")) {
                        throw new FredException("The description of a event cannot be empty.");
                    }
                    int split = input.indexOf("/at");
                    if (split == -1) {
                        throw new FredException("No date/time provided! Usage: \"TASK /at DATE/TIME\"");
                    }
                    storage.add(new Event(input.substring(6, split - 1), input.substring(split + 4)));
                    System.out.println("Fred: Got it. I've added this task:");
                    System.out.println("Fred: " + storage.get(storage.size() - 1).toString());
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else if (input.startsWith("deadline")) {
                    if (input.trim().equals("deadline")) {
                        throw new FredException("The description of a deadline cannot be empty.");
                    }
                    int split = input.indexOf("/by");
                    if (split == -1) {
                        throw new FredException("No date/time provided! Usage: \"TASK /by DATE/TIME\"");
                    }
                    storage.add(new Deadline(input.substring(9, split - 1), input.substring(split + 4)));
                    System.out.println("Fred: Got it. I've added this task:");
                    System.out.println("Fred: " + storage.get(storage.size() - 1).toString());
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.substring(7));
                    String nameDeleted = storage.get(index - 1).toString();
                    storage.remove(index - 1);
                    System.out.println("Fred: Noted. I've removed this task:");
                    System.out.println("Fred: " + nameDeleted);
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else {
                    throw new FredException("I'm sorry, but I don't know what that means :(");
                }
            } catch (NumberFormatException e) {
                System.out.println("Fred: Must enter an integer!");
            } catch (FredException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
