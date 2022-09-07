package duke;

import java.time.LocalDate;

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
        private LocalDate date;
        public Deadline(String description, String date) {
            super(description);
            this.date = LocalDate.parse(date);
        }

        @Override
        public String toString() {
            String res = "[D]";
            res += super.toString();
            res += "|" + date;
            return res;
        }
    }

    private static class Event extends Task {
        private LocalDate date;
        public Event(String description, String date) {
            super(description);
            this.date = LocalDate.parse(date);
        }

        @Override
        public String toString() {
            String res = "[E]";
            res += super.toString();
            res += "|" + date;
            return res;
        }
    }

    private Task(String text) {
        description = text;
    }

    public Task changeMark(boolean arg) throws DukeException {
        if (isDone != arg) {
            isDone = arg;
            return this;
        }
        if (arg) {
            throw DukeException.TASKALREADYMARKED;
        } else {
            throw DukeException.TASKALREADYUNMARKED;
        }
    }


    public boolean hasKeyword(String key) {
        return description.contains(key);
    }

    public static Task createToDo(String argument) {
        return new ToDo(argument);
    }

    public static Task createDeadline(String argument) throws DukeException {
        String[] arr = argument.split("\\|", 2);
        if (arr.length < 2) {
            throw DukeException.INVALIDARGUMENT;
        }
        return new Deadline(arr[0], arr[1]);
    }

    public static Task createEvent(String argument) throws DukeException {
        String[] arr = argument.split("\\|", 2);
        if (arr.length < 2) {
            throw DukeException.INVALIDARGUMENT;
        }
        return new Event(arr[0], arr[1]);
    }

    public static Task parseFromString(String string) throws DukeException {
        String[] arr = string.split("\\|", 3);
        Task task;
        switch (arr[0]) {
        case "[T]":
            task = createToDo(arr[2]);
            break;
        case "[E]":
            task = createEvent(arr[2]);
            break;
        case "[D]":
            task = createDeadline(arr[2]);
            break;
        default:
            throw DukeException.INVALIDARGUMENT;
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
