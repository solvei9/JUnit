package lesson3.Task09;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class UnstableRule extends TestWatcher{
    private RepeatRunRule repeatRunRule;

    UnstableRule(RepeatRunRule repeatRunRule) {
        this.repeatRunRule = repeatRunRule;
    }

    protected void starting(Description description) {
        if (description.getAnnotation(Unstable.class) != null) {
            repeatRunRule.setAttemptCount(description.getAnnotation(Unstable.class).count());
        }
    }
}
