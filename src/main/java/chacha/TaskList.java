package chacha;
import java.util.ArrayList;
import java.util.List;

import chacha.tasks.Task;

public class TaskList {
    
        private List<Task> tasks;
        
        public TaskList() {
            this.tasks = new ArrayList<>();
        }
    
        
        public TaskList(ArrayList<Task> inputTasks) {
            this.tasks = new ArrayList<>();
            for(Task task : inputTasks) {
                tasks.add(task);
            }
        }

        
        /** 
         * @return int
         */
        public int getSize() {
            return tasks.size();
        }

        
        /** 
         * @param taskIndex
         * @return Task
         */
        public Task get(int taskIndex) {
            return tasks.get(taskIndex);
        }

        
        /** 
         * @param task
         */
        public void add(Task task) {
            tasks.add(task);
        }

        
        /** 
         * @param taskIndex
         */
        public void remove(int taskIndex) {
            tasks.remove(taskIndex);
        }

        /**
         * Finds all tasks with keyword in description.
         * 
         * @param keyword Input keyword to search for.
         * @return New task list with tasks containing keyword.
         */
        public TaskList find(String keyword) {
            TaskList newTasks = new TaskList();
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    newTasks.add(task);
                }
            }
            return newTasks;
        }


}
