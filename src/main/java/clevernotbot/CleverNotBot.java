package clevernotbot;

import command.Command;
import exception.CleverNotBotException;
import task.TaskList;


public class CleverNotBot {

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        chatBot.startChatBot();
    }

    /**
     * Returns a string response to the User for any input that the User place
     *
     * @param input User input
     * @return A corresponding response from chatbot.
     */
    public String getResponse(String input) {
        UI uI = new UI();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.getTasksFromFile());
        Parser parser = new Parser();
        try {
            Command commandToRun = parser.parseText(input);
            assert commandToRun != null; // assert that command can not be null
            String reply = commandToRun.run(tasks, uI, storage).trim();
            return reply;
        } catch(CleverNotBotException e) {
            return e.toString();
        }
    }

}
