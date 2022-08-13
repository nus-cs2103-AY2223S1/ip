package parser;
import printer.Printer;
import storage.Storage;
import task.Task;


public class Parser {
    private boolean isListening = true;
    private Storage storage;

    public Parser(Storage storage) {
        this.storage = storage;
    }

    public void parseText(String text) {
        if (text.equals("bye")) {
            Printer.print("Bye. See you later master!");
            this.isListening = false;
        } else if (text.equals("list")){
            Printer.print(this.storage.toString());
        } else if (text.startsWith("mark")) {
            int markedIndex = Integer.parseInt(text.substring(text.length() - 1)) - 1;
            Task currentTask = this.storage.getTaskWithIndex(markedIndex);
            currentTask.markAsFinished();
            Printer.print(String.format("This task has been marked as done:\n %s",
                    currentTask.toString()));
        } else if (text.startsWith("unmark")) {
            int unMarkedIndex = Integer.parseInt(text.substring(text.length() - 1)) - 1;
            Task currentTask = this.storage.getTaskWithIndex(unMarkedIndex);
            currentTask.markAsNotFinished();
            Printer.print(String.format("This task has been marked as not done yet:\n %s",
                    currentTask.toString()));
        } else {
            Task newTask = new Task(text);
            this.storage.addTask(newTask);
            Printer.print(String.format("This task is successfully added:\n %s",
                    newTask.toString()));
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
