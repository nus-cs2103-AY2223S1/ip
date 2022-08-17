import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while(true) {
            String command = scanner.nextLine();
            String[] commandArr = command.split(" ");
            System.out.println("------------------------------");
            switch(commandArr[0]) {
                case "mark":
                    int markI;
                    try {
                        markI = Integer.parseInt(commandArr[1]);
                    } catch (NumberFormatException e) {     // if second word not integer
                        System.out.println("🙁 OOPS!!! Provide a number to mark a task.");
                        break;
                    }
                    if (markI >= 1 && markI <= tasks.size()) {   // ensure i given is within range
                        tasks.get(markI - 1).mark();
                    } else {
                        System.out.printf("🙁 OOPS!!! Provide a valid number (from 1 to %d) to mark a task.\n", tasks.size());
                    }
                    break;
                case "unmark":
                    int unmarkI;
                    try {
                        unmarkI = Integer.parseInt(commandArr[1]);
                    } catch (NumberFormatException e) {     // if second word not integer
                        System.out.println("🙁 OOPS!!! Provide a number to unmark a task.");
                        break;
                    }
                    if (unmarkI >= 1 && unmarkI <= tasks.size()) {   // ensure i given is within range
                        tasks.get(unmarkI - 1).unmark();
                    } else {
                        System.out.printf("🙁 OOPS!!! Provide a valid number (from 1 to %d) to unmark a task.\n", tasks.size());
                    }
                    break;
                case "todo":
                    String todoText = command.replaceFirst("todo", "").strip();
                    if (todoText.isEmpty()) {
                        System.out.println("🙁 OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        tasks.add(new Todo(false, todoText));
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    }
                    break;
                case "deadline":
                    String[] c1 = command.split("/by");
                    String deadlineText = c1[0].replaceFirst("deadline", "").strip();
                    String deadlineTime = c1.length > 1 ? c1[1].strip() : "";
                    if (deadlineText.isEmpty()) {
                        System.out.println("🙁 OOPS!!! The description of a deadline cannot be empty.");
                    } else if (deadlineTime.isEmpty()) {
                        System.out.println("🙁 OOPS!!! Provide a time for the deadline.");
                    } else {
                        tasks.add(new Deadline(false, deadlineText, deadlineTime));
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    }
                    break;
                case "event":
                    String[] c2 = command.split("/at");
                    String eventText = c2[0].replaceFirst("event", "").strip();
                    String eventTime = c2.length > 1 ? c2[1].strip() : "";
                    if (eventText.isEmpty()) {
                        System.out.println("🙁 OOPS!!! The description of a event cannot be empty.");
                    } else if (eventTime.isEmpty()) {
                        System.out.println("🙁 OOPS!!! Provide a time for the event.");
                    } else {
                        tasks.add(new Event(false, eventText, eventTime));
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    }
                    break;
                default:
                    switch (command) {
                        case "bye":
                            System.out.println("Bye. Hope to see you again soon!");
                            break;
                        case "list":
                            if (tasks.isEmpty()) {
                                System.out.println("🙁 OOPS!!! There are no tasks in your list yet.");
                            } else {
                                System.out.println("Here are the tasks in your list:");
                                for (int i = 0; i < tasks.size(); i++) {
                                    System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
                                }
                            }
                            break;
                        default:
                            System.out.println("🙁 OOPS!!! I'm sorry, but I don't know what that means :-(");
                            break;
                    }
            }
            System.out.println("------------------------------");
        }
    }
}
