package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses the input and returns if the scanner should scan a new line
     *
     * @param first The users input
     * @param lst The tasklist object
     * @param store The storage object
     * @return boolean A boolean that represents whether the scanner should continue with
     *                 a new line
     */
    public static boolean parse(String first, TaskList lst, Storage store) {
        try {
            if (first.equals("bye")) {
                Ui.byeMessage();
                return true;
            } else if (first.equals("list")) {
                listMessage(lst);
                return false;
            } else if (first.length() == 6 && first.substring(0, 4).equals("mark")) {
                markMessage(first, lst, store);
                return false;
            } else if (first.length() == 8 && first.substring(0, 6).equals("unmark")) {
                unmarkMessage(first, lst, store);
                return false;
            } else if (first.length() >= 4 && first.substring(0, 4).equals("todo")) {
                todoMessage(first, lst, store);
                return false;
            } else if (first.length() >= 8 && first.substring(0, 8).equals("deadline")) {
                deadlineMessage(first, lst, store);
                return false;
            } else if (first.length() >= 5 && first.substring(0, 5).equals("event")) {
                eventMessage(first, lst, store);
                return false;
            } else if (first.length() == 8 && first.substring(0, 6).equals("delete")) {
                deleteMessage(first, lst, store);
                return false;
            }
            else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            System.out.println(ex);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Prints the list when user types "list"
     *
     * @param lst The tasklist
     */
    public static void listMessage(TaskList lst) throws DukeException{
        if (lst.count() == 0) {
            throw new DukeException("Your list is empty!!");
        } else {
            for (int i = 1; i < lst.count() + 1; i++) {
                System.out.println((i) + ". " + lst.get(i - 1));
            }
        }
    }

    /**
     * Marks a task as completed
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     */
    public static void markMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        char index = str.charAt(5);
        int number = Integer.parseInt(String.valueOf(index));
        if(number > lst.count()) {
            throw new DukeException("No such task.");
        }
        lst.markTask(number - 1);
        storage.updateFile(lst);
    }

    /**
     * Marks a task as incomplete
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     */
    public static void unmarkMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        char index = str.charAt(7);
        int number = Integer.parseInt(String.valueOf(index));
        if(number > lst.count()) {
            throw new DukeException("No such task.");
        }
        lst.unmarkTask(number - 1);
        storage.updateFile(lst);
    }

    /**
     * Adds a Todo to the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     */
    public static void todoMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if(str.length() > 5) {
            String description = str.substring(5);
            Todo d = new Todo(description);
            lst.add(d);
            System.out.println("Got it. I've added this task:\n " + d +
                    "\nNow you have " + lst.count() + " tasks in the list.");
            storage.updateFile(lst);
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a Deadline to the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     */
    public static void deadlineMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if(str.length() > 9) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(9, sepPos);
                String by = str.substring(sepPos + 4);
                DateTimeFormatter fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    LocalDate.parse(by, fromFormat);
                } catch (DateTimeParseException e) {
                    System.out.println("Deadline format has to be in yyyy-MM-dd");
                    return;
                }
                Deadline l = new Deadline(description, LocalDate.parse(by));
                lst.add(l);
                System.out.println("Got it. I've added this task:\n " + l +
                        "\nNow you have " + lst.count() + " tasks in the list.");
                storage.updateFile(lst);
            } else {
                throw new DukeException("You have to include the deadline");
            }
        } else {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    /**
     * Adds a Event to the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     */
    public static void eventMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if (str.length() > 6) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(6, sepPos);
                String at = str.substring(sepPos + 4);
                Event e = new Event(description, at);
                lst.add(e);
                System.out.println("Got it. I've added this task:\n " + e +
                        "\nNow you have " + lst.count() + " tasks in the list.");
                storage.updateFile(lst);
            } else {
                throw new DukeException("You have to include the timings of an event");
            }
        } else {
            throw new DukeException("The description of a event cannot be empty.");
        }
    }

    /**
     * Deletes a task from the tasklist
     *
     * @param str The users input
     * @param lst The tasklist
     * @param storage the storage object
     */
    public static void deleteMessage(String str, TaskList lst, Storage storage) throws DukeException, IOException {
        if (str.length() > 7) {
            char index = str.charAt(7);
            int number = Integer.parseInt(String.valueOf(index));
            if(number > lst.count()) {
                throw new DukeException("No such task.");
            }
            Task t = lst.get(number - 1);
            lst.delete(t);
            System.out.println("Noted. I've removed this task: \n " + t + "\nNow you have "
                    + lst.count() + " tasks in the list.");
            storage.updateFile(lst);
        }
    }
}
