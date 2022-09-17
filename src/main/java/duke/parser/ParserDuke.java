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

    public String parseUnmarkCmd(TaskList listOfItems) throws InvalidItemException{
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
     * Reads the user command, initiates completion of the appropriate action and prints response
     */
    public String parseCommand() throws EmptyCommandException{

        String reply = "Hmm...LOOKS LIKE SOMETHING IS WRONG! Try Again!";

        try {
            if (command.isEmpty()) {
                parseEmptyString();
            } else {
                TaskList listOfItems = Storage.readFromFile(FILE_PATH);
                String comment = "Here is what I think...";
                String taskNo = "-1";
                int taskNoAsInt = -1;
                String item = "Nothing here yet!";
                String[] parts = command.split(" ");
                String instruction = parts[0];


                if (instruction.equals(LIST_CMD)) {
                    reply = parseListCmd(listOfItems);
                } else if (instruction.equals(MARK_CMD)) {
                    reply = parseMarkCmd(listOfItems);
                } else if (instruction.equals(UNMARK_CMD)) {
                    reply = parseUnmarkCmd(listOfItems);
                } else if (instruction.equals(DELETE_CMD)) {
                    taskNo = command.replaceAll("\\D+", "");
                    taskNoAsInt = Integer.parseInt(taskNo) - 1;
                    comment = "And so it must be. We leave behind what we can not hold on to." +
                            "\nI have removed this from your list:";
                    item = listOfItems.handleItem("DELETE", taskNoAsInt);
                    reply = "You have only " + listOfItems.knowTaskCount() + "remaining";
                    Storage.makeListFile(FILE_PATH, listOfItems);

                } else if (instruction.equals(FIND_CMD)) {
                    String target = command.replaceAll("find ", "");
                    String list = listOfItems.findByKeyword(target);
                    comment = "Here is what you are looking for!";
                    reply = comment + list + "\n";

                } else if (instruction.equals(TODO_CMD)) {
                    reply = parseTask(listOfItems);
                } else if (instruction.equals(DEADLINE_CMD)) {
                    reply = parseDeadline(listOfItems);
                } else if (instruction.equals(EVENT_CMD)) {
                    reply = parseEvent(listOfItems);
                } else if (instruction.equals(SORT_CMD)) {
                    listOfItems.sortList();
                    comment = "Indeed I shall invoke the Eye of Agomotto to turn time ... \n";
                    reply = comment + listOfItems;

                } else {
                    comment = "Why trouble me with the unrefined language of the youth! Speak plainly, my friend!";
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
        } catch (InvalidItemException e) {
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