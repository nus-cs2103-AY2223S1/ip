package parser;
import printer.Printer;
import storage.Storage;
import task.Task;
import task.ToDo;
import task.Event;
import task.Deadline;


public class Parser {
    private boolean isListening = true;
    private Storage storage;

    public Parser() {
        this.storage = new Storage();
    }

    public void parseText(String text) {
        String[] commands = text.split(" ", 2);
        String mainCommand = commands[0];

        if (commands.length < 2) {
            handleSingleWordCommand(mainCommand);
            return;
        }

        String secondaryCommand = commands[1];
        handleMultiWordsCommand(mainCommand, secondaryCommand);
    }

    private void handleSingleWordCommand(String command) {
        switch(command) {
            case "bye":
                Printer.print("Bye. See you later master!");
                this.isListening = false;
                break;

            case "list":
                Printer.print(this.storage.toString());
                break;
        }
    }

    private void handleMultiWordsCommand(String primaryCommand, String secondaryCommand) {
        Task currentTask;

        switch(primaryCommand) {
            case "mark":
                int markedIndex = Integer.parseInt(secondaryCommand) - 1;
                currentTask = this.storage.getTaskWithIndex(markedIndex);
                currentTask.markAsFinished();
                break;

            case "unmark":
                int unMarkedIndex = Integer.parseInt(secondaryCommand) - 1;
                currentTask = this.storage.getTaskWithIndex(unMarkedIndex);
                currentTask.markAsNotFinished();
                break;

            case "todo":
                currentTask = new ToDo(secondaryCommand);
                this.storage.addTask(currentTask);
                break;

            case "deadline":
                String[] deadlineInfo = secondaryCommand.split("/by ", 2);
                currentTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                this.storage.addTask(currentTask);
                break;

            case "event":
                String[] eventInfo = secondaryCommand.split("/at ", 2);
                currentTask = new Event(eventInfo[0], eventInfo[1]);
                this.storage.addTask(currentTask);
                break;
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
