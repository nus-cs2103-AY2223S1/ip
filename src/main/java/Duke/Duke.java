package Duke;

import java.util.Scanner;

public class Duke {
    Scanner sc;

    public TaskList tasklist;
    public UserInterface userInterface;

    public Duke() {

        this.userInterface = new UserInterface();
        this.tasklist = new TaskList();
        this.sc = new Scanner(System.in);

    }

    public void run() {
        System.out.println(userInterface.greeting());
        boolean isExit = false;
        Handler handler = new Handler(tasklist, userInterface);
        while (!isExit) {
            try {
                String echo = sc.nextLine();
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
                } else {
                    throw new DukeUnknownTaskException();
                }
            } catch (DukeException e){
                userInterface.printError(e.getMessage());

            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
