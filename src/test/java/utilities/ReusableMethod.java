package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Locates;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class ReusableMethod {
    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;

    //HARD WAIT METHOD
    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    // Burada Hata var
    public static void tumSayfaResmi() {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "testOutput/screenshot/screenshot" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void clickElementByJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("arguments[0].click();", element);
    }

    public static void extentReport() {
        extentReports = new ExtentReports();
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
        extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
        extentReports.attachReporter(extentHtmlReporter);

        //Raporda gözükmesini istediğimiz bilgiler için
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Tester", "Murat");
        extentHtmlReporter.config().setDocumentTitle("Extent Report");
        extentHtmlReporter.config().setReportName("Smoke Test Raporu");
    }
    // Eğitim bilgileri arasından belirtilen seçeneği seçen metot
    public static void selectEgitimBilgisi(String egitimSeviyesi) {
        Locates locates = new Locates();
        for (WebElement egitim : locates.egitimBilgileri) {
            if (egitim.getText().equalsIgnoreCase(egitimSeviyesi)) {
                egitim.click();
                System.out.println(egitimSeviyesi + " seçildi.");
                return;
            }
        }
        System.out.println(egitimSeviyesi + " seçeneği bulunamadı!");
    }



       private static final Faker faker = new Faker();

        public static String generateGmailAddress() {
            String localPart = faker.internet().emailAddress().split("@")[0]; // '@' öncesindeki kısmı alır
            return localPart + "@gmail.com"; // gmail uzantısı ekler
        }

        public static void main(String[] args) {
            // Test
            for (int i = 0; i < 5; i++) {
                System.out.println(generateGmailAddress());
            }
        }

    public static void waitForTitleContains(WebDriver driver, String titlePart, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.titleContains(titlePart));
            System.out.println("Başlık başarıyla bulundu: " + titlePart);
        } catch (Exception e) {
            System.err.println("Başlık bekleme işlemi başarısız: " + titlePart);
            throw e; // Hata fırlatılarak testin durması sağlanır
        }
    }







}
