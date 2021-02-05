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

public class Desafio_2 {

    WebDriver webDriver;

    @BeforeClass
    @Parameters({"url", "browser","driver","path"})
    void setup(String url, String browser, String driver,String path) throws InterruptedException {

        System.setProperty(driver, path);

        webDriver = browser.equals("chrome") ? new ChromeDriver() : new FirefoxDriver();

        webDriver.get(url);

    }

    @Test
    public void testNavegarPlataformaD2() throws InterruptedException, IOException {

        //Navegação

        webDriver.findElement(By.id("open-categories-btn")).click();

        webDriver.findElement(By.id("category-0")).click();

        webDriver.findElement(By.id("add-product-0-btn")).click();

        webDriver.findElement(By.id("add-product-1-btn")).click();

        webDriver.findElement(By.id("add-product-2-btn")).click();

        webDriver.findElement(By.id("open-categories-btn")).click();

        webDriver.findElement(By.id("category-all")).click();

        webDriver.findElement(By.id("add-product-3-btn")).click();

        webDriver.findElement(By.id("cart-btn")).click();

        for (int i = 1; i < 10; i++) {
            webDriver.findElement(By.cssSelector("#add-product-3-qtd > span")).click();
        }

        for (int i = 1; i < 6; i++) {
            webDriver.findElement(By.cssSelector("#remove-product-3-qtd > span")).click();
        }

        //Validação da quantidade

        String quantidade = webDriver.findElement(By.id("product-3-qtd")).getText();
        //Ao inserir 9 numa quantidade de 1, tem-se 10, e, após a remoção de 5 itens, o valor esperado deve ser 5
        Assert.assertEquals("5", quantidade);

        //Printando o carrinho com o resultado final da compra
        File src = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:\\Users\\Leonardo\\IdeaProjects\\Desafio_4all\\teste ss\\screenshotd2.png"));

        webDriver.findElement(By.id("finish-checkout-button")).click();

        webDriver.findElement(By.className("close-modal")).click();

        webDriver.close();

    }

}
