package task;

public class DukeTaskEvent extends DukeTask{
    
    private String time;
    
    public DukeTaskEvent (String task, boolean mark, char type, String time) {
        super(task, mark, type);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + (time.isBlank() ? "" : time);
    }

    @Override
    public String toStringSaveFile() {
        return super.toStringSaveFile() + "/" + this.time;
    }

    public String getTime() {
        return time;
    }
   
}
