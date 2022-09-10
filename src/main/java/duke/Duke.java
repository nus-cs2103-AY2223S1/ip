package duke;

import java.util.Optional;
import java.util.function.Consumer;

import duke.commands.Command;
import duke.task.TaskList;

/**
 * The Duke chatbot
 */
public class Duke {
    private final TaskList tasks;
    private final Consumer<Message> messageSender;


    /**
     * Creates a duke chatbot which persists tasks to the file named fileName.
     *
     * @param fileName Name of the file to store tasks in.
     * @param messageSender the consumer which sends a message to the ui.
     */
    public Duke(String fileName, Consumer<Message> messageSender) throws DukeException {
        assert fileName != null : "fileName should not be null";
        assert messageSender != null : "messageSender should not be null";
        this.messageSender = messageSender;
        messageSender.accept(new Message("Hello! I'm Duke\nWhat do you need to do?",
                false,
                Message.User.DUKE));


        this.tasks = getStorage(fileName).map(TaskList::new).orElse(new TaskList());
    }

    private Optional<Storage> getStorage(String fileName) {
        Storage storage;
        try {
            storage = new Storage(fileName);
        } catch (DukeException e) {
            storage = null;
        }
        return Optional.ofNullable(storage);
    }

    /**
     * Takes in the user input and returns a message from Duke.
     *
     * @param userInput user input from the gui.
     */
    public void processUserInput(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            messageSender.accept(c.execute(tasks));
        } catch (DukeException e) {
            messageSender.accept(new Message(e.getMessage(), false, Message.User.DUKE, Message.Type.ERROR));
        }
    }
}
