import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hi... I'm Bishop... \nWhat can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            String[] input = ans.split(" ", 2);
            String command = input[0];

            try {
                switch (command) {
                    case "bye":
                        System.out.println("Goodbye...");
                        return;
                    case "list":
                        Task.printList();
                        break;
                    case "mark":
                    case "unmark":
                        int taskToMark = Integer.parseInt(input[1]);
                        Task.markAsDone(command, taskToMark);
                        break;
                    case "delete":
                        int taskToDelete = Integer.parseInt(input[1]);
                        Task.delete(taskToDelete);
                        break;
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
                        System.out.println("Sorry, I don't quite understand what you mean...");
                }

            } catch (NumberFormatException e) {
                System.out.println("Task Index must be an integer...");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of a " + command + " command cannot be empty...");
            }
        }
        scanner.close();
    }
}
