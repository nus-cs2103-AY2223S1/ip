import java.util.Scanner;

public class Ui {
    private Scanner scanner;


    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    public void userInterface(){
        greet();
        TaskList tasks = new TaskList();

        while (scanner.hasNextLine()) {
            System.out.println();
            String userInput = scanner.nextLine();

            try {
                Parser parser = new Parser(userInput, tasks);
                parser.handle();
            } catch(DukeException dukeEx) {
                System.out.println(dukeEx.getMessage());
            }
        }

    }

    private void greet() {
        System.out.println("Hello I'm Duke\n What can I do for you?");
    }
}
