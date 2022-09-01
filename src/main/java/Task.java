public abstract class Task {
    private final String name;
    private final boolean completed;


    public Task(String name, boolean completed){
        this.name = name;
        this.completed = completed;
    }

    public String getName(){
        return name;
    }

    public boolean isCompleted(){
        return completed;
    }

    public String checkMarked(){
        return isCompleted()? "X":" ";
    }

    public abstract String getTime();

    public abstract String getTaskType();

    public abstract Task toggleCompleted();
    //return new Task(name,!completed);


    @Override
    public String toString() {
        return name;
    }
}
