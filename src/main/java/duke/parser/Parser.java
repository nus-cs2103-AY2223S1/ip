package duke.parser;
import java.time.LocalDate;

import duke.command.*;
import duke.exception.*;
import duke.task.*;


public class Parser {

    public static Command parseCommand(String input) throws Exception{
        String[] arguments =  input.trim().split(" ", 2);
        String commandType = arguments[0];

            if ("bye".equalsIgnoreCase(commandType)) {
//                Ui.showByeMessage();
//                System.exit(0);
               return exit();
            } else if ("list".equalsIgnoreCase(commandType)) {

                return listAllTasks();
            } else if ("unmark".equalsIgnoreCase(commandType)) {
                return unMarkTask(arguments);
            } else if ("mark".equalsIgnoreCase(commandType)) {
//
                 return markTask(arguments);
            } else if ("todo".equalsIgnoreCase(commandType)) {

               return addToDo(arguments);

            } else if ("deadline".equalsIgnoreCase(commandType)) {
               return addDeadline(arguments);

            } else if ("event".equalsIgnoreCase(commandType)) {
              return addEvent(arguments);
            } else if ("date".equalsIgnoreCase(commandType)) {
               return listTasksOnDate(arguments);

            } else if ("delete".equalsIgnoreCase(commandType)) {
               return deleteTask(arguments);
            } else {
                return invalid();

//                throw new InvalidInputException();

            }
    }

    private static Command addToDo (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        String toDo = arguments[1];
        return new AddCommand(new ToDo(toDo));
    }

    private static Command addDeadline (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        String[] inputs = arguments[1].split("/");
        String description = inputs[0];
        LocalDate date = LocalDate.parse(extractDateByKeyword("by",inputs[1]));
        return new AddCommand(new Deadline(description, date));
    }

    private static Command addEvent (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        String[] inputs = arguments[1].split("/");
        String description = inputs[0];
        LocalDate date = LocalDate.parse(extractDateByKeyword("at",inputs[1]));
        return new AddCommand(new Event(description, date));
    }

    private static Command deleteTask (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        int taskNo = Integer.parseInt(arguments[1]);
        return new deleteCommand(taskNo);
    }


    private static Command markTask (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        int taskNo = Integer.parseInt(arguments[1]);
        return new markCommand(taskNo);
    }

    private static Command unMarkTask (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        int taskNo = Integer.parseInt(arguments[1]);
        return new unMarkCommand(taskNo);
    }

    private static Command listAllTasks ()  {

        return new ListCommand();
    }

    private static Command listTasksOnDate (String[] arguments) throws EmptyDescriptionException{
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        LocalDate date = LocalDate.parse(arguments[1]);
        return new dateCommand(date);
    }

    private static Command invalid ()  {
        return new InvalidCommand();
    }
    private static Command exit ()  {
        return new byeCommand();
    }
    private static String extractDateByKeyword (String keyword, String text) {
        String[] args = text.split(keyword);
        String date = args[1].trim();
        return date;
    }
}
