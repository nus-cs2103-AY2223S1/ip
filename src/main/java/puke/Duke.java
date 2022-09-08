package puke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * Main class
 */
public class Duke {

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            return "Puke says: " + puke(Duke.d, input);
        } catch (DukeException e) {
            return e.toString();
        }
    }


    /**
     * storage field that handles logic to deal with hard disk
     */
    protected Storage storage;
    /**
     * taskList that stores details of the list status
     */
    protected TaskList tasklist;
    /**
     * Handles the UI interaction with user
     */
    protected Ui ui;
    /**
     * Handles user input
     */
    private Parser p;

    protected static Duke d = new Duke();

    /**
     * tracks the status of Duke programme
     */
    private static boolean hasended = false;

    /**
     * Creates the chatbot
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasklist = new TaskList(storage.load());
        this.p = new Parser();
    }

    
    public static String puke(Duke d,String input) throws DukeException {
        Scanner sc = new Scanner(input);
        String a = sc.next();
        if (a.equals("bye")) {
            hasended = true;
            return Duke.d.ui.systemMessage(1,d, new ToDo(""));
        }
        if (a.equals("list")) {
            return Duke.d.tasklist.listTasks();
        }
        String s = sc.nextLine();

        if (a.equals("mark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            return Duke.d.ui.taskManager("do", pos, d);
        } else if (a.equals("unmark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            return Duke.d.ui.taskManager("undo", pos, d);
        } else if (a.equals("todo")) {
            String desc = Duke.d.p.getMessage(s, "ToDo");
            Task newTask = new ToDo(desc);
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(2, d, newTask);
        } else if (a.equals("deadline")) {
            String desc = Duke.d.p.getMessage(s, "Deadline");
            String date = Duke.d.p.getDate(s);
            System.out.println(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task newTask = new Deadline(desc, LocalDateTime.parse(date, formatter));
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(2, d, newTask);
        } else if (a.equals("event")) {
            String desc = Duke.d.p.getMessage(s, "Event");
            String date = Duke.d.p.getDate(s);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task newTask = new Event(desc, LocalDateTime.parse(date, formatter));
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(2, d, newTask);
        } else if (a.equals("delete")) {
            int pos = Character.getNumericValue(s.charAt(1));
            Task temp = Duke.d.tasklist.tasks.get(pos - 1);
            Duke.d.tasklist.delete(pos - 1);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(3, d, temp);
        } else if (a.equals("find")) {
            String temp = d.p.getFindKeyword(s);
            return Duke.d.tasklist.find(temp);

        } else {
            throw new DukeException(
                    "OOPS!!! I'm sorry, but I dont't know what that means\n"
                    );
        }
    }
}
