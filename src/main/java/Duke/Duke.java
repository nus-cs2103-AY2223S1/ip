package Duke;


import java.util.Scanner;

public class Duke {
    Scanner sc;

    public TaskList tasklist;
    public ui ui;

    public Duke() {

        this.ui = new ui();
        this.tasklist = new TaskList();
        this.sc = new Scanner(System.in);

    }

    public void run() {
        System.out.println(ui.greeting());
        boolean isExit = false;
        Handler handler = new Handler(tasklist, ui);
        while (!isExit) {
            try {
                String echo = sc.nextLine();
                if (echo.equals("bye")) {
                    System.out.println(ui.bye());
                    isExit = true;
                } else if (echo.equals("list")) {
                    System.out.println(ui.showList());
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
            } catch (DukeException e){
                ui.printError(e.getMessage());

            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
