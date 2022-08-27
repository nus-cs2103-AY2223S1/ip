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
            TaskList tasks = new TaskList();
            Storage storage = new Storage(tasks);
            Parser parser = new Parser();
            
            String commandResult;
            ui.showIntroMsg();
            do {
                String input = ui.getUserCommand();
                commandResult = parser.parseInput(input, tasks);
                storage.saveTaskList(tasks);
                ui.showToUser(commandResult);
            } while (!commandResult.equals("exit"));
            ui.showOutroMsg();
        } catch (DukeException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
