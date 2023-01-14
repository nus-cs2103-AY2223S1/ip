package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Task;
import amanda.ui.Ui;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class FindCommand extends Command {

	String key;

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
