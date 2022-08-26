public class Todo extends Task {

    public Todo(String name, boolean isDone) throws DukeTaskException {
        super(name, isDone);
    }

    public static Todo load(String s) throws DukeException {
        String name = s.substring(1, s.indexOf("|")).trim();
        String isDone = s.substring(s.indexOf("|") + 1).trim();
        return new Todo(name, Boolean.parseBoolean(isDone));
    }

    @Override
    public String saveString() {
        return "T " + super.saveString();
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[T]" + temp;
    }


}
