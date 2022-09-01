public class ToDo extends Task{

    public ToDo(String name, boolean completed){
        super(name,completed);
    }

    @Override
    public String getTime() {
        return "inf";
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public Task toggleCompleted(){
        return new ToDo(getName(),!isCompleted());
    }

    @Override
    public String toString(){
        return String.format("[T][%s] %s",checkMarked(),getName());
    }
}
