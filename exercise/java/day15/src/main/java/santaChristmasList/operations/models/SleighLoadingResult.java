package santaChristmasList.operations.models;

import java.util.HashMap;
import java.util.Map;

public class SleighLoadingResult {

    public enum SleighLoadingError {
        BAD_CHILD("Missing gift: Child wasn't nice this year!"),
        GIFT_NOT_MANUFACTURED("Missing gift: Gift wasn't manufactured!"),
        GIFT_MISPLACED("Missing gift: The gift has probably been misplaced by the elves!");
        private final String reason;

        SleighLoadingError(String reason) {
            this.reason = reason;
        }

        public String getReason() {
            return reason;
        }
    }

    private final Map<Child, String> successes = new HashMap<>();
    private final Map<Child, SleighLoadingError> errors = new HashMap<>();
    public Map<Child, SleighLoadingError> getErrors() {
        return errors;
    }

    public void addError(Child child, SleighLoadingError error) {
        errors.put(child, error);
    }

    public Map<Child, String> getSuccesses() {
        return successes;
    }

    public void addSuccess(Child child, Gift gift) {
        var information = "Gift: " + gift.name() + " has been loaded!";
        successes.put(child, information);
    }
}