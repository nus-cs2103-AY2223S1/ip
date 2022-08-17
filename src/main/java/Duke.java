import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hi... I'm Bishop... ");
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            String[] input = ans.split(" ", 2);

            if (ans.equals("bye")) {
                System.out.println("Goodbye...");
                break;
            } else if (ans.equals("list")) {
                Task.printList();
            } else if (input[0].equals("mark") || input[0].equals("unmark")){
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
                    new Task(ans);
                }
            } else {
                new Task(ans);
            }
        }
        scanner.close();
    }
}
