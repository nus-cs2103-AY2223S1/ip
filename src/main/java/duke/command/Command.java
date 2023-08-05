package duke.command;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Command class which has a few types of command.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public abstract class Command {
    /**
     * Method to determine if program should exit.
     *
     * @return The exit status.
     */
    public abstract Boolean isExit();

    /**
     * Executes the given command.
     *
     * @param tasks The TaskList object for this command.
     * @param ui The Ui object for this command.
     * @param storage The Storage object for this command.
     * @throws IOException If an I/O error occurs.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * A factory method for Command class.
     *
     * @param s The command given.
     * @return A Command object depending on the command given.
     * @throws DukeException
     */
    public static Command of(String s) throws DukeException {
        String[] arr = s.split(" ", 2);
        switch (arr[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            //Fallthrough
        case "unmark":
            return new MarkCommand(arr);
        case "priority":
            return new PriorityCommand(arr);
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            return new AddCommand(arr);
        case "find":
            return new FindCommand(arr);
        case "delete":
            return new DeleteCommand(arr);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Class for 'add' command.
     */
    private static class AddCommand extends Command {
        /**
         * The command keyword.
         */
        private String comm;
        /**
         * The description for the 'add' command.
         */
        private String description;
        /**
         * The time for the 'add' command.
         */
        private String time;

        /**
         * A constructor to initialize AddCommand based on the command given.
         *
         * @param arr An array with the first element being the keyword of the command,
         *            and if applicable the rest of the command in the second element.
         * @throws ArrayIndexOutOfBoundsException If access the second element of arr when it doesn't exist.
         */
        private AddCommand(String[] arr) throws ArrayIndexOutOfBoundsException {
            this.comm = arr[0];
            if (comm.equals("todo")) {
                this.description = arr[1];
                this.time = "";
            } else {
                if (arr.length == 1) {
                    throw new ArrayIndexOutOfBoundsException("OOPS!!! The description of a "
                            + comm + " cannot be empty.");
                }
                assert arr.length > 1 : "arr length should be greater than 1";
                String[] temp = arr[1].split(" /");
                String description = temp[0];
                String time = temp[1].split(" ", 2)[1];
                assert !description.isBlank() : "String should have some values";
                assert !time.isBlank() : "String should have some values";
                this.description = description;
                this.time = time;
            }
        }

        @Override
        public Boolean isExit() {
            return false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task t = null;
            switch (comm) {
            case "todo":
                t = new Todo(description);
                break;
            case "deadline":
                t = new Deadline(description, time);
                break;
            case "event":
                t = new Event(description, time);
                break;
            }
            tasks.add(t);
            ui.printAddingTask(t, tasks);
        }
    }

    /**
     * Class for 'exit' command.
     */
    private static class ExitCommand extends Command {

        @Override
        public Boolean isExit() {
            return true;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
            storage.updateData(tasks);
            ui.printGoodbye();
        }
    }

    /**
     * Class for 'delete' command.
     */
    private static class DeleteCommand extends Command {
        /**
         * The index of the element to be deleted.
         */
        private int index;

        /**
         * A constructor to initialize DeleteCommand based on the command given.
         *
         * @param arr An array with the first element being the keyword of the command,
         *            and if applicable the rest of the command in the second element.
         */
        private DeleteCommand(String[] arr) {
            this.index = Integer.parseInt(arr[1]) - 1;
        }

        @Override
        public Boolean isExit() {
            return false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            if (index > tasks.size() - 1) {
                throw new DukeException(String.format("Cannot delete task at %d", index + 1));
            }

            int oldTasksLength = tasks.size();
            Task t = tasks.remove(index);
            assert oldTasksLength == tasks.size() + 1 : "tasks length should decrease by 1";
            ui.printDelete(t, tasks);
        }
    }

    /**
     * Class for 'mark' and 'unmark' command.
     */
    private static class MarkCommand extends Command {
        /**
         * The command keyword.
         */
        private String comm;
        /**
         * The index of the element to be marked or unmarked.
         */
        private int index;

        /**
         * A constructor to initialize MarkCommand based on the command given.
         *
         * @param arr An array with the first element being the keyword of the command,
         *            and if applicable the rest of the command in the second element.
         */
        private MarkCommand(String[] arr) {
            this.comm = arr[0];
            this.index = Integer.parseInt(arr[1]) - 1;
        }

        @Override
        public Boolean isExit() {
            return false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task t = tasks.get(index);
            switch (comm) {
            case "mark":
                t.mark();
                ui.printMarked(t);
                break;
            case "unmark":
                t.unMark();
                ui.printUnMarked(t);
                break;
            }
        }
    }

    /**
     * Class for 'priority' command
     */
    private static class PriorityCommand extends Command {
        /**
         * The index of the element whose priority is being set.
         */
        private int index;

        /**
         * The priority level to be set.
         */
        private String priority;

        /**
         * A constructor to initialize PriorityCommand based on the command given.
         *
         * @param arr An array with the first element being the keyword of the command,
         *            and if applicable the rest of the command in the second element.
         */
        private PriorityCommand(String[] arr) {
            String[] temp = arr[1].split(" ", 2);
            this.index = Integer.parseInt(temp[0]) - 1;
            this.priority = temp[1];
        }

        @Override
        public Boolean isExit() {
            return false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task t = tasks.get(index);
            t.setPriority(priority);
            ui.printPriority(t);
        }
    }

    /**
     * Class for 'list' command.
     */
    private static class ListCommand extends Command {

        @Override
        public Boolean isExit() {
            return false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.printCurrentList(tasks);
        }
    }

    private static class FindCommand extends Command {
        private String keyword;

        private FindCommand(String[] arr) {
            if (arr.length == 1) {
                throw new ArrayIndexOutOfBoundsException("OOPS!!! The description of find cannot be empty.");
            }
            this.keyword = arr[1];
        }

        @Override
        public Boolean isExit() {
            return false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            List<Integer> indexArr = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.toString().contains(keyword)) {
                    indexArr.add(i);
                    assert t.toString().contains(keyword);
                }
            }
            ui.printMatchingList(tasks, indexArr);
        }
    }
}
