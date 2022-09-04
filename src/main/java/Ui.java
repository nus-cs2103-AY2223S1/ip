import java.util.Scanner;

public class Ui {
    public void welcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void readUserInputThenOutputMessage(TaskList taskList, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Command command = Parser.parseUserInput(scanner);
            command.execute(taskList, storage);
            if (command.isExit()) {
                break;
            }
        }
        scanner.close();
    }
}
