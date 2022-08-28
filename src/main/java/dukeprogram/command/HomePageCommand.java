package dukeprogram.command;

import dukeprogram.Duke;
import dukeprogram.InternalAction;
import dukeprogram.User;
import dukeprogram.command.AccessTasksCommand;
import dukeprogram.command.Command;
import dukeprogram.facilities.TaskList;
import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

/**
 * Defines all the possible commands to be executed from the HomePage
 */
public class HomePageCommand extends Command {

    private Command commandToExitTo;

    @Override
    protected InternalAction onEnter() {
        if (SaveManager.deserialize("saveFile")) {
            try {
                User user = SaveManager.load("user");
                TaskList.initialise();
                return new InternalAction(
                        "Welcome back " + user.getName(),
                        Duke::exitCurrentState
                );
            } catch (KeyNotFoundException e) {
                return new InternalAction(
                        "File corrupted!"
                );
            }
        } else {
            User user = new User(System.getProperty("user.name"));
            SaveManager.save("user", user);

            return new InternalAction(
                    String.format("This is the first time we've met, %s.", user.getName())
                            + "\nNice to meet you! What would you like to do?",
                    Duke::exitCurrentState
            );
        }
    }

    @Override
    protected InternalAction onStay() {
        return new InternalAction("What would you like to do?");
    }

    @Override
    public InternalAction onParse(String input) {
        switch (input) {
        case "tasks":
            commandToExitTo = new AccessTasksCommand();
            return new InternalAction(
                    "Accessing tasks",
                    Duke::exitCurrentState
            );

        case "factory reset":
            commandToExitTo = this;
            return new InternalAction(
                    "Accessing tasks",
                    Duke::exitCurrentState
            );

        case "exit":
            commandToExitTo = this;
            return new InternalAction(
                    "Accessing tasks",
                    Duke::exitCurrentState
            );

        default:
            commandToExitTo = this;
            return new InternalAction(
                    "I'm not able to do that!",
                    Duke::exitCurrentState
            );
        }
    }

    @Override
    public Command onExit() {
        return commandToExitTo;
    }
}
