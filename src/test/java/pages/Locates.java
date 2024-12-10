package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;


public class Locates {

    public String expectedTitle;

    public Locates(){

        PageFactory.initElements(Driver.getDriver(),this);
    }
    // Locator for navbar items
    @FindBy(xpath = "//ul[@id='menu-1-5dc673f1']/li/a")
    public List<WebElement>  navbarItems ; // Navbar ana öğeleri

    @FindBy(xpath = "(//ul[contains(@class, 'sub-menu')]/li/a)[position() <= 21]")
    public List<WebElement> submenuItems;

    @FindBy(css = "[class$='inner-icon']")
    public  WebElement seachBoxClick;

    //@FindBy(css = "[class$='search__popup-content']")
    //public WebElement seachBoxWrite;//input[@type='search']

    @FindBy(xpath = "//input[@type='search']")
    public WebElement seachBoxWrite;

    @FindBy(css = "[data-id='5355f3f8']")
    public WebElement seachResult;

    @FindBy(xpath = "//div[@data-id='190f34a9']")
    public  List<WebElement> newsEightAndThreeGoing;

    @FindBy(css = "h3[class^='entry-title']")
    public WebElement newsTitle;

    //2NTech Localtor
    @FindBy(css = "input[type='text'][name='name']")
    public WebElement name;

    @FindBy(css = "input[type='date'][name='birth']")
    public WebElement dateBirth;

    @FindBy(xpath = "//input[@id='tcKimlik']")
    public WebElement tcNumarası;

    @FindBy(xpath = "//input[@id='phone']")
    public  WebElement phoneNo;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@id='cv_field']")
    public WebElement cvPdf;

    @FindBy(xpath = "//button[@type='button']")
    public List<WebElement> egitimBilgileri;

    @FindBy(xpath = "//input[@id='pdp1']")
    public WebElement kvkkBox;

    @FindBy(css = "button[type='submit']")
    public WebElement ileriButton;

    //2.Adım Localtor
    @FindBy(xpath = "//span[text()='2. Adım']")
    public  WebElement adım2;

    @FindBy(xpath = "//span[text()='Test Engineer']")
    public WebElement testEngineer;

    @FindBy(xpath = "//div[text()='Gönder']")
    public WebElement gonder;

    @FindBy(xpath = "//p[text()='Form Başarı ile gönderildi. Katıldığınız için teşekkür ederiz.']")
    public  WebElement başarılıGonderıldı;

    @FindBy(css = "[class='relative group/text']")
    public WebElement bilgileriDogruDoldurun;



}
