package lesson3.Task09;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.core.IsNot.not;

public class UnstableTest {
    private Path tmpFolder;
    private File file;
    private File file1;

    @Rule
    public TestRule repeatRunRule = new RepeatRunRule();

    @Rule
    public UnstableRule unstable = new UnstableRule((RepeatRunRule) repeatRunRule);

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

    @Test
    @Unstable(count = 3)
    public void emptyFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        Assert.assertTrue(file.createNewFile());
    }

    @Test
    public void sameFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        SoftAssertions s = new SoftAssertions();
        s.assertThat(file.createNewFile());
        s.assertThat(not(file.createNewFile()));
        s.assertAll();
    }

    @Test
    public void anotherFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        file1 = new File(tmpFolder + "\\file1.txt");
        SoftAssertions s = new SoftAssertions();
        s.assertThat(file.createNewFile());
        s.assertThat(file1.createNewFile());
        s.assertAll();
    }

    @Test
    public void nullFileCreateTest() throws IOException{
        try {
            file = null;
            boolean result = file.createNewFile();
            Assert.fail("Exception has not received");
        }
        catch (Exception e) {
            Assert.assertEquals("class java.lang.NullPointerException", e.getClass().toString());
        }
    }

    @Test
    public void invalidNameFileCreateTest() throws IOException {
        try {
            file = new File(tmpFolder + "\\*");
            boolean result = file.createNewFile();
            Assert.fail("Exception has not received");
        }
        catch (IOException e) {
            Assert.assertEquals("Синтаксическая ошибка в имени файла, имени папки или метке тома", e.getMessage());
        }
    }
}