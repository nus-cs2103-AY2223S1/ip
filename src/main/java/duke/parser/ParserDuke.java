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

    private String command;

    private final String LIST_CMD = "list";
    private final String MARK_CMD = "mark ";
    private final String UNMARK_CMD = "unmark ";
    private final String DELETE_CMD = "delete ";
    private final String TODO_CMD = "todo ";
    private final String EVENT_CMD = "event ";
    private final String DEADLINE_CMD = "deadline ";
    private final String FIND_CMD = "find ";
    private final String FILE_PATH = "src/main/java/duke/DukeTasks.txt";

    /**
     * Constructs a ParserDuke object with given command
     * @param command String representing user command
     */
    public ParserDuke(String command) {
        this.command = command;
    }

    /**
     * Prints response for empty user command
     */
    public void respondToEmptyString() {
        System.out.println("The folly of youth to speak with no words! Speak again, my friend!");
    }


    /**
     * Reads the user command, initiates completion of the appropriate action and prints response
     */
    public void parseCommand() {
        if (command.isEmpty()) {
            respondToEmptyString();
        } else {
            TaskList listOfItems = Storage.readFromFile(FILE_PATH);

            if (command.equals(LIST_CMD)) {
                System.out.println("These are the tasks on your list!");
                listOfItems.printList();
                System.out.println("\n***********************************************************************");
            } else if (command.contains(MARK_CMD)) {
                String taskNo = command.replaceAll("\\D+", "");
                int taskNoAsInt = Integer.parseInt(taskNo)-1;
                Storage.makeListFile(FILE_PATH, listOfItems);

                if (!command.contains(UNMARK_CMD)) {
                    System.out.println("Very well! One less burden to bear! I have marked this complete:");
                    listOfItems.handleItem("UNMARK", taskNoAsInt);
                    System.out.println("\n***********************************************************************");
                } else {
                    System.out.println("Hmm....I have marked this incomplete:");
                    listOfItems.handleItem("MARK", taskNoAsInt);
                    System.out.println("\n***********************************************************************");
                }
                Storage.makeListFile(FILE_PATH, listOfItems);
            } else if (command.contains(DELETE_CMD)) {
                String taskNo = command.replaceAll("\\D+", "");
                int taskNoAsInt = Integer.parseInt(taskNo)-1;
                System.out.println("And so it must be. We leave behind what we can not hold on to.\nI have removed this from your list:");
                listOfItems.handleItem("DELETE", taskNoAsInt);
                System.out.println("You have only " +  listOfItems.knowTaskCount()+ "remaining");
                Storage.makeListFile(FILE_PATH, listOfItems);
            } else if (command.contains(TODO_CMD)) {
                String todo = command.replaceAll("todo ", "");
                if (todo.isEmpty()) {
                    System.out.println("The folly of youth to want to do nothing! Write your task following 'todo'");
                    System.out.println("***********************************************************************");
                } else {
                    ListObject newItem = new ToDo(todo, 0);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    System.out.println("'Tis a new sky for you to scale! Here! \n" + newItem.toString()
                            + "\nYou now have " + listOfItems.knowTaskCount() + " tasks to do!"
                            + "\n***********************************************************************");
                }
            } else if (command.contains(DEADLINE_CMD)) {
                String deadline1 = command.replaceAll("deadline ", "");
                String[] words = deadline1.split("/");
                String task = words[0];
                String deadline = words[1];
                if (!task.isEmpty()) {
                    ListObject newItem = new Deadline(task, 0, deadline);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    System.out.println("Mark this on your calendar! \n" + newItem.toString()
                            + "\nYou now have " + listOfItems.knowTaskCount() + " tasks to do!"
                            + "\n***********************************************************************");
                } else if (task.isEmpty()) {
                    System.out.println("The folly of youth to cheat Time! Write your task following 'deadline'");
                    System.out.println("***********************************************************************");
                }
            } else if (command.contains(EVENT_CMD)) {
                String event1 = command.replaceAll("event ", "");
                String[] words = event1.split("/");
                String task = words[0];
                String event = words[1];
                if(!task.isEmpty()) {
                    ListObject newItem = new Event(task, 0, event);
                    listOfItems.handleItemAddition(newItem);
                    Storage.makeListFile(FILE_PATH, listOfItems);
                    System.out.println("Another moment to mark... \n" + newItem.toString()
                            + "\nYou now have " + listOfItems.knowTaskCount() + " tasks to do!"
                            + "\n***********************************************************************");
                } else if (task.isEmpty()) {
                    System.out.println("Come Alive! Write an activity following 'event'");
                    System.out.println("***********************************************************************");
                }
            } else if (command.contains(FIND_CMD)) {
                String target = command.replaceAll("find ", "");
                System.out.println("Here is what you are looking for!");

            } else {
                System.out.println("Why trouble me with the unrefined language of the youth! Speak plainly, my friend!");
                System.out.println("***********************************************************************");
            }
        }
    }
}