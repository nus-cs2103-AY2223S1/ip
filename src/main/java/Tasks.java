import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Tasks {

    // class variables
    private ArrayList<Task> taskList;

    // constructor
    public Tasks(int n) {
        this.taskList = new ArrayList<>(n);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void markMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= this.taskList.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = this.taskList.get(index);
                    task.markAsDone();
                    DukeMessage.sendMessage(" Nice! I've marked this task as done:\n" + "   " + task.toString());
                }
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void unmarkMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= this.taskList.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = this.taskList.get(index);
                    task.markAsUndone();
                    DukeMessage.sendMessage(" Nice! I've marked this task as undone:\n" + "   " + task.toString());
                }
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void todoMessage(String userAction) throws DukeException {
        try {
            Task newTodo = new Todo(userAction);
            if (newTodo.description.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else{
                taskList.add(newTodo);
                DukeMessage.sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                        + "\n Now you have " + this.taskList.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void eventMessage(String userAction) throws DukeException {
        try {
            String[] eventString = userAction.split("/at ");
            if (eventString[0].equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (eventString.length == 1) {
                throw new DukeException("The description or date/time of an event cannot be empty.");
            } else {
                Task newEvent = new Event(eventString[0], eventString[1]);
                taskList.add(newEvent);
                DukeMessage.sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                        + "\n Now you have " + this.taskList.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public void deadlineMessage(String userAction) throws DukeException {
        try {
            String[] deadlineString = userAction.split("/by ");
            if (deadlineString[0].equals("")) {
                throw new DukeException("The description and date/time of a deadline cannot be empty.");
            } else if (deadlineString.length == 1) {
                throw new DukeException("The description or date/time of a deadline cannot be empty.");
            } else {
                System.out.println(deadlineString[1]);
                Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
                taskList.add(newDeadline);
                DukeMessage.sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                            + "\n Now you have " + this.taskList.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public LocalDateTime formatDeadline(String s) throws DukeException {
        LocalDateTime dateToFormat = null;
        boolean isFormatted = false;
        try {
            dateToFormat = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
            isFormatted = true;
        } catch (DateTimeParseException e) {;
            System.out.println(s);
            System.out.println("for deadlines, it has to be in the format dd-mm-yyyy");
        }
        if (isFormatted) {
            return dateToFormat;
        } else {
            throw new DukeException("for deadlines, it has to be in the format dd-mm-yyyy HH:mm:ss");
        }
    }

    public void deleteMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= this.taskList.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = this.taskList.get(index);
                    this.taskList.remove(index);
                    DukeMessage.sendMessage(" Noted. I've removed this task:\n" + "   " + task.toString()
                            + "\n Now you have " + this.taskList.size() + " tasks in the list.");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getList() {
        String list = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                list = list + " " + (i + 1) + ": " + taskList.get(i).toString();
                break;
            }
            list = list + " " + (i + 1) + ": " + taskList.get(i).toString() + "\n";
        }
        String listMessage = " Here are the tasks in your list:\n" + list;
        DukeMessage.sendMessage(listMessage);
    }
}