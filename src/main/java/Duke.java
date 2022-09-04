import java.util.Scanner;

public class Duke {
    public Duke() {
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI();
        TaskList taskList = new TaskList(ui);
        Parser parser = new Parser(ui);


        ui.printWelcomeMessage();
        boolean breakLoop = false;
        while(!breakLoop) {
            String rawInput = scanner.nextLine().trim();

            Command command = parser.parse(rawInput);

            switch(command.getCommand()) {
            case UNKNOWN:
                ui.printDefaultMessage();
                break;
            case BYE:
                breakLoop = true;
                ui.printGoodbyeMessage();
                break;
            default:
                String response = taskList.executeTask(command);
                if(response != null) {
                    System.out.println(response);
                }
                break;
            }
        }
        taskList.destructor();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
