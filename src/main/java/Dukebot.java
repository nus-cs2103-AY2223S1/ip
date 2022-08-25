import java.io.IOException;

public class Dukebot {
    private TaskList taskList;
    private Storage storage;

    private void handleStartup() {
        System.out.println(Messages.STARTUP);
        try {
            this.storage = new Storage();
            storage.createStorage();
            this.taskList = storage.loadFromStorage();
        } catch (IOException e) {
            System.out.println(Messages.LOAD_ERROR);
            this.taskList = new TaskList();
        }
    }

    private void handleBye() {
        System.out.println(Messages.ENDING);
        System.exit(0);
    }

    private void handleList() {
        System.out.println(Messages.LIST_TASKS);
        for (int i = 0; i < this.taskList.getSize(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    private void handleRemove(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "remove"));
        }
        int currentPos = Integer.parseInt(inputLessAction[1]) - 1;
        System.out.println(Messages.TASK_REMOVED);
        System.out.println(taskList.get(currentPos).toString());
        taskList.remove(Integer.parseInt(inputLessAction[1]) - 1);
        System.out.printf((Messages.TASK_COUNT) + "%n", taskList.getSize());
    }

    private void handleMark(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "mark"));
        }
        int index = Integer.parseInt(inputLessAction[1]) - 1;
        this.taskList.mark(index);
        System.out.println(Messages.TASK_MARKED);
        System.out.println(this.taskList.get(index).toString());
    }

    private void handleUnmark(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "unmark"));
        }

        int index = Integer.parseInt(inputLessAction[1]) - 1;
        taskList.get(index).markUndone();
        System.out.println(Messages.TASK_UNMARKED);
        System.out.println(taskList.get(index).toString());
    }

    private void handleTodo(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "todo"));
        }
        String taskDesc = inputLessAction[1];
        this.taskList.add(new Todo(taskDesc));
        System.out.println(Messages.TASK_ADDED);
        System.out.println(this.taskList.get(this.taskList.getSize() - 1).toString());
        System.out.printf((Messages.TASK_COUNT) + "%n", this.taskList.getSize());
    }

    private void handleDeadline(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "deadline"));
        }
        String temp = inputLessAction[1];
        String[] tempStrArr = temp.split("/by\\s+", 2);
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
        System.out.println(Messages.TASK_ADDED);
        System.out.println(this.taskList.get(this.taskList.getSize() - 1).toString());
        System.out.printf((Messages.TASK_COUNT) + "%n", this.taskList.getSize());
    }

    private void handleEvent(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new DukeException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "event"));
        }
        String temp = inputLessAction[1];
        String[] tempStrArr = temp.split("/at\\s+", 2);
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
        System.out.println(Messages.TASK_ADDED);
        System.out.println(this.taskList.get(this.taskList.getSize() - 1).toString());
        System.out.printf((Messages.TASK_COUNT) + "%n", this.taskList.getSize());
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
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to storage");
        }

    }


    public Dukebot() {
        this.handleStartup();
    }

}
