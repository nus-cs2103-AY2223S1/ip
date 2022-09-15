package anya;

/**
 * Represents a class that deals with making sense of the user command.
 */
public class Parser {

    private Ui ui;

    Parser() {
        this.ui = new Ui();
    }

    /**
     * Process the input command by the users to give the respective command to the ChatBot to execute.
     * @param String fullCommand : the full command that is given by the users.
     * @return command : the specific command to execute later.
     * @throws AnyaException.
     */
    Command parse(String fullCommand) throws AnyaException {
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
            throw new AnyaException(ui.printMisMatchInputError());
        }
    }

    private Task parseAddCommand(String[] strArr) throws AnyaException {
        String[] taskDescription = splitDescriptionAndDate(strArr);
        String typeOfTask = strArr[0];

        if (typeOfTask.equals("todo")) {
            return new ToDo(taskDescription[0]);

        } else if (taskDescription[1].equals("")) { //missing date
            throw new AnyaException(ui.printMissingDateError(typeOfTask));

        } else if (typeOfTask.equals("deadline")) {
            return new Deadline(taskDescription[0], taskDescription[1]);

        } else if (typeOfTask.equals("event")) {
            return new Event(taskDescription[0], taskDescription[1]);
        }
        return null;
    }

    private String[] splitDescriptionAndDate(String[] ar) throws AnyaException {
        if (ar.length < 2) {
            throw new AnyaException(ui.printNoTaskDescriptionError(ar[0]));
        }

        String description = ar[1];
        String date = "";
        String[] strarr1 = new String[2];

        for (int i = 2; i < ar.length; i++) {
            boolean isBy = ar[i].equals("/by");
            boolean isAt = ar[i].equals("/at");
            boolean haveDate = isBy || isAt;

            if (haveDate) {
                date = ar[i + 1];
                break;
            } else {
                description = description + " " + ar[i];
            }

        }
        strarr1[0] = description;
        strarr1[1] = date;
        return strarr1;
    }


}
