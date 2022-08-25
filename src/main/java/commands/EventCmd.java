public class EventCmd extends Command {
    private String body;

    public EventCmd(String body) {
        this.body = body;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {
        //Check for "/at", if not available then prompt user to add timing.
        if (!body.contains("/at")) {
            throw new DENoTimingException("at");
        } else {
            //Parse the string. Make sure there is no multiple "/at" statements.
            String[] parse = body.replaceAll("\\s+", "").split("/at");
            if (parse.length > 2) {
                throw new DETimingOverflowException();
            } else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank()) {
                throw new DENoArgException();
            } else {
                addTaskType(new Event(parse[0], parse[1]), tasks, ui);
            }
        }

        saveUserTasks(storage, tasks);
    }
}
