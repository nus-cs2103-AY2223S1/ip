import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
        this.size = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(100);
        this.tasks.addAll(tasks);
        this.size = tasks.size();
    }

    public int getSize() {
        return this.size;
    }

//    public void mark(String cmd) throws DukeException {
//        try {
//            String[] wordArr = cmd.split(" ");
//            if (wordArr.length < 2) {
//                throw new DukeException("☹ OOPS!!! This mark command is invalid.");
//            }
//            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
//            if (index < 0 || index >= tasks.size()) {
//                throw new DukeException("☹ OOPS!!! The index is invalid.");
//            }
//            Task curr = tasks.get(index);
//            if (curr.getStatusIcon().equals(" ")) {
//                curr.markAsDone();
//                msg(INDENT + "Nice! I've marked this task as done:\n" + INDENT + "  " + curr + "\n");
//            } else {
//                msg(INDENT + "This task was already done.\n" + INDENT + "  " + curr + "\n");
//            }
//        } catch (DukeException e) {
//            msg(INDENT + e.getMessage() + "\n");
//        }
//    }
//
//    public void unmark(String cmd) throws DukeException {
//        try {
//            String[] wordArr = cmd.split(" ");
//            if (wordArr.length < 2) {
//                throw new DukeException("☹ OOPS!!! This unmark command is invalid.");
//            }
//            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
//            if (index < 0 || index >= tasks.size()) {
//                throw new DukeException("☹ OOPS!!! The index is invalid.");
//            }
//            Task curr = tasks.get(index);
//            if (curr.getStatusIcon().equals("X")) {
//                curr.unmarkTask();
//                msg(INDENT + "OK, I've marked this task as not done yet:\n" + INDENT + "  " + curr + "\n");
//            } else {
//                msg(INDENT + "This task has not been done in the first place.\n" + INDENT + "  " +
//                        curr + "\n");
//            }
//        } catch (DukeException e) {
//            msg(INDENT + e.getMessage() + "\n");
//        }
//    }

    public void addTask(String cmd) throws DukeException {
        try {
            String type = cmd.split(" ")[0];
            boolean isAdded = false;
            int sizePrev = tasks.size();
            switch (type) {
                case "todo":
                    addTodo(cmd);
                    isAdded = sizePrev != tasks.size();
                    break;

                case "deadline":
                    addDeadline(cmd);
                    isAdded = sizePrev != tasks.size();
                    break;

                case "event":
                    addEvent(cmd);
                    isAdded = sizePrev != tasks.size();
                    break;

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (isAdded) {
                String content;
                Task task = tasks.get(tasks.size() - 1);
                content = "Got it. I've added this task:\n";
                content += "  " + task + "\n";
                content += "Now you have " + tasks.size() + " tasks in the list.\n";
                msg(content);
            }
        } catch (DukeException e) {
            msg(e.getMessage() + "\n");
        }
    }

    public void addTodo(String cmd) throws DukeException {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String desc = cmd.split(" ", 2)[1];
            Todo todo = new Todo(desc);
            tasks.add(tasks.size(), todo);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void addDeadline(String cmd) throws DukeException {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!cmd.contains("/by")) {
                throw new DukeException("☹ OOPS!!! The deadline is required. (/by)");
            }
            String[] div = cmd.split("/");
            String desc = div[0].split(" ", 2)[1];
            if (desc.equals("") || desc.equals(" ")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String by = div[1].split(" ", 2)[1];
            Deadline deadline = new Deadline(desc, by);
            tasks.add(tasks.size(), deadline);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void addEvent(String cmd) throws DukeException {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (!cmd.contains("/at")) {
                throw new DukeException("☹ OOPS!!! Time of event required. (/at)");
            }
            String[] div = cmd.split("/", 2);
            String desc = div[0].split(" ", 2)[1];
            String at = div[1].split(" ", 2)[1];
            Event event = new Event(desc, at);
            tasks.add(tasks.size(), event);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void delete(String cmd) throws DukeException {
        try {
            String content;
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! This delete command is invalid.");
            }
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("☹ OOPS!!! The index is invalid.");
            }
            Task deleted = tasks.remove(index);
            content = INDENT + "Noted. I've removed this task:\n";
            content += INDENT + "  " + deleted + "\n";
            content += INDENT + "Now you have " + tasks.size() + " tasks in the list.\n";
            msg(content);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

}
