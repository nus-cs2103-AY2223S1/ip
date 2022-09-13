package duke;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("I am unable to create a new file.");
        }
    }

    //Solution adapted from https://github.com/nus-cs2103-AY2223S1/ip/commit/1425eadf831d33ce6909694a2c3d5d58670aacd9

    public void run(Parser parser) {
        ui.hello();
        parser.initialise();
        ui.goodBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("src/main/data/duke.txt");
        Parser parser = new Parser(duke);
        duke.run(parser);
    }

    public void showList() {
        System.out.println(HORIZONTAL_LINE_BREAK + "\n" + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE_BREAK);
    }



    public void setToDo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of todo cannot be empty");
        }
        Task toDo = new ToDo(description);
        tasks.addTask(toDo);
        System.out.println(HORIZONTAL_LINE_BREAK);
        System.out.println("Got it. I've added this task:" + "\n" + toDo + "\n" + "Now you have " + tasks.getSize()
                + " tasks in your list.");
        System.out.println(HORIZONTAL_LINE_BREAK);
        storage.save(tasks);
    }

    public void setDeadLine(String description, String by) {
        Task deadLine = new Deadline(description, by);
        tasks.addTask(deadLine);
        System.out.println(HORIZONTAL_LINE_BREAK);
        System.out.println("Got it. I've added this task:" + "\n" + deadLine + "\n" + "Now you have " + tasks.getSize()
                + " tasks in your list.");
        System.out.println(HORIZONTAL_LINE_BREAK);
        storage.save(tasks);
    }

    public void setEvent(String description, String at) {
        Task event = new Event(description, at);
        tasks.addTask(event);
        System.out.println(HORIZONTAL_LINE_BREAK);
        System.out.println("Got it. I've added this task:" + "\n" + event + "\n" + "Now you have " + tasks.getSize()
                + " tasks in your list.");
        System.out.println(HORIZONTAL_LINE_BREAK);
        storage.save(tasks);
    }

    public void mark(int index) throws DukeException {
        tasks.markTask(index);
        storage.save(tasks);
    }

    public void unmark(int index) throws DukeException {
        tasks.unMarkTask(index);
        storage.save(tasks);
    }

    public void delete(int index) throws  DukeException {
        tasks.deleteTask(index);
        storage.save(tasks);
    }

}
