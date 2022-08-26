abstract class Task {
    private String description;
    private boolean isDone = false;

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
        private String date;
        public Deadline(String description, String date) {
            super(description);
            this.date = date;
        }

        @Override
        public String toString() {
            String res = "[D]";
            res += super.toString();
            res += "|" + date+ ")";
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
            res += "|";
            res += date;
            return res;
        }
    }

    private Task(String text) {
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
        String arr[] = argument.split("\\|", 2);
        if(arr.length < 2) {
            throw DukeException.invalidArgument;
        }
        return new Deadline(arr[0], arr[1]);
    }

    public static Task Event(String argument) throws DukeException {
        String arr[] = argument.split("\\|", 2);
        if(arr.length < 2) {
            throw DukeException.invalidArgument;
        }
        return new Event(arr[0], arr[1]);
    }

    public static Task parseFromString(String string) throws DukeException{
        String arr[] = string.split("\\|", 3);
        Task task;
        switch (arr[0]) {
            case "[T]":
                task = ToDo(arr[2]);
                break;
            case "[E]":
                task = Event(arr[2]);
                break;
            case "[D]":
                task = Deadline(arr[2]);
                break;
            default:
                throw DukeException.invalidArgument;
        }
        if (arr[1].equals("[X]")) {
            task.changeMark(true);
        }
        return task;
    }

    @Override
    public String toString() {
        String res = "|";

        if (isDone) {
            res += "[X]|";
        } else {
            res += "[ ]|";
        }

        res += this.description;
        return res;
    }
}