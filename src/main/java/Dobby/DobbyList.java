package dobby;

import dobby.commands.*;
import dobby.tasks.*;

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

    //adding task to dL from .txt file
    public void addTask(String string) {
        String taskType = Parser.getTaskTypeTxt(string);
        boolean isDone = Parser.getStatusTxt(string);
        String rest = Parser.getRestTxt(string);

        if(taskType.equals("T")) {
            add(new Todo(rest, isDone));
        } else if(taskType.equals("E") || taskType.equals("D")) {
            String desc = Parser.getDescTxt(rest);
            String date = Parser.getDateTxt(rest);
            if (taskType.equals("E")) {
                add(new Event(desc, date, isDone));
            } else {
                add(new Deadline(desc, date, isDone));
            }
        } else {
            DobbyChat.wrongTaskFormat();
        }
    }

//    //adding task to dL from Ui
//    public void addTask(String cmd, String desc, String date) {
//
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
        return dobbyList.get(i - 1).toString();
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