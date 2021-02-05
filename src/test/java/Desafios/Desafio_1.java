package Desafios;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Desafio_1 {

    WebDriver webDriver;

    @BeforeClass
    @Parameters({"url", "browser","driver","path"})
    void setup(String url, String browser, String driver,String path) throws InterruptedException {

        System.setProperty(driver, path);

        webDriver = browser.equals("chrome") ? new ChromeDriver() : new FirefoxDriver();

        webDriver.get(url);

    }

    @Test
    public void testNavegarPlataformaD1() throws InterruptedException, IOException {

        //Navegação


        webDriver.findElement(By.id("open-categories-btn")).click();

        webDriver.findElement(By.id("category-1")).click();

        webDriver.findElement(By.id("add-product-4-btn")).click();

        webDriver.findElement(By.id("add-product-5-btn")).click();

        webDriver.findElement(By.id("open-categories-btn")).click();

        webDriver.findElement(By.id("category-all")).click();

        webDriver.findElement(By.id("cart-btn")).click();

        for (int i = 1; i < 5; i++) {
            webDriver.findElement(By.cssSelector("#add-product-4-qtd > span")).click();
        }

        //Printando o carrinho com o resultado final da compra
        File src = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:\\Users\\Leonardo\\IdeaProjects\\Desafio_4all\\teste ss\\screenshotd1.png"));

        webDriver.findElement(By.id("finish-checkout-button")).click();

        //Validação de texto

        String texto = webDriver.findElement(By.className("sc-dNLxif")).getText();
        Assert.assertEquals("Pedido realizado com sucesso!", texto);

        webDriver.close();

        //Thread.sleep(3000);
    }


}