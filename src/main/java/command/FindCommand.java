package command;

import storage.Storage;
import task.TaskList;
import task.NotesList;
import ui.Ui;
import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    protected String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Returns a boolean false to show that this
     * is not the last command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command depending on which type of
     * task it is and prints a string to tell the user that
     * the task has been added.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     * @throws DukeException If the description of task is empty.
     */
    @Override
    public String execute(TaskList taskList, NotesList notesList, Ui ui, Storage storage) throws DukeException {
        String response = "";
        try {
            assert !taskList.isEmpty();
            assert !notesList.isEmpty();
            if (taskList.size() == 0) {
                throw new DukeException("There are no tasks in your list. :)");
            } else {
                String taskToFind = this.fullCommand.substring(5);
                ArrayList<Task> tasksFound = new ArrayList<>(taskList.size());
                for (int i = 0; i < taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    String currTaskString = currTask.toString();
                    if (currTaskString.contains(taskToFind)) {
                        tasksFound.add(currTask);
                    }
                }
                for (int j = 0; j < taskList.size(); j++) {
                    Task currNote = notesList.get(j);
                    String currNoteString = currNote.toString();
                    if (currNoteString.contains(taskToFind)) {
                        tasksFound.add(currNote);
                    }
                }
                    if (tasksFound.isEmpty()) {
                        throw new DukeException("There is nothing that matches the task.");
                    } else {
                        System.out.println("Here are the matching tasks in your list:");
                        tasksFound.forEach(n -> System.out.println((tasksFound.indexOf(n) + 1)
                                + "." + n.toString()));
                        response = "Here are the matching tasks in your list:\n";
                        for (int k = 0; k < tasksFound.size(); k++) {
                            Task currTaskFound = tasksFound.get(k);
                            String currTaskFoundString = currTaskFound.toString();
                            response += currTaskFoundString;
                        }
                        return response;

                    }
                }
        } catch (DukeException e) {
            System.out.println(e.toString());
            return e.toString();
        }
    }
}
