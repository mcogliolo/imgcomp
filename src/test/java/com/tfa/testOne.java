package com.tfa;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testOne {

	@Test
	public void verificarTitulo() throws InterruptedException, IOException{ {
		WebDriver browser = new FirefoxDriver();
		browser.get("https://www.google.com.ar");

		WebElement caja = browser.findElement(By.id("lst-ib"));

		String query = "Calafate - Argentina";
		caja.sendKeys(query);
		caja.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		//Variable con el TITULO DEL LINK
		//HACES EL CODIGO PARA OBTENER EL LINK
		//LE HACES CLICK AL ELEMENTO LINK

		String tituloImagenes = "Imágenes de Calafate - Argentina";
		WebElement linkImagenes = browser.findElement(By.xpath(".//a[text()='"+tituloImagenes+"']"));
		linkImagenes.click();

		Thread.sleep(2000);
		
		File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotff.png"));

		String resultadoAct = browser.getTitle();

		browser.close();

		if (!resultadoAct.contains(query))
			Assert.fail("The query is not in the title");
	}

}
}