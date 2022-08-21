import java.util.LinkedList;
import java.util.List;

public class KeywordChecker {
    public static final String EXACT_KEYWORD_BYE = "bye";
    public static final String EXACT_KEYWORD_LIST = "list";
    public static final String MARK_KEYWORD_MARK = "mark ";
    public static final String MARK_KEYWORD_UNMARK = "unmark ";
    public static final String TASK_KEYWORD_TODO = "todo ";
    public static final String TASK_KEYWORD_DEADLINE = "deadline ";
    public static final String TASK_KEYWORD_EVENT = "event ";
    private static final LinkedList<String> listOfExactKeywords = new LinkedList<>(
            List.of(KeywordChecker.EXACT_KEYWORD_BYE,
                    KeywordChecker.EXACT_KEYWORD_LIST)
    );

    public static Boolean containsExactKeyword(String userInput) {
        return KeywordChecker.listOfExactKeywords.contains(userInput);
    }

    public static Boolean containsMarkKeyword(String userInput) {
        Boolean hasMarkKeyword = false;

        if (userInput.startsWith(KeywordChecker.MARK_KEYWORD_MARK)) {
            hasMarkKeyword = true;
        } else if (userInput.startsWith(KeywordChecker.MARK_KEYWORD_UNMARK)) {
            hasMarkKeyword = true;
        }

        return hasMarkKeyword;
    }

    public static Boolean containsTaskKeyword(String userInput) {
        Boolean hasTaskKeyword = false;

        if (userInput.startsWith(KeywordChecker.TASK_KEYWORD_TODO)) {
            hasTaskKeyword = true;
        } else if (userInput.startsWith(KeywordChecker.TASK_KEYWORD_DEADLINE)) {
            hasTaskKeyword = true;
        } else if (userInput.startsWith(KeywordChecker.TASK_KEYWORD_EVENT)) {
            hasTaskKeyword = true;
        }

        return hasTaskKeyword;
    }

    public static String getNonexactKeyword(String userInput) {
        String nonexactKeyword = "";

        if (userInput.startsWith(KeywordChecker.MARK_KEYWORD_MARK)) {
            nonexactKeyword = KeywordChecker.MARK_KEYWORD_MARK;
        } else if (userInput.startsWith(KeywordChecker.MARK_KEYWORD_UNMARK)) {
            nonexactKeyword = KeywordChecker.MARK_KEYWORD_UNMARK;
        } else if (userInput.startsWith(KeywordChecker.TASK_KEYWORD_TODO)) {
            nonexactKeyword = KeywordChecker.TASK_KEYWORD_TODO;
        } else if (userInput.startsWith(KeywordChecker.TASK_KEYWORD_DEADLINE)) {
            nonexactKeyword = KeywordChecker.TASK_KEYWORD_DEADLINE;
        } else if (userInput.startsWith(KeywordChecker.TASK_KEYWORD_EVENT)) {
            nonexactKeyword = KeywordChecker.TASK_KEYWORD_EVENT;
        }

        return nonexactKeyword;
    }

    public static int getSpecifier(String userInput) {
        int specifier = -1;

        switch (KeywordChecker.getNonexactKeyword(userInput)) {
            case KeywordChecker.MARK_KEYWORD_MARK:
                specifier = Integer.parseInt(userInput.substring(5));
                break;
            case KeywordChecker.MARK_KEYWORD_UNMARK:
                specifier = Integer.parseInt(userInput.substring(7));
                break;
            default:
                break;
        }

        return specifier;
    }
}
