package duke;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Helper class to parse date/time strings
 */
public class ParsedDateTime {
    private static final DateTimeFormatter[] formatters = {
        DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd"), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        DateTimeFormatter.BASIC_ISO_DATE, DateTimeFormatter.ISO_LOCAL_DATE, DateTimeFormatter.ISO_OFFSET_DATE,
        DateTimeFormatter.ISO_DATE, DateTimeFormatter.ISO_LOCAL_TIME, DateTimeFormatter.ISO_OFFSET_TIME,
        DateTimeFormatter.ISO_TIME, DateTimeFormatter.ISO_LOCAL_DATE_TIME, DateTimeFormatter.ISO_OFFSET_DATE_TIME,
        DateTimeFormatter.ISO_ZONED_DATE_TIME, DateTimeFormatter.ISO_DATE_TIME, DateTimeFormatter.ISO_ORDINAL_DATE,
        DateTimeFormatter.ISO_WEEK_DATE, DateTimeFormatter.ISO_INSTANT, DateTimeFormatter.RFC_1123_DATE_TIME };
    private static final ArrayList<StringMatcher<TemporalAdjuster>> naturalDateParsers;
    private static final ArrayList<StringMatcher<TemporalAdjuster>> naturalDateLatestTimeParsers;

    private static final int BIG_NUMBER_OF_ITERATIONS = 1024;
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int DATE_29 = 29;
    private static final int MONTH_FEB = 2;

