package duke;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses user input and carry out commands accordingly.
     *
     * @param input input from user.
     * @param taskList list of tasks in Duke application.
     * @param ui user interface of Duke application.
     * @param storage stores the taskList into the hard drive.
     */
    public static String parse(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            //  Bye
            if (input.equals("bye")) {
                return ui.sayBye();
            }
            //  List
            if (input.equals("list")) {
                return ui.list(taskList);
            }
            //  Marking
            if ((input.length() >= 4) && (input.substring(0, 4).equals("mark"))) {
                //  error checking
                if (input.length() == 4 || input.substring(5).equals("")) {
                    throw new IllegalIndexException("OOPS!!! The index of a mark cannot be empty.");
                }
                //  Isolates the index after "mark"
                String remainder = input.substring(5);
                int index = Integer.valueOf(remainder) - 1;
                taskList.mark(index);
                storage.updateStorage(taskList);
                return ui.markResponse(taskList, index);
            }
            //  Unmarking
            if ((input.length() >= 6) && (input.substring(0, 6).equals("unmark"))) {
                //  error checking
                if (input.length() == 6 || input.substring(7).equals("")) {
                    throw new IllegalIndexException("OOPS!!! The index of an unmark cannot be empty.");
                }
                //  Isolates the index after "unmark"
                String remainder = input.substring(7);
                int index = Integer.valueOf(remainder) - 1;
                taskList.unmark(index);
                storage.updateStorage(taskList);
                return ui.unmarkResponse(taskList, index);
            }
            //  Add Todo Task
            if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                //  Error checking
                if (input.length() == 4 || input.substring(5).equals("")) {
                    throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                }
                //  Isolates the task description after "todo"
                String description = input.substring(5);
                ToDo todo = new ToDo(description);
                taskList.addTask(todo);
                storage.updateStorage(taskList);
                return ui.addResponse(todo, taskList);
            }
            //  Add Deadline Task
            if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                //  Error checking
                if (input.length() == 8 || input.substring(9).equals("")) {
                    throw new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
                }
                //  Isolates remaining input after "deadline" and filters further for task description and time
                String remainder = input.substring(9);
                String[] arr = remainder.split("/by");
                String description = arr[0].trim();
                String deadline = arr[1].trim();
                Deadline deadlineTask = new Deadline(description, deadline);
                taskList.addTask(deadlineTask);
                storage.updateStorage(taskList);
                return ui.addResponse(deadlineTask, taskList);
            }
            //  Add Event Task
            if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                // Error checking
                if (input.length() == 5 || input.substring(6).equals("0")) {
                    throw new EmptyDescriptionException("OOPS!!! The description of a event cannot be empty.");
                }
                //  Isolates remaining input after "event" and filters further for task description and time
                String remainder = input.substring(6);
                String[] arr = remainder.split("/at");
                String description = arr[0].trim();
                String time = arr[1].trim();
                Event event = new Event(description, time);
                taskList.addTask(event);
                storage.updateStorage(taskList);
                return ui.addResponse(event, taskList);
            }
            //  Delete Tasks
            if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                //  Isolates index after "delete"
                String remainder = input.substring(7);
                int index = Integer.valueOf(remainder) - 1;
                String response = ui.deleteResponse(taskList, index);
                taskList.deleteTask(index);
                storage.updateStorage(taskList);
                return response;
            }
            //  Find Tasks
            if (input.length() >= 4 && input.substring(0, 4).equals("find")) {
                //  Isolates string input after "find"
                String remainder = input.substring(5);
                return ui.find(taskList, remainder);
            }
            //  If loop reaches here, input is invalid, so throw an error
            throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (InvalidTaskException e) {
            return e.getMessage();
        } catch (IllegalIndexException e) {
            return e.getMessage();
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }
}
