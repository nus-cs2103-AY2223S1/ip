package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void toggleStatus() {
        this.isDone = !isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task x = (Task) obj;
            if (this.description == null || x.description == null) {
                return false;
            }
            return this.description.equals(x.description);
        }

        return false;
    }

    public boolean isMatchKeyword(String keyword) {
        boolean result = false;
        Stream<String> matched = Arrays.stream(
                this.description.split(" "));
        List<String> filtered = matched
                .filter(
                str -> str.equals(keyword))
                .collect(Collectors.toList());
        return filtered.size() > 0;
    }
    public abstract String toSaveVersion();
}
