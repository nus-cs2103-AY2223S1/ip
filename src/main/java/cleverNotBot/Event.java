package cleverNotBot;

public class Event extends Task{
    private String at;

    public Event(String name, boolean completed, String at){
        super(name,completed);
        this.at = at;
    }

    @Override
    public String getTime() {
        return at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public Task toggleCompleted(){
        return new Event(getName(),!isCompleted(),at);
    }

    @Override
    public String toString(){
        return String.format("[E][%s] %s (at: %s)",checkMarked(),getName(),at);
    }
}
