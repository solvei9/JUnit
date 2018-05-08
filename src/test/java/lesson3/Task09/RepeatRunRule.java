package lesson3.Task09;

import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RepeatRunRule implements TestRule{
    private int attemptCount;

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new RepeatRunStatement(base, description);
    }

    public class RepeatRunStatement extends Statement {
        private final Statement base;
        private Description description;

        public RepeatRunStatement(Statement base, Description description) {
            this.base = base;
            this.description = description;
        }

        @Override
        public void evaluate() throws Throwable {
            for (int i = 1; i <= attemptCount; i++) {
                try {
                    base.evaluate();
                    i = attemptCount+1;
                } catch (Throwable t) {
                    if (i<attemptCount) {
                        System.out.println("Attempt " + i + " failed: " + description);
                    }
                    else {
                        Assert.fail("Attempt " + i + " failed: " + description);
                    }
                }
            }
        }
    }
}
