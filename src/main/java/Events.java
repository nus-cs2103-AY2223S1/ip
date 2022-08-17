public class Events extends Task{
    String time;
    String type;
    Events(String name, String time){
        super(name);
        this.time = time;
        this.type = "[E]";
    }
    @Override
    public String toString() {
        return this.type + super.getStatus() + " (" + this.time + ")";
    }
}
