package duke.parser;


import duke.exceptions.ArgumentNumberException;
import duke.exceptions.DateTimeFormatException;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.EmptyDateTimeException;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.InvalidItemException;
import duke.listobjects.Deadline;
import duke.listobjects.Event;
import duke.listobjects.ListObject;
import duke.listobjects.ToDo;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents the Parser of Duke which parses and interprets user commands
 */
public class ParserDuke {

    private static final String HELLO_CMD = "hello";
    private static final String LIST_CMD = "list";
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String DELETE_CMD = "delete";
    private static final String TODO_CMD = "todo";
    private static final String EVENT_CMD = "event";
    private static final String DEADLINE_CMD = "deadline";
    private static final String FIND_CMD = "find";
    private static final String HELP_CMD = "help";
    private static final String SORT_CMD = "sort";
    private static final String FILE_PATH = "src/main/java/duke/DukeTasks.txt";
    private final String command;

    /**
     * Constructs a ParserDuke object with given command
     *
     * @param command String representing user command
     */
    public ParserDuke(String command) {
        this.command = command;
    }

    /**
     * Prints response for empty user command
     */
    public String parseEmptyString() throws EmptyCommandException {
        throw new EmptyCommandException();
    }

    /**
     * Parses command to add a to-do task to the list of items stored
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing confirmation of addition and new number of tasks in list
     * @throws EmptyTaskException if the user does not provide task description
     */
    public String parseTask(TaskList listOfItems) throws EmptyTaskException {

        String task = this.command.replaceAll("todo", "");
        if (task.isEmpty()) {
            throw new EmptyTaskException();
        } else {
            ListObject newItem = new ToDo(task, 0);
            listOfItems.handleItemAddition(newItem);
            Storage.makeListFile(FILE_PATH, listOfItems);
            String comment = "'Tis a new sky for you to scale! Here! \n" + newItem + "\n";
            String info = "You now have " + listOfItems.knowTaskCount() + " to do!\n";
            return comment + info;
        }
    }

    /**
     * Parses command to add a deadline task to the list of items stored
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing confirmation of addition and new number of tasks in list
     * @throws EmptyTaskException      if the user does not provide task description
     * @throws EmptyDateTimeException  if the user does not provide date and time description
     * @throws ArgumentNumberException if the user does not provide the correct number of arguments
     */

    public String parseDeadline(TaskList listOfItems) throws EmptyTaskException,
            EmptyDateTimeException, ArgumentNumberException {

        String preTask = command.replaceAll("deadline", "");
        String[] words = preTask.split("/");

        if (words.length != 2) {
            throw new ArgumentNumberException();
        }

        String task = words[0];
        String dateTime = words[1];

        if (task.isEmpty()) {
            throw new EmptyTaskException();
        } else {
            String[] times = dateTime.split(" ");
            if (times.length <= 1) {
                throw new EmptyDateTimeException();
            } else if (times.length == 2) {
                try {
                    checkDateTimeFormat(times[0], times[1]);
                    ListObject newItem = new Deadline(task, 0, dateTime);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    String comment = "Mark this on your calendar! \n" + newItem + "\n";
                    String info = "You now have " + listOfItems.knowTaskCount() + "to do!\n";
                    return comment + info + "\n";
                } catch (DateTimeFormatException e) {
                    return e.getMessage();
                }
            } else {
                throw new ArgumentNumberException();
            }
        }
    }

    /**
     * Parses command to add an event task to the list of items stored
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing confirmation of addition and new number of tasks in list
     * @throws EmptyTaskException      if the user does not provide task description
     * @throws EmptyDateTimeException  if the user does not provide date and time description
     * @throws ArgumentNumberException if the user does not provide the correct number of arguments
     */
    public String parseEvent(TaskList listOfItems) throws EmptyTaskException,
            EmptyDateTimeException, ArgumentNumberException {

        String preTask = command.replaceAll("event", "");
        String[] words = preTask.split("/");

        if (words.length != 2) {
            throw new ArgumentNumberException();
        }

        String task = words[0];
        String dateTime = words[1];

        if (task.isEmpty()) {
            throw new EmptyTaskException();
        } else {
            String[] times = dateTime.split(" ");
            if (times.length <= 2) {
                throw new EmptyDateTimeException();
            } else if (times.length == 3) {
                try {
                    checkDateTimeFormat(times[0], times[1], times[2]);
                    ListObject newItem = new Event(task, 0, dateTime);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    String comment = "Another moment to mark... \n" + newItem + "\n";
                    String info = "You now have " + listOfItems.knowTaskCount() + "to do!\n";
                    return comment + info + "\n";
                } catch (DateTimeFormatException e) {
                    return e.getMessage();
                }
            } else {
                throw new ArgumentNumberException();
            }
        }
    }

