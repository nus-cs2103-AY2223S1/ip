public class DeadlineCmd extends Command {
    private String body;

    public DeadlineCmd(String body) {
        this.body = body;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {
        //Check for "/by", if not available then prompt user to add timing.
        if (!body.contains("/by")) {
            throw new DENoTimingException("by");
        } else {
            //Parse the string. Make sure there is no multiple "/by" statements.
            String[] parse = body.replaceAll("\\s+", "").split("/by");
            if (parse.length > 2) {
                throw new DETimingOverflowException();
            } else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank()) {
                throw new DENoArgException();
            } else {
                addTaskType(new Deadline(parse[0], parse[1]), tasks, ui);
            }
        }

        saveUserTasks(storage, tasks);
    }
}
