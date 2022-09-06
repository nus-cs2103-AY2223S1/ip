package duke;

import duke.command.*;
import duke.task.DeadlineTask;
import duke.task.TodoTask;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Used to interpret user inputs and match them to available commands.
 */
public class Parser {

    private static String removeWhitespace(String input) {
        return input.replaceAll(" ", "");
    }

    private static String removeStartingWhitespace(String input) {
        if (input.length() == 0) {
            return "blank";
        }
        if (input.indexOf(' ') == 0) {
            if (input.length() == 1) {
                return "blank";
            }
            return removeStartingWhitespace(input.substring(1));
        }
        return input;
    }

    private static int countArguments(String input) {
        String str = input;
        int count = 0;
        while(str.length() > 0) {
            int index = str.indexOf(' ');
            if (index == -1) {
                count++;
                break;
            } else if (index == str.length() - 1) {
                break;
            } else {
                if (index != 0) {
                    count++;
                }
                str = str.substring(index + 1);
            }
        }
        return count;
    }

    private static String findArgument(String input, int argNum) {
        if (argNum > countArguments(input)) {
            return "NOT FOUND";
        }
        String str = input;
        int count = 0;
        while (true) {
            count++;
            int index = str.indexOf(' ');
            if (count == argNum) {
                if (index == - 1) {
                    return str;
                } else {
                    return str.substring(0, index);
                }
            }
            while (true)
                if (str.charAt(index + 1) == ' ') {
                    index++;
                } else {
                    break;
                }
            str = str.substring(index + 1);

        }
    }

