package parser;
import printer.Printer;
import storage.Storage;
import task.Task;
import task.ToDo;
import task.Event;
import task.Deadline;
import exception.CommandException;
import exception.EmptyCommandException;
import exception.ToDoException;
import exception.DeadlineException;
import exception.EventException;


public class Parser {
    private boolean isListening = true;
    private Storage storage;

    public Parser() {
        this.storage = new Storage();
    }

    public void parseText(String text) {
        try {
            String[] commands = text.split(" ", 2);
            String mainCommand = commands[0];

            if (commands.length < 2) {
                handleSingleWordCommand(mainCommand);
                return;
            }

            String secondaryCommand = commands[1];
            handleMultiWordsCommand(mainCommand, secondaryCommand);
        } catch(CommandException e) {
            Printer.print(e.getMessage());
        }
    }

    private void handleSingleWordCommand(String command) throws CommandException {
        switch(command) {
            case "bye":
                Printer.print("Bye. See you later master!");
                this.isListening = false;
                break;

            case "list":
                Printer.print(this.storage.toString());
                break;

            case "todo":
                throw new ToDoException();

            case "deadline":
                throw new DeadlineException();

            case "event":
                throw new EventException();

            case "": // The command is empty string
                throw new EmptyCommandException();

            default:
                throw new CommandException();
        }
    }

    private void handleMultiWordsCommand(String primaryCommand, String secondaryCommand)
        throws CommandException {
        Task currentTask;

        switch(primaryCommand) {
            case "mark":
                try {
                    int markedIndex = Integer.parseInt(secondaryCommand) - 1;
                    currentTask = this.storage.getTaskWithIndex(markedIndex);
                } catch (Exception e) {
                    throw new CommandException();
                }

                currentTask.markAsFinished();
                break;

            case "unmark":
                try {
                    int unMarkedIndex = Integer.parseInt(secondaryCommand) - 1;
                    currentTask = this.storage.getTaskWithIndex(unMarkedIndex);
                } catch (Exception e) {
                    throw new CommandException();
                }

                currentTask.markAsNotFinished();
                break;

            case "delete":
                try {
                    int selectedIndex = Integer.parseInt(secondaryCommand) - 1;
                    this.storage.removeTaskWithIndex(selectedIndex);
                } catch (Exception e) {
                    throw new CommandException();
                }

                break;

            case "todo":
                currentTask = new ToDo(secondaryCommand);
                this.storage.addTask(currentTask);
                break;

            case "deadline":
                String[] deadlineInfo = secondaryCommand.split("/by ", 2);

                if (deadlineInfo.length < 2) {
                    throw new DeadlineException();
                }

                currentTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                this.storage.addTask(currentTask);
                break;

            case "event":
                String[] eventInfo = secondaryCommand.split("/at ", 2);

                if (eventInfo.length < 2) {
                    throw new EventException();
                }

                currentTask = new Event(eventInfo[0], eventInfo[1]);
                this.storage.addTask(currentTask);
                break;

            default:
                throw new CommandException();
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
