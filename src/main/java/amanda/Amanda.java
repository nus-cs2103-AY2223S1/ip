package amanda;

import amanda.command.Command;
import amanda.exception.*;
import amanda.manager.QueryInterpreter;
import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.ui.Ui;

/**
 * Amanda is an interactive chatbot that keeps track of users tasks and deadlines.
 */
public class Amanda {

	private final StoreManager store;
	private final TaskList tasks;

	/**
	 * Constructor of the Amanda class.
	 * @param filePath Path to the storage file of amanda.
	 */
	public Amanda(String filePath) {
		store = new StoreManager(filePath);
		tasks = new TaskList();
		store.load();
	}

	/**
	 * Runs Amanda
	 */
	public String getResponse(String input) {
		try {
			Command c = QueryInterpreter.interpret(input);
			c.execute(tasks, store); // executes the command
		} catch (InvalidCommandException | InvalidIndexException | InvalidDescriptionException
				| InvalidDateFormatException | EmptyDateException | InvalidTagException e) {
			Ui.addResponse(e.getMessage());
		}
		return Ui.getAmandaResponse();
	}
}
