package duke;

import duke.command.*;

public class Parser {

    private static boolean inputChecker(String[] arr) {
        if (arr.length  < 2) {
            return false;
        }
        if (arr[1].isBlank()) {
            return false;
        }
        return true;
    }

    public static Command parse (String input) {
        String arr[] = input.split(" ", 2);

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        else if (input.equals("list")) {
            return new ListCommand();
        }

        else if (arr[0].equals("mark")){
            if (!inputChecker(arr)) {
                System.out.println(DukeException.MarkIndexEmptyException());
            } else {
                return new MarkCommand(arr);
            }

        }
        else if (arr[0].equals("unmark")) {
            if (!inputChecker(arr)) {
                System.out.println(DukeException.UnmarkIndexEmptyException());
            } else {
                return new UnmarkCommand(arr);
            }
        }

        else if (arr[0].equals("todo")) {
            if (!inputChecker(arr)) {
                System.out.println(DukeException.EmptyTaskException());
            } else {
                return new TodoCommand(arr[1]);
            }

        }

        else if (arr[0].equals("deadline")) {
            if (!inputChecker(arr)) {
                System.out.println(DukeException.EmptyTaskException());
            } else {
                return new DeadlineCommand(arr[1]);
            }

        }
        else if (arr[0].equals("event")) {
            if (!inputChecker(arr)) {
                System.out.println(DukeException.EmptyTaskException());
            } else {
                return new EventCommand(arr[1]);
            }
        }
        else if (arr[0].equals("delete")) {
            if (!inputChecker(arr)) {
                System.out.println("Index not found in the list!");
            } else {
                return new DeleteCommand(arr[1]);
            }

        }

        else {
            System.out.println("Input not recognised! Please try again! ");
        }

        return null;
    }
}
