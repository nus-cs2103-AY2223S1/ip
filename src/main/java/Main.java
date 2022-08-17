import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);

        duke.printGreetMessage();
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    duke.printTasks();
                    break;
                default:
                    duke.addTask(command);
            }
            command = scanner.nextLine();
        }
        duke.printExitMessage();
    }
}
