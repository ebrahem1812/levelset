import org.example.LevelSetObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class LevelSet {

    WebDriver driver;
    LevelSetObj page;

    @BeforeTest
    public void preCondition(){
        driver = new ChromeDriver();
        page = new LevelSetObj(driver);

        page.goToLevelSet();
        Assert.assertTrue(page.preTest(), "Precondition Failed: Home button is not displayed!");
    }

    @AfterTest
    public void postCondition(){
        Assert.assertTrue(page.afterTest(), "Postcondition Failed: Need Help button is not displayed!");
        driver.quit();
    }

    @Test
    public void verifyFreeDocuments() {
        page.clickPaid();
        page.clickPaid();

        List<String> freeDocs = page.getFreeList();
        Assert.assertEquals(freeDocs.size(), 2, "Expected 2 free documents!");

        for (String doc : freeDocs) {
            Assert.assertEquals(doc, "Free", "Document text mismatch!");
            System.out.println("Free Document Text: " + doc);
        }
    }

    @Test
    public void verifyPaidDocuments() {
        List<String> paidDocs = page.getPaidList(30, 60);
        Assert.assertTrue(paidDocs.size() > 0, "No documents found in the price range $30-$60!");
        for (String doc : paidDocs) {
            System.out.println("Paid Document Price: " + doc);
        }
    }
}
