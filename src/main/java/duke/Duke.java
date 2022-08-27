package duke;

import java.util.List;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * An interactive chatbot.
 *
 * @author Lan Jingbo, Jerry
 */
public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    static String PATH = "./src/main/files/taskset.txt";

    /**
     * The constructor for duke.
     * @param filePath the path of the .txt file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.extractFile());
        } catch (WrongMessageException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }

    /**
     * mark the targeted task to "complete".
     *
     * @param target the index of task
     * @throws WrongMessageException potential exception
     */
    public void mark(int target) throws WrongMessageException {
        Task willMark = tasks.getTask(target - 1);
        willMark.donelah();
        System.out.println("Congratulations! you complete this task:\n"
                + willMark.toString());
    }

    /**
     * mark the targeted task to "incomplete".
     *
     * @param target the index of task
     * @throws WrongMessageException potential exception
     */
    public void unmark(int target) throws WrongMessageException {
        Task willUnmark = tasks.getTask(target - 1);
        willUnmark.nodone();
        System.out.println("You undone this task:\n"
                + willUnmark.toString());
    }

    /**
     * show the list of task.
     *
     * @throws WrongMessageException potential exception
     */
    public void showList() throws WrongMessageException {
        System.out.println("Your list is as following");
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task temp = tasks.getTask(i);
            System.out.println((i) + "." + temp.toString());
        }
    }

    /**
     * delete the targeted task.
     *
     * @param str the index of task
     * @throws WrongMessageException potential exception
     */
    public void delete(String str) throws WrongMessageException {
        String[] temp = str.split(" ");
        int key3 = Integer.decode(temp[1]);
        System.out.println("ok I will delete the task " + tasks.deleteTask(key3 - 1) + " right now!");
        System.out.println("now you have " + tasks.getSize() + " tasks in the list");
    }

    /**
     * return the list of task on given date.
     *
     * @param localDate the given date
     */
    public void getOnDate(LocalDate localDate) {
        List<Task> shortList = tasks.getTaskList().stream().filter(task -> task.isOnDate(localDate))
                .collect(Collectors.toList());
        int i = 0;
        System.out.println("Hey, these are what you need to do on this date: "
                + localDate.format(DateTimeFormatter.ofPattern("MMMM d yyyy")));
        for (Task t : shortList) {
            System.out.println((i + 1) + "." + t);
            i++;
        }
    }

    /**
     * return the list of tasks containing given String
     *
     * @param require the given string
     */
    public void search(String require) {
        List<Task> shortList = tasks.getTaskList().stream()
                .filter(task -> task.hasThis(require))
                .collect(Collectors.toList());
        int i = 0;
        if (shortList.size() > 0) {
            System.out.println("search result of " + require + " are here: ");
            for (Task t : shortList) {
                System.out.println((i + 1) + "." + t);
                i++;
            }
        } else {
            System.out.println("Sorry, We cannot find those for you");
        }
    }

    /**
     * Run the chatbot.
     */
    public void run() {
        ui.greet();

        while (true) {
            String str = ui.requirement();
            try {
                if (str.startsWith("todo")) {
                    tasks.addTask(Parser.todo(str));
                    System.out.println("Now you have " + tasks.getSize() + " task(s)");
                } else if (str.startsWith("deadline")) {
                    tasks.addTask(Parser.deadline(str));
                    System.out.println("Now you have " + tasks.getSize() + " task(s)");
                } else if (str.startsWith("event")) {
                    tasks.addTask(Parser.event(str));
                    System.out.println("Now you have " + tasks.getSize() + " task(s)");
                } else if (str.startsWith("mark")) {
                    String[] temp = str.split(" ");
                    int key = Parser.parseInteger(temp[1]);
                    mark(key);
                } else if (str.startsWith("unmark")) {
                    String[] temp = str.split(" ");
                    int key = Parser.parseInteger(temp[1]);
                    unmark(key);
                } else if (str.startsWith("list")) {
                    showList();
                } else if (str.startsWith("bye")) {
                    System.out.println("Bye! Hope to see you again soon!");
                    break;
                } else if (str.startsWith("delete")) {
                    delete(str);
                } else if (str.startsWith("on")) {
                    String[] temp = str.split(" ");
                    String date = temp[1].trim();
                    LocalDate lc = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    getOnDate(lc);
                } else if (str.startsWith("search")) {
                    search(Parser.convertInfo(str));
                } else {
                    throw new CannotUnderstandException();
                }
                // Handling exceptions
            } catch (WrongMessageException | CannotUnderstandException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            storage.saveFile(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * main function for running.
     * @param args nothing here
     */
    public static void main(String[] args) {
        new Duke(PATH).run();
    }
}