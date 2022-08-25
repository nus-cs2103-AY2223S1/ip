import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;
    private Storage storage;

    /* Define constructor for to do list */
    public ToDoList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /* Method for adding items to the list */
    public void addTask(String command) throws BobException {
        if (command.startsWith("todo")) {
            command = command.replace("todo", "").trim();
            if (command.length() == 0) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your deadlines in the following format: \n";
                errorString += "\t=> deadline <deadline-name> by <date>";
                Ui.printErrorMessage(errorString);
            } else {
                Task todo = new Todo(command);
                list.add(todo);
                storage.store(list);

                Ui.taskAddedMessage(todo, this);
            }
        } else if (command.startsWith("deadline")) {
            try {
                command = command.replace("deadline ", "").trim();
                if (command.length() == 0) {
                    String errorString = "\tInvalid formatting for deadline entered!\n";
                    errorString += "\tWrite your deadlines in the following format: \n";
                    errorString += "\t=> deadline <deadline-name> by <date>";
                    Ui.printErrorMessage(errorString);
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
                    } else if (by.trim().matches(regex) && !isAfter) {
                        String errorString = "\tI might be a non-sentient robot but you seem to be a time traveller!\n";
                        errorString += "\tPlease input deadlines BEFORE the current date and time.";
                        Ui.printErrorMessage(errorString);
                    } else {
                        Task task = new Deadline(deadline[0], deadline[1]);
                        list.add(task);
                        storage.store(list);

                        Ui.taskAddedMessage(task, this);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your deadlines in the following format: \n";
                errorString += "\t=> deadline <deadline-name> by <date>";
                Ui.printErrorMessage(errorString);
            } catch (DateTimeParseException e) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your dates in the following format: YYYY-MM-DD HHHH";

                Ui.printErrorMessage(errorString);
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
                    } else if (at.trim().matches(regex) && !isAfter) {
                        String errorString = "\tI might be a non-sentient robot but you seem to be a time traveller!\n";
                        errorString += "\tPlease input events BEFORE the current date and time.";
                        Ui.printErrorMessage(errorString);
                    } else {
                        Task task = new Event(event[0], event[1]);
                        list.add(task);
                        storage.store(list);

                        Ui.taskAddedMessage(task, this);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorString = "\tThe description of event cannot be empty!\n";
                errorString += "\tIt's impossible to go for something that does not exist...\n\n";
                errorString += "\tWrite your events in the following format: \n";
                errorString += "\t=> event <event-name> by <date>";
                Ui.printErrorMessage(errorString);
            } catch (DateTimeParseException e) {
                String errorString = "\tInvalid formatting for deadline entered!\n";
                errorString += "\tWrite your dates in the following format: YYYY-MM-DD HHHH";

                Ui.printErrorMessage(errorString);
            }
        } else {
            String errorString = "\tDeepest apologies, I am a mere automated bot.\n" +
                    "\tPlease stick to input that start with \n" +
                    "\t1. todo - for items that you have to do\n" +
                    "\t2. deadline - for items which have an upcoming deadline\n" +
                    "\t3. event - for events with a date and time\n" +
                    "\n" +
                    "\t4. mark - to mark an event as done\n" +
                    "\t5. unmark - to mark an event as undone\n" +
                    "\t6. delete - to delete an event\n" +
                    "\t7. list - to view all the events on your todo list\n" +
                    "\t8. bye - to wish me a (temporary) farewell\n";
            Ui.printErrorMessage(errorString);
        }
    }

    /* Method for deleting a specific event */
    public void deleteTask(int index) {
        Ui.taskDeletedMessage(index, this);
        this.list.remove(index);
        storage.store(list);
    }

    /*
     * Method to mark a certain item in the list as done
     * takes in the 1 index of the task
     */
    public void markItemDone(int index) {
        this.list.get(index - 1).markDone();
        Ui.markItemDoneMessage(this, index - 1); // takes in 0 index
        storage.store(list);
    }

    /*
     * Method to mark a certain item in the list as undone
     * takes in the 1 index of the task
     */
    public void markItemUndone(int index) {
        this.list.get(index - 1).markUndone();
        Ui.markItemUndoneMessage(this, index - 1); // takes in 0 index
        storage.store(list);
    }

    /* Method that gets the length of the list */
    public int getLength() {
        return this.list.size();
    }

    /*
     * Method to get a certain task in the list
     * Takes in the 0 index of the task
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /* Method for printing items in the list */
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
