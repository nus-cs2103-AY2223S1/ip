package dukeprogram;

import java.io.IOException;
import java.util.ArrayList;

import dukeprogram.command.Command;
import dukeprogram.command.HomePageCommand;
import dukeprogram.facilities.TaskList;
import dukeprogram.storage.SaveManager;


/**
 * This is the main Duke Program
 */
public class Duke {
    private static User user;

    private static Command currentContext;

    private static final ArrayList<String> responseLog = new ArrayList<>();


    /**
     * Begins the Duke program from GUI
     * @return the welcoming message of the beginning of the program
     */
    public static String[] start() {
        currentContext = new HomePageCommand();
        return new String[] { currentContext.onInvoke().getDisplayText() };
    }


    /**
     * Gets responses from Duke
     * @param input the input to hand to Duke
     * @return all the responses from Duke
     */
    public static String[] getResponses(String input) {
        InternalAction internalAction = currentContext.onParse(input);
        responseLog.add(internalAction.getDisplayText());
        internalAction.doRunnable();
        String[] responses = responseLog.stream().filter(x -> !x.equals("")).toArray(String[]::new);
        responseLog.clear();

        return responses;
    }


    /**
     * Exits the current state
     */
    public static void exitCurrentState() {
        try {
            SaveManager.serialize("saveFile");
        } catch (IOException e) {
            responseLog.add("Wait... I had some issues saving your progress");
        }
        currentContext = currentContext.onExit();
        InternalAction internalAction = currentContext.onInvoke();
        responseLog.add(internalAction.getDisplayText());
        internalAction.doRunnable();
    }

    /**
     * Sets the current state
     * @param state the state to be in
     */
    public static void setState(Command state) {
        currentContext = state;
        InternalAction internalAction = currentContext.onInvoke();
        responseLog.add(internalAction.getDisplayText());
        internalAction.doRunnable();
    }

    /**
     * Returns the current user profile loaded in this profile
     * @return the current user profile using the program
     */
    public static User getUser() {
        return user;
    }
}
