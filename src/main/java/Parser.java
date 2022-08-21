package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Parser {
    public static String parseLocalDate(String string) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate localdate = LocalDate.parse(string, format);
        return localdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            if (taskList.isEmpty()) {
                throw new DukeException("☹ OOPS!!! No tasks available!");
            } else {
                return new ListCommand();
            }
        } else if (fullCommand.length() >= 5 && (fullCommand.startsWith("mark") &&
                (Character.isWhitespace(fullCommand.charAt(4))) &&
                fullCommand.substring(5).chars().allMatch(Character::isDigit))) {
            int number = parseInt(fullCommand.substring(5));
            if (number > taskList.taskListSize() || number <= 0) {
                throw new DukeException("☹ OOPS!!! Task number does not exist.");
            } else {
                return new MarkCommand(number);
                //taskList.getTask(number - 1).markAsDone();
                //System.out.println("Nice! I've marked this task as done:\n"
                //        + taskList.getTask(number - 1).toString());
            }
        } else if (fullCommand.length() >= 7 && (fullCommand.startsWith("unmark") &&
                (Character.isWhitespace(fullCommand.charAt(6))) &&
                fullCommand.substring(7).chars().allMatch(Character::isDigit))) {
            int number = parseInt(fullCommand.substring(7));
            if (number > taskList.taskListSize() || number <= 0) {
                throw new DukeException("☹ OOPS!!! Task number does not exist.");
            } else {
                return new UnmarkCommand(number);
                //taskList.getTask(number - 1).markAsNotDone();
                //System.out.println("OK, I've marked this task as not done yet:\n"
                //        + taskList.getTask(number - 1).toString());
            }
        } else if (fullCommand.length() >= 7 && (fullCommand.startsWith("delete") &&
                (Character.isWhitespace(fullCommand.charAt(6))) &&
                fullCommand.substring(7).chars().allMatch(Character::isDigit))) {
            int number = parseInt(fullCommand.substring(7));
            if (number > taskList.taskListSize() || number <= 0) {
                throw new DukeException("☹ OOPS!!! Task number does not exist.");
            } else {
                return new DeleteCommand(number);
            }
        } else if (fullCommand.equals("todo") || (fullCommand.startsWith("todo") && fullCommand.substring(5).isBlank())) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (fullCommand.startsWith("todo") && Character.isWhitespace(fullCommand.charAt(4))) {
            return new AddCommand(new ToDo(fullCommand));
            //taskList.add(new ToDo(fullCommand));
            //System.out.println("Got it. I've added this task:");
            //System.out.println(taskList.latestTask() + "\nNow you have " + taskList.taskListSize() +
            //        " tasks in the list.");
        } else if (fullCommand.equals("deadline") || (fullCommand.startsWith("deadline")
                && fullCommand.substring(9).isBlank())) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (fullCommand.startsWith("deadline") && Character.isWhitespace(fullCommand.charAt(8))) {
            if (!fullCommand.contains("/by")) {
                throw new DukeException("☹ OOPS!!! Please use the correct format!");
            } else {
                try {
                    return new AddCommand(new Deadline(fullCommand));
                } catch (DateTimeParseException e) {
                    throw new DukeException("☹ OOPS!!! Please use the correct date format!");
                }
                //System.out.println("Got it. I've added this task:");
                //System.out.println(taskList.latestTask() + "\nNow you have " + taskList.taskListSize() +
                //        " tasks in the list.");
            }
        } else if (fullCommand.equals("event") || (fullCommand.startsWith("event") && fullCommand.substring(6).isBlank())) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (fullCommand.startsWith("event") && Character.isWhitespace(fullCommand.charAt(5))) {
            if (!fullCommand.contains("/at")) {
                throw new DukeException("☹ OOPS!!! Please use the correct format!");
            } else {
                try {
                    return new AddCommand(new Event(fullCommand));
                } catch (DateTimeParseException e) {
                    throw new DukeException("☹ OOPS!!! Please use the correct date format!");
                }
               // System.out.println("Got it. I've added this task:");
               // System.out.println(taskList.latestTask() + "\nNow you have "
               //         + taskList.taskListSize() +
               //         " tasks in the list.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
