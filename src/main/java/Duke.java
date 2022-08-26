import java.util.Scanner;

public class Duke {
<<<<<<< HEAD
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input = "";
        MarkableList list = new MarkableList();

        String welcomeMsg = "Hello I'm Duke!\nWhat can I do for you?";
        Duke.printText(welcomeMsg);

        while (input != "bye") {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                Duke.printText(list.toString());
            } else if (input.matches("[Mm]ark \\d+")) {
                int itemIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                try {
                    Duke.printText(list.markItem(itemIndex));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.printText("Uhoh! The item doesn't exist");
                }
            } else if (input.matches("[Uu]nmark \\d+")) {
                int itemIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                try {
                    Duke.printText(list.unmarkItem(itemIndex));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.printText("Uhoh! The item doesn't exist");
                }
            } else {
                try {
                    Duke.printText(list.insertItem(input));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.printText("Uhoh! The list is full.");
                }
            }
        }

        String exitMsgs = "Good bye!\nHope to see you soon!";
        Duke.printText(exitMsgs);
=======

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showResponse(String.format("Here is the list that you have saved previously:\n%s", 
                    tasks.toString()));
            if (!storage.getWarnings().isEmpty()) {
                ui.showWarning(storage.getWarnings());
            }
        } catch (InvalidStorageException e) {
            ui.showErr(e.getMessage());
            tasks = new TaskList();
        } 
>>>>>>> 038da11 (Refactor using OOP)
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser.parse(fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.showErr(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showErr(String.format("Thats an invalid number!\n" 
                        + "Please enter a number between %d and %d", 1, tasks.getNumOfTasks()));
            } finally {
                ui.showHorizontalLineLong();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}