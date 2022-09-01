package duke;

/**
 * Represents a class that deals with making sense of the user command.
 */
public class Parser {

    Parser() {

    }

    /**
     *
     * @param String fullCommand : the full command that is given by the users.
     * @return command : the specific command to execute later.
     * @throws MismatchInputException
     * @throws TaskWithNoDescriptionException
     */
    Command parse(String fullCommand) throws MismatchInputException, TaskWithNoDescriptionException {
        String[] strArr = fullCommand.split(" ");
        String command = strArr[0];
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
        } else if (command.equals("todo")) {
            String[] taskDescription = processDescription(strArr);
            Task newTask = new ToDo(taskDescription[0]);
            return new AddCommand(newTask);
        } else if (command.equals("deadline")) {
            String[] taskDescription = processDescription(strArr);
            Task newTask = new Deadline(taskDescription[0], taskDescription[1]);
            return new AddCommand(newTask);
        } else if (command.equals("event")) {
            String[] taskDescription = processDescription(strArr);
            Task newTask = new Event(taskDescription[0], taskDescription[1]);
            return new AddCommand(newTask);
        } else if (command.equals("delete")) {
            int taskNo = Integer.parseInt(strArr[1]);
            return new DeleteCommand(taskNo);
        } else if (command.equals("find")) {
            String keyword = strArr[1];
            return new FindCommand(keyword);
        } else {
            //storage.saveNewChanges(this.tasks);
            throw new MismatchInputException(":( OOPS!!! I'm sorry, but I don't know what that means");
        }
    }

    private String[] processDescription(String[] strarr) throws TaskWithNoDescriptionException {
        if (strarr.length > 1) {
            String description = strarr[1];
            String date = "";
            String[] strarr1 = new String[2];
            for (int i = 2; i < strarr.length; i++) {
                if (strarr[i].equals("/by") || strarr[i].equals("/at")) {
                    date = strarr[i + 1];
                    break;
                } else {
                    description = description + " " + strarr[i];
                }
            }
            strarr1[0] = description;
            strarr1[1] = date;
            return strarr1;
        } else {
            throw new TaskWithNoDescriptionException(":( OOPS!!! The description of a "
                    + strarr[0] + " cannot be empty.");
        }
    }



}
