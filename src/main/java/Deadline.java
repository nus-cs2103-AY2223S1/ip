public class Deadline extends Task{
    private String during;
    private String time;

    public Deadline(ParsedData parsedData) {
        super(parsedData);
        this.during = parsedData.getDuring();
        this.time = parsedData.getTime();
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getTypeLetter() {
        return "D";
    }

    @Override
    public String getDuring() {
        return this.during;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s (%s: %s)",
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description,
                this.during,
                this.time);
    }
}
