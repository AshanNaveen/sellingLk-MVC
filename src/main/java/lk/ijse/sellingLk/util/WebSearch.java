package lk.ijse.sellingLk.util;

import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.dto.WebVehicleDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WebSearch {
    public List<WebVehicleDto> search(String key) {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        WebDriver driver = new ChromeDriver(options);

        System.out.println("Web Driver is Created");

        driver.navigate().to("https://riyasewana.lk/");

        System.out.println("Site Loaded");

        List<WebVehicleDto> list = new ArrayList<>();
        try {
            driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[4]/div/div/div/form/div[2]/input")).sendKeys(key);
            driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[4]/div/div/div/form/div[9]/input")).click();
            List<WebElement> pages = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li/h2/a"));
            System.out.println(pages.size());
            int j = pages.size();
            System.out.println(pages);
            for (int i = 0; i < j; i++) {
                pages.get(i).click();
                var dto = new WebVehicleDto();

                String temp = driver.findElement(By.xpath("//*[@id=\"content\"]/h2")).getText();
                String[] cityAr = temp.split(", ");

                String title = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
                String brand = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[5]/td[2]")).getText();
                String model = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[5]/td[4]")).getText();
                String contact = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[3]/td[2]/span")).getText();
                String year = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[6]/td[2]")).getText();
                String price = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[3]/td[4]/span")).getText();

                String fuelType = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[7]/td[4]")).getText();
                String engineCapacity = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[8]/td[4]")).getText();
                String mileage = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[6]/td[4]")).getText();
                String city = cityAr[1];

                if (Pattern.matches("[2|1][9|0]\\d{2}",year))

                list.add(dto);
                System.out.print(i + "  : ");
                System.out.println(dto);

                driver.navigate().back();
                pages = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li/h2/a"));

                //Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return list;
    }
}
