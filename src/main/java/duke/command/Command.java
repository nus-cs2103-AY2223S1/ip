package duke.command;

import java.io.IOException;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import duke.ui.Ui;

public abstract class Command {
    public abstract Boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

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
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            return new AddCommand(arr);
        case "delete":
            return new DeleteCommand(arr);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static class AddCommand extends Command {
        private String comm;
        private String description;
        private String time;

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
                String[] temp = arr[1].split(" /");
                String s1 = temp[0];
                String s2 = temp[1].split(" ", 2)[1];
                this.description = s1;
                this.time = s2;
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

    private static class DeleteCommand extends Command {
        private int index;

        private DeleteCommand(String[] arr) {
            this.index = Integer.parseInt(arr[1]) - 1;
        }

        @Override
        public Boolean isExit() {
            return false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task t = tasks.remove(index);
            ui.printDelete(t, tasks);
        }
    }

    private static class MarkCommand extends Command {
        private String comm;
        private int index;

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
}
