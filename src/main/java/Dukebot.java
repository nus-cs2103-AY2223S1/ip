import java.io.IOException;

public class Dukebot {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private void handleStartup() {
        ui = new Ui();
        ui.display(Messages.STARTUP);
        try {
            this.storage = new Storage();
            storage.createStorage();
            this.taskList = storage.loadFromStorage();
        } catch (IOException e) {
            ui.display(Messages.LOAD_ERROR);
            this.taskList = new TaskList();
        }
    }

    private void handleBye() {
        ui.display(Messages.ENDING);
        System.exit(0);
    }

    private void handleList() {
        ui.display(Messages.LIST_TASKS);
        for (int i = 0; i < this.taskList.getSize(); i++) {
            ui.display((i + 1) + "." + taskList.get(i).toString());
        }
    }

    private void handleRemove(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "remove"));
        }
        int currentPos = Integer.parseInt(inputLessAction[1]) - 1;
        ui.display(Messages.TASK_REMOVED);
        ui.display(taskList.get(currentPos).toString());
        taskList.remove(Integer.parseInt(inputLessAction[1]) - 1);
        ui.display(String.format((Messages.TASK_COUNT) + "%n", taskList.getSize()));
    }

    private void handleMark(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "mark"));
        }
        int index = Integer.parseInt(inputLessAction[1]) - 1;
        this.taskList.mark(index);
        ui.display(Messages.TASK_MARKED);
        ui.display(this.taskList.get(index).toString());
    }

    private void handleUnmark(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "unmark"));
        }

        int index = Integer.parseInt(inputLessAction[1]) - 1;
        taskList.get(index).markUndone();
        ui.display(Messages.TASK_UNMARKED);
        ui.display(taskList.get(index).toString());
    }

    private void handleTodo(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "todo"));
        }
        String taskDesc = inputLessAction[1];
        this.taskList.add(new Todo(taskDesc));
        ui.display(Messages.TASK_ADDED);
        ui.display(this.taskList.get(this.taskList.getSize() - 1).toString());
        ui.display(String.format((Messages.TASK_COUNT) + "%n", this.taskList.getSize()));
    }

    private void handleDeadline(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "deadline"));
        }
        String temp = inputLessAction[1];
        String[] tempStrArr = temp.split("\\s+/by\\s+", 2);
        if (tempStrArr.length < 2) {;
            throw new DukeException(String.format(ExceptionMessages.INVALID_FORMAT, "/by"));
        }
        String taskDesc = tempStrArr[0];
        if (taskDesc.equals("")) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "deadline"));
        }
        String taskTime = tempStrArr[1];
        if (taskTime.equals("")) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_TIME, "deadline"));
        }
        this.taskList.add(new Deadline(taskDesc, taskTime));
        ui.display(Messages.TASK_ADDED);
        ui.display(this.taskList.get(this.taskList.getSize() - 1).toString());
        ui.display(String.format((Messages.TASK_COUNT) + "%n", this.taskList.getSize()));
    }

    private void handleEvent(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "event"));
        }
        String temp = inputLessAction[1];
        String[] tempStrArr = temp.split("\\s+/at\\s+", 2);
        if (tempStrArr.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.INVALID_FORMAT, "/at"));
        }
        String taskDesc = tempStrArr[0];
        if (taskDesc.equals("")) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "event"));
        }
        String taskTime = tempStrArr[1];
        if (taskTime.equals("")) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_TIME, "event"));
        }
        this.taskList.add(new Event(taskDesc, taskTime));
        ui.display(Messages.TASK_ADDED);
        ui.display(this.taskList.get(this.taskList.getSize() - 1).toString());
        ui.display(String.format((Messages.TASK_COUNT) + "%n", this.taskList.getSize()));
    }

    protected void handleInput(String input) {
        String[] inputLessAction = input.split("\\s+", 2);

        try {
            switch (inputLessAction[0]) {
            case "bye":
                this.handleBye();
            case "list":
                this.handleList();
                break;
            case "remove":
                this.handleRemove(inputLessAction);
                storage.writeToStorage(taskList);
                break;
            case "mark":
                this.handleMark(inputLessAction);
                storage.writeToStorage(taskList);
                break;
            case "unmark":
                this.handleUnmark(inputLessAction);
                storage.writeToStorage(taskList);
                break;
            case "todo":
                this.handleTodo(inputLessAction);
                storage.writeToStorage(taskList);
                break;
            case "deadline":
                this.handleDeadline(inputLessAction);
                storage.writeToStorage(taskList);
                break;
            case "event":
                this.handleEvent(inputLessAction);
                storage.writeToStorage(taskList);
                break;
            default:
                throw new DukeException(ExceptionMessages.UNSUPPORTED_ACTION);
            }
        } catch (DukeException e) {
            ui.display(e.getMessage());
        } catch (IOException e) {
            ui.display("Error writing to storage");
        }

    }


    public Dukebot() {
        this.handleStartup();
    }

}
