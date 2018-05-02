package lesson2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GroupingTest {
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

    private Path tmpFolder;
    private File file;
    private File file1;

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
}