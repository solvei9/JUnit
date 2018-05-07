package lesson3.Task10;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    private Path tmpFolder;
    private File file;
    private File file1;

    @Test
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(source = "/files.data", type = DataSource.Type.RESOURCE)
    public void emptyFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        SoftAssertions s = new SoftAssertions();
        s.assertThat(file.createNewFile());
        s.assertThat(file.exists());
        s.assertAll();
    }

    @Test
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(source = "/files.data", type = DataSource.Type.RESOURCE)
    public void sameFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        SoftAssertions s = new SoftAssertions();
        s.assertThat(file.createNewFile());
        s.assertThat(!(file.createNewFile()));
        s.assertThat(file.exists());
        s.assertAll();
    }

    @Test
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(source = "/files.data", type = DataSource.Type.RESOURCE)
    public void anotherFileCreateTest(String fileName) throws IOException {
        file = new File(tmpFolder + "\\" + fileName + ".txt");
        file1 = new File(tmpFolder + "\\" + fileName + "1.txt");
        SoftAssertions s = new SoftAssertions();
        s.assertThat(file.createNewFile());
        s.assertThat(file.exists());
        s.assertThat(file1.createNewFile());
        s.assertThat(file1.exists());
        s.assertAll();
    }
}
