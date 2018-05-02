package lesson2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RunWith(Parameterized.class)
public class ParametrizationTest {

    @Parameters(name = "fileName: {0}")
    public static Collection<Object[]> files() {
        return Arrays.asList(new Object [][] {
                {generateRandomFileName()},
                {generateRandomFileName()},
                {generateRandomFileName()},
                {generateRandomFileName()},
                {generateRandomFileName()}
        });
    }

    private Path tmpFolder;
    private File file;
    private File file1;

    //@Parameter(0)
    private String fileName;

    @Before
    public void setUp() throws IOException {
        tmpFolder = Files.createTempDirectory("TestNG");
    }

    public ParametrizationTest(String fileName) {
        this.fileName = fileName;
    }

    @Test
    public void emptyFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        Assert.assertTrue(file.createNewFile());
    }

    @Test
    public void sameFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        Assert.assertTrue(file.createNewFile());
        Assert.assertFalse(file.createNewFile());
    }

    @Test
    public void anotherFileCreateTest() throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        file1 = new File(tmpFolder + "\\" + fileName + "1.txt");
        Assert.assertTrue(file.createNewFile());
        Assert.assertTrue(file1.createNewFile());
    }

    private static String generateRandomFileName() {
        return "input" + new Random().nextInt();
    }

    @After
    public void tearDown() throws IOException {
        if (!(file==null)) {
            file.delete();
        }
        if (!(file1==null)) {
            file1.delete();
        }
        Files.delete(tmpFolder);
    }
}
