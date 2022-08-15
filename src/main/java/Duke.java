import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void printOut(String str) {
        String line = "____________________________________________________________\n";
        System.out.println(line + str + "\n" + line);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        printOut("Hello! I'm Duke.\n" +
                "What can I do for you?");
        String next = input.nextLine();

        while (!next.equals("bye")) {
            String[] nextWords = next.split(" ");
            switch(nextWords[0]) {
                case "mark":
                    try {
                        int index = Integer.parseInt(nextWords[1]) - 1;
                        tasks.get(index).markAsDone();
                        printOut("I've marked this as done:\n" + tasks.get(index).toString());
                        break;
                    }
                    catch(IndexOutOfBoundsException e) {
                        printOut("This task number is invalid!");
                        break;
                    }
                case "unmark":
                    try {
                        int index = Integer.parseInt(nextWords[1]) - 1;
                        tasks.get(index).markAsUndone();
                        printOut("I've marked this as undone:\n" + tasks.get(index).toString());
                        break;
                    }
                    catch(IndexOutOfBoundsException e) {
                        printOut("This task number is invalid!");
                        break;
                    }
                case "list":
                    System.out.println("Here are your tasks:\n" +
                        "____________________________________________________________");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.println("____________________________________________________________\n");
                    break;
                default:
                    Task temp = new Task(next);
                    tasks.add(temp);
                    printOut("added: " + next);
            }

            next = input.nextLine();
        }

        printOut("See you later. Bye!");
    }
}