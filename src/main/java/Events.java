public class Events extends Task {
    private String timing;

    public Events(String input) throws MissingDescriptionException, MissingTimingException {
        super();
        try {
            //remove initial command
            String sub = input.substring(6);
            int timeIndex = sub.lastIndexOf("/at");
            //get description part of input string
            if (timeIndex == -1) {
                throw new MissingTimingException();
            }
            String description = sub.substring(0, timeIndex - 1);
            this.description = description;
            this.timing = sub.substring(timeIndex + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }
    }

    public Events(String input, boolean isDone) throws MissingDescriptionException, MissingTimingException {
        super(isDone);
        try {
            //remove initial command
            String sub = input.substring(6);
            int timeIndex = sub.lastIndexOf("/at");
            //get description part of input string
            if (timeIndex == -1) {
                throw new MissingTimingException();
            }
            String description = sub.substring(0, timeIndex - 1);
            this.description = description;
            this.timing = sub.substring(timeIndex + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }
    }

    public Events(String description, String timing, boolean isDone) {
        super(isDone);
        this.description = description;
        this.timing = timing;
    }

    @Override
    String processData() {
        String str;
        if (this.getIsDone()){
            str = String.format("E|true|%s|%s|", this.getDescription(), this.timing);
        } else {
            str = String.format("E|false|%s|%s|", this.getDescription(), this.timing);
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()){
            str = String.format("[E] %s [X] (at %s)", this.getDescription(), this.timing);
        } else {
            str = String.format("[E] %s [ ] (at %s)", this.getDescription(), this.timing);
        }
        return str;
    }
}
