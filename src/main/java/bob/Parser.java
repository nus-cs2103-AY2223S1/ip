package bob;

import bob.commands.*;

/**
 * Represents Parser object, to read and respond to user input
 */
public class Parser {

    private enum PossibleCommands {
        BYE, LIST, MARK, UNMARK, REMOVE, FILTER, FIND, TODO, DEADLINE, EVENT, UPDATE, HELP
    }

    /**
     * Parses through user input and determines response
     *
     * @param reply user input
     * @param taskList current list of tasks
     * @return command to be executed
     * @throws BobException
     */
    //referenced https://github.com/Donovan9617/ip/blob/master/src/main/java/Duke/Parser.java for structure
    public Command parse(String reply, TaskList taskList) throws BobException {
        String[] splitReply = reply.split(" ");
        try {
            PossibleCommands c = PossibleCommands.valueOf(splitReply[0].toUpperCase());
            switch (c) {
            case BYE:
                return new ByeCommand();
            case HELP:
                return new HelpCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                if (splitReply.length == 1 || Integer.valueOf(splitReply[1]) == 0) {
                    throw new BobException("which task to mark?");
                }
                if (Integer.valueOf(splitReply[1]) > taskList.getLength()) {
                    throw new BobException("you don't have that many tasks!");
                }
                return new MarkCommand(Integer.valueOf(splitReply[1]));
            case UNMARK:
                if (splitReply.length == 1 || Integer.valueOf(splitReply[1]) == 0) {
                    throw new BobException("which task to unmark?");
                }
                if (Integer.valueOf(splitReply[1]) > taskList.getLength()) {
                    throw new BobException("you don't have that many tasks!");
                }
                return new UnmarkCommand(Integer.valueOf(splitReply[1]));
            case REMOVE:
                if (splitReply.length == 1 || Integer.valueOf(splitReply[1]) == 0) {
                    throw new BobException("which task to remove?");
                }
                if (Integer.valueOf(splitReply[1]) > taskList.getLength()) {
                    throw new BobException("you don't have that many tasks!");
                }
                return new RemoveCommand(Integer.valueOf(splitReply[1]));
            case FILTER:
                if (splitReply.length == 1) {
                    throw new BobException("what date do you want to filter?");
                }
                String date = splitReply[1];
                return new FilterCommand(date);
            case UPDATE:
                if (splitReply.length == 1) {
                    throw new BobException("what task do you want to update?");
                }
                if (splitReply.length == 2) {
                    throw new BobException("what part of the task do you want to update?");
                }
                int index = Integer.valueOf(splitReply[1]);
                String updateInfo = reply.substring(9);
                return new UpdateCommand(index, updateInfo);
            case FIND:
                if (splitReply.length == 1) {
                    throw new BobException("what word do you want to search for?");
                }
                String searchWord = splitReply[1];
                return new FindCommand(searchWord);
            case TODO:
                if (splitReply.length == 1) {
                    throw new BobException("Oops! What's the todo again?");
                }
                String taskName = reply.substring(5);
                return new TodoCommand(taskName);
            case DEADLINE:
                if (splitReply.length == 1) {
                    throw new BobException("Oops! When's your deadline again?");
                }
                String[] deadlineTemp = reply.split("/");
                String deadlineName = deadlineTemp[0].substring(9, deadlineTemp[0].length() - 1);
                String deadlineDate = deadlineTemp[1].substring(3);
                return new DeadlineCommand(deadlineName, deadlineDate);
            case EVENT:
                if (splitReply.length == 1) {
                    throw new BobException("Oops! When's your event again?");
                }
                String[] eventTemp = reply.split("/");
                String eventName = eventTemp[0].substring(6, eventTemp[0].length() - 1);
                String eventDate = eventTemp[1].substring(3);
                return new EventCommand(eventName, eventDate);
            default:
                throw new BobException("Oops! What do you want to do?");
            }
        } catch (IllegalArgumentException e) {
            throw new BobException("Oops! What do you want to do?");
        }
    }
}
