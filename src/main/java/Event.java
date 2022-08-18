public class Event extends Task {
    private final String timing;

    public Event(String desc, int id, char taskType) {
        super(desc, id, taskType);
        this.timing = getTiming(desc);
    }

    private String getTiming(String desc) {
        int index = desc.indexOf('/');
        return desc.substring(index + 1);
    }
}
