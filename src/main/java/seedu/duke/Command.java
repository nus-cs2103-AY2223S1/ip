package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal command operation that can be performed by the user.
 */
public enum Command {
    BYE,
    DEADLINE,
    DEFAULT,
    DELETE,
    EVENT,
    FIND,
    LIST,
    MARK,
    TODO,
    UNMARK;

    /**
     * Performs the respective actions with accordance to the command.
     *
     * @param tasklist Tasklist containing an arraylist of tasks.
     * @param ui Ui of the application.
     * @param storage Storage which handles the reading and saving of tasks to hard disk.
     * @param input The user's input in the command line
     * @throws DukeException if command is not found or cannot be executed.
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        ArrayList<Task> tasks = tasklist.getTasks();
        int taskNumber;
        Task task;
        String[] split;

        try {
            String output = "";
            switch(this) {

            case LIST:
                output += "Here are the tasks in your list:\n";
            
                for (int i = 0; i < tasks.size(); i++) {
                    task = tasks.get(i);
                    output += String.valueOf(i + 1) + "." + task + "\n";
                }
                break;

            case MARK:
                if (input.length() == 4) {
                    throw new DukeException("Choose which task to mark as done!");
                }
                taskNumber = Integer.parseInt(input.substring(5));
                output += tasks.get(taskNumber - 1).setDone();
                break;


            case UNMARK:
                if (input.length() == 6) {
                    throw new DukeException("Choose which task to mark as undone!");
                }
                taskNumber = Integer.parseInt(input.substring(7));
                output += tasks.get(taskNumber - 1).setUndone();
                break;

            case TODO:
                if (input.length() == 4) {
                    throw new DukeException("The description of a todo cannot be empty");
                }

                task = new Todo(input.substring(5));
                tasks.add(task);
                ui.add(task);
                output += "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
                break;

            case EVENT:
                if (input.length() == 5) {
                    throw new DukeException("The description of an event cannot be empty");
                }

                if (input.indexOf('/') == -1) {
                    throw new DukeException("The date of the event cannot be empty");
                }

                split = input.substring(6).split("/");
                task = new Event(split[0], split[1].substring(3));
                tasks.add(task);
                ui.add(task);
                output += "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
                break;

            case DEADLINE:
                if (input.length() == 8) {
                    throw new DukeException("The description of an deadline cannot be empty");
                }

                if (input.indexOf('/') == -1) {
                    throw new DukeException("The date of the deadline cannot be empty");
                }


                split = input.substring(9).split("/");
                task = new Deadline(split[0], split[1].substring(3));
                tasks.add(task);
                ui.add(task);
                output += "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
                break;

            case DELETE:
                if (input.length() == 6) {
                    throw new DukeException("Choose which task to delete!");
                }
                taskNumber = Integer.parseInt(input.substring(7)) - 1;
                task = tasks.get(taskNumber);
                tasks.remove(taskNumber);
                ui.remove(task);
                output += "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
                break;

            case BYE:
                output += ui.showGoodbye();
                break;

            case FIND:
                output += "Here are the matching tasks in your list:\n";
                
                String keyword = input.substring(5);
                int listValue = 1;

                for (int i = 0; i < tasks.size(); i++) {
                    task = tasks.get(i);
                    if (task.toString().indexOf(keyword) != -1) {
                        output += (String.valueOf(listValue) + "." + task + "\n");
        
                        listValue++;
                    }
                }
                break;

            default:
                throw new DukeException("I'm sorry, but I dont know what you mean :(");

            }

            return output;

        } catch (DukeException e) {
            return e.getMessage();

        } finally {
            storage.save(tasks);
        }
    }


}
