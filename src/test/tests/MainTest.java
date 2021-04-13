package tests;

import generators.FileGenerator;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTest {
    @BeforeMethod
    public void setUp() throws IOException {
        Files.createDirectory(Paths.get("test"));
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File testDir = new File("test");
        FileUtils.deleteDirectory(testDir);
    }

    @Parameters("filename")
    @Test(groups = { "positive" })
    public void createFileTest(String filename) throws IOException {
        File f = new File("test/" + filename);
        Assert.assertTrue(f.createNewFile());
    }

    @Test(groups = { "negative" })
    public void fileNotExistsTest() {
        File f = new File("unknownFile");
        Assert.assertFalse(f.exists());
    }

    @Test
    public void generateFileTest() throws Exception {
        String fileName = FileGenerator.create("test");

        Assert.assertTrue((new File(fileName)).exists());
    }
}
