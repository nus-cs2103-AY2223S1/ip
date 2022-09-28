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

	public String getResponse(String input) {
		Command command = Parser.parse(input);
		return command.execute(taskList, ui, storage);
	}

	public String getTaskList() {

		String text = "Here are your tasks: \n";
		text += this.taskList.taskListString();
		return text;
	}
}




