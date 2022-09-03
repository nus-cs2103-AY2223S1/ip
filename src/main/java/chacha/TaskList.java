package chacha;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chacha.tasks.Task;

public class TaskList {
    
        private List<Task> tasks;
        private HashMap<String, List<Task>> descriptionToTaskMap;
    
        
        public TaskList() {
            this.tasks = new ArrayList<>();
        }
    
        
        public TaskList(ArrayList<Task> inputTasks) {
            this.tasks = new ArrayList<>();
            for(Task task : inputTasks) {
                tasks.add(task);
            }
        }

        public int getSize() {
            return tasks.size();
        }

        public Task get(int taskIndex) {
            return tasks.get(taskIndex);
        }

        public void add(Task task) {
            tasks.add(task);
        }

        public void remove(int taskIndex) {
            tasks.remove(taskIndex);
        }


}
