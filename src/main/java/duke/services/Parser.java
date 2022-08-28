package duke.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/** Handles reading of user commands */
public class Parser {

    /** Points to the current word being read in the current command */
    private static int currWordIndex = 0;

    /**
     * Receives user's inputs and responds to them until "bye" is entered
     */
    public static void handleUserInputs() {
        Scanner inputScanner = new Scanner(System.in);
        String[] words = Arrays.stream(inputScanner.nextLine().strip().split(" ")).toArray(String[]::new);
        while (!(words.length == 1 && words[0].equals("bye"))) {
            if (words.length > 0) {
                try {
                    if (words.length == 1 && words[0].equals("list")) {
                        TaskList.listTasks(); //could put words.length == 1 cases all here
                    } else if (words.length == 1 && words[0].equals("SAVE")) {
                        Storage.wipingData = false;
                        UI.sayLines(new String[] {"Data will be saved on exit"});
                    } else if (words.length == 1 && words[0].equals("WIPE")) {
                        Storage.wipingData = true;
                        UI.sayLines(new String[] {"Data will be wiped on exit"});
                    } else if (words[0].equals("todo")) {
                        TaskList.addTodo(words);
                    } else if (words[0].equals("deadline")) {
                        TaskList.addDeadline(words);
                    } else if (words[0].equals("event")) {
                        TaskList.addEvent(words);
                    } else if (words[0].equals("mark")) {
                        TaskList.markTaskAsDone(words);
                    } else if (words[0].equals("unmark")) {
                        TaskList.markTaskAsNotDone(words);
                    } else if (words[0].equals("delete")) {
                        TaskList.deleteTask(words);
                    } else if (words[0].equals("find")) {
                        TaskList.findTasksContainingKeyword(words);
                    } else {
                        UI.sayLines(new String[]{"I'm sorry, I don't know what that means"});
                    }
                } catch (IllegalArgumentException e) {
                    UI.sayLines(new String[]{e.getMessage()});
                }
            }
            words = Arrays.stream(inputScanner.nextLine().strip().split(" ")).toArray(String[]::new);
        }
        inputScanner.close();
    }

    /**
     * Retrieves the description argument in the command
     * @param words The words of the command entered, first is some valid command name
     * @param stop The word before which the description ends
     * @return The description specified in words
     * @throws IllegalArgumentException If words specifies an empty description
     */
    public static String getDescription(String[] words, String stop) {
        currWordIndex = 1;
        StringBuilder descBuilder = new StringBuilder();
        boolean emptyDesc = true;

        while (currWordIndex < words.length && !words[currWordIndex].equals(stop)) {
            if (words[currWordIndex].isEmpty()) {
                descBuilder.append(" ");
            } else {
                descBuilder.append(words[currWordIndex]).append(" ");
                emptyDesc = false;
            }
            ++currWordIndex;
        }

        if (emptyDesc) {
            throw new IllegalArgumentException("OOPS!!! Description can't be empty");
        }

        return descBuilder.deleteCharAt(descBuilder.length()-1).toString();
    }

    /**
     * Retrieves the timing argument in the command, which must be of the form d/M/yyyy followed by an optional (h:mm)am/pm
     * @param words The words of the command entered, first is some valid command name
     * @param flag The flag that the timing belongs to
     * @return The timing specified in words
     * @throws IllegalArgumentException If words is missing flag or the timing is empty/incorrect format
     */
    public static String getTiming(String[] words, String flag) {
        if (currWordIndex >= words.length) {
            throw new IllegalArgumentException("OOPS!!! " + flag + " not found");
        } else if (currWordIndex == words.length - 1) {
            throw new IllegalArgumentException("OOPS!!! Timing for " + flag + " can't be empty");
        }

        ++currWordIndex;
        try {
            return (currWordIndex == words.length - 1)
                    ? reformatDate(words[currWordIndex], "d/M/yyyy", "d MMM yyyy")
                    : reformatDateTime(words[currWordIndex] + " " + words[++currWordIndex],
                    "d/M/yyyy h:mma", "d MMM yyyy, h:mma");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! I don't understand that date or time");
        }
    }

    public static String reformatDate(String date, String inFormat, String outFormat) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(inFormat)
                .toFormatter(Locale.getDefault());
        LocalDate ld = LocalDate.parse(date, formatter);
        return ld.format(DateTimeFormatter.ofPattern(outFormat, Locale.getDefault()));
    }

    public static String reformatDateTime(String dateTime, String inFormat, String outFormat) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern(inFormat)
                .toFormatter(Locale.getDefault());
        LocalDateTime ldt = LocalDateTime.parse(dateTime, formatter);
        return ldt.format(DateTimeFormatter.ofPattern(outFormat, Locale.getDefault()));
    }

    /**
     * Gets the task number (integer pointing to a task) specified in the command
     *
     * @param words The words of the command entered, first is always some valid command name
     * @return The task number specified
     * @throws IllegalArgumentException If the argument(s) supplied in words isn't an integer from 1 to the number of stored tasks
     */
    public static int getTaskNumber(String[] words) {
        if (TaskList.getTaskCount() == 0) {
            throw new IllegalArgumentException("OOPS!!! No tasks stored for me to do that");
        }

        int taskNumber = 0;

        try {
            taskNumber = (words.length == 2) ? Integer.parseInt(words[1]) : 0;
            if (taskNumber <= 0 || taskNumber > TaskList.getTaskCount()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("OOPS!!! The task number must be from 1 to " + TaskList.getTaskCount());
        }

        return taskNumber;
    }
}
