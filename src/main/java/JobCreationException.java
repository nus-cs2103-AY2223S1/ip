import java.util.Arrays;
import java.util.Locale;

public class JobCreationException extends Exception {
    public JobCreationException(String jobType) {
        super(String.format("☹ OOPS!!! The description of %s %s cannot be empty.",
                "aeiou".contains(jobType.toLowerCase(Locale.ROOT).charAt(0) + "") ? "an" : "a",
                jobType));
    }
}
