package duke;

import duke.commands.*;

public class Parser {

    public static Command parse(String userInput) {

        try {
            String[] splitUI = userInput.split(" ");
            switch (splitUI[0]) {
                case "list":
                    return new ListCommand();

                case "bye":
                    return new ExitCommand();
            }

            String[] splitUserInput = userInput.split(" ", 2);

            if (splitUserInput.length <= 1) {
                throw new DukeException("Sowwie meowmeow doesn't understand what you said uwu");
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

                    default:
                        System.out.println("Sowwie meowmeow doesn't understand what you said uwu");
                        return new DefaultCommand();
                }
            }

        } catch (DukeException e) {
            System.out.println(e.message);
        }
        return new DefaultCommand();
    }
}
