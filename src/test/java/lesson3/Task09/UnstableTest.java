package lesson3.Task09;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class UnstableTest {
    @Rule
    public RepeatRunRule repeatRunRule = new RepeatRunRule();

    @Rule
    public UnstableRule unstableRule = new UnstableRule(repeatRunRule);

    @Rule
    public ExternalResource tmpDir = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            tmpFolder = Files.createTempDirectory("TestNG");
        }

        @Override
        protected void after() {
            if (file!=null) {
                file.delete();
            }
            if (file1!=null) {
                file1.delete();
            }
            try {
                Files.delete(tmpFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private static boolean attempt;
    private Path tmpFolder;
    private File file;
    private File file1;

    @Test
    @Unstable(count = 2)
    public void emptyFileCreateTest() throws IOException {
        attempt = (new Random()).nextBoolean();
        if (attempt) {
            file = new File(tmpFolder + "\\file.txt");
            SoftAssertions s = new SoftAssertions();
            s.assertThat(file.createNewFile());
            s.assertThat(file.exists());
            s.assertAll();
        } else {
            Assert.fail("Failed on " + (attempt) + " attempt");
        }
    }
}