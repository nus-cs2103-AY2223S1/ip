package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.tasks.Task;
import duke.ui.Ui;
import duke.ui.DialogBox;
import duke.storage.Storage;
import duke.tasks.TaskList;


/**
 * Initalises and runs the duke program
 */
public class Duke {

	private Ui ui;
	private Storage storage;
	private TaskList taskList;

	/**
	 * Set up the Ui, Storage and TaskList
	 */
	public Duke() {
		ui = new Ui();
		storage = new Storage();
		taskList = new TaskList(storage.convertToTaskList());
	}

	/**
	 * Runs the program unitl exit is issued
	 */
//	public void run() {
//		System.out.println(ui.displayWelcomeMessage());
//		boolean isExit = false;
//		while(!isExit) {
//			String userInput = ui.getUserInput();
//			Command command = Parser.parse(userInput);
//			command.execute(taskList, ui, storage);
//			isExit = command.isExit();
//
//		}
//	}

	public String getResponse(String input) {
		Command command = Parser.parse(input);
		return command.execute(taskList, ui, storage);
	}
}




