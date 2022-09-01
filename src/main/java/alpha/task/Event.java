package alpha.task;

public class Event extends Task {

    protected String date;

    public Event(String description, String date, String taskType) {
        super(description, taskType);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(uI.getANSI_CODE("ANSI_RED") + " (on: %s)", date + uI.getANSI_CODE(""));
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Event) {
            Event e = (Event) obj;
            return (e.getDescription().equals(this.getDescription()) && e.getStatus().equals(this.getStatus())
                    && e.taskType.equals(this.taskType) && e.getDate().equals(this.getDate()));
        }
        return false;
    }
}
