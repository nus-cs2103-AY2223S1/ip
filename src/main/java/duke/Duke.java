package duke;

import utils.Constants;
import utils.DukeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

    private final List<Task> tasks;

    Duke() {
        tasks = new ArrayList<>();
    }

    public void startDuke() {
        sendGreetings();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String[] inputs = sc.nextLine().trim().split(" ", 2);
            Command command = Command.contains(inputs[0]);
            String inputDesc = (inputs.length == 1) ? "" : inputs[1];

            try {
                switch (Command.isValidInput(command, inputDesc)) {
                case LIST:
                    printTasks();
                    break;
                case BYE:
                    sendExit();
                    sc.close();
                    isRunning = false;
                    break;
                case TODO:
                    addTask(new ToDo(inputDesc));
                    break;
                case DEADLINE:
                    addTask(new Deadline(inputDesc));
                    break;
                case EVENT:
                    addTask(new Event(inputDesc));
                    break;
                case MARK:
                    markTask(inputDesc);
                    break;
                case UNMARK:
                    unmarkTask(inputDesc);
                    break;
                case DELETE:
                    deleteTask(inputDesc);
                    break;
                case INVALID:
                    throw new DukeException(Constants.ERROR_UNKNOWN_COMMAND);
                }
            } catch (DukeException e) {
                DukeUtils.printMessage(e.getMessage());
            }
        }
    }

    private void printTasks() {
        DukeUtils.printLine();
        DukeUtils.printWithIndent(Constants.MSG_TASK_LIST);
        IntStream.range(0, tasks.size())
                .forEach(i ->
                        DukeUtils.printWithIndent(String.format("%d.%s", i + 1, tasks.get(i))));
    }

    public void addTask(Task task) {
        tasks.add(task);
        DukeUtils.printMessages(
                Constants.MSG_TASK_ADDED,
                "  " + task,
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

    private void markTask(String input) {
        int index = Integer.parseInt(input) - 1;
        tasks.get(index).setDone(true);
        DukeUtils.printMessages(Constants.MSG_TASK_MARK, "  " + tasks.get(index));
    }

    private void unmarkTask(String input) {
        int index = Integer.parseInt(input) - 1;
        tasks.get(index).setDone(false);
        DukeUtils.printMessages(Constants.MSG_TASK_UNMARK, "  " + tasks.get(index));
    }

    private void deleteTask(String input) {
        int index = Integer.parseInt(input) - 1;
        tasks.remove(index);
        DukeUtils.printMessages(
                Constants.MSG_TASK_DELETED,
                "  " + tasks.get(index),
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

    private void sendGreetings() {
        DukeUtils.printMessage(Constants.MSG_GREETINGS);
    }

    private void sendExit() {
        DukeUtils.printMessage(Constants.MSG_EXIT);
    }

}