import DataStructures.Pair;
import Exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class LineParser {
    public static Pair<Case, ArrayList<String>> parse(String line) throws InvalidInputException {
        if (!(line instanceof String)) {
            throw new InvalidInputException();
        } else if (line.equals("bye")) {
            return Pair.of(Case.BYE, null);
        } else if (line.equals("list")) {
            return Pair.of(Case.LIST, null);
        } else {
            Scanner sc = new Scanner(line);
            ArrayList<String> parsed = new ArrayList<>();

            String firstWord = sc.next();
            if ((firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete"))
                    && sc.hasNextInt()) {
                Case cs;
                if (firstWord.equals("mark")) {
                    cs = Case.MARK;
                } else if (firstWord.equals("unmark")) {
                    cs = Case.UNMARK;
                } else {
                    cs = Case.DELETE;
                }
                parsed.add(sc.next());
                if (!sc.hasNext()) {
                    return Pair.of(cs, parsed);
                }
            } else if (firstWord.equals("todo") && sc.hasNext()) {
                parsed.add(sc.nextLine());
                return Pair.of(Case.TODO, parsed);
            } else if (firstWord.equals("deadline") && sc.hasNext()) {
                sc.useDelimiter("/by");
                parsed.add(sc.next());
                if (sc.hasNext()) {
                    parsed.add(sc.next());
                    return Pair.of(Case.DEADLINE, parsed);
                }
            } else if (firstWord.equals("event") && sc.hasNext()) {
                sc.useDelimiter("/at");
                parsed.add(sc.next());
                if (sc.hasNext()) {
                    parsed.add(sc.next());
                    return Pair.of(Case.EVENT, parsed);
                }
            }
            throw new InvalidInputException();
        }
    }
}
