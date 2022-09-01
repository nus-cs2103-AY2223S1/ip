import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;

public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    public static String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static int monthToInt(String month) {
        switch (month) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 0;
        }
    }

    public static class TodoTask extends Task {

        public TodoTask(String name) {
            super(name);
        }

        @Override
        public String toString() {
            if (isCompleted()) {
                return String.format("[T][1] %s", getTaskName());
            } else {
                return String.format("[T][0] %s", getTaskName());
            }
        }
    }

    public static class DeadlineTask extends Task {

        private final LocalDateTime deadline;
        private final boolean hasTime;

        public DeadlineTask(String name, String date) {
            super(name);
            int firstIndex;
            int secondIndex;
            if (date.contains("/")) {
                firstIndex = date.indexOf("/");
                secondIndex = date.substring(firstIndex + 1).indexOf("/") + firstIndex + 1;
            } else if (date.contains("-")) {
                firstIndex = date.indexOf("-");
                secondIndex = date.substring(firstIndex + 1).indexOf("-") + firstIndex + 1;
            } else if (date.contains(".")) {
                firstIndex = date.indexOf(".");
                secondIndex = date.substring(firstIndex + 1).indexOf(".") + firstIndex + 1;
            } else {
                throw new InputMismatchException();
            }
            if (secondIndex == -1) {
                throw new InputMismatchException();
            }
            int year;
            int month;
            int day;
            boolean hasTime = false;
            int hour = 0;
            int minute = 0;
            if (firstIndex == 1) {
                if (secondIndex == 3) {
                    day = Integer.parseInt(date.substring(0, 1));
                    month = Integer.parseInt(date.substring(2, 3));
                    year = Integer.parseInt(date.substring(4, 8));
                    if (date.length() > 9) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(9, 11));
                        minute = Integer.parseInt(date.substring(11, 13));
                    }
                } else if (secondIndex == 4) {
                    day = Integer.parseInt(date.substring(0, 1));
                    month = Integer.parseInt(date.substring(2, 4));
                    year = Integer.parseInt(date.substring(5, 9));
                    if (date.length() > 10) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(10, 12));
                        minute = Integer.parseInt(date.substring(12, 14));
                    }
                } else {
                    throw new InputMismatchException();
                }
            } else if (firstIndex == 2) {
                if (secondIndex == 4) {
                    day = Integer.parseInt(date.substring(0, 2));
                    month = Integer.parseInt(date.substring(3, 4));
                    year = Integer.parseInt(date.substring(5, 9));
                    if (date.length() > 10) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(10, 12));
                        minute = Integer.parseInt(date.substring(12, 14));
                    }
                } else if (secondIndex == 5) {
                    day = Integer.parseInt(date.substring(0, 2));
                    month = Integer.parseInt(date.substring(3, 5));
                    year = Integer.parseInt(date.substring(6, 10));
                    if (date.length() > 11) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(11, 13));
                        minute = Integer.parseInt(date.substring(13, 15));
                    }
                } else {
                    throw new InputMismatchException();
                }
            } else {
                throw new InputMismatchException();
            }
            deadline = LocalDateTime.of(year, month, day, hour, minute);
            this.hasTime = hasTime;

        }

        @Override
        public String toString() {
            int completed = 0;
            if (isCompleted()) {
                completed = 1;
            }
            String wordMonth = months[deadline.getDayOfMonth() - 1];
            String wordDay = String.valueOf(deadline.getMonth().getValue());
            if (deadline.getDayOfMonth() < 10) {
                wordDay = "0" + wordDay;
            }
            String wordMinute = String.valueOf(deadline.getMinute());
            if (deadline.getMinute() == 0) {
                wordMinute = "0" + wordMinute;
            }
            String wordHour = String.valueOf(deadline.getHour());
            if (deadline.getHour() == 0) {
                wordHour = "0" + wordHour;
            }
            if (hasTime) {
                return String.format("[D][%d] %s | %s %s %d %s%s ", completed, getTaskName(),
                        wordDay, wordMonth, deadline.getYear(),
                        wordHour, wordMinute);
            } else {
                return String.format("[D][%d] %s | %s %s %d", completed, getTaskName(),
                        wordDay, wordMonth, deadline.getYear());
            }

        }
    }

    public static class EventTask extends Task {

        private final LocalDateTime datetime;
        private final boolean hasTime;

        public EventTask(String name, String date) {
            super(name);
            int firstIndex;
            int secondIndex;
            if (date.contains("/")) {
                firstIndex = date.indexOf("/");
                secondIndex = date.substring(firstIndex + 1).indexOf("/") + firstIndex + 1;
            } else if (date.contains("-")) {
                firstIndex = date.indexOf("-");
                secondIndex = date.substring(firstIndex + 1).indexOf("-") + firstIndex + 1;
            } else if (date.contains(".")) {
                firstIndex = date.indexOf(".");
                secondIndex = date.substring(firstIndex + 1).indexOf(".") + firstIndex + 1;
            } else {
                throw new InputMismatchException();
            }
            if (secondIndex == -1) {
                throw new InputMismatchException();
            }
            int year;
            int month;
            int day;
            boolean hasTime = false;
            int hour = 0;
            int minute = 0;
            if (firstIndex == 1) {
                if (secondIndex == 3) {
                    day = Integer.parseInt(date.substring(0, 1));
                    month = Integer.parseInt(date.substring(2, 3));
                    year = Integer.parseInt(date.substring(4, 8));
                    if (date.length() > 9) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(9, 11));
                        minute = Integer.parseInt(date.substring(11, 13));
                    }
                } else if (secondIndex == 4) {
                    day = Integer.parseInt(date.substring(0, 1));
                    month = Integer.parseInt(date.substring(2, 4));
                    year = Integer.parseInt(date.substring(5, 9));
                    if (date.length() > 10) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(10, 12));
                        minute = Integer.parseInt(date.substring(12, 14));
                    }
                } else {
                    throw new InputMismatchException();
                }
            } else if (firstIndex == 2) {
                if (secondIndex == 4) {
                    day = Integer.parseInt(date.substring(0, 2));
                    month = Integer.parseInt(date.substring(3, 4));
                    year = Integer.parseInt(date.substring(5, 9));
                    if (date.length() > 10) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(10, 12));
                        minute = Integer.parseInt(date.substring(12, 14));
                    }
                } else if (secondIndex == 5) {
                    day = Integer.parseInt(date.substring(0, 2));
                    month = Integer.parseInt(date.substring(3, 5));
                    year = Integer.parseInt(date.substring(6, 10));
                    if (date.length() > 11) {
                        hasTime = true;
                        hour = Integer.parseInt(date.substring(11, 13));
                        minute = Integer.parseInt(date.substring(13, 15));
                    }
                } else {
                    throw new InputMismatchException();
                }
            } else {
                throw new InputMismatchException();
            }
            datetime = LocalDateTime.of(year, month, day, hour, minute);
            this.hasTime = hasTime;

        }

        @Override
        public String toString() {
            int completed = 0;
            if (isCompleted()) {
                completed = 1;
            }
            String wordMonth = months[datetime.getMonth().getValue() - 1];
            String wordDay = String.valueOf(datetime.getDayOfMonth());
            if (datetime.getDayOfMonth() < 10) {
                wordDay = "0" + wordDay;
            }
            String wordMinute = String.valueOf(datetime.getMinute());
            if (datetime.getMinute() == 0) {
                wordMinute = "0" + wordMinute;
            }
            String wordHour = String.valueOf(datetime.getHour());
            if (datetime.getHour() == 0) {
                wordHour = "0" + wordHour;
            }
            if (hasTime) {
                return String.format("[E][%d] %s | %s %s %d %s%s ", completed, getTaskName(),
                        wordDay, wordMonth, datetime.getYear(),
                        wordHour, wordMinute);
            } else {
                return String.format("[E][%d] %s | %s %s %d", completed, getTaskName(),
                        wordDay, wordMonth, datetime.getYear());
            }

        }
    }
    public Task(String name) {
        taskName = name;
        isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void markComplete() {
        isCompleted = true;
    }

    public void markIncomplete() {
        isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return String.format("[X] %s", taskName);
        } else {
            return String.format("[ ] %s", taskName);
        }
    }
}