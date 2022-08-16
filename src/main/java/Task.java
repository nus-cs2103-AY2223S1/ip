public class Task {
    protected String name;
    protected boolean isDone;
    Task(String name){
        this.name = name;
        this.isDone = false;
    }
  /*  String getName() {
        return this.name;
    }
   */
    public String getStatusIcon() {
       return(this.isDone? "[X] " : "[] " );
    }

    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }
    public String getMarkStatus() {
        return this.getStatusIcon() + "" + this.name;
    }

}
