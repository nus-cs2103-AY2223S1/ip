import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            try {
                Scanner reader = new Scanner(System.in);
                while (true) {
                    String input = reader.nextLine();
                    if (input.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    } else if (input.equals("list")) {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + ". " + tasks.get(i));
                        }
                    } else {
                        String[] strings = input.split(" ", 2);
                        switch (strings[0]) {
                            case "mark":
                                if (strings.length == 1) {
                                    throw new MarkException();
                                } else {
                                    int num = Integer.parseInt(strings[1]);
                                    tasks.get(num - 1).setDone(true);
                                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(num - 1));
                                }
                                break;

                            case "unmark":
                                if (strings.length == 1) {
                                    throw new MarkException();
                                } else {
                                    int num = Integer.parseInt(strings[1]);
                                    tasks.get(num - 1).setDone(false);
                                    System.out.println("Ok, I've marked this task as not done yet:\n" + tasks.get(num - 1));
                                }
                                break;

                            case "deadline":
                                if (strings.length == 1 || strings[1].isEmpty()) {
                                    throw new TaskException();
                                } else {
                                    String[] split = strings[1].split("/");
                                    Task task = new Deadline(split[0], split[1]);
                                    tasks.add(task);
                                    System.out.println("Got it. I've added this task:\n" + task);
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                                }
                                break;

                            case "todo":
                                if (strings.length == 1 || strings[1].isEmpty()) {
                                    throw new TaskException();
                                } else {
                                    Task task = new ToDo(strings[1]);
                                    tasks.add(task);
                                    System.out.println("Got it. I've added this task:\n" + task);
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                                }
                                break;

                            case "event":
                                if (strings.length == 1 || strings[1].isEmpty()) {
                                    throw new TaskException();
                                } else {
                                    String[] split = strings[1].split("/");
                                    Task task = new Event(split[0], split[1]);
                                    tasks.add(task);
                                    System.out.println("Got it. I've added this task:\n" + task);
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                                }
                                break;

                            default:
                                throw new InputException();
                        }
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
                continue;
            }
        }
    }

}


