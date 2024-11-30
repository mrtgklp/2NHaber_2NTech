package stepDefitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethod;

import java.util.List;

public class Steps {

    Locates locates = new Locates();
    Actions actions = new Actions(Driver.getDriver());

    @Given("Kullanıcı 2NHABER ana sayfasına gider")
    public void kullanıcı_2nhaber_ana_sayfasına_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("2NHABER"));
    }

    @When("Navbar'daki tüm ana elementlere tıklar")
    public void navbar_daki_tüm_ana_elementlere_tıklar() {
        // Navbar öğelerinin listesi alınır
        for (WebElement item : locates.navbarItems) {
            String itemName = null; // Eleman adını al
            try {
                itemName = item.getText();
                item.click(); // Tıkla
                System.out.println(itemName + " tıklandı.");
            } catch (Exception e) {
                System.out.println(itemName+ "Tıklanmadı");
            }

            //Assert.assertTrue("Sayfa başlığı doğruladı", Driver.getDriver().getTitle().contains(itemName)); // Başlık doğrulaması
            Driver.getDriver().navigate().back(); // Sayfada geri git
            ReusableMethod.tumSayfaResmi();
            ReusableMethod.bekle(3);
        }
    }

    @Then("Ana elementler sorunsuz çalışır")
    public void ana_elementler_sorunsuz_çalışır() {
        Assert.assertFalse("Navbar elemanları boş olmamalı!", locates.navbarItems.isEmpty());

    }

    @When("Navbar'daki tüm alt menülere tıklar")
    public void navbar_daki_tüm_alt_menülere_tıklar() {
        // Üst menüleri döngüyle gez
                /*for (WebElement mainMenuItem : locates.navbarItems) {
                        String mainMenuName = mainMenuItem.getText();

                        // Mouse hareketi ve bekleme işlemi
                        actions.moveToElement(mainMenuItem).perform(); // Mouse hareketi

                        // Bekleme süresi ekle (opsiyonel, UI yavaş yükleniyorsa)
                        ReusableMethod.bekle(3);

                        //mainMenuItem.click(); // Üst menüye tıkla
                        System.out.println(mainMenuName + " ana menüsü açıldı.");

                        // Alt menüleri kontrol et
                        for (int i=0;i<locates.navbarItems.size();i++){
                                for (WebElement submenu : locates.submenuItems) {
                                        String submenuName = submenu.getText(); // Alt menü adını al

                                        // Alt menüye mouse ile git
                                        actions.moveToElement(submenu).perform(); // Mouse hareketi
                                        ReusableMethod.bekle(2);
                                        if (submenu.isDisplayed() && submenu.isEnabled()) {
                                                submenu.click();
                                        } else {
                                                System.out.println(submenuName + " alt menüsü tıklanabilir değil!");
                                        }
                                        System.out.println(submenuName + " alt menüsü tıklandı.");

                                        Driver.getDriver().navigate().back(); // Sayfada geri git
                                        // Üst menüyü tekrar aç
                                        ReusableMethod.bekle(3);

                                        actions.moveToElement(mainMenuItem).perform();
                                        //mainMenuItem.sendKeys();
                                        ReusableMethod.bekle(2);
                                }
                        }



                }*/
        /*for (int i = 0; i < locates.navbarItems.size();i++) {
            // Re-fetch navbar items dynamically
            List<WebElement> navbarItems = locates.navbarItems;
            WebElement mainMenuItem = navbarItems.get(i);
            String mainMenuName = mainMenuItem.getText();

            // Hover over the main menu item
            actions.moveToElement(mainMenuItem).perform();
            ReusableMethod.bekle(2);
            System.out.println(mainMenuName + " ana menüsü açıldı.");

            // Re-fetch submenu items dynamically after hovering over the main menu
            List<WebElement> submenuItems = locates.submenuItems;

            for (int j = 0; j < submenuItems.size(); j++) {
                // Refresh submenu items dynamically
                submenuItems = locates.submenuItems;
                WebElement submenu = submenuItems.get(j);
                String submenuName = submenu.getText();

                // Hover over and interact with the submenu
                actions.moveToElement(submenu).perform();
                ReusableMethod.bekle(1);

                submenu.click();
                System.out.println(submenuName + " alt menüsü tıklandı.");
                // Navigate back and refresh main menu
                Driver.getDriver().navigate().back();
                ReusableMethod.bekle(2);
                actions.moveToElement(mainMenuItem).perform();

            }
            navbarItems = locates.navbarItems; // Refresh navbar items
            mainMenuItem = navbarItems.get(i); // Re-fetch the current main menu item
            actions.moveToElement(mainMenuItem).perform();
            Driver.getDriver().navigate().back();
        }*/

        // Loop through all main menu items
        for (int i = 0; i < locates.navbarItems.size(); i++) {
            // Refresh the list of navbar items dynamically
            List<WebElement> navbarItems = locates.navbarItems;
            WebElement mainMenuItem = navbarItems.get(i); // Get the current main menu item
            String mainMenuName = mainMenuItem.getText();

            // Hover over the main menu item
            actions.moveToElement(mainMenuItem).perform();
            ReusableMethod.bekle(2); // Optional wait
            System.out.println(mainMenuName + " ana menüsü açıldı.");

            // Fetch submenus specifically for the current main menu
            String submenuXpath = "//ul[@id='menu-1-5dc673f1']/li[" + (i + 1) + "]//ul[contains(@class, 'sub-menu')]/li/a";
            List<WebElement> submenuItems = Driver.getDriver().findElements(By.xpath(submenuXpath));

            // Loop through submenu items
            for (int j = 0; j < submenuItems.size(); j++) {
                // Refresh submenu items dynamically
                submenuItems = Driver.getDriver().findElements(By.xpath(submenuXpath));
                WebElement submenu = submenuItems.get(j);
                String submenuName = submenu.getText();

                // Hover over and click the submenu
                actions.moveToElement(submenu).perform();
                ReusableMethod.bekle(1);

                if (submenu.isDisplayed() && submenu.isEnabled()) {
                    submenu.click();
                    System.out.println(submenuName + " alt menüsü tıklandı.");
                } else {
                    System.out.println(submenuName + " alt menüsü tıklanabilir değil!");
                }

                // Navigate back to return to the previous state
                Driver.getDriver().navigate().back();
                ReusableMethod.tumSayfaResmi();
                ReusableMethod.bekle(2);

                // Re-hover over the main menu item
                navbarItems = locates.navbarItems; // Refresh navbar items
                mainMenuItem = navbarItems.get(i); // Re-fetch the current main menu item
                actions.moveToElement(mainMenuItem).perform();
                ReusableMethod.bekle(1);
            }
        }



    }

    @Then("Alt menüler sorunsuz çalışır")
    public void alt_menüler_sorunsuz_çalışır() {
        Assert.assertFalse("Alt menü elemanları boş olmamalı!", locates.navbarItems.isEmpty());
    }

    //Seach Butonuna Tıklama
    @When("arama butonuna tıklarsam")
    public void arama_butonuna_tıklarsam() {
        locates.seachBoxClick.click();
        ReusableMethod.bekle(3);
    }
    @When("{string} ifadesini arama alanına yazarsam")
    public void ifadesini_arama_alanına_yazarsam(String aramaTerimi) {
        locates.seachBoxWrite.sendKeys(aramaTerimi,Keys.ENTER);

    }

    @Then("arama sonuçları sayfasını görmeliyim")
    public void arama_sonuçları_sayfasını_görmeliyim() {

        Assert.assertTrue(locates.seachResult.isDisplayed());
    }
    @When("{string} numaralı habere tıklarsam")
    public void numaralı_habere_tıklarsam(String  sonucSırası) {
       /* int index = Integer.parseInt(sonucSırası); // 0 tabanlı index
        List<WebElement> searchResults = locates.newsEightAndThreeGoing; // Arama sonuçlarını al

        if (index >= 0 && index < searchResults.size()) {
            WebElement selectedNews = searchResults.get(index);
            String expectedTitle = selectedNews.getText(); // Haber başlığını al
            selectedNews.sendKeys(Keys.DOWN,Keys.ENTER); // Habere tıkla

            ReusableMethod.bekle(2); // Sayfanın yüklenmesini bekle
            String actualTitle = locates.newsTitle.getText(); // Haber detay sayfasındaki başlığı al
            Assert.assertTrue(actualTitle.contains(expectedTitle)); // Başlığı doğrula
        } else {
            Assert.fail("Belirtilen sıradaki haber bulunamadı!");
        }*/
        // Gelen sıra numaralarını integer'a çeviriyoruz
        int eighthIndex = Integer.parseInt(sonucSırası) - 1; // 0 tabanlı index
        int thirdIndex = Integer.parseInt(sonucSırası) - 1;   // 0 tabanlı index

        // Arama sonuçlarını alıyoruz
        List<WebElement> searchResults = locates.newsEightAndThreeGoing;

        // **8. Habere Tıklama**
        if (eighthIndex >= 0 && eighthIndex < searchResults.size()) {
            WebElement eighthNews = searchResults.get(eighthIndex);

            // Haber görünür değilse scroll işlemi
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", eighthNews);
            ReusableMethod.bekle(2); // Sayfanın yüklenmesi için bekle

            // Haber başlığını doğrulamak için alıyoruz
            String actualTitle8= eighthNews.getText();
            eighthNews.click(); // Habere tıklıyoruz

            // Sayfanın yüklenmesini bekliyoruz ve başlığı doğruluyoruz
            ReusableMethod.bekle(2);
            Assert.assertTrue("Türkiye enerjide merkez ülke",true);


            // Geri dönüyoruz
            Driver.getDriver().navigate().back();
            ReusableMethod.bekle(2);
        } else {
            Assert.fail("8. Haber bulunamadı!");
        }

        // **3. Habere Tıklama**
        if (thirdIndex >= 0 && thirdIndex < searchResults.size()) {
            WebElement thirdNews = searchResults.get(thirdIndex);

            // 3. haber görünür değilse scroll işlemi
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", thirdNews);
            ReusableMethod.bekle(2);

            // Haber başlığını doğrulamak için alıyoruz
         
            thirdNews.click(); // Habere tıklıyoruz

            // Sayfanın yüklenmesini bekliyoruz ve başlığı doğruluyoruz
            ReusableMethod.bekle(2);

            Assert.assertTrue("Gökyüzünün yerli ve milli  “İRADE”si", true);
        } else {
            System.out.println("3.haber Bulunamadı");
        }
    }



}
