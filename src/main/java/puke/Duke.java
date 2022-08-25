package puke;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * Main class
 */
public class Duke {

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

    private Scanner receiver = new Scanner(System.in);
    protected static Duke d = new Duke();

    /**
     * Creates the chatbot
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasklist = new TaskList(storage.load());
        this.p = new Parser();
    }

    
    public static void puke(Scanner sc, Duke d) throws DukeException {
        String a = sc.next();
        if (a.equals("bye")) {
            Duke.d.ui.systemMessage(1,d, new ToDo(""));
            ended = true;
            return;
        }
        if (a.equals("list")) {
            Duke.d.tasklist.listTasks();
            puke(sc,d);
        }
        String s = sc.nextLine();

        if (a.equals("mark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            Duke.d.ui.taskManager("do", pos, d);
            puke(sc,d);
        } else if (a.equals("unmark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            Duke.d.ui.taskManager("undo", pos, d);
            puke(sc,d);
        } else if (a.equals("todo")) {
            String desc = Duke.d.p.getMessage(s, "ToDo");
            Task newTask = new ToDo(desc);
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.ui.systemMessage(2, d, newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            puke(sc,d);
        } else if (a.equals("deadline")) {
            String desc = Duke.d.p.getMessage(s, "Deadline");
            String date = Duke.d.p.getDate(s);
            Task newTask = new Deadline(desc, LocalDate.parse(date));
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.ui.systemMessage(2, d, newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            puke(sc,d);
        } else if (a.equals("event")) {
            String desc = Duke.d.p.getMessage(s, "Event");
            String date = Duke.d.p.getDate(s);
            Task newTask = new Event(desc, LocalDate.parse(date));
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.ui.systemMessage(2, d, newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            puke(sc,d);
        } else if (a.equals("delete")) {
            int pos = Character.getNumericValue(s.charAt(1));
            Task temp = Duke.d.tasklist.tasks.get(pos - 1);
            Duke.d.tasklist.delete(pos - 1);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            Duke.d.ui.systemMessage(3, d, temp);
        } else if (a.equals("find")) {
            String temp = d.p.getFindTask(s);
            Duke.d.tasklist.find(temp);

        } else {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "OOPS!!! I'm sorry, but I dont't know what that means\n" +
                    "    ____________________________________________________________");
        }
    }

    private static boolean ended = false;
    public static void startBot() {
        try {
            puke(Duke.d.receiver, d);
        } catch (DukeException e) {
            System.out.println(e);
        } catch (StackOverflowError e) {
            System.out.println("goodbye");
        } finally {
            if (ended) {
                return;
            } else {
                startBot();
            }
        }
    }

    /**
     * Entry point to application
     * @param args
     */
    public static void main(String[] args) {
        Duke.d.ui.intro();
        Duke.startBot();
        Duke.d.receiver.close();
    }
}
