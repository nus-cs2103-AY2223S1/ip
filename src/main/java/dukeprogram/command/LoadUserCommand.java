package dukeprogram.command;

import java.util.Iterator;
import java.util.Optional;

import dukeprogram.Duke;
import dukeprogram.facilities.LoanCollection;
import dukeprogram.facilities.User;
import dukeprogram.facilities.TaskList;
import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

/**
 * Defines all the possible commands to be executed from the HomePage
 */
public class LoadUserCommand extends Command {

    private User user;
    private TaskList taskList;
    private LoanCollection loanCollection;

    public LoadUserCommand(Duke duke) {
        super(duke);
    }

    /**
     * Loads the current user from the save file
     * @return an optional containing the User loaded
     */
    public Optional<User> load() {
        if (SaveManager.deserialize("saveFile")) {
            try {
                user = SaveManager.load("user");
                duke.sendMessage(String.format("Welcome back %s!", user.getName()));
            } catch (KeyNotFoundException e) {
                return Optional.empty();
            }
        } else {
            user = new User(System.getProperty("user.name"), User.USER_IMAGE);
            SaveManager.save("user", user);
            duke.sendMessage(String.format("Nice to meet you %s!", user.getName()));
        }

        duke.sendMessage("Don't forget, you can input \"help\" to review valid commands.");

        taskList = TaskList.loadTaskList();
        loanCollection = LoanCollection.loadLoanCollection();
        return Optional.of(user);
    }

    /**
     * Parses the first element of the userInput to decide what facility to use
     * @param elements the elements to accept
     */
    @Override
    public void parse(Iterator<String> elements) {
        load().ifPresent(loadedUser -> user = loadedUser);
    }

    public Optional<User> getUser() {
        return Optional.of(user);
    }

    public Optional<TaskList> getTaskList() {
        return Optional.of(taskList);
    }

    public Optional<LoanCollection> getLoanCollection() {
        return Optional.of(loanCollection);
    }
}
