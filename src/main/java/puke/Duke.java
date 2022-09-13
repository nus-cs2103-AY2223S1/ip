package puke;

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * Main class
 */
public class Duke {


    protected String getResponse(String input) {
        try {
            return "Puke says: " + puke(d, input);
        } catch (DukeException e) {
            return e.toString();
        } catch (NoSuchElementException e) {
            return "Puke says: Please concentrate and ask again";
        } catch (IndexOutOfBoundsException e) {
            return "Puke says: How many tasks do you think you have? \nPlease think carefully and ask again";
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
        assert !(a.isEmpty()) : "input should not be empty!";
        if (a.equals("bye")) {
            hasended = true;
            return d.ui.systemMessage(1,d, new ToDo(""));
        }
        if (a.equals("list")) {
            return d.tasklist.listTasks();
        }
        if (a.equals("help")) {
            return d.ui.intro();
        }
        String s = sc.nextLine();

        if (s.isEmpty()) {
            return "I dont know what happened!";
        }

        if (a.equals("mark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            return d.ui.taskManager("do", pos, d);
        } else if (a.equals("unmark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            return d.ui.taskManager("undo", pos, d);
        } else if (a.equals("todo")) {
            String desc = Duke.d.p.getMessage(s, "ToDo");
            Task newTask = new ToDo(desc);
            d.tasklist.addIncrement(newTask);
            d.storage.saveTasks(Duke.d.tasklist.tasks);
            return d.ui.systemMessage(2, d, newTask);
        } else if (a.equals("deadline")) {
            String desc = Duke.d.p.getMessage(s, "Deadline");
            String date = Duke.d.p.getDate(s);
            System.out.println(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task newTask = new Deadline(desc, LocalDateTime.parse(date, formatter));
            d.tasklist.addIncrement(newTask);
            d.storage.saveTasks(Duke.d.tasklist.tasks);
            return d.ui.systemMessage(2, d, newTask);
        } else if (a.equals("event")) {
            String desc = d.p.getMessage(s, "Event");
            String date = d.p.getDate(s);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task newTask = new Event(desc, LocalDateTime.parse(date, formatter));
            d.tasklist.addIncrement(newTask);
            d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(2, d, newTask);
        } else if (a.equals("delete")) {
            int pos = Character.getNumericValue(s.charAt(1));
            Task temp = Duke.d.tasklist.tasks.get(pos - 1);
            d.tasklist.delete(pos - 1);
            d.storage.saveTasks(Duke.d.tasklist.tasks);
            return d.ui.systemMessage(3, d, temp);
        } else if (a.equals("find")) {
            String temp = d.p.getFindKeyword(s);
            return d.tasklist.find(temp);

        } else {
            return "OOPS!!! I'm sorry, but I dont't know what that means\n";
        }
    }
}
