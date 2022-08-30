package duke;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses user input and carry out commands accordingly.
     *
     * @param tasklist list of tasks in Duke application.
     * @param ui user interface of Duke application.
     * @param storage stores the tasklist into the hard drive.
     */
    public static void parse(TaskList tasklist, Ui ui, Storage storage) {
        while (ui.hasNextLine()) {
            String input = ui.getUserCommand();
            try {
                //  Bye
                if (input.equals("bye")) {
                    ui.sayBye();
                    return;
                }
                //  List
                if (input.equals("list")) {
                    ui.list(tasklist);
                    continue;
                }
                //  Marking
                if ((input.length() >= 4) && (input.substring(0, 4).equals("mark"))) {
                    //  error checking
                    if (input.length() == 4 || input.substring(5).equals("")) {
                        throw new IllegalIndexException("OOPS!!! The index of a mark cannot be empty.");
                    }
                    String remainder = input.substring(5);
                    int index = Integer.valueOf(remainder) - 1;
                    tasklist.mark(index, ui);
                    storage.updateStorage(tasklist);
                    continue;
                }
                //  Unmarking
                if ((input.length() >= 6) && (input.substring(0, 6).equals("unmark"))) {
                    //  error checking
                    if (input.length() == 6 || input.substring(7).equals("")) {
                        throw new IllegalIndexException("OOPS!!! The index of an unmark cannot be empty.");
                    }
                    String remainder = input.substring(7);
                    int index = Integer.valueOf(remainder) - 1;
                    tasklist.unmark(index, ui);
                    storage.updateStorage(tasklist);
                    continue;
                }
                //  Add Todo Task
                if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                    //  error checking
                    if (input.length() == 4 || input.substring(5).equals("")) {
                        throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasklist.addTask(new ToDo(input.substring(5)), ui);
                    storage.updateStorage(tasklist);
                    continue;
                }
                //  Add Deadline Task
                if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    //  error checking
                    if (input.length() == 8 || input.substring(9).equals("")) {
                        throw new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String remainder = input.substring(9);
                    String[] arr = remainder.split("/by");
                    String description = arr[0].trim();
                    String deadline = arr[1].trim();
                    tasklist.addTask(new Deadline(description, deadline), ui);
                    storage.updateStorage(tasklist);
                    continue;
                }
                //  Add Event Task
                if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    // error checking
                    if (input.length() == 5 || input.substring(6).equals("0")) {
                        throw new EmptyDescriptionException("OOPS!!! The description of a event cannot be empty.");
                    }
                    String remainder = input.substring(6);
                    String[] arr = remainder.split("/at");
                    String description = arr[0].trim();
                    String time = arr[1].trim();
                    tasklist.addTask(new Event(description, time), ui);
                    storage.updateStorage(tasklist);
                    continue;
                }
                //  Delete Tasks
                if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    String remainder = input.substring(7);
                    int index = Integer.valueOf(remainder) - 1;
                    tasklist.deleteTask(index, ui);
                    storage.updateStorage(tasklist);
                    continue;
                }
                //  Find Tasks
                if (input.length() >= 4 && input.substring(0, 4).equals("find")) {
                    String remainder = input.substring(5);
                    ui.find(tasklist, remainder);
                    continue;
                }
                //  if loop reaches here, raise error
                throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidTaskException e) {
                System.out.println(e);
                continue;
            } catch (IllegalIndexException e) {
                System.out.println(e);
                continue;
            } catch (EmptyDescriptionException e) {
                System.out.println(e);
                continue;
            }
        }
    }
}
