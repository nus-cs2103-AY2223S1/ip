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
    private static Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * The constructor for duke.
     *
     * @param filePath the path of the .txt file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.extractFile());
            assert tasks.getSize() >= 0 : "Errors happened with your file.";
        } catch (WrongMessageException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }

    public String todo(String require) throws WrongMessageException {
        try {
            String content = require.substring(4).trim();
            if (content.equals("")) {
                throw new WrongMessageException();
            }
            Todo todo = new Todo(content);
            tasks.addTask(todo);
            int size = tasks.getSize();
            storage.saveFile(tasks);
            return "add task " + todo.toString() + "\n"
                    + "now you have " + size + " tasks";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String deadline(String require) throws WrongMessageException {
        try {
            String info = require.substring(8).trim();
            String ddlDate = require.split("/by")[1].trim();
            String content = info.split("/by")[0].trim();
            if (content.equals("") || info.startsWith("/by")) {
                throw new WrongMessageException();
            }
            Deadline ddl = new Deadline(content, ddlDate);
            tasks.addTask(ddl);
            int size = tasks.getSize();
            storage.saveFile(tasks);
            return "add task " + ddl.toString() + "\n"
                    + "now you have " + size + " tasks";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String event(String require) throws WrongMessageException {
        try {
            String info = require.substring(5).trim();
            String happenTime = require.split("/at")[1].trim();
            String content = info.split("/at")[0].trim();
            if (content.equals("") || info.startsWith("/at")) {
                throw new WrongMessageException();
            }
            Event evt = new Event(content, happenTime);
            tasks.addTask(evt);
            int size = tasks.getSize();
            storage.saveFile(tasks);
            return "add task " + evt.toString() + "\n"
                    + "now you have " + size + " tasks";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * mark the targeted task to "complete".
     *
     * @param target the index of task
     * @throws WrongMessageException potential exception
     */
    public String mark(int target) throws WrongMessageException, IOException {
        Task willMark = tasks.getTask(target - 1);
        willMark.donelah();
        storage.saveFile(tasks);
        return ("Congratulations! you complete this task:\n"
                + willMark.toString());
    }

    /**
     * mark the targeted task to "incomplete".
     *
     * @param target the index of task
     * @throws WrongMessageException potential exception
     */
    public String unmark(int target) throws WrongMessageException, IOException {
        Task willUnmark = tasks.getTask(target - 1);
        willUnmark.nodone();
        storage.saveFile(tasks);
        return ("You undone this task:\n"
                + willUnmark.toString());
    }

    /**
     * show the list of task.
     *
     * @throws WrongMessageException potential exception
     */
    public String showList() throws WrongMessageException {
        String s = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task temp = tasks.getTask(i);
            s += (i) + "." + temp.toString() + "\n";
        }
        return s;
    }

    /**
     * delete the targeted task.
     *
     * @param str the index of task
     * @throws WrongMessageException potential exception
     */
    public String delete(String str) throws WrongMessageException, IOException {
        String[] temp = str.split(" ");
        String s = "";
        int key3 = Integer.decode(temp[1]);
        s += ("ok I will delete the task " + tasks.deleteTask(key3) + " right now!") + "\n"
                + "now you have " + tasks.getSize() + " tasks in the list";
        storage.saveFile(tasks);
        return s;
    }

    /**
     * return the list of task on given date.
     *
     * @param localDate the given date
     */
    public String getOnDate(LocalDate localDate) {
        String s = "";
        List<Task> shortList = tasks.getTaskList().stream().filter(task -> task.isOnDate(localDate))
                .collect(Collectors.toList());
        int i = 0;
        for (Task t : shortList) {
            s += ((i + 1) + "." + t) + "\n";
            i++;
        }
        return ("Hey, these are what you need to do on this date: "
                + localDate.format(DateTimeFormatter.ofPattern("MMMM d yyyy"))) + "\n"
                + s;
    }

    /**
     * return the list of tasks containing given String.
     *
     * @param require the given string
     */
    public String search(String require) {
        String s = "";
        List<Task> shortList = tasks.getTaskList().stream()
                .filter(task -> task.hasThis(require))
                .collect(Collectors.toList());
        int i = 0;
        if (shortList.size() > 0) {
            for (Task t : shortList) {
                s += ((i + 1) + "." + t) + "\n";
                i++;
            }
            return ("search result of " + require + " are here: ") + "\n"
                    + s;
        } else {
            return ("Sorry, We cannot find those for you");
        }
    }

    public String bye() {
        return "Bye! Hope to see you again soon!";
    }

    public String hi() {
        return ui.greet();
    }

    /**
     * Run the chatbot, Life is good.
     */
    /*public void run() {

        ui.greet();
        String s;

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
    }*/
}