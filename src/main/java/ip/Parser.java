package ip;

import java.util.Scanner;

class Parser {
    private static final String END_COMMAND = "bye";
    private static final String PRINT_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    private Scanner sc = new Scanner(System.in);
    private boolean isEnded = false;

    private Ui ui;
    private TaskList taskList;

    Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    protected boolean hasNext() {
        return sc.hasNext() && !isEnded;
    }

    protected boolean handleNext() throws DukeException {
        String inputCmd = sc.next();
        String inputRem = sc.nextLine();
        String storageStr = String.format("%s %s", inputCmd, inputRem);
        switch (inputCmd) {
        case (END_COMMAND):
            return false;
        case (PRINT_COMMAND):
            ui.printAll(taskList);
            break;
        case (MARK_COMMAND):
            markTask(inputRem);
            break;
        case (UNMARK_COMMAND):
            unmarkTask(inputRem);
            break;
        case (TODO_COMMAND):
            createTask(inputRem, TaskType.TODO);
            break;
        case (DEADLINE_COMMAND):
            createTask(inputRem, TaskType.DEADLINE);
            break;
        case (EVENT_COMMAND):
            createTask(inputRem, TaskType.EVENT);
            break;
        case (DELETE_COMMAND):
            deleteTask(inputRem);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    private void markTask(String indexStr) throws DukeException {
        try {
            int index = Integer.parseInt(indexStr);
            Task task = taskList.markTask(index);
            ui.printMarkTask(task);
        } catch (NumberFormatException e) {
            throw new DukeException("Mark Task - Please input a valid number!");
        }
    }

    private void unmarkTask(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr);
            Task task = taskList.unmarkTask(index);
            ui.printUnmarkTask(task);
        } catch {

        }

    }

    private void createTask(String str, TaskType type){
        try {
            str = str.strip();
            Task newTask;
            String description = "";
            String extraInfo = "";
            switch(type) {
                case TODO:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    description = str;
                    newTask = new ToDo(str);
                    break;
                case DEADLINE:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] strList = str.split(" /by ");
                    description = strList[0];
                    extraInfo = strList[1];
                    newTask = new Deadline(description, extraInfo);
                    break;
                case EVENT:
                    if (str.strip().equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    strList = str.split(" /at ");
                    description = strList[0];
                    extraInfo = strList[1];
                    newTask = new Event(description, extraInfo);
                    break;
                default:
                    throw new DukeException("Type of task is not recognised!");
            }
            taskList.add(newTask);
            String singulStr = "Now you have 1 task in the list";
            if (taskList.size() == 1) {
                prettyPrint(String.format("%s\n%s   %s\n%s %s",
                        CREATE_MESSAGE, TAB, newTask.toString(), TAB, singulStr));
            } else {
                prettyPrint(String.format("%s\n%s   %s\n%s Now you have %d tasks in the list.",
                        CREATE_MESSAGE, TAB, newTask.toString(), TAB, taskList.size()));
            }
        } catch (DukeException e) {
            prettyPrint(e.toString());
        }
    }
}
