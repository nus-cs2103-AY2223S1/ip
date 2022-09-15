package duke;

import java.io.IOException;

public class Duke  {

    private Storage storage;
    private TaskList taskList;
    private UserInterface userInterface;
    private GuiUserInterface guiUserInterface;

    /**
     * Constructor for Duke.
     *
     * @param path Relative path in local storage to save data.
     */
    public Duke(String path) {
        try {
            this.taskList = new TaskList();
            storage = new Storage(path, taskList);
            storage.load();
            userInterface = new UserInterface(taskList);
            guiUserInterface = new GuiUserInterface(taskList);
        } catch (IOException e) {
            e.printStackTrace();
            UserInterface.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public Duke() {
    }

    ;

    public static void main(String[] args) throws DukeException {
//        new Duke("data/tasks.txt").run();
        new Duke("data/tasks.txt");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        EventHandler handler = new EventHandler(this.taskList, this.userInterface, this.guiUserInterface, this.storage);
        if (input.equals("bye")) {
            return guiUserInterface.sayBye();
        } else if (input.equals("list")) {
            return guiUserInterface.printList();
        } else if (input.startsWith("mark")) {
            return handler.markTask(input);
        } else if (input.startsWith("unmark")) {
            return handler.unmarkTask(input);
        } else if (input.startsWith("delete")) {
            return handler.deleteTask(input);
        } else if (input.startsWith("todo")) {
            return handler.addTodo(input);
        } else if (input.startsWith("deadline")) {
            return handler.addDeadline(input);
        } else if (input.startsWith("event")) {
            return handler.addEvent(input);
        } else if (input.startsWith("find")) {
            return handler.find(input);
        } else if (input.startsWith("help")) {
            return handler.help();
        } else {
            throw new DukeUnknownCommandException();
        }
    }
}
