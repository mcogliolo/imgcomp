package com.tfa;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testOne {
	
	@Test
	public void verificarTitulo(){
		WebDriver browser = new FirefoxDriver();
		browser.get("www.google.com.ar");
	}
	
}