package bobthebot.utils;

import bobthebot.command.*;
import bobthebot.exceptions.BobException;
import bobthebot.tasks.ToDoList;

/**
 * Parser class which primarily handles the logic of how the handle the input.
 */
public class Parser {
    /**
     * Takes in the user's command and handles the logic of the output.
     *
     * @param command A String containing the user's input.
     * @param list The Todo List which the command will act on.
     * @return String representing result of the command.
     */
    public String parseCommand(String command, ToDoList list) throws BobException {
        String[] splitCommand = command.trim().split("\\s+", 2);
        switch (splitCommand[0]) {
        case "list":
            return parseList(list);
        case "mark":
            return parseMark(splitCommand, list);
        case "unmark":
            return parseUnmark(splitCommand, list);
        case "delete":
            return parseDelete(splitCommand, list);
        case "find":
            return parseFind(splitCommand, list);
        default:
            throw new BobException("whoops invalid");
        }
    }

    /**
     * Parses the "list" command, which shows the user the items in the ToDo list, their type and status.
     *
     * @param list ToDoList which "list" command acts on.
     * @return String representing the output of the "list" command.
     */
    private String parseList(ToDoList list) {
        ListCommand listCommand = new ListCommand(list);
        return listCommand.execute();
    }

    /**
     * Parses the "mark" command, which marks an item on the list as done.
     *
     * @param splitCommand A String array containing the "mark" command and it's argument.
     * @param list ToDoList which "mark" command acts on.
     * @return String representing the output of the "mark" command.
     */
    private String parseMark(String[] splitCommand, ToDoList list) throws BobException {
        if (list.getLength() == 0) {
            String errorString = "\tPlease add items to your list before wanting to mark them as done!";
            throw new BobException(errorString);
        }

        if (splitCommand.length < 2) {
            String errorString = "\tPlease input an index to mark as done!";
            throw new BobException(errorString);
        }

        int index = Integer.parseInt(splitCommand[1]);
        MarkCommand markCommand = new MarkCommand(index, list);
        return markCommand.execute();
    }

    /**
     * Parses the "unmark" command, which marks an item on the list as undone.
     *
     * @param splitCommand A String array containing the "unmark" command and it's argument.
     * @param list ToDoList which "unmark" command acts on.
     * @return String representing the output of the "unmark" command.
     */
    private String parseUnmark(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            String errorString = "\tPlease input an index to delete!";
            throw new BobException(errorString);
        }

        if (list.getLength() == 0) {
            String errorString = "\tPlease add items to your list before wanting to mark them as undone!";
            throw new BobException(errorString);
        }

        if (splitCommand.length < 2) {
            String errorString = "\tPlease input an index to mark as undone!";
            throw new BobException(errorString);
        }

        int index = Integer.parseInt(splitCommand[1]);
        UnmarkCommand unmarkCommand = new UnmarkCommand(index, list);
        return unmarkCommand.execute();
    }

    /**
     * Parses the "delete" command, which deletes the specified element in the list.
     *
     * @param splitCommand A String array containing the "delete" command and it's argument.
     * @param list ToDoList which "mark" command acts on.
     * @return String representing the output of the "mark" command.
     */
    private String parseDelete(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            String errorString = "\tPlease input an index to delete!";
            throw new BobException(errorString);
        }

        int index = Integer.parseInt(splitCommand[1]);
        if (index > list.getLength() || index <= 0) {
            String errorString = "\tPlease input an index of an existing task!";
            throw new BobException(errorString);
        }

        DeleteCommand deleteCommand = new DeleteCommand(index, list);
        return deleteCommand.execute();
    }

    /**
     * Parses the "find" command, which finds the elements in the ToDo list which match with
     *     the argument of the command.
     *
     * @param splitCommand A String array containing the "find" command and it's argument.
     * @param list ToDoList which "find" command acts on.
     * @return String representing the output of the "find" command.
     */
    private String parseFind(String[] splitCommand, ToDoList list) {
        FindCommand findCommand = new FindCommand(list, splitCommand[1]);
        return findCommand.execute();
    }
}
