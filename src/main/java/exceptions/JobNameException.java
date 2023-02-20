package exceptions;

import java.util.Locale;

/**
 * JobNameException is thrown if there is an invalid job name
 */
public class JobNameException extends Exception {

    /**
     * Creates a new JobNameException
     * @param jobType the type of job
     */
    public JobNameException(String jobType) {
        super(String.format("☹ OOPS!!! The description of %s %s cannot be empty.",
                "aeiou".contains(jobType.toLowerCase(Locale.ROOT).charAt(0) + "") ? "an" : "a",
                jobType));
    }
}
