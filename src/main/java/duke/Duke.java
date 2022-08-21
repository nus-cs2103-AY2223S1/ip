package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private boolean isAcceptingInput;
    private TaskList tasks;

    public Duke(String savePath, String saveName) {
        this.ui = new Ui();
        this.storage = new Storage(savePath, saveName);
        tasks = new TaskList();

        storage.loadFile(tasks);
        isAcceptingInput = true;
        ui.printStartupMessage();
    }

    private void exit() {
        isAcceptingInput = false;
        ui.printExitMessage();
    }

    private int getTaskIndexFromParsedOutput(String[] parsedOutput, String cmd) throws InputIndexOutOfBoundsException {
        int taskNum = Integer.parseInt(parsedOutput[1]);
        int taskIndex = taskNum - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getTaskCount()) {
            throw new InputIndexOutOfBoundsException(cmd + " " + taskNum);
        }
        return taskIndex;
    }

    private void processInput(String input) {
        try {
            String[] parsedOutput = Parser.parseInput(input);
            String cmd = parsedOutput[0];
            switch (cmd) {
            case "bye":
                exit();
                return;
            case "list":
                ui.listTasks(tasks.getTasks());
                break;
            case "find": {
                ArrayList<Task> foundTasks = tasks.find(parsedOutput[1]);
                ui.listFoundTasks(foundTasks);
                break;
            }
            case "mark": {
                int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
                Task markedTask = tasks.markTask(taskIndex);
                ui.printMarkedTask(markedTask);
                break;
            }
            case "unmark": {
                int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
                Task unmarkedTask = tasks.unmarkTask(taskIndex);
                ui.printUnmarkedTask(unmarkedTask);
                break;
            }
            case "delete": {
                int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
                Task removedTask = tasks.removeTask(taskIndex);
                ui.printRemovedTask(removedTask, tasks.getTaskCount());
                break;
            }
            case "todo": {
                Task addedTask = tasks.addTodo(parsedOutput[1]);
                ui.printAddedTask(addedTask, tasks.getTaskCount());
                break;
            }
            case "deadline": {
                String description = parsedOutput[1];
                String date = parsedOutput[2];
                String time = parsedOutput[3];
                Task addedTask = tasks.addDeadline(description, date, time);
                ui.printAddedTask(addedTask, tasks.getTaskCount());
                break;
            }
            case "event": {
                String description = parsedOutput[1];
                String dateStart = parsedOutput[2];
                String timeStart = parsedOutput[3];
                String dateEnd = parsedOutput[4];
                String timeEnd = parsedOutput[5];
                Task addedTask = tasks.addEvent(description, dateStart, timeStart, dateEnd, timeEnd);
                ui.printAddedTask(addedTask, tasks.getTaskCount());
                break;
            }
            default:
                throw new InvalidDukeInputException();
            }
        } catch (BannedDukeCharacterException e) {
            ui.printBannedCharacterInputResponse(e.getMessage());
        } catch (InvalidDukeInputException e) {
            ui.printInvalidInputResponse();
        } catch (MissingDukeInputException e) {
            ui.printMissingInputResponse(e.getMessage());
        } catch (InputIndexOutOfBoundsException e) {
            String[] cmdNum = e.getMessage().split(" ");
            String cmd = cmdNum[0];
            String inputNum = cmdNum[1];
            ui.printInputIndexOutOfBoundsResponse(cmd, inputNum);
        } catch (DateTimeParseException e) {
            ui.printDateTimeErrorResponse();
        }
    }

    private void runDuke() {
        Scanner sc = new Scanner(System.in);
        while (isAcceptingInput) {
            String input = sc.nextLine();
            processInput(input);
            storage.saveFile(tasks);
        }
        sc.close();
    }

    public static void main(String[] args) {
        String savePath = "savedata/";
        String saveName = "duke.txt";
        new Duke(savePath, saveName).runDuke();
    }
}
