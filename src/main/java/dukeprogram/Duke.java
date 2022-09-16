package dukeprogram;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import dukeprogram.command.ContinuableCommand;
import dukeprogram.command.LoadUserCommand;
import dukeprogram.facilities.LoanCollection;
import dukeprogram.facilities.TaskList;
import dukeprogram.facilities.User;
import dukeprogram.parser.Parser;
import dukeprogram.storage.SaveManager;
import dukeprogram.userinterface.DukeResponse;
import dukeprogram.userinterface.MainWindow;
import dukeprogram.userinterface.Widget;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

/**
 * This is the main Duke Program
 */
public class Duke {
    private final MainWindow mainWindow;

    private User user;
    private TaskList taskList;
    private LoanCollection loanCollection;
    private final Parser parser;

    private Optional<ContinuableCommand> attachedState = Optional.empty();

    /**
     * Creates an instance of Duke
     * @param mainWindow the window is attached this instance
     */
    public Duke(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.parser = new Parser(this);
        LoadUserCommand loadUserCommand = new LoadUserCommand(this);
        loadUserCommand.load().ifPresentOrElse(
                loadedUser -> {
                    this.user = loadedUser;
                    this.taskList = loadUserCommand.getTaskList().orElseThrow(NullPointerException::new);
                    this.loanCollection = loadUserCommand.getLoanCollection().orElseThrow(NullPointerException::new);
                },
                //CHECKSTYLE.OFF: SeparatorWrap
                () -> sendMessage("I can't identify you, file was corrupted...")
        );

        Timer saveTimer = new Timer();
        saveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                save();
                sendMessage("Oh, by the way, I just saved your file.");
            }
        }, 300_000, 300_000);
    }


    /**
     * Sends a message to the GUI of this current instance
     */
    public void sendMessage(String message) {
        mainWindow.sendDukeDialog(new DukeResponse(message));
    }

    /**
     * Sends a message to the GUI of this current instance
     */
    public void sendMessage(String message, Widget widget) {
        mainWindow.sendDukeDialog(new DukeResponse(message, widget));
    }


    /**
     * Parses the input given
     * @param userInput the user input given
     */
    public void parseInput(String userInput) {
        if (attachedState.isPresent()) {
            System.out.println("Caught attachment");
            try {
                attachedState.get().continueParse(parser.convertToIterator(userInput));
            } catch (InvalidCommandException e) {
                // ignores the attached state and resends input as a new command
                System.out.println("Ignored");
                attachedState = Optional.empty();
                parseInput(userInput);
            }
        } else {
            try {
                parser.parse(userInput);
            } catch (IncompleteCommandException e) {
                sendMessage("You need to finish the command.");
            }
        }
    }

    /**
     * Attaches a state to parse for the next command
     * @param state the state to attach
     */
    public void attachState(ContinuableCommand state) {
        attachedState = Optional.of(state);
    }

    public TaskList getTaskList() {
        assert taskList != null : "Task list doesn't exist!";

        return taskList;
    }

    public LoanCollection getLoanCollection() {
        return loanCollection;
    }

    public User getUser() {
        return user;
    }

    /**
     * Saves this file to disk by calling the serialize method on the SaveManager
     */
    public void save() {
        try {
            SaveManager.serialize("savefile");
        } catch (IOException e) {
            sendMessage("Hey, I tried to save this file, but I couldn't for some reason...");
            sendMessage("Is the save file open on your computer?");
            System.out.println(e);
        }
    }
}
