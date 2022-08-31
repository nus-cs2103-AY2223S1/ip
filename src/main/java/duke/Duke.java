package duke;

import java.io.IOException;

public class Duke  {

    private Storage storage;
    private TaskList taskList;
    private UserInterface userInterface;
    private GuiUserInterface guiUserInterface;

    /**
     * Constructor for Duke.
     *
     * @param path Relative path in local storage to save data.
     */
    public Duke(String path) {
        try {
            this.taskList = new TaskList();
            storage = new Storage(path, taskList);
            userInterface = new UserInterface(taskList);
            guiUserInterface = new GuiUserInterface(taskList);
            storage.load();
        } catch (IOException e) {
            UserInterface.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public Duke() {
    }

    ;

    public static void main(String[] args) throws DukeException {
//        new Duke("data/tasks.txt").run();
        new Duke("data/tasks.txt");
    }

//    public void run() throws DukeException {
//        ArrayList<Task> myList = new ArrayList<>();
//        boolean bye = false;
//        Scanner scanner = new Scanner(System.in);
//        EventHandler handler = new EventHandler(this.taskList, this.userInterface);
//        System.out.println("Hello I am Duke!");
//
//        while (!bye) {
//            String input = scanner.nextLine();
//            if (input.equals("bye")) {
//                bye = true;
//
//                userInterface.sayBye();
//            } else if (input.equals("list")) {
//                userInterface.printList();
//            } else if (input.startsWith("mark")) {
//                handler.markTask(input);
//            } else if (input.startsWith("unmark")) {
//                handler.unmarkTask(input);
//            } else if (input.startsWith("delete")) {
//                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
//                handler.deleteTask(taskIndex);
//            } else if (input.startsWith("todo")) {
//                handler.addTodo(input);
//            } else if (input.startsWith("deadline")) {
//                handler.addDeadline(input);
//            } else if (input.startsWith("event")) {
//                handler.addEvent(input);
//            } else if (input.startsWith("find")) {
//                handler.find(input);
//            } else {
//                throw new DukeUnknownCommandException();
//            }
//            storage.save();
//
//        }
//
//        scanner.close();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        EventHandler handler = new EventHandler(this.taskList, this.userInterface, this.guiUserInterface, this.storage);
        if (input.equals("bye")) {
            return guiUserInterface.sayBye();
        } else if (input.equals("list")) {
            return guiUserInterface.printList();
        } else if (input.startsWith("mark")) {
            return handler.markTask(input);
        } else if (input.startsWith("unmark")) {
            return handler.unmarkTask(input);
        } else if (input.startsWith("delete")) {
            return handler.deleteTask(input);
        } else if (input.startsWith("todo")) {
            return handler.addTodo(input);
        } else if (input.startsWith("deadline")) {
            return handler.addDeadline(input);
        } else if (input.startsWith("event")) {
            return handler.addEvent(input);
        } else if (input.startsWith("find")) {
            return handler.find(input);
        } else {
            throw new DukeUnknownCommandException();
        }
    }
}
