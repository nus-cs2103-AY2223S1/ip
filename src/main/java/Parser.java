import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

class Parser {
    private static final String SPACE = " +";
    private static final String SEP = " +/";

    static enum DateFormatEnum {
        T1("MMM d yyyy"),
        T2("dd/MM/yyyy"),
        T3("dd-MM-yyyy");

        final DateTimeFormatter dtf;

        DateFormatEnum(String signature) {
            dtf = DateTimeFormatter.ofPattern(signature);
        }
    }

    static enum DateTimeFormatEnum {
        T1("MMM d yyyy HH:mm"),
        T2("dd/MM/yyyy HH:mm"),
        T3("dd-MM-yyyy HH:mm");

        final DateTimeFormatter dtf;

        DateTimeFormatEnum(String signature) {
            dtf = DateTimeFormatter.ofPattern(signature);
        }
    }

    Parser() {
    }

    ParsedData parse(String txt) {
        String[] parsedTmp = txt.split(SPACE, 2);

        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(txt, parsedTmp[0]);
        }

        String command = parsedTmp[0];
        parsedTmp = parsedTmp[1].split(SEP, 2);

        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(txt, command, parsedTmp[0]);
        }

        return new ParsedData(txt, command, parsedTmp[0], parsedTmp[1]);
    }

    static Optional<LocalDateTime> strToDateTime(String str) {
        for (DateTimeFormatEnum signature : DateTimeFormatEnum.values()) {
            try {
                return Optional.of(LocalDateTime.parse(str, signature.dtf));
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateFormatEnum signature : DateFormatEnum.values()) {
            try {
                return Optional.of(LocalDate.parse(str, signature.dtf).atStartOfDay());
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        return Optional.empty();
    }

}
