package jean.parser;

import jean.command.*;
import jean.exception.JeanException;
import jean.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static MarkCommand checkMark(String fullCommand, TaskList taskList) throws JeanException {
        if (fullCommand.length() == 4) {
            throw new JeanException("You must name a valid task to mark!"
                                     + "\nNom d'un valable tâche à marqué comme fait!");
        } else if (Integer.parseInt(fullCommand.substring(5)) > taskList.getNumberOfTasks() || Integer.parseInt(fullCommand.substring(5)) < 1) {
            throw new JeanException("There are only " + taskList.getNumberOfTasks() + " task(s)!"
                                    + "\nIl y a seulement " + taskList.getNumberOfTasks() + " tâche(s)!");
        } else if (taskList.getTaskList().get(Integer.parseInt(fullCommand.substring(5)) - 1).getIsDone()){
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
            } else if (Integer.parseInt(fullCommand.substring(7)) > taskList.getNumberOfTasks() || Integer.parseInt(fullCommand.substring(7)) < 1) {
                throw new JeanException("There are only " + taskList.getNumberOfTasks() + " task(s)!"
                                        + "\nIl y a seulement " + taskList.getNumberOfTasks() + " tâche(s)!");
            } else if (!taskList.getTaskList().get(Integer.parseInt(fullCommand.substring(7)) - 1).getIsDone()){
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
        } else if (Integer.parseInt(fullCommand.substring(7)) < 1 || Integer.parseInt(fullCommand.substring(7)) > taskList.getNumberOfTasks()) {
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

    public static Command parse(String fullCommand, TaskList taskList) throws JeanException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            return checkMark(fullCommand, taskList);
        } else if (fullCommand.startsWith("unmark")) {
            return checkUnmark(fullCommand, taskList);
        } else if (fullCommand.startsWith("todo")) {
            return checkTodo(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return checkDeadline(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return checkEvent(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return checkDelete(fullCommand, taskList);
        } else if (fullCommand.startsWith("find")) {
            return checkFind(fullCommand);
        } else {
            throw new JeanException("No such command!");
        }
    }
}
