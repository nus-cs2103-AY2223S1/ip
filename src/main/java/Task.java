public class Task {
    private String taskDesc;

    public Task(String taskDesc){
        this.taskDesc = taskDesc;
    }

    @Override
    public String toString(){
        return this.taskDesc;
    }
}
