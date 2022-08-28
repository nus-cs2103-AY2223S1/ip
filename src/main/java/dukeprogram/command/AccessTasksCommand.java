package dukeprogram.command;

import dukeprogram.Duke;
import dukeprogram.InternalAction;
import dukeprogram.facilities.TaskList;

/**
 * Access all the available tasks
 */
public class AccessTasksCommand extends Command {

    private Command nextCommand;

    @Override
    public InternalAction onEnter() {
        nextCommand = new ListTasksCommand();
        return new InternalAction(
                //CHECKSTYLE.OFF: SeparatorWrap
                () -> {
                    Duke.exitCurrentState();
                    Duke.setState(this);
                }
        );
    }

    @Override
    public InternalAction onStay() {
        return new InternalAction("Input one of these command:\n[add | list | mark | unmark | delete]");
    }


    @Override
    public InternalAction onParse(String input) {
        String[] separatedCommands = input.split(" ");

        switch (separatedCommands[0]) {
        case "list":
            nextCommand = new ListTasksCommand();
            return new InternalAction(() -> {
                Duke.exitCurrentState();
                Duke.setState(this);
            });

        case "find":
            nextCommand = this;
            return new FindTaskCommand().onParse(input);

        case "add":
            nextCommand = this;
            return new AddTaskCommand().onParse(input);

        case "mark":
            nextCommand = this;
            return annotateTask(separatedCommands, true);

        case "unmark":
            nextCommand = this;
            return annotateTask(separatedCommands, false);

        case "delete":
            nextCommand = this;
            return new DeleteTaskCommand().onParse(input);

        case "back":
            nextCommand = this;
            return new InternalAction(Duke::exitCurrentState);

        default:
            nextCommand = this;
            return new InternalAction("I didn't understand that!", Duke::exitCurrentState);
        }
    }

    @Override
    public Command onExit() {
        return nextCommand;
    }


    @SuppressWarnings("checkstyle:SeparatorWrap")
    private InternalAction annotateTask(String[] separatedCommands, boolean isMarking) {
        nextCommand = this;

        if (separatedCommands.length < 2) {
            return new InternalAction(
                    String.format("You have to specify which task index you want to %s!",
                            isMarking ? "mark" : "unmark")
            );
        }

        int index;
        try {
            index = Integer.parseInt(separatedCommands[1]) - 1;
            if (index < 0 || index >= TaskList.current().getSize()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return new InternalAction("You have to specify a valid task index man...");
        }

        return new InternalAction(
                String.format("Alright, I'll %s task %d", isMarking ? "mark" : "unmark", index + 1),
                //CHECKSTYLE.OFF: SeparatorWrap
                () -> { // On this line, the () is interpreted to be part of a function declaration
                    TaskList.current().get(index).markJobState(isMarking);
                    Duke.exitCurrentState();
                }
        );
    }
}
