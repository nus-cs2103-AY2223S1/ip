package task;

/**
 * Represent a generic event task format that is used by Duke. The event task is constrained by
 * any type of time limit that the user defines.
 */
public class DukeTaskEvent extends DukeTask{
    
    private String time;
    
    public DukeTaskEvent (String task, boolean mark, char type, String time) {
        super(task, mark, type);
        this.time = time;
    }

    /**
     * Print out a format of event task.
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + (time.isBlank() ? "" : time);
    }

    /**
     * Print out a format of event task. This is for save file use.
     * @return String
     */
    @Override
    public String toStringSaveFile() {
        return super.toStringSaveFile() + "/" + this.time;
    }

    /**
     * Return time.
     * @return String
     */
    public String getTime() {
        return time;
    }
   
}
