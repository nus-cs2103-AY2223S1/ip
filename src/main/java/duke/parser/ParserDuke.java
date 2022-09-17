package duke.parser;

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

    private static final String BREAK_LINE = "\n";
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
    public String parseEmptyString() {
        String reply = "The folly of youth to speak with no words! Speak again, my friend!";
        System.out.println(reply);
        return reply;
    }

    /**
     * Reads the user command, initiates completion of the appropriate action and prints response
     */
    public String parseCommand() {

        if (command.isEmpty()) {
            String reply = parseEmptyString();
            return reply;

        } else {

            TaskList listOfItems = Storage.readFromFile(FILE_PATH);
            String reply = "Hmm...LOOKS LIKE SOMETHING IS WRONG! Try Again!";
            String comment = "Here is what I think...";
            String taskNo = "-1";
            int taskNoAsInt = -1;
            String item = "Nothing here yet!";
            String[] parts = command.split(" ");
            String instruction = parts[0];


            if (instruction.equals(LIST_CMD)) {
                comment = "These are the tasks on your list!";
                String list = listOfItems.toString();
                reply = comment + list + BREAK_LINE;
                return reply;
            } else if (instruction.equals(MARK_CMD)) {
                taskNo = command.replaceAll("\\D+", "");
                taskNoAsInt = Integer.parseInt(taskNo) - 1;
                Storage.makeListFile(FILE_PATH, listOfItems);
                comment = "Very well! One less burden to bear! I have marked this complete:";
                item = listOfItems.handleItem("MARK", taskNoAsInt);
                reply = comment + "\n" + item;
            } else if (instruction.equals(UNMARK_CMD)) {
                taskNo = command.replaceAll("\\D+", "");
                taskNoAsInt = Integer.parseInt(taskNo) - 1;
                Storage.makeListFile(FILE_PATH, listOfItems);
                comment = "Hmm....I have marked this incomplete:";
                item = listOfItems.handleItem("UNMARK", taskNoAsInt);
                reply = comment + "\n" + item;
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
                reply = comment + list + BREAK_LINE;

            } else if (instruction.equals(TODO_CMD)) {
                String todo = command.replaceAll("todo ", "");
                if (todo.isEmpty()) {
                    comment = "The folly of youth to want to do nothing! Write your task following 'todo'";
                    reply = comment + BREAK_LINE;
                } else {
                    ListObject newItem = new ToDo(todo, 0);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    comment = "'Tis a new sky for you to scale! Here! \n" + newItem;
                    String info = "\nYou now have " + listOfItems.knowTaskCount() + " to do!";
                    reply = comment + info + BREAK_LINE;
                }
            } else if (instruction.equals(DEADLINE_CMD)) {
                String deadline1 = command.replaceAll("deadline ", "");
                String[] words = deadline1.split("/");
                item = words[0];
                String deadline = words[1];
                if (!item.isEmpty()) {
                    ListObject newItem = new Deadline(item, 0, deadline);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    comment = "Mark this on your calendar! \n" + newItem;
                    String info = "\nYou now have " + listOfItems.knowTaskCount() + " tasks to do!";
                    reply = comment + info + BREAK_LINE;

                } else {
                    reply = "The folly of youth to cheat Time! Write your task following 'deadline'"
                            + BREAK_LINE;
                }
            } else if (instruction.equals(EVENT_CMD)) {
                String event1 = command.replaceAll("event ", "");
                String[] words2 = event1.split("/");
                item = words2[0];
                String event = words2[1];

                if (!item.isEmpty()) {
                    ListObject newItem = new Event(item, 0, event);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    comment = "Another moment to mark... \n" + newItem;
                    String info = "\nYou now have " + listOfItems.knowTaskCount() + " tasks to do!";
                    reply = comment + info + BREAK_LINE;

                } else {
                    comment = "Come Alive! Write an activity following 'event'";
                    reply = comment + BREAK_LINE;

                }

            } else if (instruction.equals(SORT_CMD)) {
                listOfItems.sortList();
                comment = "Indeed I shall invoke the Eye of Agomotto to turn time ... \n";
                reply = comment + listOfItems;

            } else {
                comment = "Why trouble me with the unrefined language of the youth! Speak plainly, my friend!";
                reply = comment + BREAK_LINE;
            }

            return reply;

        }
    }
}