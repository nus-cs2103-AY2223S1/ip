package duke;

import java.util.ArrayList;

/**
 * Deals with different commands. Adds different commands to an arrayList.
 */
public class Parser {
    protected String description;
    protected boolean isDone;


    public Parser(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representing the command Delete.
     *
     * @param command String command.
     * @param arrayList arrayList.
     * @param number integer.
     * @return Parser
     * @throws DukeException if duke cannot identify the command.
     */
    public static Parser of(String command, ArrayList<String> arrayList, int number) throws DukeException {
        if (command.split(" ")[0].equals("mark")) {
            try {
                int num = Integer.parseInt(command.substring(5)) - 1;
                return new Mark(arrayList.get(num), arrayList, num);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'mark' command should be mark + space + number");
            }
        }
        if (command.split(" ")[0].equals("unmark")) {
            try {
                int num = Integer.parseInt(command.substring(7)) - 1;
                return new Unmark(arrayList.get(num), arrayList, num);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'unmark' command should be unmark + space + number");
            }
        }
        if (command.split(" ")[0].equals("todo")) {
            try {
                arrayList.add("[T][ ] " + command.substring(5));
                return new ToDos(command.substring(5), number);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'todo' command should be todo + space + task");
            }
        }
        if (command.split(" ")[0].equals("deadline")) {
            try {
                return new Deadlines(command, number, arrayList);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'deadline' command should be deadline + space + task"
                        + " + /by MMM d yyyy");
            }
        }
        if (command.split(" ")[0].equals("event")) {
            try {
                return new Events(command, number, arrayList);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'event' command should be event + space + task"
                        + " + /at time");
            }
        }
        if (command.split(" ")[0].equals("find")) {
            try {
                return new Find(command.split(" ")[1], arrayList);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'find' command should be find + space + task");
            }
        }
        if (command.split(" ")[0].equals("tag")) {
            try {
                return new Tag(arrayList, Integer.parseInt(command.split(" ")[1]) - 1);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'tag' command should be tag + space + number");
            }
        }
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    public boolean addToList() {
        return true;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    public String toString() {
        return " " + description + "\n";
    }
}
