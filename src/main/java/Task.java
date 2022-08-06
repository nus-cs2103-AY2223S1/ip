public class Task {

    private String name;
    private boolean status = false;

    public Task(String name){
        this.name = name;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setDone(){
        status = true;
    }

    public void setNotDone(){
        status = false;
    }

    /**
     * Overriden toString function. Prints the completion status and the task name
     * @return String representation of this task.
     */
    @Override
    public String toString(){
        if(status == true)
            return "[X] " + name;
        else
            return "[ ] " + name;
    }
}
