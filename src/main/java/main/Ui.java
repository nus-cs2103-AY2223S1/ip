package main;

import exception.DukeException;
import exception.TaskListOutOfBoundsException;
import task.Task;

public class Ui {

    TaskList tasks;

    private final String UI_LINE_SPACING = "----------------------------------------";
    private final String greeting = "Hello! I'm Duke  \n" + "What can I do for you?";
    private final String markMessage = "Task has been marked done:";
    private final String unmarkMessage = "Task has been marked not done:";
    private final String addMessage = "Task added: \n";
    private final String deleteMessage = "Task deleted: \n";
    
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void greeting() {
        this.chat(this.greeting);
    }

    public void list(TaskList tasks) {
        this.chat(tasks.toString());
    }

    public void add(Task newTask) {
        this.chat(String.format("%s%s", addMessage, newTask));
    }

    public void delete(int pos) throws DukeException{
        try {
            this.chat(String.format("%s%s", deleteMessage, this.tasks.get(pos)));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    public void mark(int pos) throws DukeException {
        try {
            this.chat(markMessage + this.tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    public void unmark(int pos) throws DukeException {
        try {
            this.chat(unmarkMessage + tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    public void errorMessage(DukeException e) {
        this.chat(e.getLocalizedMessage());
    }

    public void errorEnd() {
        this.chat("Error in saving Tasks, progress from current session is not saved :(");
    }

    public void bye() {
        this.chat("Bye bye! :D");
    }

    public void chat(String message) {
        System.out.println(UI_LINE_SPACING + "\n" + message + "\n" + UI_LINE_SPACING);
    }
}
