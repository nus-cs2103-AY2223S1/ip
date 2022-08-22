import java.time.LocalDate;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task of(String input) throws BlinkException{
        String[] info = input.split("\\|", 3);
        switch(info[0].strip()) {
            case "T":
                ToDos todo = new ToDos(info[2].strip());
                if (info[1].strip().equals("1")) {
                    todo.isDone = true;
                }
                return todo;
            case "D":
                String[] desc = info[2].split("\\|");
                Deadlines deadline = new Deadlines(desc[0].strip(), desc[1].strip());
                if (info[1].strip().equals("1")) {
                    deadline.isDone = true;
                }
                return deadline;
            case "E":
                String[] temp = info[2].split("\\|");
                Events event = new Events(temp[0].strip(), temp[1].strip());
                if (info[1].strip().equals("1")) {
                    event.isDone = true;
                }
                return event;
            default:
                throw new BlinkException("wtf happened");
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String mark() throws BlinkException{
        if (this.isDone) {
            throw new BlinkException("This task has already been done :|");
        } else {
            this.isDone = true;
            return "Mission complete! Nice ah\n" + this;
        }
    }

    public String unMark() throws BlinkException{
        if (!this.isDone) {
            throw new BlinkException("An unfinished task cannot be unmark...");
        } else {
            this.isDone = false;
            return "Looks like there is more work to do\n" + this;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    abstract String saveString();

    abstract boolean checkDate(LocalDate anoDate);
}