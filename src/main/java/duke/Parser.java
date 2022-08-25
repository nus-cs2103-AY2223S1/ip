package duke;

import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    public static String[] keywords = {"bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", "find"};
    public Parser() {};

    public Duke.Keyword getKeyword(String keyword) throws DukeException {
        if (Arrays.asList(keywords).contains(keyword)) {
            return Duke.Keyword.valueOf(keyword.toUpperCase());
        } else {
            throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public int getIndex(Scanner scanner) {
        return scanner.nextInt();
    }

    public Deadline createDeadline(String nextLine) {
        String[] section = nextLine.split(" /by ");
        return new Deadline(section[0], false, section[1]);
    }

    public Event createEvent(String nextLine) {
        String[]sections =nextLine.split(" /at ");
        return new Event(sections[0], false, sections[1]);
    }

    public void ignoreLine(Scanner scanner) {
        scanner.nextLine();
    }
}
