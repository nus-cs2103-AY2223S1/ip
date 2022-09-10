package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import java.util.Locale;

public class Parser {

    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor to initialize a Parser.
     *
     * @param tasks A TaskList object storing all the tasks.
     * @param ui A Ui Object handling the Ui of the project.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns a String representation of the time.
     *
     * @param time the String input time from console.
     * @return A String representation of time to be output in console.
     */
    public String parseTime(String time) {
        if (time.startsWith("00")) {
            return "12" + ":" + time.substring(2) + "AM";
        } else {
            int currTime = Integer.parseInt(time);
            if (currTime >= 1300) {
                int newTime = currTime / 100 - 12;
                String hour = Integer.toString(newTime);
                return hour + ":" + time.substring(2) + "PM";
            } else {
                return time.charAt(1) + ":" + time.substring(2) + "AM";
            }
        }

    }

    /**
     * Takes user input from console and parse the input to do the relevant actions
     * like adding a task to the list.
     *
     * @param userInput the String input from the console.
     */
    public String parse(String userInput) {
        if (userInput.startsWith("mark")) {
            try {
                char i = userInput.charAt(5);
                int index = Character.getNumericValue(i);
                return tasks.markDone(index);

            } catch (StringIndexOutOfBoundsException e) {
                return ui.printInsufficientInfoException(Ui.Keywords.mark);
            } catch (IndexOutOfBoundsException e) {
                return ui.printIndexOutOfBoundsException(Ui.Keywords.unmark);
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                char i = userInput.charAt(7);
                int index = Character.getNumericValue(i);
                return tasks.markUndone(index);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.printInsufficientInfoException(Ui.Keywords.unmark);
            } catch (IndexOutOfBoundsException e) {
                return ui.printIndexOutOfBoundsException(Ui.Keywords.unmark);
            }
        } else if (userInput.startsWith("list")) {
            return tasks.printList();
        } else if (userInput.startsWith("delete")) {
            char i = userInput.charAt(7);
            int index = Character.getNumericValue(i);

            return tasks.deleteTask(index);
        } else if (userInput.startsWith("todo")) {
            try {
                String task = userInput.substring(5);

                return tasks.addToDo(task);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.printInsufficientInfoException(Ui.Keywords.todo);
            }
        } else if (userInput.startsWith("deadline")) {
            try {
                int indexOfSlash = userInput.indexOf("/");
                LocalDate d1 = parseEventOrDeadlineDate(userInput, indexOfSlash);
                String newTime = parseEventOrDeadlineTime(userInput, indexOfSlash);
                String task = userInput.substring(9, indexOfSlash);

                return tasks.addDeadline(task, d1, newTime);

            } catch (StringIndexOutOfBoundsException e) {
                return ui.printInsufficientInfoException(Ui.Keywords.deadline);
            }
        } else if (userInput.startsWith("event")) {
            try {
                int indexOfSlash = userInput.indexOf("/");
                LocalDate d1 = parseEventOrDeadlineDate(userInput, indexOfSlash);
                String newTime = parseEventOrDeadlineTime(userInput, indexOfSlash);
                String task = userInput.substring(6, indexOfSlash);

                return tasks.addEvent(task, d1, newTime);

            } catch (StringIndexOutOfBoundsException e) {
                return ui.printInsufficientInfoException(Ui.Keywords.event);
            }
        } else if (userInput.startsWith("find")) {
          String keyword = userInput.substring(5);
          return tasks.findMatchingTasks(keyword);
        } else if (userInput.startsWith("tag")) {
            char i = userInput.charAt(4);
            int index = Character.getNumericValue(i);
            String hashtag = userInput.substring(6);

            return tasks.tagTask(index, hashtag);
        } else {
            return ui.printUnrecognisedWord();
        }
    }

    public LocalDate parseEventOrDeadlineDate(String userInput, int indexOfSlash) {

        String date = userInput.substring(indexOfSlash + 4, indexOfSlash + 13);
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().parseLenient()
                .appendPattern("[yyyy-MM-dd]")
                .appendPattern("[M/dd/yyyy]")
                .appendPattern("[M/d/yyyy]")
                .appendPattern("[MM/dd/yyyy]")
                .appendPattern("[MMM dd yyyy]");
        DateTimeFormatter df = builder.toFormatter(Locale.ENGLISH);
        LocalDate d1 = LocalDate.parse(date, df);
        return d1;
    }

    public String parseEventOrDeadlineTime(String userInput, int indexOfSlash) {
        String time = userInput.substring(indexOfSlash + 14);
        String newTime = parseTime(time);
        return newTime;
    }

}

