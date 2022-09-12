package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser class to handle requirements.
 *
 * @author Lan Jingbo, Jerry
 */
class Parser {

    private Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * handling the different requirements.
     *
     * @param requirement
     * @return
     */
    public String run(String requirement) {
        String[] splitInput = requirement.split(" ", 2);
        try {
            switch (splitInput[0]) {
                //Handle case when task a Todo
                case "todo":
                    return duke.todo(requirement);

                //Handle case when task is a deadline
                case "deadline": {
                    return duke.deadline(requirement);
                }

                //Handle case when task is an event
                case "event": {
                    return duke.event(requirement);
                }

                //Handle case when user wants to list tasks
                case "list":
                    return duke.showList();

                //Handle case when user wants to handleMark task
                case "mark": {
                    checkForMissingArgs(splitInput);
                    //-1 to get index in 0 indexing
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    return duke.mark(index);
                }

                //Handle case when user wants to handleUnmark task
                case "unmark": {
                    checkForMissingArgs(splitInput);
                    //-1 to get index in 0 indexing
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    return duke.unmark(index);
                }

                //Handle case when user wants to handleDelete task
                case "delete": {
                    checkForMissingArgs(splitInput);
                    //-1 to get in 0 indexing
                    //int index = Integer.parseInt(splitInput[1]);
                    return duke.delete(requirement);
                }

                //Handle case when user wants to find tasks
                case "search":
                    checkForMissingArgs(splitInput);
                    String str = splitInput[1].trim();
                    return duke.search(str);

                //Handle case when user wants to quit bot
                case "bye":
                    return duke.bye();

                case "on":
                    LocalDate lc = LocalDate.parse(splitInput[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return duke.getOnDate(lc);

                case "hi":
                    return duke.hi();

                //Default case: Not any of the tasks(aTodo, Deadline, Event) and hence, throws an Exception
                default:
                    //To handle any extra words the user keyed in
                    throw new CannotUnderstandException();

            }
        } catch (WrongMessageException | CannotUnderstandException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * To check whether the input requirements has wrongnesses.
     *
     * @param input requirements
     * @throws WrongMessageException
     */
    public void checkForMissingArgs(String[] input) throws WrongMessageException {
        if (input.length == 1 || input[1].trim().isEmpty()) {
            throw new WrongMessageException("MISSING");
        }
    }
}