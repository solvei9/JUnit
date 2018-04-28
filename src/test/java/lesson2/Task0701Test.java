package lesson2;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task0701Test {
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
        file.createNewFile();
    }

    @Test
    public void sameFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        file.createNewFile();
        file.createNewFile();
    }

    @Test
    public void anotherFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\file.txt");
        System.out.println(tmpFolder + "\\file.txt");
        file.createNewFile();
        file1 = new File(tmpFolder + "\\file1.txt");
        file1.createNewFile();
    }

    @Test
    public void nullFileCreateTest() throws IOException {
        file.createNewFile();
    }

    @Test
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