package FlightPriceAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class FlightTestCases {
    private WebDriver driver;

    private String websiteURL = "https://airmalta.com/";

    private void delay(Long duration) {
        try {
            Thread.sleep(duration); // sleep/stop a thread for 1 second
        } catch(InterruptedException e) {
            System.out.println("An Excetion occured: " + e);
        }
    }

    @Test
    public void OpenWebsite(){


        driver = new InitializeDriver().initialize();
        // open website
        driver.get(websiteURL);

        // select One Way trip
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[1]/div[1]/div")).click();
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[1]/div[1]/div[2]/div[2]")).click();

        // select Vienna International (VIE) as Departing Airport
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[1]/div[1]/div/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[1]/div[1]/div/div/div[2]/input")).sendKeys("vienna");
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[1]/div[1]/div/div/div[2]/div/div[2]")).click();

        // select Malta International Airport (MLA) as Flying To
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[1]/div[2]/div/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[1]/div[2]/div/div/div[2]/input")).sendKeys("Malta");
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[1]/div[2]/div/div/div[2]/div/div[2]/div[1]")).click();

        // select Today Dates
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[2]/div[1]/div/div[1]/div/input")).click();

        // Delay 200 ms waiting for DatePiker to open
        delay(200L);

        //new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.className("DayPicker-Day--today")));
        driver.findElements(By.className("DayPicker-Day--today")).get(0).click();

        // Show Prices
        driver.findElement(By.xpath("//*[@id=\"flight-search-widget\"]/div/div/form/div[2]/div[2]/div[3]/button[1]")).click();


        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.className("liRvXf")));


        // Check if the page is in calender view if not go ti calenfer view
        if (!driver.findElements(By.className("gFvwyZ")).isEmpty()) {
            driver.findElements(By.className("gFvwyZ")).get(0).click();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.className("fhEQLR")));

        }

        String price = driver.findElement(By.className("fhEQLR")).findElement(By.className("liRvXf")).getText().trim();

        System.out.println(price);

        Assert.assertEquals(price,"333.67");

       driver.quit();

    }


}
