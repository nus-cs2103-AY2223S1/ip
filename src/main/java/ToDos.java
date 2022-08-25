public class ToDos extends Task {
    public ToDos(String description) throws MissingDescriptionException {
        super();
        try {
            String substring = description.substring(5);
            this.description = substring;
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }
    }

    public ToDos(String description, boolean isDone) {
        super(isDone);
        this.description = description;
    }

    @Override
    public String processData() {
        String str;
        if (this.getIsDone()){
            str = String.format("T|true|%s|0|", this.getDescription());
        } else {
            str = String.format("T|false|%s|0|", this.getDescription());
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()){
            str = String.format("[T] %s [X]", this.getDescription());
        } else {
            str = String.format("[T] %s [ ]", this.getDescription());
        }
        return str;
    }
}
