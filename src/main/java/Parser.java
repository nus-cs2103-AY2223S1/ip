package anya;

/**
 * Represents a class that deals with making sense of the user command.
 */
public class Parser {

    Parser() {

    }

    /**
     * Process the input command by the users to give the respective command to the ChatBot to execute.
     * @param String fullCommand : the full command that is given by the users.
     * @return command : the specific command to execute later.
     * @throws MismatchInputException
     * @throws TaskWithNoDescriptionException
     */
    Command parse(String fullCommand) throws MismatchInputException, TaskWithNoDescriptionException {
        String[] strArr = fullCommand.split(" ");
        String command = strArr[0];
        boolean isToDo = command.equals("todo");
        boolean isDeadline = command.equals("deadline");
        boolean isEvent = command.equals("event");
        boolean isTask = isToDo || isDeadline || isEvent;

        if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new ListCommand();

        } else if (command.equals("mark")) {
            int taskNo = Integer.parseInt(strArr[1]);
            return new MarkCommand(taskNo);

        } else if (command.equals("unmark")) {
            int taskNo = Integer.parseInt(strArr[1]);
            return new UnmarkCommand(taskNo);

        } else if (isTask) {
            Task newTask = parseAddCommand(strArr);
            return new AddCommand(newTask);

        } else if (command.equals("delete")) {
            int taskNo = Integer.parseInt(strArr[1]);
            return new DeleteCommand(taskNo);

        } else if (command.equals("find")) {
            String keyword = strArr[1];
            return new FindCommand(keyword);

        } else if (command.equals("sortdeadlines")) {
            return new SortDeadlineCommand();

        } else {
            throw new MismatchInputException();
        }
    }

    private Task parseAddCommand(String[] strArr) throws TaskWithNoDescriptionException {
        String[] taskDescription = splitDescriptionAndDate(strArr);
        String typeOfTask = strArr[0];

        if (typeOfTask.equals("todo")) {
            return new ToDo(taskDescription[0]);

        } else if (typeOfTask.equals("deadline")) {
            return new Deadline(taskDescription[0], taskDescription[1]);

        } else if (typeOfTask.equals("event")) {
            return new Event(taskDescription[0], taskDescription[1]);
        }
        return null;
    }

    private String[] splitDescriptionAndDate(String[] strarr) throws TaskWithNoDescriptionException {
        if (strarr.length < 2) {
            throw new TaskWithNoDescriptionException();
        }

        String description = strarr[1];
        String date = "";
        String[] strarr1 = new String[2];

        for (int i = 2; i < strarr.length; i++) {
            boolean isBy = strarr[i].equals("/by");
            boolean isAt = strarr[i].equals("/at");
            boolean haveDate = isBy || isAt;

            if (haveDate) {
                date = strarr[i + 1];
                break;
            } else {
                description = description + " " + strarr[i];
            }

        }
        strarr1[0] = description;
        strarr1[1] = date;
        return strarr1;
    }


}
