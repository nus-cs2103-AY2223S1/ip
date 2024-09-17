package Duke;



import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

public class Duke {
    Scanner sc;
    public Storage storage;
    public TaskList tasklist;
    public UserInterface userInterface;

    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.userInterface = new UserInterface();
        this.tasklist = new TaskList();
        this.sc = new Scanner(System.in);

        try {
            this.tasklist = new TaskList();
            storage.load();
        } catch (IOException e) {
            this.tasklist = new TaskList();
            userInterface.printError(e.getMessage());
        }

    }

    public Duke(String path) {
        this.userInterface = new UserInterface();
        this.storage = new Storage(path);
        try {
            this.tasklist = new TaskList();
            storage.load();

        } catch (IOException e){
            this.tasklist = new TaskList();
            userInterface.printError(e.getMessage());

        }
    }

    public void run() {
        System.out.println(userInterface.greeting());
        boolean isExit = false;

        while (!isExit) {
            try {
                Handler handler = new Handler(tasklist, userInterface);

                String echo = userInterface.getInput();

                if (echo.equals("bye")) {
                    System.out.println(userInterface.bye());
                    isExit = true;
                } else if (echo.equals("list")) {
                    System.out.println(userInterface.showList());
                } else if (echo.startsWith("mark")) {
                    System.out.println(handler.handleMark(echo));
                } else if (echo.startsWith("unmark")) {
                    System.out.println(handler.handleUnmark(echo));
                } else if (echo.startsWith("todo")) {
                    System.out.println(handler.handleToDo(echo));
                } else if (echo.startsWith("deadline")) {
                    System.out.println(handler.handleDeadline(echo));
                } else if (echo.startsWith("event")) {
                    System.out.println(handler.handleEvent(echo));
                } else if (echo.startsWith("delete")){
                    System.out.println(handler.handleDelete(echo));
                } else {
                    throw new DukeUnknownTaskException();
                }
                storage.save();

            } catch (DukeException e){
                userInterface.printError(e.getMessage());

            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
