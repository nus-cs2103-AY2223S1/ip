package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

        String[] words = input.split(" ",2);
        String first = words[0];

        try {
            if (first.equals("bye")) {
                ui.sayBye();
                return true;

            } else if (first.equals("list")) {
                String textToAppend = "Here are the tasks in your list: \n";

                try {
                    ArrayList<String> data = storage.load();
                    for (int i = 0; i < data.size(); i++) {
                        String j = data.get(i);
                        String[] details = j.split("/");
                        String verb = "";
                        String date= "";
                        if (details[0].equals("E")) {
                            verb = "at ";
                            LocalDate d = LocalDate.parse(details[3]);
                            date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                        } else if (details[0].equals("D")) {
                            verb = "by ";
                            LocalDate d = LocalDate.parse(details[3]);
                            date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                        }
                        String s =  (i + 1) + "." + "[" + details[0] + "]" + "[" + details[1] + "] " + details[2] + verb + date + "\n";
                        textToAppend += s;
                    }

                    System.out.println(textToAppend);

                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }


            } else if (first.equals("mark")) {
                char c = input.charAt(5);
                int index = Integer.parseInt(String.valueOf(c));
                lst.markTask(index - 1);
                lst.updateStorage(storage);

            } else if (first.equals("unmark")) {
                char c = input.charAt(7);
                int index = Integer.parseInt(String.valueOf(c));
                lst.unmarkTask(index-1);
                lst.updateStorage(storage);


            } else if (first.equals("deadline") || first.equals("event") || first.equals("todo")) {
                if (words.length==1) {
                    throw new DukeException("The description of a " +  first + " cannot be empty.");
                }

                String s = words[1];

                String desc = "";
                Task t = new Deadline.ToDo("test");
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
                }

                else if (first.equals("event")) {
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
                }

                else if (first.equals("todo")) {
                    desc = words[1];
                    t = new Deadline.ToDo(desc);
                    lst.addNewTask(t);
                }
                lst.updateStorage(storage);
                System.out.println("Got it. I've added this duke.task: \n" + t.formatTask() + "\nNow you have " + lst.size() + " tasks in the list.");
            }

            else if (first.equals("delete")) {
                if (words.length==1) {
                    throw new DukeException("Please specify duke.task to delete");
                }
                int index = Integer.parseInt(words[1]) - 1;
                lst.deleteTask(index);
                lst.updateStorage(storage);
            }

            else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
        return false;
    }
}
