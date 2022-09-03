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
        ui = new Ui();
        ui.greeting();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException, DukeException {
        Parser.run(tasks);
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String command) throws IOException {;
        if ("bye".equals(command)) {
            return Parser.echo("Bye. Hope to see you again soon!");
        } else if ("list".equals(command)) {
            return Parser.echo(TaskList.printTaskList(tasks));
        } else if (command.contains("unmark")) { // to detect unmark command
            String number = command.replaceAll("[^\\d.]", "");
            int n = Integer.parseInt(number);
            if (n > tasks.size()){
                return Parser.echo(DukeException.IndexOutofBoundsException(tasks));
            } else {
                Task unmarkedTask = tasks.get(n-1);
                unmarkedTask.markAsUndone();
                String taskStatus = String.format("OK, I've marked this task as not done yet:\n%s", unmarkedTask);
                Parser.writeToFile(tasks);
                return Parser.echo(taskStatus);
            }
        } else if (command.contains("mark")){ // to detect mark command
            String number = command.replaceAll("[^\\d.]", "");
            int n = Integer.parseInt(number);
            if (n > tasks.size()){
                return Parser.echo(DukeException.IndexOutofBoundsException(tasks));
            } else {
                Task markedTask = tasks.get(n-1);
                markedTask.markAsDone();
                String taskStatus = String.format("Nice! I've marked this task as done:\n%s", markedTask);
                Parser.writeToFile(tasks);
                return Parser.echo(taskStatus);
            }
        } else if (command.contains("todo")) {
            String todoTask = command.replace("todo ", "");
            if (todoTask.equals(command) || "".equals(todoTask)) {
                String error = DukeException.taskErrorMessage(command);
                return Parser.echo(error);
            } else {
                Task newTask = new Todo(todoTask);
                tasks.add(newTask);
                String taskStatus = String.format("Got it. I've added this task:\n" +
                        "%s\n" +
                        "Now you have %d tasks in the list.", newTask, tasks.size());
                Parser.writeToFile(tasks);
                return Parser.echo(taskStatus);
            }

        } else if (command.contains("deadline")) {
            String deadlineTask = command.replace("deadline ", "");
            if (deadlineTask.equals(command) || "".equals(deadlineTask)) {
                String error = DukeException.taskErrorMessage(command);
                return Parser.echo(error);
            } else {
                String[] parts = deadlineTask.split(" /by ", 2);
                Deadline newTask = new Deadline(parts[0], parts[1]);
                tasks.add(newTask);
                String taskStatus = String.format("Got it. I've added this task:\n" +
                        "%s\n" +
                        "Now you have %d tasks in the list.", newTask, tasks.size());
                Parser.writeToFile(tasks);
                return Parser.echo(taskStatus);
            }
        } else if (command.contains("event")) {
            String eventTask = command.replace("event ", "");
            if (eventTask.equals(command) || "".equals(eventTask)) {
                String error = DukeException.taskErrorMessage(command);
                return Parser.echo(error);
            } else {
                String[] parts = eventTask.split(" /",2);
                Task newTask = new Event(parts[0], parts[1]);
                tasks.add(newTask);
                String taskStatus = String.format("Got it. I've added this task:\n" +
                        "%s\n" +
                        "Now you have %d tasks in the list.", newTask, tasks.size());
                Parser.writeToFile(tasks);
                return Parser.echo(taskStatus);
            }
        } else if (command.contains("delete")) {
            String deleteTaskNumber = command.replace("delete ", "");
            if (deleteTaskNumber.equals(command) || "".equals(deleteTaskNumber)) {
                String error = DukeException.taskErrorMessage(command);
                return Parser.echo(error);
            } else {
                int n = Integer.parseInt(deleteTaskNumber);
                if (n > tasks.size()){
                    return Parser.echo(DukeException.IndexOutofBoundsException(tasks));
                } else {
                    Task deletedTask = tasks.remove(n-1);
                    String taskStatus = String.format("Noted. I've removed this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.", deletedTask, tasks.size());
                    Parser.writeToFile(tasks);
                    return Parser.echo(taskStatus);
                }
            }
        } else if(command.contains("find")) {
            String findWord = command.replace("find ", "");
            if (findWord.equals(command) || "".equals(findWord)) {
                String error = DukeException.taskErrorMessage(command);
                return Parser.echo(error);
            } else {
                TaskList keywordList = tasks.find(findWord);
                String stringList = TaskList.printTaskList(keywordList);
                return Parser.echo("Here are the matching tasks in your list:\n" + stringList);
            }
        } else {
            return Parser.echo(DukeException.taskErrorMessage());
        }
    }
}
