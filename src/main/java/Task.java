abstract public class Task {
    private  String modifier = "[?]";
    private String description;
    private String addOn = "nya!";
    private Boolean completed;

    private String notDoneSymbol = "[Zzzzz]";

    private String doneSymbol    = "[/ᐠ｡ꞈ｡ᐟ\\]";

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String describe() {
        return this.description + " " + addOn;
    }
    public Boolean status() {
        return this.completed;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsNotDone() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String symbol;
        if (this.completed) {
            symbol = doneSymbol;
        } else {
            symbol = notDoneSymbol;
        }
        return symbol + " " + this.description + " " + addOn;
    }
}
