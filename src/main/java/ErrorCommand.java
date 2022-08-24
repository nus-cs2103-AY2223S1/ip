public class ErrorCommand extends Commands{
    public ErrorCommand(){

    }
    @Override
    public void execute(TaskList<Todo> list, Storage storage) {
        //do nothing
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
