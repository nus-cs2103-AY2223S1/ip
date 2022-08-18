package DukeProgram;

import java.util.Locale;

public class JobNameException extends Exception {
    public JobNameException(String jobType) {
        super(String.format("☹ OOPS!!! The description of %s %s cannot be empty.",
                "aeiou".contains(jobType.toLowerCase(Locale.ROOT).charAt(0) + "") ? "an" : "a",
                jobType));
    }
}
