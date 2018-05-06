package lesson3.Task10;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UniversalDataProviders {
    @DataProvider
    public static Object[][] dataSourceLoader(FrameworkMethod testMethod) throws IOException {
        DataSource ds = testMethod.getAnnotation(DataSource.class);
        if (ds == null) {
            throw new Error("Test method has no @DataSource annotation: " + testMethod.getName());
        }
        switch (ds.type()) {
            case RESOURCE:
                return loadFilesFromResource(ds.source());

            case FILE:
                return loadFilesFromFile(ds.source());

            default:
                throw new Error("Data source type is not supported: " + ds.type());
        }
    }

    private static Object[][] loadFilesFromResource(String resource) throws IOException {
        return loadDataFromInputStream(UniversalDataProviders.class.getResourceAsStream(resource));
    }

    private static Object[][] loadFilesFromFile(String file) throws IOException {
        return loadDataFromInputStream(new FileInputStream(new File(file)));
    }

    private static Object[][] loadDataFromInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        List<Object[]> fileData = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            fileData.add( new Object[] {line});
            line = reader.readLine();
        }
        reader.close();
        return (fileData.toArray(new Object[][]{}));
    }
}
