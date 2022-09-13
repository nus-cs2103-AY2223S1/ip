package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;

/**
 * Represents the karen chatbot.
 */
public class Karen {

    private Command command;

    /**
     * Creates instance of karen.
     *
     * @param filePath Location to create the storage
     */
    public Karen(String filePath) {
        this.command = new Command(filePath);
    }

    /**
     * Runs the chatbot when called.
     */
    public String getResponse(String inputText) {
        try {
            Parser parser = new Parser(inputText);
            String firstWord = parser.getFirstText();

            switch (firstWord) {
            case "bye": {
                return command.byeCommand();
            }
            case "list": {
                return command.listCommand();
            }
            case "mark": {
                return command.markCommand(parser);
            }
            case "unmark": {
                return command.unmarkCommand(parser);
            }
            case "todo": {
                return command.todoCommand(parser);
            }
            case "deadline": {
                return command.deadlineCommand(parser);
            }
            case "event": {
                return command.eventCommand(parser);
            }
            case "delete": {
                return command.deleteCommand(parser);
            }
            case "find": {
                return command.findCommand(parser);
            }
            case "update": {
                return command.updateCommand(parser);
            }
            default:
                return command.unknownCommand();
            }
        } catch (DukeException e) {
            return command.exceptionCommand(e);
        }
    }
}
