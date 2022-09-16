package duke.comparator;

import java.util.Comparator;

import duke.task.Task;

public class DateComparator implements Comparator<Task> {
    /**
     * {@inheritdocs}
     * @param o1 {@inheritdocs}
     * @param o2 {@inheritdocs}
     * @return {@inheritdocs}
     */
    @Override
    public int compare(Task o1, Task o2) {

        return o1.getParsedDate().getDateTime().compareTo(o2.getParsedDate().getDateTime());
    }

    /**
     * {@inheritdocs}
     * @param obj {@inheritdocs}
     * @return {@inheritdocs}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DateComparator)) {
            return false;
        }

        return true;
    }
}
