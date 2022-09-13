package dukeprogram.command;

import java.util.Iterator;
import java.util.Optional;

import dukeprogram.Duke;
import dukeprogram.User;
import dukeprogram.facilities.TaskList;
import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

/**
 * Defines all the possible commands to be executed from the HomePage
 */
public class LoadUserCommand extends Command {

    private User user;
    private TaskList taskList;

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
                taskList = TaskList.loadTaskList();
                duke.sendMessage("Welcome back " + user.getName());
                return Optional.of(user);
            } catch (KeyNotFoundException e) {
                return Optional.empty();
            }
        } else {
            user = new User(System.getProperty("user.name"), User.USER_IMAGE);
            SaveManager.save("user", user);
            taskList = TaskList.loadTaskList();
            duke.sendMessage("Nice to meet you " + user.getName());
            return Optional.of(user);
        }
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
}
