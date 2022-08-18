abstract class Task {
    String description;
    boolean isDone = false;

    private static class ToDo extends Task {
        public ToDo(String text) {
            super(text);
        }
        @Override
        public String toString() {
            String res = "[T]";
            res += super.toString();
            return res;
        }
    }

    private static class Deadline extends Task {
        String date;
        public Deadline(String description, String date) {
            super(description);
            this.date = date;
        }

        @Override
        public String toString() {
            String res = "[D]";
            res += super.toString();
            res += "(by: " + date+ ")";
            return res;
        }
    }

    private static class Event extends Task {
        String date;
        public Event(String description, String date) {
            super(description);
            this.date = date;
        }

        @Override
        public String toString() {
            String res = "[E]";
            res += super.toString();
            res += "(at: " + date+ ")";
            return res;
        }
    }

    public Task(String text) {
        description = text;
    }

    public void changeMark(boolean arg) throws DukeException {
        if(isDone == arg) {
            if (arg == true) {
                throw DukeException.taskAlreadyMarked;
            } else {
                throw DukeException.taskAlreadyUnmarked;
            }
        }
        isDone = arg;
    }

    public static Task ToDo(String argument) {
        return new ToDo(argument);
    }

    public static Task Deadline(String argument) throws DukeException {
        String arr[] = argument.split("/by ", 2);
        if(arr.length < 2) {
            throw DukeException.invalidArgument;
        }
        return new Deadline(arr[0], arr[1]);
    }

    public static Task Event(String argument) throws DukeException {
        String arr[] = argument.split("/at ", 2);
        if(arr.length < 2) {
            throw DukeException.invalidArgument;
        }
        return new Event(arr[0], arr[1]);
    }

    @Override
    public String toString() {
        String res = "";

        if (isDone) {
            res += "[X] ";
        } else {
            res += "[ ] ";
        }

        res += this.description;
        return res;
    }
}