package com.tfa;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testOne {

	@Test
	public void verificarTitulo() throws InterruptedException, IOException, HeadlessException, AWTException {
		{
			WebDriver browser = new FirefoxDriver();
			
			browser.manage().window().maximize();
			browser.get("https://www.google.com.ar");

			WebElement caja = browser.findElement(By.id("lst-ib"));

			String query = "Calafate - Argentina";
			caja.sendKeys(query);
			caja.sendKeys(Keys.ENTER);

			Thread.sleep(2000);

			// Variable con el TITULO DEL LINK
			// HACES EL CODIGO PARA OBTENER EL LINK
			// LE HACES CLICK AL ELEMENTO LINK

			String tituloImagenes = "El Calafate - Wikipedia, la enciclopedia libre";
			WebElement linkImagenes = browser.findElement(By.xpath(".//a[text()='" + tituloImagenes + "']"));
			linkImagenes.click();

			Thread.sleep(7000);

			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File("c:\\tmp\\screenshotff.png"));
			
//			File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotff.png"));

			String resultadoAct = browser.getTitle();

			browser.close();

			if (!resultadoAct.contains("Wikipedia"))
				Assert.fail("The query is not in the title");
		}

	}

	@Test
	public void verificarTituloChrome() throws InterruptedException, IOException, HeadlessException, AWTException {
		{
			System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver.exe");
			WebDriver browser = new ChromeDriver();
			browser.manage().window().maximize();
			browser.get("https://www.google.com.ar");

			WebElement caja = browser.findElement(By.id("lst-ib"));

			String query = "Calafate - Argentina";
			caja.sendKeys(query);
			caja.sendKeys(Keys.ENTER);

			Thread.sleep(2000);

			// Variable con el TITULO DEL LINK
			// HACES EL CODIGO PARA OBTENER EL LINK
			// LE HACES CLICK AL ELEMENTO LINK

			String tituloImagenes = "El Calafate - Wikipedia, la enciclopedia libre";
			WebElement linkImagenes = browser.findElement(By.xpath(".//a[text()='" + tituloImagenes + "']"));
			linkImagenes.click();

			Thread.sleep(7000);

			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File("c:\\tmp\\screenshotch.png"));
//			File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotch.png"));

			String resultadoAct = browser.getTitle();

			browser.close();

			if (!resultadoAct.contains("Wikipedia"))
				Assert.fail("The query is not in the title");
		}

	}
}