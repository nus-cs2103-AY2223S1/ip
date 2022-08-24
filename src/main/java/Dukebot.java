public class Dukebot {
    private TaskList taskList;

    private void handleStartup() {
        System.out.println(Messages.startup);
        this.taskList = new TaskList();
    }

    private void handleBye() {
        System.out.println(Messages.ending);
        System.exit(0);
    }

    private void handleList() {
        for (int i = 0; i < this.taskList.getSize(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    private void handleRemove(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new EmptyDescriptionException("remove");
        }
        int currentPos = Integer.parseInt(inputLessAction[1]) - 1;
        System.out.println(Messages.taskRemoved);
        System.out.println(taskList.get(currentPos).toString());
        taskList.remove(Integer.parseInt(inputLessAction[1]) - 1);
        System.out.printf((Messages.taskCount) + "%n", taskList.getSize());
    }

    private void handleMark(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new EmptyDescriptionException("mark");
        }
        int index = Integer.parseInt(inputLessAction[1]) - 1;
        this.taskList.mark(index);
        System.out.println(Messages.taskMarked);
        System.out.println(this.taskList.get(index).toString());
    }

    private void handleUnmark(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new EmptyDescriptionException("unmark");
        }

        int index = Integer.parseInt(inputLessAction[1]) - 1;
        taskList.get(index).markUndone();
        System.out.println(Messages.taskUnmarked);
        System.out.println(taskList.get(index).toString());
    }

    private void handleTodo(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new EmptyDescriptionException("todo");
        }
        String taskDesc = inputLessAction[1];
        this.taskList.add(new Todo(taskDesc));
        System.out.println(Messages.taskAdded);
        System.out.println(this.taskList.get(this.taskList.getSize() - 1).toString());
        System.out.printf((Messages.taskCount) + "%n", this.taskList.getSize());
    }

    private void handleDeadline(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new EmptyDescriptionException("deadline");
        }
        String temp = inputLessAction[1];
        String[] tempStrArr = temp.split("/by\s+", 2);
        if (tempStrArr.length < 2) {
            throw new InvalidFormatException("/by");
        }
        String taskDesc = tempStrArr[0];
        if (taskDesc.equals("")) {
            throw new EmptyDescriptionException("deadline");
        }
        String taskTime = tempStrArr[1];
        if (taskTime.equals("")) {
            throw new EmptyTimeException("deadline");
        }
        this.taskList.add(new Deadline(taskDesc, taskTime));
        System.out.println(Messages.taskAdded);
        System.out.println(this.taskList.get(this.taskList.getSize() - 1).toString());
        System.out.printf((Messages.taskCount) + "%n", this.taskList.getSize());
    }

    private void handleEvent(String[] inputLessAction) throws DukeException {
        if (inputLessAction.length < 2) {
            throw new EmptyDescriptionException("event");
        }
        String temp = inputLessAction[1];
        String[] tempStrArr = temp.split("/at\s+", 2);
        if (tempStrArr.length < 2) {
            throw new InvalidFormatException("/at");
        }
        String taskDesc = tempStrArr[0];
        if (taskDesc.equals("")) {
            throw new EmptyDescriptionException("event");
        }
        String taskTime = tempStrArr[1];
        if (taskTime.equals("")) {
            throw new EmptyTimeException("event");
        }
        this.taskList.add(new Event(taskDesc, taskTime));
        System.out.println(Messages.taskAdded);
        System.out.println(this.taskList.get(this.taskList.getSize() - 1).toString());
        System.out.printf((Messages.taskCount) + "%n", this.taskList.getSize());
    }

    protected void handleInput(String input) {
        String[] inputLessAction = input.split("\s+", 2);

        try {
            switch (inputLessAction[0]) {
                case "bye":
                    this.handleBye();
                case "list":
                    this.handleList();
                    break;
                case "remove":
                    this.handleRemove(inputLessAction);
                    break;
                case "mark":
                    this.handleMark(inputLessAction);
                    break;
                case "unmark":
                    this.handleUnmark(inputLessAction);
                    break;
                case "todo":
                    this.handleTodo(inputLessAction);
                    break;
                case "deadline":
                    this.handleDeadline(inputLessAction);
                    break;
                case "event":
                    this.handleEvent(inputLessAction);
                    break;
                default:
                    throw new InvalidKeywordException();
            }
        } catch (DukeException e) {
            System.out.println(e);
        }

    }


    public Dukebot() {
        this.handleStartup();
    }

}
