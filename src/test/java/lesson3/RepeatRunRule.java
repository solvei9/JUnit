package lesson3;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RepeatRunRule implements TestRule{

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
            try {
                base.evaluate();
            }
            catch (Throwable t) {
                System.out.println("Attempt failed: " + description);
                base.evaluate();
            }
        }
    }
}
