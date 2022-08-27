package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * use duke.todo, duke.event, deadlines
     *
     * @param tasks
     * @param str
     */
    public static void makeTask(TaskList tasks, String str) throws DukeException {
        if(str.split(" ", 2)[0].equals("todo")) {

            if (str.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                String task = str.split(" ", 2)[1];
                todo input = new todo(str);
                Ui.addedTask(tasks, input);
            }
        }
        else if(str.split(" ", 2)[0].equals("deadline")) {
            int index = str.indexOf("/");
            String taskName = str.substring(0, index - 1);
            String taskNameOnly = taskName.split(" ",2)[1];
            deadline input = new deadline(taskNameOnly);
            input.date = new formatDate(str.substring(index + 4));
            Ui.addedTask(tasks, input);
        }
        else if(str.split(" ", 2)[0].equals("event")) {
            int index = str.indexOf("/");
            String taskName = str.substring(0, index - 1);
            String taskNameOnly = taskName.split(" ",2)[1];
            event input = new event(taskNameOnly);
            input.day = new formatDate(str.substring(index + 4));
            Ui.addedTask(tasks, input);
        }
    }
}
