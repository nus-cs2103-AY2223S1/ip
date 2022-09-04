import java.util.Scanner;

public class IncorrectCommand extends Command {
    IncorrectCommand(Scanner scanner) {
        scanner.nextLine();
    }

    public void execute(TaskList taskList, Storage storage) {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means:-(");
    }
}
