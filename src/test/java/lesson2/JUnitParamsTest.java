package lesson2;

import com.tngtech.java.junit.dataprovider.DataProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {
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
}
