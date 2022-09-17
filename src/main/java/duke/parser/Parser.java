package duke.parser;

import duke.exception.EmptyTodoListException;

import java.time.LocalDate;

/**
 * Makes sense of user command.
 */
public class Parser {
    private String[] words;

    /**
     * Retrieves command word entered by user.
     * @param input Message entered.
     * @return Command word.
     */
    public String checkResponse(String input) {
        words = input.split(" ");
        return words[0];
    }

    /**
     * Obtains task number for operation to be performed.
     * @return Task number.
     */
    public int getTaskNumber() {
        assert words.length > 1;
        return Integer.parseInt(words[1]);
    }

    public String getKeyword() {
        assert words.length > 1;
        return words[1];
    }

    /**
     * Obtains description of deadline task.
     * @return Description of deadline task.
     */
    public String getDeadlineDescription() {
        int a = 2;
        assert words.length > 1;
        StringBuilder task = new StringBuilder(words[1]);
        while (!words[a].equals("/by")) {
            task.append(" ");
            task.append(words[a]);
            a++;
        }
        return task.toString();
    }

    /**
     * Obtains time of deadline task.
     * @return Time of deadline task.
     */
    public LocalDate getDeadlineTime() {
        int a = 2;
        assert words.length > 1;
        while (!words[a].equals("/by")) {
            a++;
        }
        a++;
        StringBuilder deadline = new StringBuilder(words[a]);
        for (int b = a + 1; b < words.length; b++) {
            deadline.append(" ");
            deadline.append(words[b]);
        }
        return LocalDate.parse(deadline);
    }

    /**
     * Obtains description of event task.
     * @return Description of event task.
     */
    public String getEventDescription() {
        int a = 2;
        assert words.length > 1;
        StringBuilder task = new StringBuilder(words[1]);
        while (!words[a].equals("/at")) {
            task.append(" ");
            task.append(words[a]);
            a++;
        }
        return task.toString();
    }

    /**
     * Obtains time of event task.
     * @return Time of event task.
     */
    public String getEventTime() {
        int a = 2;
        assert words.length > 1;
        while (!words[a].equals("/at")) {
            a++;
        }
        a++;
        StringBuilder event = new StringBuilder(words[a]);
        for (int b = a + 1; b < words.length; b++) {
            event.append(" ");
            event.append(words[b]);
        }
        return event.toString();
    }

    /**
     * Obtains description of todo task.
     * @return Description of todo task.
     */
    public String getTodoDescription() throws EmptyTodoListException {
        if (words.length <= 1) {
            throw new EmptyTodoListException();
        }
        StringBuilder task = new StringBuilder(words[1]);
        for (int a = 2; a < words.length; a++) {
            task.append(" ");
            task.append(words[a]);
        }
        return task.toString();
    }
}
