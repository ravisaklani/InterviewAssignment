package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Amazon {
	private WebDriver driver;

	/**
	   * 
	   */
	public Amazon(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='twotabsearchtextbox']")
	private WebElement searchBox;

	@FindBy(how = How.XPATH, using = "//input[@value='Go']")
	private WebElement searchSubmit;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]")
	private WebElement nextButton;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'a-autoid-')][@role='button']")
	private WebElement sortingDropDown;

	@FindBy(how = How.XPATH, using = "//div[@aria-hidden='false']//a[contains(text(),'Price: Low to High')]")
	private WebElement lowToHighPrice;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Paperback')]")
	private WebElement paperBackFormat;

	@FindBy(how = How.XPATH, using = "//a[@class='a-link-normal s-no-outline']")
	private WebElement itemList;

	@FindBy(how = How.XPATH, using = "//input[@id='add-to-cart-button']")
	private WebElement addToCart;

	@FindBy(how = How.XPATH, using = "//*[@id='hlb-ptc-btn-native']")
	private WebElement ProccedToCheckout;

	public void searchSomething(String value) {
		searchBox.sendKeys(value);
		searchSubmit.click();
	}

	public void clickNext() {
		nextButton.click();
	}

	public void sortItems() throws InterruptedException {
		sortingDropDown.click();
		Thread.sleep(1000);
		lowToHighPrice.click();
	}

	public void selectFormat() {
		paperBackFormat.click();
	}

	public void selectFromListOfItems() {
		itemList.click();
	}

	public void clickAddToCart() throws InterruptedException {
		addToCart.click();
		Thread.sleep(2000);
		ProccedToCheckout.click();
	}

}
