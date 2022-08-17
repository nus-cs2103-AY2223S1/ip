import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasksToDo = new Task[100];
        int i = 0;
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi I'm Duke, What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye"))
        {
            String[] strs = input.split(" ");
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println((j + 1) + ". " + tasksToDo[j].toString());
                }
            } else if (strs.length == 2 && (strs[0].equals("mark") || strs[0].equals("unmark"))) {
                int index = Integer.parseInt(strs[1]) - 1;
                Task task = tasksToDo[index];
                if (strs[0].equals("mark")) {
                    task.markTaskAsDone();
                    System.out.println("Nice! I have mark this task as done:\n" + task.toString());
                } else if (strs[0].equals("unmark")) {
                    task.unMarkTaskAsDone();
                    System.out.println("Ok, I have mark this task as not done yet:\n" + task.toString());
                }
            } else {
                try {
                    String[] details;
                    switch (strs[0]) {
                        case "deadline":
                            details = input.split(" ", 2)[1].split(" /by ");
                            tasksToDo[i] = new Deadline(details[0], details[1]);
                            i++;
                            System.out.println("Got it! I've added this task:\n" + tasksToDo[i - 1].toString()
                                    + "\nNow you've got " + i + " tasks in the list!");
                            break;
                        case "event":
                            details = input.split(" ", 2)[1].split(" /at ");
                            tasksToDo[i] = new Event(details[0], details[1]);
                            i++;
                            System.out.println("Got it! I've added this task:\n" + tasksToDo[i - 1].toString()
                                                + "\nNow you've got " + i + " tasks in the list!");
                            break;
                        case "todo":
                            tasksToDo[i] = new Todo(input.split(" ", 2)[1]);
                            i++;
                            System.out.println("Got it! I've added this task:\n" + tasksToDo[i - 1].toString()
                                    + "\nNow you've got " + i + " tasks in the list!");
                            break;
                        default:
                            System.out.println("OOPS!!! I don't understand what that means!");
                    }
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("OOPS!!! The description of a " + strs[0] + " cannot be empty");
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");

    }
}