    static {
        naturalDateParsers = new ArrayList<>();
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "mon", "monday" }),
                s -> getDayOfWeekTemporalAdjuster(DayOfWeek.MONDAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "tue", "tuesday" }),
                s -> getDayOfWeekTemporalAdjuster(DayOfWeek.TUESDAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "wed", "wednesday" }),
                s -> getDayOfWeekTemporalAdjuster(DayOfWeek.WEDNESDAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "thur", "thursday" }),
                s -> getDayOfWeekTemporalAdjuster(DayOfWeek.THURSDAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "fri", "friday" }),
                s -> getDayOfWeekTemporalAdjuster(DayOfWeek.FRIDAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "sat", "saturday" }),
                s -> getDayOfWeekTemporalAdjuster(DayOfWeek.SATURDAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "sun", "sunday" }),
                s -> getDayOfWeekTemporalAdjuster(DayOfWeek.SUNDAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "jan", "january" }),
                s -> getMonthTemporalAdjuster(Month.JANUARY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "feb", "february" }),
                s -> getMonthTemporalAdjuster(Month.FEBRUARY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "mar", "march" }),
                s -> getMonthTemporalAdjuster(Month.MARCH)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "apr", "april" }),
                s -> getMonthTemporalAdjuster(Month.APRIL)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "may" }),
                s -> getMonthTemporalAdjuster(Month.MAY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "jun", "june" }),
                s -> getMonthTemporalAdjuster(Month.JUNE)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "jul", "july" }),
                s -> getMonthTemporalAdjuster(Month.JULY)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "aug", "august" }),
                s -> getMonthTemporalAdjuster(Month.AUGUST)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "sep", "september" }),
                s -> getMonthTemporalAdjuster(Month.SEPTEMBER)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "oct", "october" }),
                s -> getMonthTemporalAdjuster(Month.OCTOBER)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "nov", "november" }),
                s -> getMonthTemporalAdjuster(Month.NOVEMBER)));
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(new String[]{ "dec", "december" }),
                s -> getMonthTemporalAdjuster(Month.DECEMBER)));
        String[] dates = new String[38];
        for (int i = 1; i <= 31; i++) {
            // 1th included, this is a feature
            dates[i - 1] = String.format("%dth", i);
        }
        // custom ordinals
        dates[31] = "1st";
        dates[32] = "2nd";
        dates[33] = "3rd";
        dates[34] = "21st";
        dates[35] = "22nd";
        dates[36] = "23rd";
        dates[37] = "31st";
        naturalDateParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(dates),
                s -> {
                    boolean isTwoDigit = '0' <= s.charAt(1) && s.charAt(1) <= '9';
                    int date = Integer.parseInt(s.substring(0, 1));
                    if (isTwoDigit) {
                        date = Integer.parseInt(s.substring(0, 2));
                    }
                    return getDateTemporalAdjuster(date);
                }));
        Predicate<String> isDate = s -> {
            String[] parts = s.split("/");
            if (parts.length == 2 || parts.length == 3) {
                for (String part : parts) {
                    if (!isNumeric(part)) {
                        return false;
                    }
                }
                return true;
            }
            parts = s.split("-");
            if (parts.length == 2 || parts.length == 3) {
                for (String part : parts) {
                    if (!isNumeric(part)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        };
        naturalDateParsers.add(new StringMatcher<>(
                isDate,
                s -> {
                    try {
                        int date = -1;
                        int month = -1;
                        int year = -1;
                        String[] parts = s.split("/");
                        if (parts.length == 2 || parts.length == 3) {
                            date = Integer.parseInt(parts[0]);
                            month = Integer.parseInt(parts[1]);
                            if (parts.length == 3) {
                                year = Integer.parseInt(parts[2]);
                            }
                        }
                        parts = s.split("-");
                        if (parts.length == 2 || parts.length == 3) {
                            date = Integer.parseInt(parts[0]);
                            month = Integer.parseInt(parts[1]);
                            if (parts.length == 3) {
                                year = Integer.parseInt(parts[2]);
                            }
                        }
                        if (year == -1) {
                            return getDateMonthTemporalAdjuster(date, month);
                        }
                        return getDateMonthYearTemporalAdjuster(date, month, year);
                    } catch (NumberFormatException ex) {
                        return temporal -> {
                            throw new DateTimeException("Parse error for date");
                        };
                    }
                }));
        naturalDateParsers.add(new StringMatcher<>(
                ParsedDateTime::isNumeric,
                s -> {
                    try {
                        int year = Integer.parseInt(s);
                        if (year < 1) {
                            return temporal -> {
                                throw new DateTimeException("No negative years");
                            };
                        }
                        return getYearTemporalAdjuster(year);
                    } catch (NumberFormatException ex) {
                        return temporal -> {
                            throw new DateTimeException("Parse error for year");
                        };
                    }
                }));
        Predicate<String> isTime = s -> {
            String[] parts = s.split("[:.]");
            if (parts.length != 2) {
                return false;
            }
            if (!isNumeric(parts[0])) {
                return false;
            }
            if (isNumeric(parts[1])) {
                return true;
            }
            if (parts[1].length() < 3) {
                return false;
            }
            if (!isNumeric(parts[1].substring(0, parts[1].length() - 2))) {
                return false;
            }
            String suffix = parts[1].substring(parts[1].length() - 2)
                    .toLowerCase();
            return suffix.equals("am") || suffix.equals("pm");
        };
        naturalDateParsers.add(new StringMatcher<>(
                isTime,
                s -> {
                    try {
                        String[] parts = s.split("[:.]");
                        int hour = Integer.parseInt(parts[0]);
                        int minute;
                        boolean invalidHour;
                        if (isNumeric(parts[1])) {
                            minute = Integer.parseInt(parts[1]);
                            invalidHour = hour < 0 || hour >= HOURS_PER_DAY;
                        } else {
                            minute = Integer.parseInt(parts[1].substring(0, parts[1].length() - 2));
                            String suffix = parts[1].substring(parts[1].length() - 2)
                                    .toLowerCase();
                            invalidHour = hour <= 0 || hour > 12;
                            if (suffix.equals("am")) {
                                if (hour == 12) {
                                    hour = 0;
                                }
                            } else if (suffix.equals("pm")) {
                                if (hour != 12) {
                                    hour += 12;
                                }
                            }
                        }
                        boolean invalidMinute = minute < 0 || minute >= MINUTES_PER_HOUR;
                        if (invalidHour || invalidMinute) {
                            return temporal -> {
                                throw new DateTimeException("Invalid hour/minute for time");
                            };
                        }
                        return getTimeTemporalAdjuster(hour, minute);
                    } catch (NumberFormatException ex) {
                        return temporal -> {
                            throw new DateTimeException("Parse error for time");
                        };
                    }
                }));
        TemporalAdjuster lastTimeOfDay = temporal ->
                LocalDateTime.from(temporal).withHour(23).withMinute(59).withSecond(0).withNano(0);
        TemporalAdjuster lastTimeOfMonth = temporal ->
                LocalDateTime.from(temporal).with(TemporalAdjusters.lastDayOfMonth()).with(lastTimeOfDay);
        TemporalAdjuster lastTimeOfYear = temporal ->
                LocalDateTime.from(temporal).with(TemporalAdjusters.lastDayOfYear()).with(lastTimeOfDay);
        naturalDateLatestTimeParsers = new ArrayList<>();
        String[] weekdays = new String[]{
            "mon", "monday", "tue", "tuesday", "wed", "wednesday", "thur", "thursday", "fri", "friday",
            "sat", "saturday", "sun", "sunday" };
        String[] months = new String[]{
            "jan", "january", "feb", "february", "mar", "march",
            "apr", "april", "may", "jun", "june",
            "jul", "july", "aug", "august", "sep", "september",
            "oct", "october", "nov", "november", "dec", "december" };
        naturalDateLatestTimeParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(weekdays),
                s -> lastTimeOfDay));
        naturalDateLatestTimeParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(months),
                s -> lastTimeOfMonth));
        naturalDateLatestTimeParsers.add(new StringMatcher<>(
                StringMatcher.getCaseInsensitiveMatcher(dates),
                s -> lastTimeOfDay));
        naturalDateLatestTimeParsers.add(new StringMatcher<>(
                isDate, s -> lastTimeOfDay));
        naturalDateLatestTimeParsers.add(new StringMatcher<>(
                ParsedDateTime::isNumeric, s -> lastTimeOfYear));
        naturalDateLatestTimeParsers.add(new StringMatcher<>(
                isTime, s -> t -> t));
    }

    private Optional<LocalDateTime> parsedDateTime;
    private String input;

    /**
     * Constructs an object to handle if the date/time can be parsed.
     *
     * @param input String that may represent date/time.
     */
    public ParsedDateTime(String input) {
        assert input != null;
        this.input = input;
        parsedDateTime = Optional.empty();
        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDateTime = Optional.of(LocalDateTime.parse(input, formatter));
                break;
            } catch (DateTimeParseException ex) {
                // Just try another one
            }
        }
    }

    /**
     * Constructs an object that handles an already parsed date/time.
     *
     * @param parsed Already parsed date/time.
     */
    public ParsedDateTime(LocalDateTime parsed) {
        input = "";
        parsedDateTime = Optional.of(parsed);
    }

    /**
     * Finds the earliest time after now that matches the description of input. If no such
     * time exists, it falls back on the earliest time before now. If no such time still
     * exists, it just gives the input as a ParsedDateTime which stores a String.
     *
     * @param input Human-readable description of the time, which only contains
     *              valid descriptors. Valid descriptors include:
     *              * case insensitive Mon-Sun/Monday-Sunday
     *              * an ordinal which specifies a date of the month, e.g. 3rd or 31st
     *              * two or three numbers separated by a slashes or dashes which denote
     *                a date, e.g. 3/7 for the next 3rd of July, 3/7/2012 for the 3rd
     *                of July on 2012, and 1-2 for the next first of February.
     *              * a single number, for a year, e.g. 2024 for year 2024
     *              * a month name, or the abbreviation of a month name, e.g. Jan, July
     *              * two numbers separated by colons or periods (with optional
     *                AM/am/PM/pm after it) for a time
     * @param latestTime If true, gives the latest time in the first block of time
     *                   which matches the input description. Example: If today is not
     *                   Monday, "Mon" gives the time as 23:59 on Monday if latestTime
     *                   else 00:00.
     * @return ParsedDateTime of the input. If input is not readable as a time, it
     *         a ParsedDateTime instance that acts like a String.
     */
    public static ParsedDateTime of(String input, boolean latestTime) {
        String[] tokens = input.split(" ");
        try {
            ArrayList<TemporalAdjuster> adjusters = getDateTimeAdjusters(naturalDateParsers, tokens);
            ArrayList<TemporalAdjuster> latestTimeAdjusters = getDateTimeAdjusters(
                    naturalDateLatestTimeParsers, tokens);
            Optional<Temporal> result = Optional.empty();
            Temporal now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            result = result.or(() -> findAfterTimeMatching(now, adjusters));
            result = result.or(() -> findBeforeTimeMatching(now, adjusters));
            return result.map(t -> {
                if (!latestTime) {
                    return t;
                }
                return applyLatestTimeAdjusters(latestTimeAdjusters, t);
            })
                    .map(LocalDateTime::from)
                    .map(t -> new ParsedDateTime(t))
                    .orElseGet(() -> new ParsedDateTime(input));
        } catch (IllegalArgumentException ex) {
            // thrown by getNaturalDateParsers if not all tokens are valid
            return new ParsedDateTime(input);
        }
    }

    /**
     * Applies the latest time adjusters. Each adjuster gives the latest time the token would consider as part
     * of the same length of time specified by the token, so this method finds the earliest time all the tokens
     * would still consider as part of the same length of time.
     *
     * @param latestTimeAdjusters List of time adjusters which give the latest time considered.
     * @param t The time which is specified by all tokens.
     * @return Latest time considered by all tokens.
     */
    private static LocalDateTime applyLatestTimeAdjusters(ArrayList<TemporalAdjuster> latestTimeAdjusters, Temporal t) {
        return latestTimeAdjusters.stream()
                .map(adjuster -> LocalDateTime.from(t.with(adjuster)))
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.from(t));
    }

    /**
     * Gets a time adjuster to set to the earliest time at or after the given time
     * which is of the day of week specified.
     *
     * @param day Day of week.
     * @return Time adjuster which sets to next or same time.
     */
    private static TemporalAdjuster getDayOfWeekTemporalAdjuster(DayOfWeek day) {
        return temporal -> {
            if (DayOfWeek.from(LocalDateTime.from(temporal)).equals(day)) {
                return temporal;
            }
            Temporal result = TemporalAdjusters.next(day).adjustInto(temporal);
            return LocalDateTime.from(result).truncatedTo(ChronoUnit.DAYS);
        };
    }

    /**
     * Checks if a string consists of digits.
     *
     * @param s A String.
     * @return true if consisting only of ASCII digits, false otherwise.
     */
    private static boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || '9' < s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets a time adjuster to set to the earliest time at or after the given time
     * which is of the month specified.
     *
     * @param month Month.
     * @return Time adjuster which sets to next or same time.
     */
    private static TemporalAdjuster getMonthTemporalAdjuster(Month month) {
        return temporal -> {
            if (Month.from(temporal).equals(month)) {
                return temporal;
            }
            Temporal result = temporal;
            while (!Month.from(result).equals(month)) {
                result = TemporalAdjusters.firstDayOfNextMonth().adjustInto(result);
            }
            return LocalDateTime.from(result).truncatedTo(ChronoUnit.DAYS);
        };
    }

    /**
     * Gets a time adjuster to set to the earliest time at or after the given time
     * which is of the date of the month specified.
     *
     * @param date Date of the month.
     * @return Time adjuster which sets to next or same time.
     */
    private static TemporalAdjuster getDateTemporalAdjuster(int date) {
        assert 1 <= date && date <= 31;
        return temporal -> {
            int temporalDayOfMonth = temporal.get(ChronoField.DAY_OF_MONTH);
            if (temporalDayOfMonth == date) {
                return temporal;
            }
            Temporal result = temporal;
            while (true) {
                int resultDayOfMonth = result.get(ChronoField.DAY_OF_MONTH);
                if (resultDayOfMonth == date) {
                    break;
                }
                if (resultDayOfMonth > date) {
                    result = result.with(TemporalAdjusters.firstDayOfNextMonth());
                    resultDayOfMonth = 1;
                }
                result = LocalDateTime.from(result).plusDays(date - resultDayOfMonth);
            }
            LocalDateTime startOfDay = LocalDateTime.from(result).truncatedTo(ChronoUnit.DAYS);
            return startOfDay;
        };
    }

    /**
     * Gets a time adjuster to set to the earliest time at or after the given time
     * which is of the time specified.
     *
     * @param hour Hour of day.
     * @param minute Minute of hour.
     * @return Time adjuster which sets to next or same time.
     */
    private static TemporalAdjuster getTimeTemporalAdjuster(int hour, int minute) {
        boolean isValidHour = 0 <= hour && hour < HOURS_PER_DAY;
        boolean isValidMinute = 0 <= minute && minute < MINUTES_PER_HOUR;
        if (!isValidHour || !isValidMinute) {
            return temporal -> {
                throw new DateTimeException(
                        String.format("Invalid time: Hour %d, Minute %d", hour, minute));
            };
        }
        int minuteOfDay = minute + hour * MINUTES_PER_HOUR;
        return temporal -> {
            if (temporal.get(ChronoField.MINUTE_OF_DAY) <= minuteOfDay) {
                return temporal.with(ChronoField.MINUTE_OF_DAY, minuteOfDay);
            }
            return temporal.plus(1, ChronoUnit.DAYS)
                    .with(ChronoField.MINUTE_OF_DAY, minuteOfDay);
        };
    }

    /**
     * Gets a time adjuster to set to the earliest time at or after the given time
     * which is of the date/month specified.
     *
     * @param date Date of month.
     * @param month Month.
     * @return Time adjuster which sets to next or same time.
     */
    private static TemporalAdjuster getDateMonthTemporalAdjuster(int date, int month) {
        boolean isValidDate = 1 <= date && date <= 31;
        boolean isValidMonth = 1 <= month && month <= 12;
        int[] daysInMonth = new int[]{ 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        boolean isValidCombination = isValidDate && isValidMonth && date <= daysInMonth[month];
        if (!isValidCombination) {
            return temporal -> {
                throw new DateTimeException(
                        String.format("Date does not exist: Date %d, Month %d", date, month));
            };
        }
        return temporal -> {
            int temporalDate = temporal.get(ChronoField.DAY_OF_MONTH);
            int temporalMonth = temporal.get(ChronoField.MONTH_OF_YEAR);
            boolean isDate = temporalDate == date;
            boolean isMonth = temporalMonth == month;
            if (isDate && isMonth) {
                return temporal;
            }
            boolean isLaterOnSameMonth = temporalMonth == month && temporalDate > date;
            int year = LocalDateTime.from(temporal).getYear();
            if (temporalMonth > month || isLaterOnSameMonth) {
                year++;
            }
            if (date == DATE_29 && month == MONTH_FEB) {
                year = leapYearNextOrSame(year);
            }
            return LocalDateTime.of(year, month, date, 0, 0, 0);
        };
    }

    /**
     * Gets a time adjuster to set to the earliest time at or after the given time
     * which is of the date/month/year specified.
     *
     * @param date Date of month.
     * @param month Month.
     * @param year Year.
     * @return Time adjuster which sets to next or same time.
     */
    private static TemporalAdjuster getDateMonthYearTemporalAdjuster(int date, int month, int year) {
        boolean isValidDate = 1 <= date && date <= 31;
        boolean isValidMonth = 1 <= month && month <= 12;
        boolean isValidYear = 1 <= year;
        int[] daysInMonth = new int[]{
            0, 31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        boolean isValidCombination = isValidYear && isValidDate && isValidMonth && date <= daysInMonth[month];
        if (!isValidCombination) {
            return temporal -> {
                throw new DateTimeException(String.format(
                        "Date does not exist: Date %d, Month %d, Year %d", date, month, year));
            };
        }
        return temporal -> {
            int temporalDate = temporal.get(ChronoField.DAY_OF_MONTH);
            int temporalMonth = temporal.get(ChronoField.MONTH_OF_YEAR);
            int temporalYear = temporal.get(ChronoField.YEAR);
            boolean isDate = temporalDate == date;
            boolean isMonth = temporalMonth == month;
            boolean isYear = temporalYear == year;
            if (isDate && isMonth && isYear) {
                return temporal;
            }
            boolean isLaterOnSameYear = isYear && temporalMonth > month;
            boolean isLaterOnSameMonth = isYear && isMonth && temporalDate > date;
            if (temporalYear > year || isLaterOnSameYear || isLaterOnSameMonth) {
                throw new DateTimeException(String.format(
                        "Later date does not exist: Date %d, Month %d, Year %d", date, month, year));
            }
            return LocalDateTime.of(year, month, date, 0, 0, 0);
        };
    }

    /**
     * Gets a time adjuster to set to the earliest time at or after the given time
     * which is of the year specified.
     *
     * @param year Year.
     * @return Time adjuster which sets to next or same time.
     */
    private static TemporalAdjuster getYearTemporalAdjuster(int year) {
        boolean isValidYear = 1 <= year;
        if (!isValidYear) {
            return temporal -> {
                throw new DateTimeException(String.format(
                        "Date does not exist:Year %d", year));
            };
        }
        return temporal -> {
            int temporalYear = temporal.get(ChronoField.YEAR);
            boolean isYear = temporalYear == year;
            if (isYear) {
                return temporal;
            }
            if (temporalYear > year) {
                throw new DateTimeException(String.format(
                        "Later date does not exist: Year %d", year));
            }
            return LocalDateTime.of(year, 1, 1, 0, 0, 0);
        };
    }

    /**
     * Gets the next or same year which is a leap year.
     *
     * @param year Year.
     * @return Next or same year which is a leap year.
     */
    private static int leapYearNextOrSame(int year) {
        while (!isLeapYear(year)) {
            year++;
        }
        return year;
    }

    /**
     * Checks if the input year is a leap year.
     *
     * @param year Year.
     * @return true if it is a leap year, false otherwise.
     */
    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    /**
     * Gets TemporalAdjusters which match the tokens available from the matchers.
     *
     * @param matchers StringMatchers which check the tokens to give TemporalAdjusters.
     * @param tokens Array of String tokens.
     * @return ArrayList of TemporalAdjusters.
     * @throws IllegalArgumentException If some token is not recognized by the StringMatchers.
     */
    private static ArrayList<TemporalAdjuster> getDateTimeAdjusters(
            ArrayList<StringMatcher<TemporalAdjuster>> matchers, String[] tokens) {
        ArrayList<TemporalAdjuster> adjusters = new ArrayList<>();
        for (String token : tokens) {
            if (token.equals("")) {
                continue;
            }
            adjusters.add(parseToken(matchers, token));
        }
        return adjusters;
    }

    /**
     * Gets TemporalAdjusters which match the token available from the matchers.
     *
     * @param matchers StringMatchers which check the tokens to give TemporalAdjusters.
     * @param token Array of String tokens.
     * @return TemporalAdjuster determined by StringMatchers.
     * @throws IllegalArgumentException If the token is not recognized by the StringMatchers.
     */
    private static TemporalAdjuster parseToken(ArrayList<StringMatcher<TemporalAdjuster>> matchers, String token) {
        Optional<TemporalAdjuster> adjuster = Optional.empty();
        for (StringMatcher<TemporalAdjuster> matcher : matchers) {
            adjuster = adjuster.or(() -> matcher.run(token));
        }
        return adjuster.orElseThrow(() -> new IllegalArgumentException("Unrecognised token"));
    }

    /**
     * Finds the time after the given time which is a fixed point of all the adjusters.
     * Warning: Imperfect practical implementation which stops an infinite loop.
     *
     * @param time Starting time.
     * @param adjusters Adjusters which find the next time that works according to their description.
     * @return Time which satisfies all adjusters' descriptions.
     */
    private static Optional<Temporal> findAfterTimeMatching(Temporal time, ArrayList<TemporalAdjuster> adjusters) {
        try {
            Temporal result = time;
            for (int i = 0; i < BIG_NUMBER_OF_ITERATIONS; i++) {
                Temporal originalTime = result;
                for (TemporalAdjuster adjuster : adjusters) {
                    result = adjuster.adjustInto(result);
                }
                if (originalTime.equals(result)) {
                    return Optional.of(result);
                }
            }
            return Optional.empty();
        } catch (DateTimeException ex) {
            return Optional.empty();
        }
    }

    /**
     * Finds the time before the given time which is a fixed point of all the adjusters.
     *
     * @param time Ending time.
     * @param adjusters Adjusters which find the next time that works according to their description.
     * @return Time which satisfies all adjusters' descriptions.
     */
    private static Optional<Temporal> findBeforeTimeMatching(Temporal time, ArrayList<TemporalAdjuster> adjusters) {
        try {
            Temporal result = LocalDateTime.of(1, 1, 1, 0, 0, 0);
            LocalDateTime endingTime = LocalDateTime.from(time);
            while (LocalDateTime.from(result).compareTo(endingTime) <= 0) {
                Temporal originalTime = result;
                for (TemporalAdjuster adjuster : adjusters) {
                    result = adjuster.adjustInto(result);
                }
                if (originalTime.equals(result)) {
                    return Optional.of(result);
                }
            }
            return Optional.empty();
        } catch (DateTimeException ex) {
            return Optional.empty();
        }
    }

    /**
     * Gets a nicely-formatted date.
     *
     * @return The date if it parses, else the original string.
     */
    @Override
    public String toString() {
        return parsedDateTime.map((dateTime) -> {
            assert dateTime != null;
            return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        }).orElse(input);
    }
}
