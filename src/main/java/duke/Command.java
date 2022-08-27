package duke;

public class Command {
    private final String keyword;
    private final String content;
    private final String EXIT = "bye";
    private final String COMPLETE = "done";
    private final String INCOMPLETE = "not done";

    public Command(String keyword, String content) {
        this.keyword = keyword.toLowerCase();
        this.content = content;
    }

    protected int getContentId() {
        int index = content.indexOf(' ');
        if (index > 0) {
            return Integer.parseInt(content.substring(0, index));
        }
        return Integer.parseInt(content);
    }

    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Parser.isTaskKeyword(keyword)) {
            Parser.isValidTaskCommand(keyword, content);
            if (keyword.equals("todo")) {
                Task newTask = new Todo(content, 'T');
                tasks.add(newTask);
                ui.addTaskConfirmation(newTask, tasks.getSize());
            } else if (keyword.equals("deadline")) {
                Task newTask = new Deadline(content, 'D');
                tasks.add(newTask);
                ui.addTaskConfirmation(newTask, tasks.getSize());
            } else if (keyword.equals("event")) {
                Task newTask = new Event(content, 'E');
                tasks.add(newTask);
                ui.addTaskConfirmation(newTask, tasks.getSize());
            }
        } else {
            if (keyword.equals("list")) {
                ui.display(tasks.enumerateList());
            } else if (keyword.equals(EXIT)) {
                ui.lineBreak();
                ui.outro();
            } else {
                int index = getContentId() - 1;
                if (keyword.equals("mark")) {
                    Task updatedTask = tasks.markTask(index);
                    ui.updateTask(updatedTask, COMPLETE);
                } else if (keyword.equals("unmark")) {
                    Task updatedTask = tasks.unmarkTask(index);
                    ui.updateTask(updatedTask, INCOMPLETE);
                } else if (keyword.equals("delete")) {
                    Task deletedTask = tasks.delete(index);
                    ui.deleteTaskConfirmation(deletedTask, tasks.getSize());
                }
            }
        }
        storage.save(tasks.enumerateList());
    }

    protected boolean isBye(){
        return keyword.equals(EXIT);
    }
}
