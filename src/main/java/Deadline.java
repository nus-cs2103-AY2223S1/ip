import java.time.LocalDateTime;
import java.util.Optional;

class Deadline extends Task {
    private static final String PREFIX = "by ";
    private static final String SPLIT = "/by ";

    private String deadline;
    private final Optional<LocalDateTime> dateTime;

    Deadline(String description, String deadline, Optional<LocalDateTime> dateTime) {
        super(description);
        this.deadline = deadline;
        this.dateTime = dateTime;
    }

    static Deadline createDeadline(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionException("deadline");

        if (data.additionalInfo.length() == 0 || !data.additionalInfo.startsWith(PREFIX))
            throw new EmptyTimeException("deadline", SPLIT);

        String additionalInfo = data.additionalInfo.substring(3);
        return new Deadline(data.description, additionalInfo, Parser.strToDateTime(additionalInfo));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
