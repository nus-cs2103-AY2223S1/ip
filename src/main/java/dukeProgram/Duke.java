package dukeProgram;

import dukeProgram.facilities.TaskList;
import dukeProgram.ui.UserInterface;
import exceptions.KeyNotFoundException;
import dukeProgram.storage.SaveManager;
import utilities.SerializedNamesFormatter;

import java.io.IOException;

public class Duke {
    private static User user;

    /**
     * Runs the main program.
     * @param args
     */
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

    /**
     * Loads the current user from existing deserialized data
     * @return The User profile that has been loaded
     */
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

    /**
     * Begins a new user profile
     * @param userName the user's name that the profile is created for
     */
    private static void beginNewUser(String userName) {
        UserInterface.printInStyle(
                String.format("This is the first time we've met, %s!", userName),
                "Nice to meet you!"
        );
        user = new User(userName);

        SaveManager.save("user", user);
    }

    /**
     * Initialises the facilities of the program
     */
    private static void initialiseFacilities() {
        TaskList.initialise();
    }

    /**
     * Returns the current user profile loaded in this profile
     * @return the current user profile using the program
     */
    public static User getUser() {
        return user;
    }
}
