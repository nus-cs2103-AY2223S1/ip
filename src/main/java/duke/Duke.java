package duke;

import duke.dukeExceptions.DukeException;

/**
 * Represents the Duke task manager program.
 * 
 * @author Ramanathan Kumarappan
 */
public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        try {
            TaskList taskList = new TaskList();
            Storage storage = new Storage(taskList);
            Parser parser = new Parser();
            
            String commandResult;
            ui.showIntroMsg();
            do {
                String userInput = ui.getUserCommand();
                commandResult = parser.parseInput(userInput, taskList);
                storage.saveTaskList(taskList);
                ui.showToUser(commandResult);
            } while (!commandResult.equals("exit"));
            ui.showOutroMsg();
        } catch (DukeException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