    private static String parseDate(String date) {
        int firstIndex;
        int secondIndex;
        if (date.contains("/")) {
            firstIndex = date.indexOf("/");
            secondIndex = date.substring(firstIndex + 1).indexOf("/") + firstIndex + 1;
        } else if (date.contains("-")) {
            firstIndex = date.indexOf("-");
            secondIndex = date.substring(firstIndex + 1).indexOf("-") + firstIndex + 1;
        } else if (date.contains(".")) {
            firstIndex = date.indexOf(".");
            secondIndex = date.substring(firstIndex + 1).indexOf(".") + firstIndex + 1;
        } else {
            throw new DateTimeException("Not a valid date format");
        }
        if (secondIndex == -1) {
            throw new DateTimeException("Not a valid date format");
        }
        int year;
        int month;
        int day;
        if (firstIndex == 1) {
            if (secondIndex == 3) {
                day = Integer.parseInt(date.substring(0, 1));
                month = Integer.parseInt(date.substring(2, 3));
                year = Integer.parseInt(date.substring(4, 8));
            } else if (secondIndex == 4) {
                day = Integer.parseInt(date.substring(0, 1));
                month = Integer.parseInt(date.substring(2, 4));
                year = Integer.parseInt(date.substring(5, 9));
            } else {
                throw new DateTimeException("Not a valid date format");
            }
        } else if (firstIndex == 2) {
            if (secondIndex == 4) {
                day = Integer.parseInt(date.substring(0, 2));
                month = Integer.parseInt(date.substring(3, 4));
                year = Integer.parseInt(date.substring(5, 9));
            } else if (secondIndex == 5) {
                day = Integer.parseInt(date.substring(0, 2));
                month = Integer.parseInt(date.substring(3, 5));
                year = Integer.parseInt(date.substring(6, 10));
            } else {
                throw new DateTimeException("Not a valid date format");
            }
        } else {
            throw new DateTimeException("Not a valid date format");
        }
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new DateTimeException("No such day exists!");
        }
        String wordDay = Integer.toString(day);
        String wordMonth = Integer.toString(month);
        if (day < 10) {
            wordDay = "0" + day;
        }
        if (month < 10) {
            wordMonth = "0" + month;
        }
        return String.format("%s%s%d", wordDay, wordMonth, year);
    }

    /**
     * Converts a date from word format to ddmmyyyy format.
     * e.g. 31 Jan 2022 will be converted to 31012022
     *
     * @param date Date to be converted into ddmmyy format. date should be in the format of e.g. 25 Mar 2019
     * @return The converted date in ddmmyyyy format.
     */
    public static String parseWordDate(String date) {
        if (date.length() != 11) {
            throw new DateTimeException("Not recognised!");
        }
        String wordDate = date.substring(3, 6);
        String numDate;
        switch (wordDate) {
            case "Jan":
                numDate = "01";
                break;
            case "Feb":
                numDate = "02";
                break;
            case "Mar":
                numDate = "03";
                break;
            case "Apr":
                numDate = "04";
                break;
            case "May":
                numDate = "05";
                break;
            case "Jun":
                numDate = "06";
                break;
            case "Jul":
                numDate = "07";
                break;
            case "Aug":
                numDate = "08";
                break;
            case "Sep":
                numDate = "09";
                break;
            case "Oct":
                numDate = "10";
                break;
            case "Nov":
                numDate = "11";
                break;
            case "Dec":
                numDate = "12";
                break;
            default:
                numDate = "00";
        }
        return date.substring(0, 2) + numDate + date.substring(7);
    }

    /**
     * Converts an integer between 1-12 to a 3-letter representation of the month.
     * e.g. 5 will be converted to May, 12 will be converted to Dec.
     *
     * @param m Integer representation of a month, ranging from 1-12.
     * @return A 3-letter word representation of the month.
     */
    public static String intToWordMonth(int m) {
        switch (m) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return "Nam";
        }
    }

    private static String parseTime(String time) {
        if (time.length() != 4) {
            throw new DateTimeException("Time should be provided as 4 digits as per the 24 hour clock");
        }
        try {
            int intTime = Integer.parseInt(time);
            int hour = intTime / 100;
            if (hour > 23) {
                throw new DateTimeException("No such time exists!");
            }
            int minute = intTime - hour * 100;
            if (minute > 59) {
                throw new DateTimeException("No such time exists!");
            }
        } catch (NumberFormatException e) {
            throw new DateTimeException("Please input numbers as time!");
        }
        return time;
    }

    /**
     * Takes in a string input from the user and converts it to a command recognised by Duke.
     *
     * @param input String input from the user.
     * @return A command that can be executed by Duke.
     * @throws DukeException if the user's desired command cannot be properly performed.
     */
    public static Command parse(String input) throws DukeException {
        //first remove any whitespace at the start
        input = removeStartingWhitespace(input);
        if (input.equals("blank")) {
            return new NullCommand();
        }
        try {
            String command = findArgument(input, 1).toLowerCase();
            switch (command) {
                case "bye":
                    if (countArguments(input) != 1) {
                        throw new IllegalArgumentException("Who is that?");
                    }
                    return new ByeCommand();
                case "list":
                    if (countArguments(input) != 1) {
                        throw new IllegalArgumentException("Just use 'list'!");
                    }
                    return new ListCommand();
                case "mark":
                    if (countArguments(input) != 2) {
                        throw new IllegalArgumentException("Incorrect number of arguments for command mark");
                    }
                    try {
                        input = (removeWhitespace(input));
                        int taskNum = Integer.parseInt(input.substring(4));
                        return new MarkCommand(taskNum);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Must input a number!");
                    }
                case "unmark":
                    if (countArguments(input) != 2) {
                        throw new IllegalArgumentException("Incorrect number of arguments for command unmark");
                    }
                    try {
                        input = (removeWhitespace(input));
                        int taskNum = Integer.parseInt(input.substring(6));
                        return new UnmarkCommand(taskNum);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Must input a number!");
                    }
                case "delete":
                    if (countArguments(input) != 2) {
                        throw new IllegalArgumentException("Incorrect number of arguments for command delete");
                    }
                    try {
                        input = (removeWhitespace(input));
                        int taskNum = Integer.parseInt(input.substring(6));
                        return new DeleteCommand(taskNum);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Must input a number!");
                    }
                case "deadline": {
                    if (countArguments(input) < 4) {
                        String message = "To add a deadline task, enter a task name followed by /by and then a deadline";
                        throw new IllegalArgumentException(message);
                    }
                    if (countArguments(input) > 5) {
                        throw new IllegalArgumentException("Incorrect number of arguments for command deadline");
                    }
                    if (!findArgument(input, 3).equalsIgnoreCase("/by")) {
                        throw new IllegalArgumentException("You need to include /by to specify the deadline");
                    }
                    String taskName = findArgument(input, 2);
                    String date = findArgument(input, 4);
                    date = parseDate(date);
                    if (countArguments(input) == 5) {
                        String time = findArgument(input, 5);
                        time = parseTime(time);
                        return new AddDeadlineCommand(taskName, date, time);
                    } else {
                        return new AddDeadlineCommand(taskName, date);
                    }
                }
                case "event": {
                    if (countArguments(input) < 4) {
                        String message = "To add an event task, enter a task name followed by /at and then the event datetime";
                        throw new IllegalArgumentException(message);
                    }
                    if (countArguments(input) > 5) {
                        throw new IllegalArgumentException("Incorrect number of arguments for command deadline");
                    }
                    if (!findArgument(input, 3).equalsIgnoreCase("/at")) {
                        throw new IllegalArgumentException("You need to include /at to specify the datetime");
                    }
                    String taskName = findArgument(input, 2);
                    String date = findArgument(input, 4);
                    date = parseDate(date);
                    if (countArguments(input) == 5) {
                        String time = findArgument(input, 5);
                        time = parseTime(time);
                        return new AddEventCommand(taskName, date, time);
                    } else {
                        return new AddEventCommand(taskName, date);
                    }
                }
                case "todo":
                    if (countArguments(input) != 2) {
                        throw new IllegalArgumentException("Incorrect number of arguments for command todo");
                    }
                    String taskName = findArgument(input, 2);
                    return new AddTodoCommand(taskName);
                default:
                    return new UnrecognisedCommand();
            }
        } catch (DateTimeException | IllegalArgumentException e) {
            throw new DukeException(e.getMessage());
        }
    }
}