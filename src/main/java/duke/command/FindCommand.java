package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

import java.util.stream.Stream;

public class FindCommand extends Command {
    private String target;
    public FindCommand(String target) {
        super(Action.FIND);
        this.target = target;
    }

    public String getTarget() {
        return this.target;
    }

    @Override
    public void execute(TaskList tasks, MessagePrinter messagePrinter, Storage storage) {
        String message = "Here are the matching tasks in your list:\n";
        TaskList temp = new TaskList();
        Stream.iterate(0, x -> x + 1).limit(tasks.size())
                .map(i -> tasks.get(i))
                .filter(task -> task.getName().contains(this.target))
                .forEach(task -> temp.add(task));
        if (temp.size() == 0) {
            message = "Currently no matching tasks in the list.";
        } else {
            message = message + temp.toString();
        }
        messagePrinter.printMessage(message);
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public String getFormat() {
        return "find [keyword]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FindCommand) {
            FindCommand c = (FindCommand) obj;
            if (this.target == c.getTarget()) {
                return true;
            }
            if (this.target == null || c.getTarget() == null) {
                return false;
            }
            return this.target.equals(c.getTarget());
        }
        return false;
    }
}
