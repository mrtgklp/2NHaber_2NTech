package stepDefitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.Locates;
import utilities.Driver;
import utilities.ReusableMethod;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Steps2NTech {
    Locates locates = new Locates();
    Faker faker = new Faker();

    @Given("Kullanıcı {string} sayfasını açar")
    public void kullanıcı_sayfasını_açar(String url) {
        Driver.getDriver().get(url);
    }


    @When("Kullanıcı Ad Soyadı alanına  yazar")
    public void kullanıcı_ad_soyadı_alanına_yazar() {
        locates.name.sendKeys(faker.name().fullName(), Keys.ENTER);
    }

    @When("Kullanıcı Doğum Tarihini  olarak girer")
    public void kullanıcı_doğum_tarihini_olarak_girer() {
        // Rastgele doğum günü oluştur
        Date birthday = faker.date().birthday();

        // Tarihi istediğiniz formatta (örneğin "dd.MM.yyyy") biçimlendirin
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedBirthday = dateFormat.format(birthday);

        locates.dateBirth.sendKeys(formattedBirthday);
    }

    @When("Kullanıcı T.C Kimlik Numarası alanına  yazar")
    public void kullanıcı_t_c_kimlik_numarası_alanına_yazar() {
        String tcKimlikNo;
        do {
            tcKimlikNo = faker.number().digits(11); // 11 haneli sayı
        } while (tcKimlikNo.startsWith("0"));


        locates.tcNumarası.sendKeys(tcKimlikNo, Keys.TAB);
    }


    @When("Kullanıcı Cep Telefonu alanına  yazar")
    public void kullanıcı_cep_telefonu_alanına_yazar() {
        String phoneNumber;

        // Telefon numarasını "05XXXXXXXXX" formatında oluştur
        phoneNumber = "05" + faker.number().digits(9);

        // Selenium ile input'a gönder
        locates.phoneNo.sendKeys(phoneNumber, Keys.ENTER);
    }

    @When("Kullanıcı Mail Adresi alanına  yazar")
    public void kullanıcı_mail_adresi_alanına_yazar() {
            locates.email.sendKeys(faker.internet().emailAddress(),Keys.ENTER,Keys.PAGE_DOWN);
    }

    @And("Kullanıcı CV dosyasını {string} olarak yükler")
    public void kullanıcıCVDosyasınıOlarakYukler(String cvYukle) throws AWTException {
        ReusableMethod.clickElementByJS(locates.cvPdf);
        locates.cvPdf.sendKeys(cvYukle);
        ReusableMethod.bekle(3);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);






    }

    @When("Kullanıcı Eğitim Durumu olarak {string} seçeneğini seçer")
    public void kullanıcı_eğitim_durumu_olarak_seçeneğini_seçer(String lisans) {
            ReusableMethod.selectEgitimBilgisi(lisans);


    }


    @When("Kullanıcı KVKK Metni'ni onaylar")
    public void kullanıcı_kvkk_metni_ni_onaylar() {
        locates.kvkkBox.isDisplayed();
        System.out.println("Kvkk Metni butonu basılı geliyor");

    }

    @When("Kullanıcı İleri ok tuşuna tıklar")
    public void kullanıcı_ileri_ok_tuşuna_tıklar() {
            locates.ileriButton.click();
    }

    @When("Kullanıcı İkinci Adım ekranını görüntüler")
    public void kullanıcı_ikinci_adım_ekranını_görüntüler() {
        locates.adım2.isDisplayed();
    }

    @When("Kullanıcı Test Engineer pozisyonunu seçer")
    public void kullanıcı_pozisyonunu_seçer() {
        locates.testEngineer.click();

    }

    @When("Kullanıcı Formu Gönder butonuna tıklar")
    public void kullanıcı_formu_gönder_butonuna_tıklar() {
        locates.gonder.click();
        ReusableMethod.bekle(4);

    }



    @Then("Formun başarılı bir şekilde gönderildiği mesajını görmelidir--")
    public void formunBasarılıBirSekildeGonderildigiMesajınıGormelidir() {
        Assert.assertTrue(locates.başarılıGonderıldı.isDisplayed());

    }

    @And("Kullanıcı Sayfayı Kapatır")
    public void kullanıcıSayfayıKapatır() {
        Driver.closeDriver();
    }
}
