package dukeprogram.parser;

import java.util.Arrays;
import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.Main;
import dukeprogram.command.AccessLoansCommand;
import dukeprogram.command.AccessTasksCommand;
import dukeprogram.command.SetVariableCommand;
import dukeprogram.userinterface.Widget;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;
import javafx.scene.control.Button;

/**
 * Parser will parse a command string in any given command context.
 */
public class Parser {

    private Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses the user's input into usable elements to trigger commands
     * @param userInput the command of the user
     */
    public void parse(String userInput) throws IncompleteCommandException {
        Iterator<String> elements = convertToIterator(userInput);

        if (!elements.hasNext()) {
            throw new IncompleteCommandException();
        }

        String thisElement = elements.next();

        try {
            switch (thisElement) {
            case "tasks":
                new AccessTasksCommand(duke).parse(elements);
                break;

            case "set":
                new SetVariableCommand(duke).parse(elements);
                break;

            case "loans":
                new AccessLoansCommand(duke).parse(elements);
                break;

            case "help":
                printHelp();
                break;

            default:
                duke.sendMessage(String.format("\"%s\" is not a valid command!", thisElement));
            }
        } catch (InvalidCommandException | IncompleteCommandException e) {
            duke.sendMessage(e.getMessage());
        }
    }

    private void printHelp() {
        Button userGuideButton = new Button("User Guide");
        userGuideButton.getStyleClass().add("header");

        String styleDefault = "-fx-background-color: #FF1493; -fx-background-radius: 5px;";
        String styleHover = "-fx-background-color: #FF69B4; -fx-background-radius: 5px;";

        userGuideButton.setStyle(styleDefault);
        userGuideButton.setOnMouseEntered(e -> userGuideButton.setStyle(styleHover));
        userGuideButton.setOnMouseExited(e -> userGuideButton.setStyle(styleDefault));

        userGuideButton.setMinHeight(40);
        userGuideButton.setOnAction(
                e -> Main.getPrimaryHostService().showDocument("www.google.com")
        );

        duke.sendMessage("I am able to record tasks or loans.");
        duke.sendMessage("Type \"tasks help\" for more information regarding tasks.\n"
                        + "\nOtherwise, type \"loans help\" for more information regarding loans.\n"
                        + "\nHere is the user guide on how to use this program.",
                new Widget(userGuideButton));
    }

    /**
     * Converts the provided string into an iterator split by whitespaces
     * @param input the input string given
     * @return an iterator of the input, separated by whitespaces
     */
    public Iterator<String> convertToIterator(String input) {
        return Arrays.stream(input.split(" ")).iterator();
    }
}
