package lesson2;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.core.IsNot.not;

public class AssertionTest {
    private Path tmpFolder;
    private File file;
    private File file1;

    @Before
    public void setUp() throws IOException {
        tmpFolder = Files.createTempDirectory("TestNG");
    }

    @Test
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

    @After
    public void tearDown() throws IOException {
        if (file!=null) {
            boolean result = file.delete();
        }
        if (file1!=null) {
            boolean result = file1.delete();
        }
        Files.delete(tmpFolder);
    }
}