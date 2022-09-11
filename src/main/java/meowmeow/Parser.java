package meowmeow;

import meowmeow.commands.*;

/**
 * Class Parser is a class that parses the user's input and translates it into a Command.
 */
public class Parser {

    public static Command parse(String userInput) {

        try {
            String[] splitUI = userInput.split(" ");
            assert splitUI.length > 0 : "splitUI should not be empty";
            switch (splitUI[0]) {
            case "list":
                return new ListCommand();

            case "bye":
                return new ExitCommand();

            case "undo":
                return new UndoCommand();

            default:
                break;
            }

            String[] splitUserInput = userInput.split(" ", 2);

            if (splitUserInput.length <= 1) {
                throw new MeowmeowException("Sowwie meowmeow doesn't understand what you said uwu");
            } else {
                String cmdWord = splitUserInput[0];

                switch (cmdWord) {
                case "mark":
                    int taskNum = Integer.parseInt(splitUserInput[1]);
                    return new MarkCommand(true, taskNum);

                case "unmark":
                    taskNum = Integer.parseInt(splitUserInput[1]);
                    return new MarkCommand(false, taskNum);

                case "todo":
                    String taskName = splitUserInput[1];
                    return new AddCommand('T', taskName);

                case "deadline":
                    String nameAndLocalDateTime = splitUserInput[1];
                    return new AddCommand('D', nameAndLocalDateTime);

                case "event":
                    String nameAndTime = splitUserInput[1];
                    return new AddCommand('E', nameAndTime);

                case "delete":
                    int taskToDelete = Integer.parseInt(splitUserInput[1]);
                    return new DeleteCommand(taskToDelete);

                case "find":
                    String taskToFind = splitUserInput[1];
                    return new FindCommand(taskToFind);

                default:
                    return new DefaultCommand();
                }
            }

        } catch (MeowmeowException e) {
            System.out.println(e);
        }
        return new DefaultCommand();
    }
}
