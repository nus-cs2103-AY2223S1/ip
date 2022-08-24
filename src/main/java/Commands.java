public abstract class Commands {

    public Commands(){
    }
    abstract public void execute(TaskList<Todo> list, Storage storage) throws IndexOutOfBoundsException;
    abstract public boolean isExit();
}
