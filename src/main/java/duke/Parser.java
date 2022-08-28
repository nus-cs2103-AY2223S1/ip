package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Class for parsing user inputs to be executed
 */
public class Parser {
    /**
     * Parses user input and returns true if program is to be exited
     * @param input user input
     * @param lst Tasklist of Duke program
     * @param ui user interface
     * @param storage storage for data
     * @return a boolean to decide if program is to be exited
     * @throws DukeException
     */
    public static boolean parse(String input, TaskList lst, Ui ui, Storage storage) throws DukeException {

        String[] words = input.split(" ", 2);
        String first = words[0];

        try {
            if (first.equals("bye")) {
                ui.sayBye();
                return true;

            } else if (first.equals("list")) {
                String result = "Here are the tasks in your list:\n";
                for (int i = 0; i < lst.size(); i++) {
                    result += (i + 1) + "." + lst.get(i).formatTask() + "\n";
                }

                System.out.println(result);

            } else if (first.equals("mark")) {
                char c = input.charAt(5);
                int index = Integer.parseInt(String.valueOf(c));
                lst.markTask(index - 1);
                lst.updateStorage(storage);

            } else if (first.equals("unmark")) {
                char c = input.charAt(7);
                int index = Integer.parseInt(String.valueOf(c));
                lst.unmarkTask(index - 1);
                lst.updateStorage(storage);


            } else if (first.equals("deadline") || first.equals("event") || first.equals("todo")) {
                if (words.length == 1) {
                    throw new DukeException("The description of a " + first + " cannot be empty.");
                }

                String s = words[1];
                String desc = "";
                Task t = new Task("test");
                if (first.equals("deadline")) {
                    String[] arr = s.split("/by");
                    desc = arr[0];
                    String time = arr[1].strip();
                    LocalDate d;
                    try {
                        d = LocalDate.parse(time);
                    } catch (java.time.format.DateTimeParseException e) {
                        throw new DukeException("Please provide a date in the format yyyy-mm-dd.");
                    }

                    t = new Deadline(desc, d);
                    lst.addNewTask(t);
                } else if (first.equals("event")) {
                    String[] arr = s.split("/at");
                    desc = arr[0];
                    String time = arr[1].strip();
                    LocalDate d;
                    try {
                        d = LocalDate.parse(time);
                    } catch (java.time.format.DateTimeParseException e) {
                        throw new DukeException("Please provide a date in the format yyyy-mm-dd.");
                    }
                    t = new Event(desc, d);
                    lst.addNewTask(t);

                } else if (first.equals("todo")) {
                    desc = words[1];
                    t = new Deadline.ToDo(desc);
                    lst.addNewTask(t);
                }
                lst.updateStorage(storage);
                System.out.println("Got it. I've added this duke.task: \n" + t.formatTask() + "\nNow you have "
                        + lst.size() + " tasks in the list.");

            } else if (first.equals("delete")) {
                if (words.length == 1) {
                    throw new DukeException("Please specify task to delete");
                }
                int index = Integer.parseInt(words[1]) - 1;
                lst.deleteTask(index);
                lst.updateStorage(storage);

            } else if (first.equals("find")) {
                String toFind = words[1];
                if (toFind.length() == 0) {
                    throw new DukeException("Please enter a keyword!");
                }
                ArrayList<String> result = lst.findTasks(toFind);
                System.out.println("Here are the matching tasks in your list:\n");
                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i));
                }

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means");
            }
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
        return false;
    }
}
