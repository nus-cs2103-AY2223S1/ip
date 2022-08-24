import java.util.ArrayList;
import java.util.List;

public class TaskList {
     List<Task> list;

     public TaskList(){
          this.list = new ArrayList<>();
     }
     public TaskList(List<Task> list){
          this.list = list;
     }

     void addTask(Task task){
          list.add(task);
     }

     void markTaskAtIndex (int index) {
          list.get(index).markAsDone();
     }
     void unmarkTaskAtIndex (int index) {
           list.get(index).markAsUndone();
     }

     void removeTaskAtIndex(int index){
          list.remove(index);
     }

     Task getTaskAtIndex (int index) {
          return list.get(index);
     }

     public int listSize(){
          return list.size();
     }

      List<Task> getList(){
          return list;
     }
}
