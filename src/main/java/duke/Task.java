package duke;

public class Task {
    protected String name;
    protected boolean isDone;

    Task(String name, boolean isDone){
        this.name = name;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
       return(this.isDone? "[X] " : "[] " );
    }

    public void mark() {
        this.isDone = true;
        Ui ui = new Ui();
        ui.markHelperUi(this);
    }

    public void unmark() {
        this.isDone = false;
        Ui ui = new Ui();
        ui.unmarkHelperUi(this);
    }

    public String getStatus() {
        return this.getStatusIcon() + "" + this.name;
    }

    }



