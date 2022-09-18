package clevernotbot;

import command.Command;
import exception.CleverNotBotException;
import task.TaskList;

import java.util.Scanner;

public class ChatBot {
    private UI uI = new UI();
    private Storage storage = new Storage();
    private TaskList tasks = new TaskList(storage.getTasksFromFile());
    private Scanner sc = new Scanner(System.in);
    private Parser parser = new Parser();
    private boolean isExitProgram = false;

    public ChatBot() {

    }

    public void startChatBot() {
        greetUser();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String reply = getResponse(input);
            uI.chat(reply);
            if (isExitProgram) {
                break;
            }
        }

    }

    private String greetUser() {
        return getResponse("greet");
    }

    /**
     * Returns a string response to the User for any input that the User place
     *
     * @param input User input
     * @return A corresponding response from chatbot.
     */
    public String getResponse(String input) {
        try {
            Command commandToRun = parser.parseText(input);
            assert commandToRun != null; // assert that command is not null
            isExitProgram = commandToRun.isExitingProgram();
            String reply = commandToRun.run(tasks, uI, storage).trim();
            return reply;
        } catch (CleverNotBotException e) {
            return e.toString();
        }
    }

}
