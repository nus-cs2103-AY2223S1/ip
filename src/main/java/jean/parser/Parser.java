package jean.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jean.command.AddCommand;
import jean.command.Command;
import jean.command.DeleteCommand;
import jean.command.ExitCommand;
import jean.command.FindCommand;
import jean.command.ListCommand;
import jean.command.MarkCommand;
import jean.command.SortCommand;
import jean.command.UnmarkCommand;
import jean.exception.JeanException;
import jean.task.Deadline;
import jean.task.Event;
import jean.task.TaskList;
import jean.task.Todo;

/**
 * A class which serves to decipher user input.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String SORT_COMMAND = "sort";

    private static int getTaskIndex(String fullCommand, int indexOfSubstring) {
        return Integer.parseInt(fullCommand.substring(indexOfSubstring));
    }

    private static MarkCommand checkMark(String fullCommand, TaskList taskList) throws JeanException {
        if (fullCommand.length() == 4) {
            throw new JeanException("You must name a valid task to mark!"
                                     + "\nNom d'un valable tâche à marqué comme fait!");
        } else if (getTaskIndex(fullCommand, 5) > taskList.getNumberOfTasks()
                || getTaskIndex(fullCommand, 5) < 1) {
            throw new JeanException("There are only " + taskList.getNumberOfTasks() + " task(s)!"
                                    + "\nIl y a seulement " + taskList.getNumberOfTasks() + " tâche(s)!");
        } else if (taskList.getTaskList().get(getTaskIndex(fullCommand, 5) - 1)
                .getIsDone()) {
            throw new JeanException("It is already marked!"
                                    + "\nC'est déjaà fini!");
        } else {
            return new MarkCommand(Integer.parseInt(fullCommand.substring(5)) - 1);
        }
    }

    private static UnmarkCommand checkUnmark(String fullCommand, TaskList taskList) throws JeanException {
        if (fullCommand.length() == 6) {
            throw new JeanException("You must name a valid task to unmark!"
                                    + "\nNom d'une valable tâche à marqué comme défait!");
        } else if (getTaskIndex(fullCommand, 7) > taskList.getNumberOfTasks()
                || getTaskIndex(fullCommand, 7) < 1) {
            throw new JeanException("There are only " + taskList.getNumberOfTasks() + " task(s)!"
                                    + "\nIl y a seulement " + taskList.getNumberOfTasks() + " tâche(s)!");
        } else if (!taskList.getTaskList().get(getTaskIndex(fullCommand, 7) - 1)
                .getIsDone()) {
            throw new JeanException("It is not marked!"
                                    + "\nCe n'est pas encore fini!");
        } else {
            return new UnmarkCommand(Integer.parseInt(fullCommand.substring(7)) - 1);
        }
    }

    private static AddCommand checkTodo(String fullCommand) throws JeanException {
        if (fullCommand.length() == 4) {
            throw new JeanException("The description must not be empty!"
                                    + "\nVous devez donner une description!");
        } else {
            return new AddCommand(new Todo(fullCommand.substring(5)));
        }
    }

    private static AddCommand checkDeadline(String fullCommand) throws JeanException {
        int sep = fullCommand.indexOf("/by");

        if (sep == 9 || fullCommand.length() == 8) {
            throw new JeanException("The description must not be empty!"
                                    + "\nVous devez donner une description!");
        } else if (sep == -1) {
            throw new JeanException("You must give a deadline!"
                                    + "\nVous devez donner un délai!");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(fullCommand.substring(sep + 4), formatter);
            return new AddCommand(new Deadline(fullCommand.substring(9, sep), dateTime));
        }
    }

    private static AddCommand checkEvent(String fullCommand) throws JeanException {
        int sep = fullCommand.indexOf("/at");
        if (sep == 6 || fullCommand.length() == 5) {
            throw new JeanException("The description must not be empty!"
                    + "\nVous devez donner une description!");
        } else if (sep == -1) {
            throw new JeanException("You must give a time!"
                    + "\nVous devez donner une heure!");
        } else {
            return new AddCommand(new Event(fullCommand.substring(6, sep), fullCommand.substring(sep + 4)));
        }
    }

    private static DeleteCommand checkDelete(String fullCommand, TaskList taskList) throws JeanException {
        if (fullCommand.length() == 6) {
            throw new JeanException("You must name a task to delete!"
                                    + "\nNom d'une tâche à supprimer!");
        } else if (Integer.parseInt(fullCommand.substring(7)) < 1
                || Integer.parseInt(fullCommand.substring(7)) > taskList.getNumberOfTasks()) {
            throw new JeanException("It is not possible!"
                                    + "\nC'est impossible!");
        } else {
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(7)) - 1);
        }
    }

    private static FindCommand checkFind(String fullCommand) throws JeanException {
        if (fullCommand.length() == 4) {
            throw new JeanException("You must give a word to search!"
                                    + "\nVous devez donner un mot à chercher!");
        } else {
            return new FindCommand(fullCommand.substring(5));
        }
    }

    /**
     * Reads user command and calls its corresponding command if the input is valid.
     *
     * @param fullCommand The user input.
     * @param taskList The list of task to verify validity of input.
     * @return Returns a command corresponding to the user input.
     * @throws JeanException When user's input is invalid.
     */
    public static Command parse(String fullCommand, TaskList taskList) throws JeanException {
        assert !fullCommand.isEmpty() : "Command should not be empty!";

        if (fullCommand.equals(BYE_COMMAND)) {
            return new ExitCommand();
        } else if (fullCommand.equals(LIST_COMMAND)) {
            return new ListCommand();
        } else if (fullCommand.startsWith(MARK_COMMAND)) {
            return checkMark(fullCommand, taskList);
        } else if (fullCommand.startsWith(UNMARK_COMMAND)) {
            return checkUnmark(fullCommand, taskList);
        } else if (fullCommand.startsWith(TODO_COMMAND)) {
            return checkTodo(fullCommand);
        } else if (fullCommand.startsWith(DEADLINE_COMMAND)) {
            return checkDeadline(fullCommand);
        } else if (fullCommand.startsWith(EVENT_COMMAND)) {
            return checkEvent(fullCommand);
        } else if (fullCommand.startsWith(DELETE_COMMAND)) {
            return checkDelete(fullCommand, taskList);
        } else if (fullCommand.startsWith(FIND_COMMAND)) {
            return checkFind(fullCommand);
        } else if (fullCommand.startsWith(SORT_COMMAND)) {
            return new SortCommand();
        } else {
            throw new JeanException("No such command!");
        }
    }
}
