package duke.comparator;

import java.util.Comparator;

import duke.task.Task;

public class CompletionComparator implements Comparator<Task> {

    /**
     * {@inheritdocs}
     * @param o1 {@inheritdocs}
     * @param o2 {@inheritdocs}
     * @return {@inheritdocs}
     */
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.isDone() && o2.isDone()) {
            return 0;
        } else if (o2.isDone()) {
            return -1;
        } else if (o1.isDone()) {
            return 1;
        } else {
            return 0;
        }
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
        if (!(obj instanceof CompletionComparator)) {
            return false;
        }

        return true;
    }
}
