package lesson2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Task0703Test {

        private Path tmpFolder;
        private File file;
        private File file1;

        @Before
        public void setUp() throws IOException {
            tmpFolder = Files.createTempDirectory("TestNG");
        }

        @Test
        public void emptyFileCreateTest(String fileName) throws IOException {
            file = new File(tmpFolder + "\\" + fileName + ".txt");
            Assert.assertTrue(file.createNewFile());
        }

        @Test
        public void sameFileCreateTest(String fileName) throws IOException {
            file = new File(tmpFolder + "\\" + fileName + ".txt");
            Assert.assertTrue(file.createNewFile());
            Assert.assertFalse(file.createNewFile());
        }

        @Test
        public void anotherFileCreateTest(String fileName) throws IOException {
            file = new File(tmpFolder + "\\" + fileName + ".txt");
            file1 = new File(tmpFolder + "\\" + fileName + "1.txt");
            Assert.assertTrue(file.createNewFile());
            Assert.assertTrue(file1.createNewFile());
        }

        public Iterator<Object[]> files() {
            List<Object[]> data = new ArrayList<Object[]>();
            for (int i = 0; i < 5; i++) {
                data.add(new Object[]{
                        generateRandomFileName()
                });
            }
            return data.iterator();
        }

        /*public Iterator<Object[]> loadFilesFronFile() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    DataProvider.class.getResourceAsStream("/files.data")));
            List<Object[]> fileData = new ArrayList<Object[]>();
            String line = reader.readLine();
            while (line != null) {
                fileData.add( new Object[] {line});
                line = reader.readLine();
            }
            return fileData.iterator();
        }*/

        public String generateRandomFileName() {
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
