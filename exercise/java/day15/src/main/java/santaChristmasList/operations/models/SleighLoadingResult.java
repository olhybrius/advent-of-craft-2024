package santaChristmasList.operations.models;

import java.util.HashMap;
import java.util.Map;

public class SleighLoadingResult {

    private final Map<Child, String> successes = new HashMap<>();
    private final Map<Child, String> errors = new HashMap<>();
    public Map<Child, String> getErrors() {
        return errors;
    }

    public void addError(Child child, String error) {
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