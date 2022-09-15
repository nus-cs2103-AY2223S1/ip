package chacha;

import chacha.tasks.Task;

import java.time.LocalDateTime;
import java.util.Comparator;

    public class DateComparator implements Comparator<Task> {

        @Override
        public int compare(Task task1, Task task2) {
            System.out.println("sorting command");
            return task1.getDate().compareTo(task2.getDate());
        }

    }

