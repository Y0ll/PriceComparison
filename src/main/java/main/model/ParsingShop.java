package main.model;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ParsingShop
{
    private List<String> properties = new ArrayList<>();
    private String pathToFile = "data/prices.xls";
    private String nameProduct;
    private ChromeOptions options = new ChromeOptions();
    public ParsingShop(String nameProduct) throws IOException {
        this.nameProduct = nameProduct;
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        options.setHeadless(false);
        options.addArguments("--start-maximized");
    }

    public List<String> parsingMVIDEO() throws IOException, InterruptedException {
        properties.clear();
        String search = "https://www.mvideo.ru/product-list-page?q=" + nameProduct;
        WebDriver driver = new ChromeDriver(options);
        String inShop = "";
        String img = "";
        driver.get(search);
        String product = "Name = ";
        Thread.sleep(6000);
        try {
            inShop = driver.findElements(By.cssSelector("body > mvid-root > div > mvid-primary-layout > mvid-layout > div > main > mvid-srp > mvid-product-list-block > div.plp__card-grid > mvid-product-list > mvid-plp-product-cards-layout > div > mvid-product-cards-row > div > mvid-preorder-v2-wrapper > div > mvid-plp-notification-block > div")).get(0).getText();
        }
        catch (Exception ignored){}
        String price = driver.findElements(By.cssSelector("body > mvid-root > div > mvid-primary-layout > mvid-layout > div > main > mvid-srp > mvid-product-list-block > div.plp__card-grid > mvid-product-list > mvid-plp-product-cards-layout > div > mvid-product-cards-row > div > div:nth-child(6) > mvid-plp-price-block > div > mvid-price > div > span")).get(0).getText();
        String name = driver.findElements(By.cssSelector("body > mvid-root > div > mvid-primary-layout > mvid-layout > div > main > mvid-srp > mvid-product-list-block > div.plp__card-grid > mvid-product-list > mvid-plp-product-cards-layout > div > mvid-product-cards-row > div > div:nth-child(4) > mvid-plp-product-title > div > a")).get(0).getText();

        driver.findElements(By.cssSelector("body > mvid-root > div > mvid-primary-layout > mvid-layout > div > main > mvid-srp > mvid-product-list-block > div.plp__card-grid > mvid-product-list > mvid-plp-product-cards-layout > div > mvid-product-cards-row > div > div:nth-child(4) > mvid-plp-product-title > div > a")).get(0).click();
        Thread.sleep(6000);
        try {
            img = driver.findElements(By.cssSelector("body > mvid-root > div > mvid-primary-layout > mvid-layout > div > main > mvid-pdp > mvid-pdp-general > div > mvid-general-details > section > div.left.ng-star-inserted > div > div > mvid-media-place > mvideo-product-carousel > div > div > mvideoru-carousel-active-item > div > div > mvideo-zoomable-image > button > picture > img")).get(0).getAttribute("src");
        }
        catch (Exception ignored){}
        properties.add(name);
        properties.add(price);
        properties.add("MVIDEO");
        if(inShop.length() == 0 & name.length() == 0)
        {
            properties.add("Нет в наличии");

        }
        else{
            if(name.length() != 0 & inShop.length() == 0)
            {
                properties.add("Есть в наличии");
            }
            if(name.length() != 0 & inShop.length() != 0) {
                properties.add(inShop);
            }
        }
        properties.add(driver.getCurrentUrl());
        properties.add(img);
        driver.quit();
//        writeIntoExcel(pathToFile, name, price, "MVIDEO");
        return properties;
    }

    public List<String> parsingDNS() throws IOException, InterruptedException {
        properties.clear();
        String search = "https://www.dns-shop.ru/search/?q=" + nameProduct;
        WebDriver driver = new ChromeDriver(options);
        String inShop = "";
        String image = "";

        driver.get(search);
        String product = "";
        Thread.sleep(6000);
        try {
            inShop =  driver.findElements(By.cssSelector("div.order-avail-wrap")).get(0).getText();
            image = driver.findElements(By.cssSelector("#search-results > div.products-list > div > div.catalog-products.view-simple > div:nth-child(1) > div.catalog-product__image > a > picture > img")).get(0).getAttribute("src");

            driver.findElements(By.cssSelector("#search-results > div.products-list > div > div.catalog-products.view-simple > div:nth-child(1)")).get(0).click();

        }
        catch (Exception ignored) {}
        try{ driver.findElements(By.cssSelector("body > div.container.category-child > div > div.products-page__content > div.products-page__list > div.products-list > div > div:nth-child(2) > div:nth-child(1) > a")).get(0).click();}
        catch (Exception ignored){}
        Thread.sleep(6000);
        try {
            String name = driver.findElements(By.cssSelector("div.product-card-top.product-card-top_full > h1")).get(0).getText() + " " + driver.findElements(By.cssSelector("div.product-card-top.product-card-top_full > div.product-card-top__specs")).get(0).getText();
            String price = driver.findElements(By.cssSelector("div.product-card-top.product-card-top_full > div.product-card-top__buy > div.product-buy.product-buy_one-line > div > div.product-buy__price")).get(0).getText();
            if(image.equals(""))
            {
                try
                {
                    image = driver.findElements(By.cssSelector("picture > img")).get(0).getAttribute("src");
                }
                catch (Exception ignored){}
            }
            properties.add(name);
            properties.add(price);
            properties.add("DNS");
            properties.add(inShop);
            properties.add(driver.getCurrentUrl());
            properties.add(image);
//            writeIntoExcel(pathToFile, name, price, "DNS");
        }
        catch (Exception ignodred){}
        driver.quit();
        return properties;
    }

//    @SuppressWarnings("deprecation")
//    public static void writeIntoExcel(String file, String nameProduct, String priceProduct, String shop) throws FileNotFoundException, IOException {
//        try
//        {
//            FileInputStream myxls = new FileInputStream(file);
//            HSSFWorkbook pricesSheet = new HSSFWorkbook(myxls);
//            HSSFSheet worksheet = pricesSheet.getSheetAt(0);
//            int lastRow=worksheet.getLastRowNum();
//            Row row = worksheet.createRow(++lastRow);
//            row.createCell(0).setCellValue(shop);
//            row.createCell(1).setCellValue(nameProduct);
//            row.createCell(2).setCellValue(priceProduct);
//            row.createCell(3).setCellValue((new Date()).toString());
//            myxls.close();
//            FileOutputStream output_file =new FileOutputStream(new File(file));
//            pricesSheet.write(output_file);
//            output_file.close();
//        }
//        catch(Exception e)
//        {
//        }
//    }

}
