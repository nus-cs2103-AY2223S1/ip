package util;

import keyword.KeywordPair;
import tasks.Task;

import java.util.List;

/**
 * This class is used for formatting before saving.
 */
public class FileFormatter {
    /**
     * Formats a single task.
     *
     * @param task The task to be formatted.
     * @return The formatted string.
     */
    public String formatTask(Task task) {
        return String.format("%s|%s|%s|%s|%s|\n",
                task.getTypeLetter(),
                task.getStatusLetter(),
                task.getDescription(),
                task.getDuring(),
                task.getTimeText());
    }

    /**
     * Formats a task list.
     *
     * @param taskList The task list to be formatted.
     * @return The formatted string.
     */
    public String formatTaskList(List<Task> taskList) {
        StringBuilder result = new StringBuilder();
        for (Task task : taskList) {
            result.append(formatTask(task));
        }
        return result.toString();
    }

    /**
     * Formats a single keyword.
     *
     * @param pair The keyword pair to be formatted.
     * @return The formatted string.
     */
    public String formatKeyword(KeywordPair pair) {
        return String.format("%s|%s|\n",
                pair.getKeyword(),
                pair.getCommand());
    }

    /**
     * Formats a keyword list.
     *
     * @param keywordPairs The keyword pair list to be formatted.
     * @return The formatted string.
     */
    public String formatKeywordList(List<KeywordPair> keywordPairs) {
        StringBuilder result = new StringBuilder();
        for (KeywordPair pair : keywordPairs) {
            result.append(formatKeyword(pair));
        }
        return result.toString();
    }
}
