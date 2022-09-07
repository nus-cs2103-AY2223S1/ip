package duke;

import duke.commands.Command;

import java.util.Scanner;

/**
 * Chatbot main.
 */
public class Duke {

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
            case HELP:
                ui.printHelpMessage();
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

    /**
     * Run the chatbot.
     * @param args This is useless.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
