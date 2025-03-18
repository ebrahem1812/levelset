package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LevelSetObj extends AbstractComponent {

    WebDriver driver;

    public LevelSetObj(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "button[class*='btn-home-hero-v3']")
    WebElement button;
    By buttonBy = By.cssSelector("button[class*='btn-home-hero-v3']");

    @FindBy(xpath = "//button[@class='btn btn-sm btn-info' and contains(text(),'Need')]")
    WebElement needHelp;
    By needHelpBy = By.xpath("//button[@class='btn btn-sm btn-info' and contains(text(),'Need')]");

    @FindBy(xpath = "//a[contains(@class,'btn-outline ')]")
    WebElement paidButton;
    By paidButtonBy = By.xpath("//a[contains(@class,'btn-outline ')]");

    @FindBy(xpath = "//span[@class='price-amount' and contains(text(),'Free')]")
    List<WebElement> freeDocuments;
    By freeDocumentsBy = By.xpath("//span[@class='price-amount' and contains(text(),'Free')]");


    public By getPriceLocator(int minPrice, int maxPrice) {
        return By.xpath("//span[contains(@class,'price-amount') and number(translate(text(),'$',''))>"
                + minPrice + " and number(translate(text(),'$',''))<" + maxPrice + "]");
    }


    public void goToLevelSet(){
        driver.get("https://www.levelset.com/");
    }


    public void clickPaid(){
        waitForElement(paidButtonBy);
        clickElement(paidButton);
    }


    public List<String> getFreeList(){
        waitForElement(freeDocumentsBy);
        return getElementsText(freeDocuments);
    }


    public List<String> getPaidList(int minPrice, int maxPrice){
        By locator = getPriceLocator(minPrice, maxPrice);
        waitForElement(locator);
        List<WebElement> paidElements = driver.findElements(locator);
        return getElementsText(paidElements);
    }


    public boolean preTest(){
        waitForElement(buttonBy);
        return button.isDisplayed();
    }


    public boolean afterTest(){
        waitForElement(needHelpBy);
        return needHelp.isDisplayed();
    }
}
