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
                        continue;
                    }
                    if (markI >= 1 && markI <= tasks.size()) {   // ensure i given is within range
                        tasks.get(markI - 1).mark();
                        break;
                    }
                case "unmark":
                    int unmarkI;
                    try {
                        unmarkI = Integer.parseInt(commandArr[1]);
                    } catch (NumberFormatException e) {     // if second word not integer
                        continue;
                    }
                    if (unmarkI >= 1 && unmarkI <= tasks.size()) {   // ensure i given is within range
                        tasks.get(unmarkI - 1).unmark();
                        break;
                    }
                case "todo":
                    tasks.add(new Todo(false,
                                        command.replaceFirst("todo ", "")));
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    break;
                case "deadline":
                    String[] c1 = command.split(" /by ");    // c[0]: "deadline" + text; c[1]: deadline
                    tasks.add(new Deadline(false,
                            c1[0].replaceFirst("deadline ", ""),
                            c1[1]));
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    break;
                case "event":
                    String[] c2 = command.split(" /at ");    // c[0]: "event" + text; c[1]: time
                    tasks.add(new Event(false,
                            c2[0].replaceFirst("event ", ""),
                            c2[1]));
                    System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                    break;
                default:
                    switch (command) {
                        case "bye":
                            System.out.println("Bye. Hope to see you again soon!");
                            break;
                        case "list":
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
                            }
                            break;
                        default:    // default task type will be todo
                            tasks.add(new Todo(false, command));
                            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                            break;
                    }
            }
            System.out.println("------------------------------");
        }
    }
}
