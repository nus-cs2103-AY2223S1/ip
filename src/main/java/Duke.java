import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hi... I'm Bishop... ");
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            String[] input = ans.split(" ", 2);
            String command = input[0];

            if (ans.equals("bye")) {
                System.out.println("Goodbye...");
                break;
            } else if (ans.equals("list")) {
                Task.printList();
            } else if (command.equals("mark") || command.equals("unmark")){
                try {
                    if (input.length <= 1) {
                        System.out.println("Task Index is missing");
                        continue;
                    }
                    int taskIndex = Integer.parseInt(input[1]);
                    Task.markAsDone(input[0], taskIndex);
                } catch (NumberFormatException e) {
                    // If mark or unmarked command is used but incorrect format,
                    // we assume a new task is added
                    System.out.println("Input task index");
                }
            } else {

                switch(command) {
                    case "todo":
                        new Todo(input[1]);
                        break;
                    case "deadline":
                        String[] str = input[1].split(" /by ", 2);
                        new Deadline(str[0], str[1]);
                        break;
                    case "event":
                        String[] str2 = input[1].split(" /at ", 2);
                        new Event(str2[0], str2[1]);
                        break;
                    default:
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-( ");
                }
            }

        }
        scanner.close();
    }
}
