package DukeProgram;

import DukeProgram.Facilities.TaskList;
import DukeProgram.UI.UserInterface;
import Exceptions.KeyNotFoundException;
import DukeProgram.Storage.SaveManager;
import Utilities.SerializedNamesFormatter;

import java.io.IOException;

public class Duke {
    private static User user;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String userName = UserInterface.askForInput("Who are you?");

        if (SaveManager.deserialize(
                SerializedNamesFormatter.createFileNameForUser(userName)
        )) {
            user = loadCurrentUser();
        } else {
            beginNewUser(userName);
        }

        initialiseFacilities();

        UserInterface.advanceLocation("Home");

        //main loop
        UserInterface.requestCommands();

        // on exit
        UserInterface.retreatLocation();
        UserInterface.printInStyle("Goodbye! Hope to see you again soon!");

        try {
            SaveManager.serialize(SerializedNamesFormatter.createFileNameForUser(userName));
        } catch (IOException e) {
            UserInterface.printInStyle(e.getMessage());
        }
    }

    private static User loadCurrentUser() {
        try {
            user = SaveManager.load("user");
            UserInterface.printInStyle(String.format("Welcome back %s", user.getName()));
        } catch (KeyNotFoundException e) {
            UserInterface.printInStyle(
                    "Hmm...",
                    "I can't seem to remember your name. Can you remind me?"
            );
            String userName = UserInterface.askForInput("What is your name?");
            user.setName(userName);
            SaveManager.save("user", user);
        }
        return user;
    }

    private static void beginNewUser(String userName) {
        UserInterface.printInStyle(
                String.format("This is the first time we've met, %s!", userName),
                "Nice to meet you!"
        );
        user = new User(userName);

        SaveManager.save("user", user);
    }

    private static void initialiseFacilities() {
        TaskList.initialise();
    }

    public static User getUser() {
        return user;
    }
}
