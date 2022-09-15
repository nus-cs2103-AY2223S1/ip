package chacha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import chacha.tasks.Task;

/**
 * Task list containing tasks.
 */
public class TaskList {
    
        private List<Task> tasks;
        
        /**
         * Constructor for new task list.
         */
        public TaskList() {
            this.tasks = new ArrayList<>();
        }
    
        /**
         * Constructor for task list with tasks.
         * 
         * @param inputTasks List of tasks to input into task list.
         */
        public TaskList(ArrayList<Task> inputTasks) {
            this.tasks = new ArrayList<>();
            for(Task task : inputTasks) {
                tasks.add(task);
            }
        }

        /** 
         * Gets the length of task list.
         * 
         * @return Length of task list.
         */
        public int getSize() {
            return tasks.size();
        }
        
        /** 
         * Gets the task at a certain index.
         * 
         * @param taskIndex Index of task in task list to get.
         * @return Task at given index.
         */
        public Task get(int taskIndex) {
            return tasks.get(taskIndex);
        }

        /** 
         * Adds a task to task list.
         * 
         * @param task To be added to task list.
         */
        public void add(Task task) {
            tasks.add(task);
        }
        
        /** 
         * Removes task at a certain index.
         * 
         * @param taskIndex Task at a given index.
         */
        public void remove(int taskIndex) {
            tasks.remove(taskIndex);
        }

        /**
         * Finds all tasks with keyword in description.
         * 
         * @param keywords Input varargs keywords to search for.
         * @return New task list with tasks containing keyword.
         */
        public TaskList find(String ... keywords) {
            TaskList newTasks = new TaskList();
            for (Task task : tasks) {
                for (String keyword : keywords) {
                    if (task.getDescription().contains(keyword)) {
                        newTasks.add(task);
                        break;
                    }
                }

            }
            return newTasks;
        }

        public void sort() {
            Collections.sort(tasks, new DateComparator());
        }


}
