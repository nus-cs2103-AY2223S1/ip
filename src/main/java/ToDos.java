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
