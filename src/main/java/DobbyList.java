import java.util.ArrayList;
import java.util.List;

public class DobbyList {
    private static List<Task> dobbyList = new ArrayList<>();

    public void add(Task newTask) {
        dobbyList.add(newTask);
    }
    public void mark(int toMark) {
        dobbyList.get(toMark - 1).mark();
    }
    public void unmark(int toUnmark) {
        dobbyList.get(toUnmark - 1).unmark();
    }
    public void delete(int toDelete) {
        dobbyList.remove(toDelete - 1);
    }

    //TODO Update methods to use parser
    public void addTask(String string) {
        String s = string.substring(5);
        boolean isDone = s.charAt(0) == 'X';
        String taskAndDate = s.substring(5);
        if(string.startsWith("E")) {
            addEvent(taskAndDate, isDone);
        } else if(string.startsWith("D")) {
            addDeadline(taskAndDate, isDone);
        } else if(string.startsWith("T")) {
            addTodo(taskAndDate, isDone);
        } else if(string.equals("No Task Available")) {

        } else {
            DobbyChat.wrongTaskFormat();
        }
    }

    //to be updated then deleted
    public void addTodo(String desc, boolean isDone) {
        Todo newTodo = new Todo(desc, isDone);
        dobbyList.add(newTodo);
    }
    //to be updated then deleted
    public void addDeadline(String string, boolean isDone) {
        int endIndex = string.indexOf("|") - 2;
        String task = string.substring(0, endIndex);
        String date = string.substring(endIndex + 4);
        Deadline newDeadline = new Deadline(task, date, isDone);
    }
    //to be updated then deleted
    public void addEvent(String string, boolean isDone) {
        int endIndex = string.indexOf("|") - 2;
        String task = string.substring(0, endIndex);
        String date = string.substring(endIndex + 4);
        Event newEvent = new Event(task, date, isDone);
    }

//    //DONE
//    public void addDeadline(String desc, String date, boolean isDone) {
//        Deadline newDeadline = new Deadline(desc, date, isDone);
//        dobbyList.add(newDeadline);
//    }
//    public void addEvent(String desc, String date, boolean isDone) {
//        Event newEvent = new Event(desc, date, isDone);
//        dobbyList.add(newEvent);
//    }

    @Override
    public String toString() {
        String dobbyListString = "";
        String intro = "Here are the tasks in your list: " + "\n\t";

        int i = 0;
        for(Task dobbyTask : dobbyList) {
            dobbyListString += (i+1) + "." + dobbyTask.toString() + "\n\t";
            i++;
        }
        return intro + dobbyListString;
    }
    public String toPrint() {
        if(getLength() == 0) {
            return "No Task Available";
        } else {
            String dobbyListString = "";
            for( Task dobbyTask : dobbyList) {
                dobbyListString += dobbyTask.toPrint() + "\n";
            }
            return dobbyListString;
        }
    }
    public String getTaskString(int i) {
        return dobbyList.get(i).toString();
    }
    public Task getTask(int i) {
        return dobbyList.get(i - 1);
    }
    public int getLength() {
        return dobbyList.size();
    }
    public Boolean isEmpty() {
        return dobbyList.size() == 0;
    }
}