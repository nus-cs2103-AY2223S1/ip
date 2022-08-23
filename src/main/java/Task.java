public class Task {
    private String description;
    private boolean isDone;
    public Task(String description){
        this.description=description;
        this.isDone=false;
    }
    //return the status of this task
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    //mark this task as Done without reply
    public void taskDone(){
        this.isDone=true;
    }
    public void correctDescrition(String rightDescription){
        this.description=rightDescription;
    }
    //undone this task and print reply
    public void taskUndone(){
        this.isDone=false;

    }
    //print the representation of this task containing status and description
    public String printTask(){
        return ("["+this.getStatusIcon()+"]"+" "+this.description);
    }
    //create a certain kind of task
    public static Task createATask(String s) throws DukeException {
        if (s.split(" ")[0].equals("todo")) {
            return new ToDo(s);
        } else if (s.split(" ")[0].equals("deadline")) {
            return new DeadLine(s);
        } else if (s.split(" ")[0].equals("event")) {
            return new Event(s);
        }
        return null;
    }

    public String getDescription(){
        return this.description;
    }
}
