package duke.parser;

import duke.exceptions.*;
import duke.listobjects.Deadline;
import duke.listobjects.Event;
import duke.listobjects.ListObject;
import duke.listobjects.ToDo;
import duke.storage.Storage;
import duke.tasklist.TaskList;


/**
 * Represents the Parser of Duke which parses and interprets user commands
 */
public class ParserDuke {

    private final String HELLO_CMD = "hello";
    private final String LIST_CMD = "list";
    private final String MARK_CMD = "mark";
    private final String UNMARK_CMD = "unmark";
    private final String DELETE_CMD = "delete";
    private final String TODO_CMD = "todo";
    private final String EVENT_CMD = "event";
    private final String DEADLINE_CMD = "deadline";
    private final String FIND_CMD = "find";
    private final String HELP_CMD = "help";
    private final String SORT_CMD = "sort";
    private final String FILE_PATH = "src/main/java/duke/DukeTasks.txt";
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

    public String parseTask(TaskList listOfItems) throws EmptyTaskException{

        String task = this.command.replaceAll("todo", "");
        if(task.isEmpty()){
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

    public String parseDeadline(TaskList listOfItems) throws EmptyTaskException,
            EmptyDateTimeException, ArgumentNumberException{

        String preTask = command.replaceAll("deadline", "");
        String[] words = preTask.split("/");

        if(words.length!=2){
            throw new ArgumentNumberException();
        }

        String task = words[0];
        String dateTime = words[1];

        if(task.isEmpty()){
            throw new EmptyTaskException();
        } else {
            String[] times = dateTime.split(" ");
            if(times.length <= 1) {
                throw new EmptyDateTimeException();
            } else if (times.length == 2) {
                ListObject newItem = new Deadline(task, 0, dateTime);
                listOfItems.handleItemAddition(newItem);
                Storage.makeListFile(FILE_PATH, listOfItems);
                Storage.makeListFile(FILE_PATH, listOfItems);
                String comment = "Mark this on your calendar! \n" + newItem + "\n";
                String info = "You now have " + listOfItems.knowTaskCount() + " to do!\n";
                return comment + info + "\n";
            } else {
                throw new ArgumentNumberException();
            }
        }
    }

    public String parseEvent(TaskList listOfItems) throws EmptyTaskException,
            EmptyDateTimeException, ArgumentNumberException{

        String preTask = command.replaceAll("event", "");
        String[] words = preTask.split("/");

        if(words.length!=2){
            throw new ArgumentNumberException();
        }

        String task = words[0];
        String dateTime = words[1];

        if(task.isEmpty()){
            throw new EmptyTaskException();
        } else {
            String[] times = dateTime.split(" ");
            if(times.length <= 2) {
                throw new EmptyDateTimeException();
            } else if (times.length == 3) {
                ListObject newItem = new Event(task, 0, dateTime);
                listOfItems.handleItemAddition(newItem);
                Storage.makeListFile(FILE_PATH, listOfItems);
                String comment = "Another moment to mark... \n" + newItem + "\n";
                String info = "You now have " + listOfItems.knowTaskCount() + " to do!\n";
                return comment + info + "\n";
            } else {
                throw new ArgumentNumberException();
            }
        }
    }

    public String parseListCmd(TaskList listOfItems){

        if(listOfItems.getListLength() != 0) {
            String comment = "These are the tasks on your list!\n";
            String list = listOfItems.toString();
            return comment + list + "\n";
        } else {
            return "Your pages are yet blank. But the White Book was not written in a day...";
        }
    }

    public String parseMarkCmd(TaskList listOfItems) throws InvalidItemException{
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

    public String parseSortCmd(TaskList listOfItems) {
        listOfItems.sortList();
        String comment = "Indeed I shall invoke the Eye of Agomotto to turn time ... \n";
        return comment + listOfItems;
    }

    public String parseDeleteCmd(TaskList listOfItems) {
        try {
            String taskNo = command.replaceAll("\\D+", "");
            int taskNoAsInt = Integer.parseInt(taskNo) - 1;
            String comment = "And so it must be...\nWe leave behind what we can not hold on to.\n" +
                    "I have removed this from your list:\n";
            String item = listOfItems.handleItem("DELETE", taskNoAsInt);
            Storage.makeListFile(FILE_PATH, listOfItems);
            return "You have only " + listOfItems.knowTaskCount() + "remaining";
        } catch (InvalidItemException e) {
            return e.getMessage();
        }
    }

    public String parseFind(TaskList listOfItems) throws ArgumentNumberException{
        try {
            String target = command.replaceAll("find ", "");
            if(!target.isEmpty()) {
            String list = listOfItems.findByKeyword(target);
            String comment = "Here is what you are looking for!";
            return comment + list + "\n";
            } else {
                throw new ArgumentNumberException();
            }
        } catch (InvalidItemException e) {
            return e.getMessage();
        }
    }

    public String parseHelpCmd(){
        String comment = "Aye, what good is a phantom were he not to share what he knows?\n";
        String helpList = "You may use the following commands and arguments: \n" +
                "`help` - view all commands and arguments\n" +
                "`hello` - learn my name and story\n" +
                "`list` - view the stored list\n" +
                "`todo [TASK_NAME]` - add a task as a todo item\n" +
                "`deadline [TASK_NAME]/[DATE] [TIME]` - add a task with a deadline with date in the " +
                "format of yyyy-MM-dd and time in the format HH:mm\n" +
                "`event [TASK_NAME]/[DATE] [START_TIME] [END_TIME]` - add an event with a date of occurance," +
                " start and end timings where date is formatted as yyyy-MM-dd and time as HH:mm\n" +
                "`mark [ITEM_ID]` - mark an item, as identified by its position on the list, as complete\n" +
                "`unmark [ITEM_ID]` - mark an item, as identified by its position on the list, as incomplete\n" +
                "`delete [ITEM_ID]` - delete an item, as identified by its position on the list, from the list\n" +
                "`find [KEYWORD]` - find items containing a keyword in their task descriptions" +
                "`sort` - sort all items on the list based on their deadlines and task descriptions\n" +
                "`bye` - end the conversation with me. This automatically saves the current state of the list.\n";
        return comment + helpList;
    }

    /**
     * Prints Duke's self-introduction and helps to customise its personality.
     */
    public String introduceDuke() {
        String intro = "I once wandered these halls, centuries ago. I am Duke Aemon of Old.\n";
        String quote1 = "Indeed, my memory is long when I am but a ghost of a memory myself...\n" +
                "But you are young blood. What brings you to these ancient halls?\n";

        String reply = "Welcome, my friend!\n" + intro + quote1;
        return reply;
    }





    /**
     * Reads the user command, initiates completion of the appropriate action and prints response
     */
    public String parseCommand() throws EmptyCommandException{

        String reply = "Hmm...LOOKS LIKE SOMETHING IS WRONG! Try Again!";

        try {
            if (command.isEmpty()) {
                parseEmptyString();
            } else {

                TaskList listOfItems = Storage.readFromFile(FILE_PATH);
                String[] parts = command.split(" ");
                String instruction = parts[0];


                if (instruction.equals(LIST_CMD)) {
                    reply = parseListCmd(listOfItems);
                } else if (instruction.equals(HELP_CMD)){
                    reply = parseHelpCmd();
                } else if (instruction.equals(HELLO_CMD)){
                    reply = introduceDuke();
                } else if (instruction.equals(MARK_CMD)) {
                    reply = parseMarkCmd(listOfItems);
                } else if (instruction.equals(UNMARK_CMD)) {
                    reply = parseUnmarkCmd(listOfItems);
                } else if (instruction.equals(DELETE_CMD)) {
                    reply = parseDeleteCmd(listOfItems);
                } else if (instruction.equals(FIND_CMD)) {
                    reply = parseFind(listOfItems);
                } else if (instruction.equals(TODO_CMD)) {
                    reply = parseTask(listOfItems);
                } else if (instruction.equals(DEADLINE_CMD)) {
                    reply = parseDeadline(listOfItems);
                } else if (instruction.equals(EVENT_CMD)) {
                    reply = parseEvent(listOfItems);
                } else if (instruction.equals(SORT_CMD)) {
                    reply = parseSortCmd(listOfItems);
                } else {
                    String comment = "Why trouble me with the unrefined language of the youth! " +
                            "Speak plainly, my friend!";
                    reply = comment + "\n";
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
            reply = "Aemon went back to his crypt for a nap.\n" +
                    "Please restart the program after deleteing the DukeList.txt file.";
        }
        finally {
            return reply;
        }
    }
}