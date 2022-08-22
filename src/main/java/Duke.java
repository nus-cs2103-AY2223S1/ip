import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TasksList tasksList;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.storage.initialiseSaveFile();
            this.tasksList = storage.createTaskList();
            this.ui.showMessage("Loading from save...\n");
        } catch (DukeException e) {
            this.ui.showError(e);
            tasksList = new TasksList();
        }
    }

    public void run() {
        this.ui.showGreeting();
        while (this.ui.isActive()) {
            try {
                String input = this.ui.getUserCommand();
                Command command = Parser.parse(input, this.storage, this.tasksList, this.ui);
                command.execute();
            } catch (DukeException e) {
                this.ui.showError(e);
            } finally {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

//    public static void main(String[] args) {
//        String logo = " ____        _\n"
//                + "|  _ \\ _   _| | _____\n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello, I'm Duke!\n" + logo);
//
//        Scanner sc = new Scanner(System.in);
//        Storage storage = new Storage();
//        TasksList tasksList;
//        try {
//            tasksList = storage.createTaskList();
//            System.out.println("Loading from save...\n");
//        } catch (DukeException e) {
//            System.out.println(e);
//            tasksList = new TasksList();
//        }
//        System.out.println("Duke: What can I do for you?");
//
//        while (sc.hasNext()) {
//            String command = sc.nextLine();
//            String[] words = command.split(" ", 2);
//            try {
//                if (command.equals("bye")) {
//                    System.out.println("Duke: Bye! Hope to see you again soon!");
//                    sc.close();
//                    storage.writeToSave(tasksList);
//                    break;
//                }
//                if (command.equals("list")) {
//                    tasksList.listTasks();
//                    continue;
//                }
//                if (words[0].equals("mark")) {
//                    tasksList.markTask(words, storage);
//                    continue;
//                }
//                if (words[0].equals("unmark")) {
//                    tasksList.unmarkTask(words, storage);
//                    continue;
//                }
//                if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")) {
//                    tasksList.addTask(words, storage);
//                    continue;
//                }
//                if (words[0].equals("delete")) {
//                    tasksList.deleteTask(words, storage);
//                    continue;
//                }
//                throw new DukeException("Unknown command. Please try again.");
//            } catch (DukeException e) {
//                System.out.println(e);
//            } finally {
//                System.out.println();
//            }
//        }
//    }
}
