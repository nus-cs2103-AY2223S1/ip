package dukeprogram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dukeprogram.command.Command;
import dukeprogram.command.HomePageCommand;
import dukeprogram.storage.SaveManager;


/**
 * This is the main Duke Program
 */
public class Duke {
    private static User user;

    private static Command currentContext;

    private static final ArrayList<DukeResponse> responseLog = new ArrayList<>();


    /**
     * Begins the Duke program from GUI
     * @return the welcoming message of the beginning of the program
     */
    public static DukeResponse[] start() {
        System.out.println("Starting");
        currentContext = new HomePageCommand();
        InternalAction internalAction = currentContext.onInvoke();
        user = ((HomePageCommand) currentContext).getUser();

        return internalAction.getAllResponses();
    }


    /**
     * Gets responses from Duke
     * @param input the input to hand to Duke
     * @return all the responses from Duke
     */
    public static DukeResponse[] getResponses(String input) {
        InternalAction internalAction = currentContext.onParse(input);
        responseLog.addAll(List.of(internalAction.getAllResponses()));
        internalAction.doRunnable();
        DukeResponse[] responses = responseLog.toArray(DukeResponse[]::new);
        responseLog.clear();

        return responses;
    }


    /**
     * Exits the current state
     */
    public static void exitCurrentState() {
        assert currentContext != null;
        try {
            SaveManager.serialize("saveFile");
        } catch (IOException e) {
            System.out.println(e);
            responseLog.add(new DukeResponse("Wait... I had some issues saving your progress"));
        }
        currentContext = currentContext.onExit();
        InternalAction internalAction = currentContext.onInvoke();
        responseLog.addAll(List.of(internalAction.getAllResponses()));
        internalAction.doRunnable();
    }

    /**
     * Sets the current state
     * @param state the state to be in
     */
    public static void setState(Command state) {
        currentContext = state;
        InternalAction internalAction = currentContext.onInvoke();
        responseLog.addAll(List.of(internalAction.getAllResponses()));
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
