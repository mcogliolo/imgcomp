package com.tfa;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AgustinJAIIOTestB {

	private static final String BROWSER_FF = "FF";
	private static final String BROWSER_CHROME = "Chrome";
	private static final String BROWSER_IE = "IE";

	@Test
	public void compareImageBetweenBrowsers() {

		String pathOne = "c:\\tmp\\screenshot-br1.png";
		String pathTwo = "c:\\tmp\\screenshot-br2.png";
		String pathThree = "c:\\tmp\\z-result.png";

		try {
			navigateAndTakeScreenshot(BROWSER_CHROME, pathOne);
			navigateAndTakeScreenshot(BROWSER_FF, pathTwo);

			BufferedImage imgOne = ImageIO.read(new File(pathOne));
			BufferedImage imgTwo = ImageIO.read(new File(pathTwo));

			this.bufferedImagesEqual(imgOne, imgTwo, pathThree);

		} catch (HeadlessException e) {
			System.out.println("There is not a display in plugged in. " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("There was a problem with the thread: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Problem with accessing the file. " + e.getMessage());
		} catch (AWTException e) {
			System.out.println("There is a problem with the display. " + e.getMessage());
		}

	}

	private synchronized void navigateAndTakeScreenshot(String browserName, String path)
			throws InterruptedException, IOException, HeadlessException, AWTException {
		WebDriver browser = this.getBrowser(browserName);
		browser.manage().window().maximize();
		browser.get("https://scholar.google.com.ar/");

		Thread.sleep(7000);

		int height = browser.manage().window().getSize().getHeight();
		int width = browser.manage().window().getSize().getWidth();

		System.out.println("Browser: " + browserName);
		System.out.println("Alto: " + height);
		System.out.println("Ancho: " + width);
		System.out.println("Alto Pantalla: " + Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		System.out.println("Ancho Pantalla: " + Toolkit.getDefaultToolkit().getScreenSize().getWidth());

		Actions action = new Actions(browser);
		action.sendKeys(Keys.F11).perform();

		Thread.sleep(3000);
		
		BufferedImage image = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		
		ImageIO.write(image, "png", new File(path));
		
		action.sendKeys(Keys.F11).perform();
		browser.close();
	}

	private WebDriver getBrowser(String browserName) {

		if (browserName.equalsIgnoreCase(BROWSER_FF))
			return new FirefoxDriver();

		if (browserName.equalsIgnoreCase(BROWSER_CHROME)) {
			System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver.exe");
			return new ChromeDriver();
		}

		if (browserName.equalsIgnoreCase(BROWSER_IE)) {
			System.setProperty("webdriver.ie.driver", "C:\\eclipse\\iedriver.exe");
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			return new InternetExplorerDriver();
		}

		return new OperaDriver();
	}

	private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2, String path) throws IOException {
		boolean isEqual;

		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
			BufferedImage bi = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);

			for (int x = 0; x < img1.getWidth(); x++) {
				for (int y = 0; y < img1.getHeight(); y++) {
					if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
						isEqual = false;
						bi.setRGB(x, y, Color.ORANGE.getRGB());
					}
				}
			}

			ImageIO.write(bi, "png", new File(path));

		} else {
			isEqual = false;
		}

		isEqual = true;

		return isEqual;
	}

}