    /**
     * Parses command to list all items stored, if list is not empty
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing all tasks in list
     */
    public String parseListCmd(TaskList listOfItems) {

        if (listOfItems.getListLength() != 0) {
            String comment = "These are the tasks on your list!\n";
            String list = listOfItems.toString();
            return comment + list + "\n";
        } else {
            return "Your pages are yet blank. But the White Book was not written in a day...";
        }
    }

    /**
     * Parses command to mark task as complete on stored list, if the item exists
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing completion of action and the modified task
     */
    public String parseMarkCmd(TaskList listOfItems) {
        try {
            String taskNo = command.replaceAll("\\D+", "");
            int taskNoAsInt = Integer.parseInt(taskNo) - 1;
            String comment = "Very well! One less burden to bear! I have marked this complete:\n";
            String item = listOfItems.handleItem("MARK", taskNoAsInt);
            Storage.makeListFile(FILE_PATH, listOfItems);
            return comment + item;
        } catch (InvalidItemException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses command to mark task as incomplete on stored list, if the item exists
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing completion of action and the modified task
     */
    public String parseUnmarkCmd(TaskList listOfItems) {
        try {
            String taskNo = command.replaceAll("\\D+", "");
            int taskNoAsInt = Integer.parseInt(taskNo) - 1;
            String comment = "Hmm....I have marked this incomplete:\n";
            String item = listOfItems.handleItem("UNMARK", taskNoAsInt);
            Storage.makeListFile(FILE_PATH, listOfItems);
            return comment + item;
        } catch (InvalidItemException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses command to sort all the items in the list based on deadline (if it exists) and task description
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing sorted list
     */
    public String parseSortCmd(TaskList listOfItems) {
        listOfItems.sortList();
        String comment = "Indeed I shall invoke the Eye of Agomotto to turn time ... \n";
        return comment + listOfItems;
    }

    /**
     * Parses command to delete task on stored list, if the item exists
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing completion of action and the deleted task
     */
    public String parseDeleteCmd(TaskList listOfItems) {
        try {
            String taskNo = command.replaceAll("\\D+", "");
            int taskNoAsInt = Integer.parseInt(taskNo) - 1;
            String comment = "And so it must be...\nWe leave behind what we can not hold on to.\n"
                    + "I have removed this from your list:\n";
            String item = listOfItems.handleItem("DELETE", taskNoAsInt);
            Storage.makeListFile(FILE_PATH, listOfItems);
            return "You have only " + listOfItems.knowTaskCount() + "remaining";
        } catch (InvalidItemException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses command to find task based on keyword on stored list, if the item exists
     *
     * @param listOfItems TaskList representing all stored tasks
     * @return String representing list with only items whose description contains keyword
     * @throws ArgumentNumberException if the user has not provided keyword to search for
     */
    public String parseFind(TaskList listOfItems) throws ArgumentNumberException {
        try {
            String target = command.replaceAll("find ", "");
            if (!target.isEmpty()) {
                String list = listOfItems.findByKeyword(target);
                String comment = "Here is what you are looking for!\n";
                return comment + list + "\n";
            } else {
                throw new ArgumentNumberException();
            }
        } catch (InvalidItemException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses command to display all commands, their input formats and functions
     *
     * @return String representing list of all commands and related details
     */
    public String parseHelpCmd() {
        String comment = "Aye, what good is a phantom were he not to share what he knows?\n";
        String helpList = "You may use the following commands and arguments: \n"
                + "1. `help` - view all commands and arguments\n"
                + "2. `hello` - learn my name and story\n"
                + "3. `list` - view the stored list\n"
                + "4. `todo [TASK_NAME]` - add a task as a todo item\n"
                + "5. `deadline [TASK_NAME]/[DATE] [TIME]` - add a task with a deadline with date in the "
                + "format of yyyy-MM-dd and time in the format HH:mm\n"
                + "6. `event [TASK_NAME]/[DATE] [START_TIME] [END_TIME]` - add an event with a date of occurance,"
                + " start and end timings where date is formatted as yyyy-MM-dd and time as HH:mm\n"
                + "7. `mark [ITEM_ID]` - mark an item, as identified by its position on the list, as complete\n"
                + "8. `unmark [ITEM_ID]` - mark an item, as identified by its position on the list, as incomplete\n"
                + "9. `delete [ITEM_ID]` - delete an item, as identified by its position on the list, from the list\n"
                + "10. `find [KEYWORD]` - find items containing a keyword in their task descriptions\n"
                + "11. `sort` - sort all items on the list based on their deadlines and task descriptions\n"
                + "12. `bye` - end the conversation with me. This automatically saves the current state of the list.\n";
        return comment + helpList;
    }

    /**
     * Prints Duke's self-introduction and helps to customise its personality.
     */
    public String introduceDuke() {
        String intro = "I once wandered these halls, centuries ago. I am Duke Aemon of Old.\n";
        String quote1 = "Indeed, my memory is long when I am but a ghost of a memory myself...\n"
                + "But you are young blood. What brings you to these ancient halls?\n";

        return "Welcome, my friend!\n" + intro + quote1;
    }

    /**
     * Checks if the input date and time are formatted correctly after parsing event or deadline creation commands
     *
     * @param dateTimes String of a variable number representing input date and time
     * @throws DateTimeFormatException if the user has entered date or time in an incorrect format
     */
    public void checkDateTimeFormat(String... dateTimes) throws DateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            String date = dateTimes[0];
            LocalDate dateFormat = LocalDate.parse(date, formatter);
            String timeBegin = dateTimes[1];
            LocalTime timeBeginFormat = LocalTime.parse(timeBegin, DateTimeFormatter.ISO_LOCAL_TIME);
            if(dateTimes.length==3) {
                String timeEnd = dateTimes[2];
                LocalTime timeEndFormat = LocalTime.parse(timeEnd, DateTimeFormatter.ISO_LOCAL_TIME);
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }


    /**
     * Reads the user command, initiates completion of the appropriate action and prints response
     */
    public String parseCommand() throws EmptyCommandException {

        String reply = "Hmm...LOOKS LIKE SOMETHING IS WRONG! Try Again!";

        try {
            if (command.isEmpty()) {
                parseEmptyString();
            } else {

                TaskList listOfItems = Storage.readFromFile(FILE_PATH);
                String[] parts = command.split(" ");
                String instruction = parts[0];


                switch (instruction) {
                case LIST_CMD:
                    reply = parseListCmd(listOfItems);
                    break;
                case HELP_CMD:
                    reply = parseHelpCmd();
                    break;
                case HELLO_CMD:
                    reply = introduceDuke();
                    break;
                case MARK_CMD:
                    reply = parseMarkCmd(listOfItems);
                    break;
                case UNMARK_CMD:
                    reply = parseUnmarkCmd(listOfItems);
                    break;
                case DELETE_CMD:
                    reply = parseDeleteCmd(listOfItems);
                    break;
                case FIND_CMD:
                    reply = parseFind(listOfItems);
                    break;
                case TODO_CMD:
                    reply = parseTask(listOfItems);
                    break;
                case DEADLINE_CMD:
                    reply = parseDeadline(listOfItems);
                    break;
                case EVENT_CMD:
                    reply = parseEvent(listOfItems);
                    break;
                case SORT_CMD:
                    reply = parseSortCmd(listOfItems);
                    break;
                default:
                    String comment = "Why trouble me with the unrefined language of the youth! "
                            + "Speak plainly, my friend!";
                    reply = comment + "\n";
                    break;
                }

                return reply;
            }
        } catch (EmptyCommandException e) {
            reply = e.getMessage();
        } catch (EmptyTaskException e) {
            reply = e.getMessage();
        } catch (EmptyDateTimeException e) {
            reply = e.getMessage();
        } catch (ArgumentNumberException e) {
            reply = e.getMessage();
        } catch (Exception e) {
            reply = "Aemon went back to his crypt for a nap.\n"
                    + "Please restart the program after deleting the DukeList.txt file.\n";
        } finally {
            return reply;
        }
    }
}
