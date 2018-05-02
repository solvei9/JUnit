package lesson2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        Assert.assertTrue(file.createNewFile());
        Assert.assertFalse(file.createNewFile());
    }

    @Test
    public void anotherFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        file1 = new File(tmpFolder + "\\file1.txt");
        Assert.assertTrue(file.createNewFile());
        Assert.assertTrue(file1.createNewFile());
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