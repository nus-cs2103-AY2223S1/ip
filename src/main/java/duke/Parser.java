package duke;

import java.util.ArrayList;

/**
 * Deals with different commands. Adds different commands to an arrayList.
 */
public class Parser {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an object of Parser.
     *
     * @param description
     */
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
    public static Command of(String command, ArrayList<String> arrayList, int number) throws DukeException {
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
            return new Deadlines(command, number, arrayList);
        }
        if (command.split(" ")[0].equals("event")) {
            return new Events(command, number, arrayList);
        }
        if (command.split(" ")[0].equals("find")) {
            try {
                return new Find(command.split(" ")[1], arrayList);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'find' command should be find + space + task");
            }
        }
        if (command.split(" ")[0].equals("delete")) {
            try {
                int num = Integer.parseInt(command.split(" ")[1]) - 1;
                return new Delete(arrayList.get(num), number, num, arrayList);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'delete' command should be delete + space + "
                        + "number");
            }
        }
        if (command.split(" ")[0].equals("tag")) {
            try {
                return new Tag(arrayList, Integer.parseInt(command.split(" ")[1]) - 1,
                        command.split(" ")[2]);
            } catch (Exception e) {
                throw new DukeException("Sorry. The format for 'tag' command should be tag + space + number "
                        + "+ space + content");
            }
        }
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
