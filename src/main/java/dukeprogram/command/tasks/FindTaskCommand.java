package dukeprogram.command.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.tasks.Task;
import dukeprogram.userinterface.Widget;
import javafx.scene.layout.Region;

/**
 * Finds a task in the task list
 */
public class FindTaskCommand extends Command {

    /**
     * Creates a FindTaskCommand
     * @param duke the instance of Duke that spawned this command
     */
    public FindTaskCommand(Duke duke) {
        super(duke);

    }

    @Override
    public void parse(Iterator<String> elements) {
        StringBuilder sb = new StringBuilder();

        while (elements.hasNext()) {
            sb.append(elements.next());
            if (elements.hasNext()) {
                sb.append(" ");
            }
        }

        Task[] tasksFound = duke.getTaskList().findTasks(sb.toString());

        duke.sendMessage("Here are the matches that I've found:\t\t\t\t\t\n",
                        new Widget(List.of(Arrays.stream(tasksFound)
                                .map(Task::createLabelWidget).toArray(Region[]::new)))
        );
    }
}
