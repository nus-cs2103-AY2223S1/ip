package Duke;

import java.util.Scanner;

//making sense of the user command
public class Parser {
    public static void readLine(TaskList tasks) {
        Ui.printHelloMsg();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (true) {
            String[] strs = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye!");
                break;
            } else if (input.equals("list")) {
                Ui.printList(tasks);
            } else if (strs.length == 2 && (strs[0].equals("mark") || strs[0].equals("unmark"))) {
                int index = Integer.parseInt(strs[1]) - 1;
                if (strs[0].equals("mark")) {
                    tasks.markTaskAsDone(index);
                } else if (strs[0].equals("unmark")) {
                    tasks.unMarkTaskAsDone(index);
                }
            } else if (strs.length == 2 && (strs[0].equals("delete"))) {
                int index = Integer.parseInt(strs[1]) - 1;
                tasks.delete(index);
            } else {
                try {
                    String[] details;
                    Task task;
                    switch (strs[0]) {
                        case "deadline":
                            details = input.split(" ", 2)[1].split(" /by ");
                            task = new Deadline(details[0], false, details[1]);
                            tasks.add(task);
                            break;
                        case "event":
                            details = input.split(" ", 2)[1].split(" /at ");
                            task = new Event(details[0], false, details[1]);
                            tasks.add(task);
                            break;
                        case "todo":
                            String detail = input.split(" ", 2)[1];
                            task = new Todo(detail, false);
                            tasks.add(task);
                            break;
                        default:
                            Ui.printDontUnderstandMsg();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Ui.printDescriptionCantBeEmptyMsg(strs[0]);
                }
            }
            input = scanner.nextLine();
        }
    }
}
