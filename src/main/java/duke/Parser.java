package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Parser {

    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

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

    public void parse(String userInput) {
        if (userInput.startsWith("mark")) {
            try {
                char i = userInput.charAt(5);
                int index = Character.getNumericValue(i);
                tasks.markDone(index);

            } catch (StringIndexOutOfBoundsException e) {
                ui.printInsufficientInfoException(Ui.Keywords.mark);
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBoundsException(Ui.Keywords.unmark);
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                char i = userInput.charAt(7);
                int index = Character.getNumericValue(i);
                tasks.markUndone(index);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printInsufficientInfoException(Ui.Keywords.unmark);
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBoundsException(Ui.Keywords.unmark);
            }
        } else if (userInput.startsWith("list")) {
            tasks.printList();
        } else if (userInput.startsWith("delete")) {
            char i = userInput.charAt(7);
            int index = Character.getNumericValue(i);

            tasks.deleteTask(index);
        } else if (userInput.startsWith("todo")) {
            try {
                String task = userInput.substring(5);

                tasks.addToDo(task);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printInsufficientInfoException(Ui.Keywords.todo);
            }
        } else if (userInput.startsWith("deadline")) {
            try {
                int indexOfSlash = userInput.indexOf("/");
                String dateStr = userInput.substring(indexOfSlash + 4, indexOfSlash + 13);
                String time = userInput.substring(indexOfSlash + 14);
                String newTime = parseTime(time);
                DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive().parseLenient()
                        .appendPattern("[yyyy-MM-dd]")
                        .appendPattern("[M/dd/yyyy]")
                        .appendPattern("[M/d/yyyy]")
                        .appendPattern("[MM/dd/yyyy]")
                        .appendPattern("[MMM dd yyyy]");
                DateTimeFormatter df = builder.toFormatter(Locale.ENGLISH);
                LocalDate d1 = LocalDate.parse(dateStr, df);
                String task = userInput.substring(9, indexOfSlash);

                tasks.addDeadline(task, d1, newTime);

            } catch (StringIndexOutOfBoundsException e) {
                ui.printInsufficientInfoException(Ui.Keywords.deadline);
            }
        } else if (userInput.startsWith("event")) {
            try {
                int indexOfSlash = userInput.indexOf("/");
                String date = userInput.substring(indexOfSlash + 4, indexOfSlash + 13);
                String time = userInput.substring(indexOfSlash + 14);
                String newTime = parseTime(time);
                DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive().parseLenient()
                        .appendPattern("[yyyy-MM-dd]")
                        .appendPattern("[M/dd/yyyy]")
                        .appendPattern("[M/d/yyyy]")
                        .appendPattern("[MM/dd/yyyy]")
                        .appendPattern("[MMM dd yyyy]");
                DateTimeFormatter df = builder.toFormatter(Locale.ENGLISH);
                LocalDate d1 = LocalDate.parse(date, df);
                String task = userInput.substring(6, indexOfSlash);

                tasks.addEvent(task, d1, newTime);

            } catch (StringIndexOutOfBoundsException e) {
                ui.printInsufficientInfoException(Ui.Keywords.event);
            }
        } else {
            ui.printUnrecognisedWord();
        }
    }

}

