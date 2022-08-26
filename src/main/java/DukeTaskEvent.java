public class DukeTaskEvent extends DukeTask{
    
    String time;
    
    DukeTaskEvent (String task, boolean mark, char type, String time) {
        super(task, mark, type);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + (time.isBlank() ? "" : time);
    }

    public String getTime() {
        return time;
    }
   
}
