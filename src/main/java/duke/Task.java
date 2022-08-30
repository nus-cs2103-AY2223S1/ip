package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description task's description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return marked X if its done else dont mark
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * set isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * set isDone to false
     */
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
                ToDo input = new ToDo(str);
                Ui.addedTask(tasks, input);
            }
        }
        else if(str.split(" ", 2)[0].equals("deadline")) {
            int index = str.indexOf("/");
            String taskNameWithType = str.substring(0, index - 1);
            String taskNameOnly = taskNameWithType.split(" ",2)[1];
            Deadline input = new Deadline(taskNameOnly);
            input.date = new FormatDate(str.substring(index + 4));
            Ui.addedTask(tasks, input);
        }
        else if(str.split(" ", 2)[0].equals("event")) {
            int index = str.indexOf("/");
            String taskNameWithType = str.substring(0, index - 1);
            String taskNameOnly = taskNameWithType.split(" ",2)[1];
            Event input = new Event(taskNameOnly);
            input.day = new FormatDate(str.substring(index + 4));
            Ui.addedTask(tasks, input);
        }
    }

    /**
     *
     * @param str the "find book" command to be passed in
     * @param tasks the taskList which we are searching from
     */
    public static void find(String str, TaskList tasks) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(str.substring(5))) {
                result.add(task);
            }
        }
        Ui.finderPrinter(result);
    }
}
