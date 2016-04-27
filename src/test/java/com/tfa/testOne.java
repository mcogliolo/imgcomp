package com.tfa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testOne {

	@Test
	public void verificarTitulo() throws InterruptedException{ {
		WebDriver browser = new FirefoxDriver();
		browser.get("https://www.google.com.ar");

		WebElement caja = browser.findElement(By.id("lst-ib"));

		caja.sendKeys("Calafate- Argentina");
		caja.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		String resultadoAct = browser.getTitle();
		String resultadoEsp = "Promociones, Turismo, Hoteles, Excursiones";

		browser.close();

		Assert.assertEquals(resultadoEsp, resultadoAct);
	}

}
}