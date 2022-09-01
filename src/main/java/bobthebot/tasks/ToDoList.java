package bobthebot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import bobthebot.utils.Storage;
import bobthebot.utils.Ui;

/**
 * Class which represents a ToDoList.
 */
public class ToDoList {
    private ArrayList<Task> list;
    private Storage storage;

    /**
     * Constructs instance of ToDoList.
     *
     * @param list of tasks. List of tasks can be empty or contains elements taken from storage.
     * @param storage Storage from which
     */
    public ToDoList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Adds items to the list. Also handles the logic for the type of task to be added.
     *
     * @param command A String which contains the command about the task to be added.
     * @return Task added message.
     */
    public String addTask(String command) {
        if (command.startsWith("todo")) {
            command = command.replace("todo", "").trim();
            if (command.length() == 0) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your deadlines in the following format: \n";
                errorString += "\t=> deadline <deadline-name> by <date>";
                Ui.printErrorMessage(errorString);
                return errorString;
            } else {
                Task todo = new Todo(command);
                list.add(todo);
                storage.store(list);
                return Ui.taskAddedMessage(todo, this);
            }
        } else if (command.startsWith("deadline")) {
            try {
                command = command.replace("deadline ", "").trim();
                if (command.length() == 0) {
                    String errorString = "\tInvalid formatting for deadline entered!\n";
                    errorString += "\tWrite your deadlines in the following format: \n";
                    errorString += "\t=> deadline <deadline-name> by <date>";
                    Ui.printErrorMessage(errorString);
                    return errorString;
                } else {
                    String[] deadline = command.split(" /by ");

                    String by = deadline[1];

                    // generate boolean indicating if the deadline is before or after current date
                    // and time
                    LocalDateTime currDate = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(by, format);
                    Boolean isAfter = deadlineDate.isAfter(currDate);

                    String regex = "(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2})(\\d{2})";
                    // invalid deadline format
                    if (!by.trim().matches(regex)) {
                        String errorString = "\tInvalid formatting for deadline entered!\n";
                        errorString += "\tWrite your deadlines in the following format: YYYY-MM-DD HHHH";
                        Ui.printErrorMessage(errorString);
                        return errorString;
                    } else if (by.trim().matches(regex) && !isAfter) {
                        String errorString = "\tI might be a non-sentient robot but you seem to be a time traveller!\n";
                        errorString += "\tPlease input deadlines BEFORE the current date and time.";
                        Ui.printErrorMessage(errorString);
                        return errorString;
                    } else {
                        Task task = new Deadline(deadline[0], deadline[1]);
                        list.add(task);
                        storage.store(list);
                        return Ui.taskAddedMessage(task, this);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your deadlines in the following format: \n";
                errorString += "\t=> deadline <deadline-name> by <date>";
                Ui.printErrorMessage(errorString);
                return errorString;
            } catch (DateTimeParseException e) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your dates in the following format: YYYY-MM-DD HHHH";
                Ui.printErrorMessage(errorString);
                return errorString;
            }
        } else if (command.startsWith("event")) {
            try {
                command = command.replace("event ", "").trim();
                if (command.length() == 0) {
                    String errorString = "\tThe description of event cannot be empty!\n";
                    errorString += "\tIt's impossible to go for something that does not exist...\n\n";
                    errorString += "\tWrite your events in the following format: \n";
                    errorString += "\t=> event <event-name> by <date>";

                    Ui.printErrorMessage(errorString);
                    return errorString;
                } else {
                    String[] event = command.split(" /at ");

                    String at = event[1];

                    // generate boolean indicating if the deadline is before or after current date
                    // and time
                    LocalDateTime currDate = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(at, format);
                    Boolean isAfter = deadlineDate.isAfter(currDate);

                    String regex = "(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2})(\\d{2})";
                    // invalid deadline format
                    if (!at.trim().matches(regex)) {
                        String errorString = "\tInvalid formatting for date entered!\n";
                        errorString += "\tWrite your date in the following format: YYYY-MM-DD HHHH";
                        Ui.printErrorMessage(errorString);
                        return errorString;
                    } else if (at.trim().matches(regex) && !isAfter) {
                        String errorString = "\tI might be a non-sentient robot but you seem to be a time traveller!\n";
                        errorString += "\tPlease input events BEFORE the current date and time.";
                        Ui.printErrorMessage(errorString);
                        return errorString;
                    } else {
                        Task task = new Event(event[0], event[1]);
                        list.add(task);
                        storage.store(list);

                        return Ui.taskAddedMessage(task, this);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorString = "\tThe description of event cannot be empty!\n";
                errorString += "\tIt's impossible to go for something that does not exist...\n\n";
                errorString += "\tWrite your events in the following format: \n";
                errorString += "\t=> event <event-name> by <date>";
                Ui.printErrorMessage(errorString);
                return errorString;
            } catch (DateTimeParseException e) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your dates in the following format: YYYY-MM-DD HHHH";

                Ui.printErrorMessage(errorString);
                return errorString;
            }
        } else {
            String errorString = "\tDeepest apologies, I am a mere automated bot.\n"
                    + "\tPlease stick to input that start with \n"
                    + "\t1. todo - for items that you have to do\n"
                    + "\t2. deadline - for items which have an upcoming deadline\n"
                    + "\t3. event - for events with a date and time\n"
                    + "\n"
                    + "\t4. mark - to mark an event as done\n"
                    + "\t5. unmark - to mark an event as undone\n"
                    + "\t6. delete - to delete an event\n"
                    + "\t7. list - to view all the events on your todo list\n"
                    + "\t8. find - to find items in your list containing a certain keyword\n"
                    + "\t9. bye - to wish me a (temporary) farewell";
            Ui.printErrorMessage(errorString);
            return errorString;
        }
    }

    /**
     * Deletes a specific event from the list, and updating the storage.
     *
     * @param index Specifies 0 index of task to be deleted.
     * @return Task deleted message.
     */
    public String deleteTask(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        storage.store(list);
        return Ui.taskDeletedMessage(task, this);
    }

    /**
     * Marks a specific event from the list as done, and updating the storage.
     *
     * @param index Specifies 1 index of task to be marked as done.
     * @return Task mark item done message.
     */
    public String markItemDone(int index) {
        this.list.get(index - 1).markDone();
        storage.store(list);
        return Ui.markItemDoneMessage(this, index - 1); // takes in 0 index
    }

    /**
     * Marks a specific event from the list as undone, and updating the storage.
     *
     * @param index Specifies 1 index of task to be marked as undone.
     * @return Task mark item undone message.
     */
    public String markItemUndone(int index) {
        this.list.get(index - 1).markUndone();
        storage.store(list);
        return Ui.markItemUndoneMessage(this, index - 1); // takes in 0 index
    }

    /**
     * Returns the number of items in the ToDo List.
     *
     * @return An int representing the number of items in the ToDo List.
     */
    public int getLength() {
        return this.list.size();
    }

    /**
     * Returns the specified task.
     *
     * @param index 0 index of the task specified.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Returns a String containing all the elements in the list.
     *
     * @return String containing all the elements in the list.
     */
    @Override
    public String toString() {
        int numOfElements = this.list.size();
        String res = "\tHere are your tasks:\n";
        for (int i = 1; i <= numOfElements; i++) {
            String curr = "\t" + i + ". " + this.list.get(i - 1).toString();
            if (i != numOfElements) {
                curr += "\n";
            }
            res += curr;
        }
        return res;
    }
}
