import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        TaskList taskList = new TaskList();
        Scanner in = new Scanner(System.in);
        outer:
        while (true) {
            try {
                String s = in.next();
                CommandType type;
                try {
                    type = CommandType.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
                switch (type) {
                case BYE:
                    break outer;
                case MARK: {
                    int selectedTask = in.nextInt();
                    taskList.mark(selectedTask);
                    break;
                }
                case UNMARK: {
                    int selectedTask = in.nextInt();
                    taskList.unmark(selectedTask);
                    break;
                }
                case DELETE: {
                    int selectedTask = in.nextInt();
                    taskList.delete(selectedTask);
                    break;
                }
                case LIST: {
                    System.out.println(taskList);
                    break;
                }
                case TODO: {
                    s = in.nextLine();
                    if (s.length() < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    s = s.substring(1);
                    Task t = new Todo(s);
                    taskList.add(t);
                    break;
                }
                case DEADLINE: {
                    StringBuilder desc = new StringBuilder();
                    String token;
                    while (!(token = in.next()).equals("/by")) {
                        desc.append(" " + token);
                    }
                    s = in.nextLine();
                    s = s.substring(1);
                    try {
                        Task t = new Deadline(desc.substring(1), s);
                        taskList.add(t);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case EVENT: {
                    StringBuilder desc = new StringBuilder();
                    String token;
                    while (!(token = in.next()).equals("/at")) {
                        desc.append(" " + token);
                    }
                    s = in.nextLine();
                    s = s.substring(1);
                    try {
                        Task t = new Event(desc.substring(1), s);
                        taskList.add(t);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            String dataFileName = "./data/duke.txt";
            Storage.newDir("./data");
            Storage.newFile(dataFileName);
            try {
                Storage.save(taskList.getTasks(), dataFileName);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

        in.close();*/
    }
}
