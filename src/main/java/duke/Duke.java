package duke;

import java.io.IOException;


public class Duke {

    public static void main(String[] args) {
            new Duke("data/tasks.txt").run();
        }

    private String filePath;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasklist;

    private boolean isRunning = false;

    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasklist = new TaskList();
    }

    public void run() {
        try {
            isRunning = true;

            ui.greet();
            ui.start();

            storage.load(tasklist);
            storage.save(tasklist); // just in case user exits without any list modifications

            while (isRunning) {
                try {
                    Pair<Command, String> p = parser.parse(ui.getInput());
                    execute(p.x, p.y);
                } catch (DukeTaskException e) {
                    System.out.println("Error occurred when creating task:" + e);
                } catch (DukeException e) {
                    System.out.println("Error occurred " + e);
                } catch (NumberFormatException e) {
                    System.out.println("Error occurred: Could not identify index");
                } finally {
                    printLine();
                }
            }
        } catch (DukeException e) {
            System.out.println("Error occurred during loading: " + e);
        } catch (IOException e) {
            System.out.print("Error occurred with saving/loading: " + e);
        }
    }

    private void execute(Command c, String s) throws IOException, DukeTaskException {
        switch(c) {
        case BYE:
            ui.end();
            System.out.println("Bye. Hope to see you again soon!");
            isRunning = false;
            break;
        case LIST:
            tasklist.list();
            break;
        case MARK:
            Task marked = tasklist.mark(Integer.parseInt(s) - 1);
            storage.save(tasklist);
            System.out.println("This task is now marked: \n" + marked);
            break;
        case UNMARK:
            Task unmarked = tasklist.unmark(Integer.parseInt(s) - 1);
            storage.save(tasklist);
            System.out.println("This task is now unmarked: \n" + unmarked);
            break;
        case DELETE:
            Task deleted = tasklist.delete(Integer.parseInt(s) - 1);
            storage.save(tasklist);
            System.out.println("This task is now removed: \n" + deleted);
            break;
        case TODO:
            Task todo = tasklist.addTodo(s);
            storage.save(tasklist);
            System.out.println("added todo: " + todo.getName());
            break;
        case DEADLINE:
            Task deadline = tasklist.addDeadline(s);
            storage.save(tasklist);
            System.out.println("added deadline: " + deadline.getName());
            break;
        case EVENT:
            Task event = tasklist.addEvent(s);
            storage.save(tasklist);
            System.out.println("added event: " + event.getName());
            break;
        }
    }

    public void stop() {
        isRunning = false;
    }

    public static void printLine() {
        System.out.println("--------------------------------------");
    }

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }
}









/*
       //Unused:

            try {
                String s = scan.nextLine();
                int indexOfSpace = s.indexOf(' ');
                isMultipleWords = indexOfSpace > -1;
                firstWord = s;
                restWord = "";
                if (isMultipleWords) {
                    firstWord = s.substring(0, indexOfSpace);
                    restWord = s.substring(indexOfSpace).trim();
                }
                System.out.println("--------------------------------------");

                switch(firstWord) {
                    case "bye":
                        scan.close();
                        System.out.println("Bye. Hope to see you again soon!");
                        break LOOP;
                    case "list":
                        int count = 1;
                        System.out.println("Here are the tasks in your list:");
                        for (Task item : log) {
                            System.out.println(count + ". " + item.toString());
                            count++;
                        }
                        break;
                    case "mark":
                        if (!isMultipleWords) {
                            throw new DukeException("Index of task to mark required");
                        }
                    {
                        int index = Integer.parseInt(restWord) - 1; //array starts from 0
                        Task temp = log.get(index);
                        temp.Mark();
                        System.out.println("This task is now done: \n" + temp);
                    }
                    case "unmark":
                        if (!isMultipleWords) {
                            throw new DukeException("Index of task to unmark required");
                        }
                        int index = Integer.parseInt(restWord) - 1; //array starts from 0
                        Task temp = log.get(index);
                        temp.Unmark();
                        System.out.println("This task is now not done: \n" + temp);
                }
*/