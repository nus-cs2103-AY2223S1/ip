package duke;

import java.io.IOException;

/**
 * Main class
 * @author Ashiqur Rahman A0230107Y
 */
public class Duke {
    private String filePath = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor that creates Duke object
     */
    public Duke() {
        try {
            ui = new Ui();
            ui.greeting();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException, DukeException {
        Parser.run(tasks);
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }

    /**
     * Method that returns the response of different commands
     * @param command command that is asked by the user
     */
    public String getResponse(String command) throws IOException {
        if ("hi".equals(command) || "hello".equals(command)) {
            return ui.greeting();
        } else if ("bye".equals(command)) {
            return Parser.echo("Aww going so soon :(( \nHope to see you again soon!");
        } else if ("list".equals(command)) {
            return Parser.echo(TaskList.printTaskList(tasks));
        } else if (command.contains("unmark")) { // to detect unmark command
            return getReplyForMarkAndUnmark(command);
        } else if (command.contains("mark")){ // to detect mark command
            return getReplyForMarkAndUnmark(command);
        } else if (command.contains("todo")) {
            return getReplyForTodo(command);
        } else if (command.contains("deadline")) {
            return getReplyForDeadline(command);
        } else if (command.contains("event")) {
            return getReplyForEvent(command);
        } else if (command.contains("delete")) {
            return getReplyForDelete(command);
        } else if(command.contains("find")) {
            return getReplyForFind(command);
        } else {
            return Parser.echo(DukeException.taskErrorMessage());
        }
    }

    /**
     * Method to get response for Find
     * @param command full command given by the user
     */
    private String getReplyForFind(String command) {
        String findWord = command.replace("find ", "");
        if (findWord.equals(command) || "".equals(findWord)) {
            String error = DukeException.taskErrorMessage(command);
            return Parser.echo(error);
        } else {
            TaskList keywordList = tasks.find(findWord);
            String stringList = TaskList.printTaskList(keywordList);
            return Parser.echo("Found these tasks in your list:\n" + stringList);
        }
    }

    /**
     * Method to get response for Delete
     * @param command full command given by the user
     */
    private String getReplyForDelete(String command) throws IOException {
        String deleteTaskNumber = command.replace("delete ", "");
        if (deleteTaskNumber.equals(command) || "".equals(deleteTaskNumber)) {
            String error = DukeException.taskErrorMessage(command);
            return Parser.echo(error);
        }
        if (command.contains("all")) {
            String massTaskStatus = getMassTaskStatus(command);
            return massTaskStatus;
        } else {
            int n = getIndexInTaskList(deleteTaskNumber);
            if (n > tasks.size() - 1){
                return Parser.echo(DukeException.IndexOutofBoundsException(tasks));
            }
            Task deletedTask = tasks.remove(n);
            String taskStatus = String.format("One less task. I've removed this task:\n" +
                    "%s\n" +
                    "Now you have %d tasks in the list.You got this!", deletedTask, tasks.size());
            Parser.writeToFile(tasks);
            return Parser.echo(taskStatus);
        }
    }

    /**
     * Method to get response for Event
     * @param command full command given by the user
     */
    private String getReplyForEvent(String command) throws IOException {
        String eventTask = command.replace("event ", "");
        if (eventTask.equals(command) || "".equals(eventTask)) {
            String error = DukeException.taskErrorMessage(command);
            return Parser.echo(error);
        } else {
            String[] parts = eventTask.split(" /",2);
            Task newTask = new Event(parts[0], parts[1]);
            tasks.add(newTask);
            String taskStatus = String.format("Oki, niceee. I've added this task:\n" +
                    "%s\n" +
                    "Now you have %d tasks in the list.", newTask, tasks.size());
            Parser.writeToFile(tasks);
            return Parser.echo(taskStatus);
        }
    }

    /**
     * Method to get response for Deadline
     * @param command full command given by the user
     */
    private String getReplyForDeadline(String command) throws IOException {
        String deadlineTask = command.replace("deadline ", "");
        if (deadlineTask.equals(command) || "".equals(deadlineTask)) {
            String error = DukeException.taskErrorMessage(command);
            return Parser.echo(error);
        } else {
            String[] parts = deadlineTask.split(" /by ", 2);
            Deadline newTask = new Deadline(parts[0], parts[1]);
            tasks.add(newTask);
            String taskStatus = String.format("Oki, niceee. I've added this task:\n" +
                    "%s\n" +
                    "Now you have %d tasks in the list.", newTask, tasks.size());
            Parser.writeToFile(tasks);
            return Parser.echo(taskStatus);
        }
    }

    /**
     * Method to get response for Todo
     * @param command full command given by the user
     */
    private String getReplyForTodo(String command) throws IOException {
        String todoTask = command.replace("todo ", "");
        if (todoTask.equals(command) || "".equals(todoTask)) {
            String error = DukeException.taskErrorMessage(command);
            return Parser.echo(error);
        }
        Task newTask = new Todo(todoTask);
        tasks.add(newTask);
        String taskStatus = String.format("Oki, nicee. I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.", newTask, tasks.size());
        Parser.writeToFile(tasks);
        return Parser.echo(taskStatus);
    }

    /**
     * Method to get response for Unmark and Mark
     * @param command full command given by the user
     */
    private String getReplyForMarkAndUnmark(String command) throws IOException {
        if (command.contains("all")) {
            String massTaskStatus = getMassTaskStatus(command);
            return massTaskStatus;
        } else {
            int n = getIndexInTaskList(command);
            if (n > tasks.size()){
                return Parser.echo(DukeException.IndexOutofBoundsException(tasks));
            }
            String taskStatus = getTaskStatus(n, command);
            return Parser.echo(taskStatus);
        }
    }

    private String getMassTaskStatus(String command) throws IOException {
        String taskStatus = "";
        if (command.contains("unmark")) {
            for(int i = 0; i < tasks.size(); i++) {
                tasks.get(i).markAsUndone();
            }
            Parser.writeToFile(tasks);
            taskStatus = String.format("Awww, I've marked all %d task(s) as not done yet.\n", tasks.size());
        } else if (command.contains("mark")) {
            for(int i = 0; i < tasks.size(); i++) {
                tasks.get(i).markAsDone();
            }
            Parser.writeToFile(tasks);
            taskStatus = String.format("You are really amazing!! I've marked all %d task(s) as done.\n", tasks.size());
        } else if (command.contains("delete")) {
            int initialTaskSize = tasks.size();
            tasks.clear();
            Parser.writeToFile(tasks);
            taskStatus = String.format("The tasks were boring anyway, I've removed all %d task(s)\n" +
                    "Now you have 0 task in the list.\n", initialTaskSize);
        }
        return Parser.echo(taskStatus);
    }
    /**
     * Method to find index in the Tasklist from the command
     * @param command full command given by the user
     */
    private int getIndexInTaskList(String command) {
        String number = command.replaceAll("[^\\d.]", "");
        int n = Integer.parseInt(number);
        return n-1;
    }

    /**
     * Method to get task status message for Marking and Unmarking of Task
     * @param command full command given by the user
     */
    private String getTaskStatus(int n, String command) throws IOException {
        String taskStatus;
        Task commandedTask = tasks.get(n);
        if (command.contains("unmark")) {
            commandedTask.markAsUndone();
            taskStatus = String.format("Aww, I've marked this task as not done yet:\n%s", commandedTask);
        } else {
            commandedTask.markAsDone();
            taskStatus = String.format("Nice! I've marked this task as done:\n%s", commandedTask);
        }
        Parser.writeToFile(tasks);
        return taskStatus;
    }
}
