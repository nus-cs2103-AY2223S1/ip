package duke;

import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

public class Parser {
    private String[] inputString;

    /**
     * Creates a Parser instance to parse the given input.
     *
     * @param inputString The user input split by a space, with a maximum length of 2.
     */
    public Parser(String[] inputString) {
        this.inputString = inputString;
    }

    /**
     * Parses the user input to create a Deadline instance.
     *
     * @return A Deadline Instance with the user-specified description and deadline.
     * @throws DukeException When user input has missing fields or incorrect formatting.
     */
    public Deadline parseDeadline() throws DukeException {
        if (inputString.length == 1) {
            throw new DukeException("Oops! You forgot to indicate the description and deadline " +
                    "for your deadline");
        }
        if (inputString[1].contains("/by")) {
            String[] deadlineDetails = inputString[1].split("/by");
            if (deadlineDetails.length == 1) {
                throw new DukeException("Please be sure to specify both description and deadline");
            }
            String description = deadlineDetails[0].trim();
            String deadline = deadlineDetails[1].trim();
            if (description.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the description " +
                        "for your deadline");
            }
            if (deadline.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the deadline");
            }

            LocalDateTime dateTime = LocalDateTime.parse(deadline, Duke.DATE_TIME_FORMATTER);
            return new Deadline(description, dateTime);
        } else {
            throw new DukeException("Oops! You forgot to use /by to separate " +
                    "the description and deadline");
        }
    }
    /**
     * Parses the user input to create a Todo instance.
     *
     * @return A Todo Instance with the user-specified description.
     * @throws DukeException When user input has missing fields or incorrect formatting.
     */
    public Todo parseTodo() throws DukeException {
        if (inputString.length == 1) {
            throw new DukeException("Oops! You forgot to indicate the description for your todo");
        }
        return new Todo(inputString[1]);
    }

    /**
     * Parses the user input to create an Event instance.
     *
     * @return A Deadline Instance with the user-specified description and timing.
     * @throws DukeException When user input has missing fields or incorrect formatting.
     */
    public Event parseEvent() throws DukeException {
        if (inputString.length == 1) {
            throw new DukeException("Oops! You forgot to indicate the description and timing " +
                    "for your event");
        }
        if (inputString[1].contains("/at")) {
            String[] eventDetails = inputString[1].split("/at");
            if (eventDetails.length == 1) {
                throw new DukeException("Please be sure to specify both description and timing" +
                        " for your event");
            }
            String description = eventDetails[0].trim();
            String timing = eventDetails[1].trim();
            if (description.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the description " +
                        "for your event");
            }
            if (timing.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the timing " +
                        "for your event");
            }
            LocalDateTime dateTime = LocalDateTime.parse(timing, Duke.DATE_TIME_FORMATTER);
            return new Event(description, dateTime);
        } else {
            throw new DukeException("Oops! You forgot to use /at to separate " +
                    "the description and timing");
        }
    }

    /**
     * Returns the task number of the task to be marked.
     *
     * @return The task number of the task  to be marked.
     * @throws DukeException When user input has missing fields or incorrect formatting.
     */
    public int parseMark() throws DukeException {
        if (inputString.length == 1) {
            throw new DukeException("Oops! you forgot to indicate the task number");
        }
        return parseInt(inputString[1].trim());
    }
    /**
     * Returns the task number of the task to be unmarked.
     *
     * @return The task number of the task to be unmarked.
     * @throws DukeException When user input has missing fields or incorrect formatting.
     */
    public int parseUnmark() throws DukeException {
        if (inputString.length == 1) {
            throw new DukeException("Oops! You forgot to indicate the task number");
        }
        return parseInt(inputString[1].trim());
    }

    /**
     * Returns the task number of the task to be deleted.
     *
     * @return The task number of the task to be deleted.
     * @throws DukeException When user input has missing fields or incorrect formatting.
     */
    public int parseDelete() throws DukeException {
        if (inputString.length == 1 || inputString[1].trim().length() == 0) {
            throw new DukeException("Oops! You forgot to specify which task number to delete");
        }
        return parseInt(inputString[1]);
    }

    public String parseFind() throws DukeException {
        if (inputString.length == 1 || inputString[1].trim().length() == 0) {
            throw new DukeException("Oops! You forgot to specify a keyword");
        }
        return inputString[1].trim();
    }
}
