public class Duke {
    public Ui ui;
    public TaskList taskList;
    public Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public enum Keyword
    {
        EXIT("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete"), MARK("mark"),
        UNMARK("unmark");

        private String keyword;

        private Keyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }
    }

    private boolean processUserInput(String userInput) throws DukeException{
        String command;
        String taskDetails;

        if (userInput.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The user input cannot be empty.");
        }
        command = userInput.split(" ")[0];
        if (command.equals(Keyword.EXIT.getKeyword())) {
            ui.printFarewellMessage();
            return true;
        } else if (command.equals(Keyword.LIST.getKeyword())) {
            taskList.printTaskList();
        } else if (command.equals(Keyword.MARK.getKeyword()) || command.equals(Keyword.UNMARK.getKeyword())) {
            if (userInput.split(" ").length == 1) {
                throw new DukeException("☹ OOPS!!! The mark/unmark command cannot have a missing index.");
            }
            String index = userInput.split(" ")[1];
            taskList.updateTaskStatus(Integer.parseInt(index), (command.equals(Keyword.MARK.getKeyword()) ? true : false));
        } else {
            Task task;
            if (userInput.split(" ", 2).length == 1) {
                if (command.equals(Keyword.TODO.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (command.equals(Keyword.EVENT.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (command.equals(Keyword.DEADLINE.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (command.equals(Keyword.DEADLINE.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The delete command cannot have a missing index.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            taskDetails = userInput.split(" ", 2)[1];
            if (command.equals(Keyword.TODO.getKeyword())) {
                task = new Todo(taskDetails);
                taskList.addTask(task);
            } else if (command.equals(Keyword.EVENT.getKeyword())) {
                task = new Event(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                taskList.addTask(task);
            } else if (command.equals(Keyword.DEADLINE.getKeyword())) {
                task = new Deadline(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                taskList.addTask(task);
            }  else if (command.equals(Keyword.DELETE.getKeyword())) {
                int taskIndex = Integer.parseInt(taskDetails);
                taskList.deleteTask(taskIndex);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return false;
    }

    public void run() {
        ui.printGreetingMessage();
        taskList = new TaskList(storage.loadTaskList());
        String userInput;

        while(true) {
            userInput = ui.getUserInput();
            try {
                Boolean exitCommand = processUserInput(userInput);
                if (exitCommand) {
                    storage.saveTaskList(taskList.getTaskList());
                    break;
                }
            } catch (DukeException exception) {
                ui.printMessage(exception.toString());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
