import java.util.LinkedList;
import java.util.List;

public class KeywordChecker {
    public static final String EXACT_KEYWORD_BYE = "bye";
    public static final String EXACT_KEYWORD_LIST = "list";
    public static final String NONEXACT_KEYWORD_MARK = "mark ";
    public static final String NONEXACT_KEYWORD_UNMARK = "unmark ";
    private static final LinkedList<String> listOfExactKeywords = new LinkedList<>(
            List.of(KeywordChecker.EXACT_KEYWORD_BYE,
                    KeywordChecker.EXACT_KEYWORD_LIST)
    );
    private static final LinkedList<String> listOfNonexactKeywords = new LinkedList<>(
            List.of(KeywordChecker.NONEXACT_KEYWORD_MARK,
                    KeywordChecker.NONEXACT_KEYWORD_UNMARK)
    );

    public static Boolean containsExactKeyword(String userInput) {
        return KeywordChecker.listOfExactKeywords.contains(userInput);
    }

    public static Boolean containsNonexactKeyword(String userInput) {
        Boolean hasNonexactKeyword = false;

        if (userInput.length() >= 5 && userInput.substring(0, 5).equals(KeywordChecker.NONEXACT_KEYWORD_MARK)) {
            hasNonexactKeyword = true;
        } else if (userInput.length() >= 7 && userInput.substring(0, 7).equals(KeywordChecker.NONEXACT_KEYWORD_UNMARK)) {
            hasNonexactKeyword = true;
        }

        return hasNonexactKeyword;
    }

    public static String getNonexactKeyword(String userInput) {
        String nonexactKeyword = "";

        if (userInput.substring(0, 5).equals(KeywordChecker.NONEXACT_KEYWORD_MARK)) {
            nonexactKeyword = KeywordChecker.NONEXACT_KEYWORD_MARK;
        } else if (userInput.substring(0, 7).equals(KeywordChecker.NONEXACT_KEYWORD_UNMARK)) {
            nonexactKeyword = KeywordChecker.NONEXACT_KEYWORD_UNMARK;
        }

        return nonexactKeyword;
    }

    public static int getSpecifier(String userInput) {
        int specifier = -1;

        switch (KeywordChecker.getNonexactKeyword(userInput)) {
            case KeywordChecker.NONEXACT_KEYWORD_MARK:
                specifier = Integer.parseInt(userInput.substring(5));
                break;
            case KeywordChecker.NONEXACT_KEYWORD_UNMARK:
                specifier = Integer.parseInt(userInput.substring(7));
                break;
            default:
                break;
        }

        return specifier;
    }
}
