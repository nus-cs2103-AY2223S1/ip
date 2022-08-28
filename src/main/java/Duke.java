import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke (String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        this.parser = new Parser(tasks, storage);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError;
            tasks  = new TaskList();
        }

    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        String message = sc.nextLine();

        while (!message.equals("bye")) {
            try {
                this.parser.parse(message);
            } catch (DukeException e){
                System.out.println(e);
            }
            message = sc.nextLine();
        }
        System.out.println("\nBye. Hope to see you again soon!");
        sc.close();
        }
    }
