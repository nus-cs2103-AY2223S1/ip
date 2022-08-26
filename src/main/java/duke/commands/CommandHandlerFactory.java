package duke.commands;

import duke.exceptions.DukeException;

import java.util.List;

public class CommandHandlerFactory {
    public CommandHandler getHandler(String input) throws DukeException {
       Parser parser = new Parser();
       Command command = parser.getCommand(input);

       List<String> args = parser.parseCommand(input);
       String value = args.get(0);
       String flag = args.get(1);
       String additionalValue = args.get(2);

       switch(command) {
           case LIST:
               return new CommandListHandler(value, flag, additionalValue);
           case MARK:
               return new CommandMarkHandler(value, flag, additionalValue);
           case UNMARK:
               return new CommandUnmarkHandler(value, flag, additionalValue);
           case TODO:
               return new CommandTodoHandler(value, flag, additionalValue);
           case DEADLINE:
               return new CommandDeadlineHandler(value, flag, additionalValue);
           case EVENT:
               return new CommandEventHandler(value, flag, additionalValue);
           case DELETE:
               return new CommandDeleteHandler(value, flag, additionalValue);
           default:
               throw new DukeException("No such command!");
       }
    }
}
