package DukeProgram.Commands;

import DukeProgram.Duke;
import DukeProgram.Storage.SaveManager;
import DukeProgram.UI.UserInterface;
import DukeProgram.UiMessage;
import Exceptions.InvalidCommandException;

import java.util.Random;

public class FactoryResetCommand extends Command {
    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        throw new InvalidCommandException(this, commandString,
                new UiMessage("FactoryResetCommand accepts no other commands"));
    }

    @Override
    public boolean execute() {
        UserInterface.printInStyle(
                String.format("If you are sure you want to do this, " +
                        "%s, please input the following numbers...", Duke.getUser().getName()));

        int key = new Random()
                .nextInt((50000 - 10000) + 1) + 10000;
        try {
            if (key == Integer.parseInt(UserInterface.askForInput(String.format("Type in %d", key)))) {
                UserInterface.printInStyle(String.format("Goodbye %s", Duke.getUser().getName()));
                if (SaveManager.wipeData()) {
                    System.exit(0);
                } else {
                    UserInterface.printInStyle("I couldn't delete the file.");
                }
            } else {
                UserInterface.printInStyle("Terminating last command...");
            }
        } catch (NumberFormatException e) {
            UserInterface.printInStyle("Terminating last command...");
        }

        return true;
    }
}
