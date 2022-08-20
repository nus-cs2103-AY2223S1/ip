import java.util.ArrayList;
import java.util.List;

public class DobbyList {
    private static List<Task> dobbyList = new ArrayList<>();

    //old add function without task differentiation, no longer used
    public void add(String s) {
        Task newTask = new Task(s);
        dobbyList.add(newTask);
    }
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
    @Override
    public String toString() {
        if(getLength() == 0) {
            return "Dobby is FREEEEEEE";
        } else {
            String dobbyListString = "";
            String intro = "Here are the tasks in your list: " + "\n\t";

            int i = 0;
            for(Task dobbyTask : dobbyList) {
/*          Old Method of getting list in string form

            String task = dobbyList.get(i).toString();
            String status = dobbyList.get(i).getStatusIcon();
            dobbyListString += (i+1) + "."
                                + "[" + status + "] "
                                + task + "\n\t";
*/
                dobbyListString += (i+1) + "." + dobbyTask.toString() + "\n\t";
                i++;
            }
            return intro + dobbyListString;
        }
    }
    public String toSave() {
        if(getLength() == 0) {
            return "No Task Available";
        } else {
            String dobbyListString = "";
            int i = 0;
            for( Task dobbyTask : dobbyList) {
                dobbyListString += dobbyTask.toPrint() + "\n";
                i++;
            }
            return dobbyListString;
        }
    }
    public String getTaskString(int i) {
/*      Old method of getting individual task in string form

        String task = dobbyList.get(i).toString();
        String status = dobbyList.get(i).getStatusIcon();
        return "\t[" + status + "] " + task;
 */
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
