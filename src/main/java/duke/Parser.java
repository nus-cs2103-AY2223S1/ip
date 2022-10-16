package duke;

import java.util.LinkedList;
import java.util.List;

/**
 * The Parser class handles the detection of all user commands.
 */
class Parser {
    public static final String EXACT_KEYWORD_BYE = "bye";
    public static final String EXACT_KEYWORD_LIST = "list";
    public static final String MARK_KEYWORD_MARK = "mark ";
    public static final String MARK_KEYWORD_UNMARK = "unmark ";
    public static final String TASK_KEYWORD_TODO = "todo ";
    public static final String TASK_KEYWORD_DEADLINE = "deadline ";
    public static final String TASK_KEYWORD_EVENT = "event ";
    public static final String MARK_KEYWORD_DELETE = "delete ";
    public static final String FIND_KEYWORD = "find ";
    public static final String ARCHIVE_KEYWORD = "archive";
    public static final String DEADLINE_PATTERN = "deadline (.*) /by ([0-9]{4})-([0-9]{2})-([0-9]{2})";
    public static final String EVENT_PATTERN = "event (.*) /at ([0-9]{4})-([0-9]{2})-([0-9]{2})";
    private static final LinkedList<String> listOfExactKeywords = new LinkedList<>(
            List.of(Parser.EXACT_KEYWORD_BYE,
                    Parser.EXACT_KEYWORD_LIST)
    );

    /**
     * Gets whether an exact keyword is present.
     *
     * @param userInput the user input.
     * @return true if an exact keyword is present, false otherwise.
     */
    public static Boolean containsExactKeyword(String userInput) {
        return Parser.listOfExactKeywords.contains(userInput);
    }

    /**
     * Gets whether a mark type keyword is present.
     *
     * @param userInput the user input.
     * @return true if a mark type keyword if present, false otherwise.
     */
    public static Boolean containsMarkKeyword(String userInput) {
        Boolean hasMarkKeyword = false;

        if (userInput.startsWith(Parser.MARK_KEYWORD_MARK)) {
            hasMarkKeyword = true;
        } else if (userInput.startsWith(Parser.MARK_KEYWORD_UNMARK)) {
            hasMarkKeyword = true;
        } else if (userInput.startsWith(Parser.MARK_KEYWORD_DELETE)) {
            hasMarkKeyword = true;
        }

        return hasMarkKeyword;
    }

    /**
     * Gets whether a task type keyword is present.
     *
     * @param userInput the user input.
     * @return true if a task type keyword is present, false otherwise.
     */
    public static Boolean containsTaskKeyword(String userInput) {
        Boolean hasTaskKeyword = false;

        if (userInput.startsWith(Parser.TASK_KEYWORD_TODO)) {
            hasTaskKeyword = true;
        } else if (userInput.startsWith(Parser.TASK_KEYWORD_DEADLINE)) {
            hasTaskKeyword = true;
        } else if (userInput.startsWith(Parser.TASK_KEYWORD_EVENT)) {
            hasTaskKeyword = true;
        }

        return hasTaskKeyword;
    }

    /**
     * Gets the term the user is finding with the find keyword.
     *
     * @param userInput the user input.
     * @return the term to find.
     */
    public static String getTermTofind(String userInput) {
        return userInput.substring(5);
    }

    /**
     * Gets whether the keyword find is present.
     *
     * @param userInput the user input.
     * @return true if the keyword find is present, false otherwise.
     */
    public static boolean containsFindKeyword(String userInput) {
        return userInput.startsWith(Parser.FIND_KEYWORD);
    }

    /**
     * Gets whether the keyword archive is present.
     *
     * @param userInput the user input.
     * @return true if the keyword archive is present, false otherwise.
     */
    public static boolean containsArchiveKeyword(String userInput) {
        return userInput.startsWith(Parser.ARCHIVE_KEYWORD);
    }

    /**
     * Gets the nonexact keyword present in the user input.
     *
     * @param userInput the user input.
     * @return the nonexact keyword if present, an empty string otherwise.
     */
    public static String getNonexactKeyword(String userInput) {
        String nonexactKeyword = "";

        if (userInput.startsWith(Parser.MARK_KEYWORD_MARK)) {
            nonexactKeyword = Parser.MARK_KEYWORD_MARK;
        } else if (userInput.startsWith(Parser.MARK_KEYWORD_UNMARK)) {
            nonexactKeyword = Parser.MARK_KEYWORD_UNMARK;
        } else if (userInput.startsWith(Parser.MARK_KEYWORD_DELETE)) {
            nonexactKeyword = Parser.MARK_KEYWORD_DELETE;
        } else if (userInput.startsWith(Parser.TASK_KEYWORD_TODO)) {
            nonexactKeyword = Parser.TASK_KEYWORD_TODO;
        } else if (userInput.startsWith(Parser.TASK_KEYWORD_DEADLINE)) {
            nonexactKeyword = Parser.TASK_KEYWORD_DEADLINE;
        } else if (userInput.startsWith(Parser.TASK_KEYWORD_EVENT)) {
            nonexactKeyword = Parser.TASK_KEYWORD_EVENT;
        } else if (userInput.startsWith(Parser.FIND_KEYWORD)) {
            nonexactKeyword = Parser.FIND_KEYWORD;
        }

        return nonexactKeyword;
    }

    /**
     * Gets the specifier following a nonexact keyword.
     * e.g. In "mark 1", the specifier is '1'.
     *
     * @param userInput the user input.
     * @return the relevant specifier.
     */
    public static int getSpecifier(String userInput) {
        int specifier = -1;

        switch (Parser.getNonexactKeyword(userInput)) {
        case Parser.MARK_KEYWORD_MARK:
            specifier = Integer.parseInt(userInput.substring(5));
            break;
        case Parser.MARK_KEYWORD_UNMARK:
        case Parser.MARK_KEYWORD_DELETE:
            specifier = Integer.parseInt(userInput.substring(7));
            break;
        default:
            break;
        }

        return specifier;
    }

    /**
     * Checks if the entry is the correct pattern.
     *
     * @param entry the user entry
     * @return true if the pattern is correct, false otherwise
     */
    public static boolean matchesDeadlinePattern(String entry) {
        return entry.matches(Parser.DEADLINE_PATTERN);
    }

    /**
     * Checks if the entry is the correct pattern.
     *
     * @param entry the user entry
     * @return true if the pattern is correct, false otherwise
     */
    public static boolean matchesEventPattern(String entry) {
        return entry.matches(Parser.EVENT_PATTERN);
    }
}
