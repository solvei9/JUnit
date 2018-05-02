package lesson2;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GroupingTest {
    private Path tmpFolder;
    private File file;
    private File file1;

    @Before
    public void setUp() throws IOException {
        tmpFolder = Files.createTempDirectory("TestNG");
    }

    @Test
    @Category(TestCategories.PositiveTests.class)
    public void emptyFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        file.createNewFile();
    }

    @Test
    @Category(TestCategories.PositiveTests.class)
    public void sameFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        file.createNewFile();
        file.createNewFile();
    }

    @Test
    @Category(TestCategories.PositiveTests.class)
    public void anotherFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        System.out.println(tmpFolder + "\\file.txt");
        file.createNewFile();
        file1 = new File(tmpFolder + "\\file1.txt");
        file1.createNewFile();
    }

    @Test
    @Category(TestCategories.NegativeTests.class)
    public void nullFileCreateTest() throws IOException {
        file.createNewFile();
    }

    @Test
    @Category(TestCategories.NegativeTests.class)
    public void invalidNameFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\*");
        file.createNewFile();
    }

    @After
    public void tearDown() throws IOException {
        if (file!=null) {
            file.delete();
        }
        if (file1!=null) {
            file1.delete();
        }
        Files.delete(tmpFolder);
    }
}