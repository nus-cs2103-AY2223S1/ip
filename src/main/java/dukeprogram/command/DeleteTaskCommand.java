package dukeprogram.command;

import dukeprogram.Duke;
import dukeprogram.InternalAction;
import dukeprogram.Task;
import dukeprogram.facilities.TaskList;

/**
 * Deletes a task by index
 */
public class DeleteTaskCommand extends Command {

    @Override
    protected InternalAction onEnter() {
        return new InternalAction("I can help you delete tasks!");
    }

    @Override
    protected InternalAction onStay() {
        return onEnter();
    }

    @Override
    public InternalAction onParse(String input) {
        String[] fullCommandParameters = input.split(" ");
        if (fullCommandParameters.length < 2) {
            return new InternalAction(
                    "You gotta tell me which index what want me to delete!");
        }

        TaskList currentTaskList = TaskList.current();
        int index;
        if (fullCommandParameters[1].equals("all")) {
            index = -1;
        } else {
            try {
                index = Integer.parseInt(fullCommandParameters[1]) - 1;
                if (index < 0 || index >= currentTaskList.getSize()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                return new InternalAction("You gotta give me a valid index man...");
            }
        }

        if (index == -1) {
            return new InternalAction(
                    //CHECKSTYLE.OFF: SeparatorWrap
                    () -> Duke.setState(new Command() {
                        @Override
                        protected InternalAction onEnter() {
                            return new InternalAction(
                                    "Are you sure you want to delete all tasks?");
                        }

                        @Override
                        protected InternalAction onStay() {
                            return onEnter();
                        }

                        @Override
                        public InternalAction onParse(String input) {
                            if (input.equals("yes")) {
                                TaskList.current().clear();
                                return new InternalAction(
                                        String.format("Ok, I've deleted all the items in %s.",
                                                TaskList.current().getName()),
                                        Duke::exitCurrentState
                                );
                            } else if (!input.equals("no")) {
                                return new InternalAction("Please only input \"yes\" or \" no\"");
                            }

                            return new InternalAction("Suits you man.",
                                    Duke::exitCurrentState);
                        }

                        @Override
                        public Command onExit() {
                            return DeleteTaskCommand.this.onExit();
                        }
                    })
            );
        } else {
            Task task = TaskList.current().get(index);
            currentTaskList.remove(index);

            return new InternalAction(
                    "Okay, I've removed this task as requested:\n" + task.toString(),
                    Duke::exitCurrentState
            );
        }
    }

    @Override
    public Command onExit() {
        return new AccessTasksCommand();
    }
}
