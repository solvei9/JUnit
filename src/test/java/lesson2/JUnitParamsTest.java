package lesson2;

import com.tngtech.java.junit.dataprovider.DataProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {

    @DataProvider
    public static Object[][] files() {
        return new Object [][] {
                {generateRandomFileName()},
                {generateRandomFileName()},
                {generateRandomFileName()},
                {generateRandomFileName()},
                {generateRandomFileName()}
        };
    }

    private Path tmpFolder;
    private File file;
    private File file1;

    @Before
    public void setUp() throws IOException {
        tmpFolder = Files.createTempDirectory("TestNG");
    }

    @Test
    @Parameters(method = "files")
    public void emptyFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        Assert.assertTrue(file.createNewFile());
    }

    @Test
    @Parameters(method = "files")
    public void sameFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        Assert.assertTrue(file.createNewFile());
        Assert.assertFalse(file.createNewFile());
    }

    @Test
    @Parameters(method = "files")
    public void anotherFileCreateTest(String fileName) throws IOException {
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
