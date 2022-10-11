package rattus.chatbot.data.task;

import java.util.ArrayList;
import java.util.function.Predicate;

import rattus.chatbot.util.MessageBuilder;

/**
 * A list of tasks.
 *
 * @author jq1836
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Returns an instance of {@link Task} that corresponds to the entry number given.
     *
     * @param entry The index of the element to return, with index 1 corresponding to the first element.
     * @return A task that corresponds to the entry number given.
     */
    @Override
    public Task get(int entry) {
        if (!isInRange(entry)) {
            return null;
        }
        return super.get(entry - 1);
    }

    /**
     * Returns an instance of {@link Task} that corresponds to the entry number given. Removes the task from the list
     * in the process.
     *
     * @param entry The index of the element to be removed, with index 1 corresponding to the first element.
     * @return A task that corresponds to the entry number given.
     */
    @Override
    public Task remove(int entry) {
        if (!isInRange(entry)) {
            return null;
        }
        return super.remove(entry - 1);
    }

    /**
     * Returns true if the entry number corresponds to an entry in the list.
     *
     * @param entry The index to be compared with the last index.
     * @return A boolean describing whether the entry number is in range.
     */
    public boolean isInRange(int entry) {
        return entry > 0 && entry <= size();
    }

    @Override
    public String toString() {
        MessageBuilder messageBuilder = new MessageBuilder();
        for (Integer entry = 1; entry < size() + 1; entry++) {
            messageBuilder.buildLine("\t", entry.toString(), ".", get(entry).toString());
        }
        return messageBuilder.toString();
    }

    /**
     * Returns an instance of {@link TaskList} that contains the tasks that satisfies the condition.
     *
     * @param condition A predicate which tests if a task satisfies the condition.
     * @return An instance of TaskList that contains the tasks that satisfies the condition.
     */
    public TaskList filter(Predicate<Task> condition) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : this) {
            if (condition.test(task)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * Returns a string that encodes all the tasks.
     *
     * @return A string that encodes all the tasks.
     */
    public String encodeAll() {
        String result = "";
        for (int entry = 1; entry < this.size() + 1; entry++) {
            result += this.get(entry).encode() + "\n";
        }
        return result;
    }
}
