package dukeprogram.parser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import dukeprogram.Duke;
import dukeprogram.Main;
import dukeprogram.command.SetVariableCommand;
import dukeprogram.command.loans.AccessLoansCommand;
import dukeprogram.command.tasks.AccessTasksCommand;
import dukeprogram.userinterface.TextStyle;
import dukeprogram.userinterface.Widget;
import dukeprogram.userinterface.WidgetButton;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;
import javafx.application.Platform;

/**
 * Parser will parse a command string in any given command context.
 */
public class Parser {

    private final Duke duke;
    private int numberOfInvalidCommands = 0;
    private final int maximumNumberOfInvalidCommands = 5;

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
                numberOfInvalidCommands = 0;
                break;

            case "set":
                new SetVariableCommand(duke).parse(elements);
                numberOfInvalidCommands = 0;
                break;

            case "loans":
                new AccessLoansCommand(duke).parse(elements);
                numberOfInvalidCommands = 0;
                break;

            case "help":
                printHelp();
                numberOfInvalidCommands = 0;
                break;

            case "bye":
                exitProgram();
                break;

            case "thank":
            case "thanks":
                duke.sendMessage("Your welcome!");
                break;

            case "hi":
            case "hello":
                duke.sendMessage("Hello :)");
                break;

            default:
                throw new InvalidCommandException(String.format("\"%s\" is not a valid command!", thisElement));
            }
        } catch (InvalidCommandException | IncompleteCommandException e) {
            countInvalidCommands(e);
        }
    }

    private void exitProgram() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        }, 1300);
        duke.sendMessage("Goodbye!");
        duke.serializeToFile();
    }

    private void countInvalidCommands(Exception e) {
        if (++numberOfInvalidCommands == maximumNumberOfInvalidCommands) {
            duke.sendMessage("Please stop doing that.", TextStyle.Warning);
            duke.sendMessage("Have a look at the user guide", createWidgetForUserGuide());
        } else if (numberOfInvalidCommands < maximumNumberOfInvalidCommands) {
            duke.sendMessage(e.getMessage());
        }
    }

    private void printHelp() {
        duke.sendMessage("I am able to record tasks or loans.");
        duke.sendMessage("Type \"tasks help\" for more information regarding tasks.\n"
                        + "\nOtherwise, type \"loans help\" for more information regarding loans.\n"
                        + "\nHere is the user guide on how to use this program.",
                createWidgetForUserGuide());
    }

    private Widget createWidgetForUserGuide() {
        return new Widget(new WidgetButton("User Guide",
                e -> Main.getPrimaryHostService()
                        .showDocument("https://rui-han-crh.github.io/ip/"))
        );
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
