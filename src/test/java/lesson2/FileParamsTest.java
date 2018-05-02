package lesson2;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class FileParamsTest {
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
    public static Object[][] loadFilesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                DataProvider.class.getResourceAsStream("/files.data")));
        List<Object[]> fileData = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            fileData.add( new Object[] {line});
            line = reader.readLine();
        }
        reader.close();
        return ((Object[][]) fileData.toArray(new Object[][]{}));
    }

    private Path tmpFolder;
    private File file;
    private File file1;

    @Test
    @UseDataProvider("loadFilesFromFile")
    public void emptyFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        Assert.assertTrue(file.createNewFile());
    }

    @Test
    @UseDataProvider("loadFilesFromFile")
    public void sameFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        Assert.assertTrue(file.createNewFile());
        Assert.assertFalse(file.createNewFile());
    }

    @Test
    @UseDataProvider("loadFilesFromFile")
    public void anotherFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        file1 = new File(tmpFolder + "\\" + fileName + "1.txt");
        Assert.assertTrue(file.createNewFile());
        Assert.assertTrue(file1.createNewFile());
    }
}
