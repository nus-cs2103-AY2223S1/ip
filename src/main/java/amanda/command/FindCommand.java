package amanda.command;

import java.util.ArrayList;
import java.util.StringTokenizer;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Task;
import amanda.ui.Ui;

/**
 * FindCommand is a command to search for all the tasks in the current list with the given keyword by the user
 */
public class FindCommand extends Command {
	private String key;

	/**
	 * Constructor for FindCommand
	 * @param input keyword.
	 */
	public FindCommand(String input) {
		StringTokenizer tokens = new StringTokenizer(input, " ");
		tokens.nextToken();
		this.key = tokens.nextToken();
	}

	@Override
	public void execute(TaskList tasks, StoreManager store) {
		String curr;
		int idx = 0;
		ArrayList<Task> matches = new ArrayList<>();
		for (Task t: TaskList.getList()) {
			curr = t.getDesc();
			StringTokenizer tokens = new StringTokenizer(curr, " ");
			String token;
			while (tokens.hasMoreTokens()) {
				token = tokens.nextToken();
				if (token.equals(key)) {
					matches.add(TaskList.getList().get(idx));
					break;
				}
			}
			idx++;
		}
		if (matches.size() == 0) {
			Ui.addResponse("No matches found. Try harder next time.");
		} else {
			Ui.findResponse();
			for (int i = 0; i < matches.size(); i++) { // iterate through matches task list and print each task.
				Ui.addResponse((i + 1) + "." + matches.get(i) + "\n");
			}
		}
	}

}
