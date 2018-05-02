package lesson2;

import org.junit.*;
import org.junit.rules.ExternalResource;
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
}
