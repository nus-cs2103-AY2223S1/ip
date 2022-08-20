import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
        // tasks = new ArrayList<>();

        storage.loadFile(tasks);
        isAcceptingInput = true;
        ui.printStartupMessage();
    }

    private void exit() {
        isAcceptingInput = false;
        ui.printExitMessage();
    }

    private void processInput(String input) {
        try {
            input = input.trim();
            if (input.contains("|")) {
                throw new BannedDukeCharacterException("|");
            }
            if (input.equals("bye")) {
                exit();
                return;
            } else if (input.equals("list")) {
                ui.listTasks(tasks.getTasks());
                return;
            }

            String[] str = input.split(" ", 2);
            String cmd = str[0];
            if (str.length == 1 &&
                    (cmd.equals("mark")
                            || cmd.equals("unmark")
                            || cmd.equals("delete")
                            || cmd.equals("todo")
                            || cmd.equals("deadline")
                            || cmd.equals("event"))) {
                throw new MissingDukeInputException(cmd);
            }

            switch (cmd) {
            case "mark": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.getTaskCount()) {
                    throw new InputIndexOutOfBoundsException("mark task " + str[1]);
                }
                Task markedTask = tasks.markTask(taskIndex);
                ui.printMarkedTask(markedTask);
                break;
            }
            case "unmark": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.getTaskCount()) {
                    throw new InputIndexOutOfBoundsException("unmark task " + str[1]);
                }
                Task unmarkedTask = tasks.unmarkTask(taskIndex);
                ui.printUnmarkedTask(unmarkedTask);
                break;
            }
            case "delete": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.getTaskCount()) {
                    throw new InputIndexOutOfBoundsException("delete task " + str[1]);
                }
                Task removedTask = tasks.removeTask(taskIndex);
                ui.printRemovedTask(removedTask, tasks.getTaskCount());
                break;
            }
            case "todo": {
                Task addedTask = tasks.addTodo(str[1]);
                ui.printAddedTask(addedTask, tasks.getTaskCount());
                break;
            }
            case "deadline": {
                String[] str2 = str[1].split(" /by ");
                if (str2.length == 1) {
                    throw new MissingDukeInputException(cmd);
                }
                String description = str2[0];
                String[] dateTime = Arrays.copyOf(str2[1].split(" "), 2);
                String date = dateTime[0];
                String time = dateTime[1] == null ? "" : dateTime[1];
                Task addedTask = tasks.addDeadline(description, date, time);
                ui.printAddedTask(addedTask, tasks.getTaskCount());
                break;
            }
            case "event": {
                String[] str2 = str[1].split(" /at ");
                if (str2.length == 1) {
                    throw new MissingDukeInputException(cmd);
                }
                String description = str2[0];
                String[] dateTimes = Arrays.copyOf(str2[1].split(" "), 4);
                String dateStart = dateTimes[0];
                String timeStart = dateTimes[1] == null ? "" : dateTimes[1];
                String dateEnd = dateTimes[2] == null ? "" : dateTimes[2];
                String timeEnd = dateTimes[3] == null ? "" : dateTimes[3];
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
            ui.printInputIndexOutOfBoundsResponse(e.getMessage());
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
