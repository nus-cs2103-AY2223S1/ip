package duke;

<<<<<<< HEAD:src/main/java/duke/Events.java
/**
 * Represents a task of an event.
 */
public class Events extends Task{
    String time;
    String type;

    /**
     * Creates an Events object.
     * @param name Name of the Event task.
     * @param isDone Status of whether the task is done.
     * @param time The time of the event task inputted by user.
     */
    Events(String name, boolean isDone, String time){
=======
public class Event extends Task{
    String time;
    String type;
    Event(String name, boolean isDone, String time){
>>>>>>> branch-A-CodingStandard:src/main/java/duke/Event.java
        super(name, isDone);
        this.time = time;
        this.type = "[E]";
    }

    /**
     * Represents an Event task as a String.
     * @return String representation that include the type, status, name and time of the event.
     */
    @Override
    public String toString() {
        return this.type + super.getStatus() + " (at: " + this.time + ")";
    }
}
