package lk.ijse.sellingLk.util;

import lk.ijse.sellingLk.dto.SearchDto;
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
    private SearchDto dto = null;

    private String link = "";

    public List<WebVehicleDto> search(SearchDto dto) {
        this.dto = dto;

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        WebDriver driver = new ChromeDriver(options);

        setType();
        setBrand();
        setModel();
        setYear();
        setFuelType();
        setPrice();

        link = link.toLowerCase();
        System.out.println(link);


        System.out.println("Web Driver is Created");

        driver.navigate().to("https://riyasewana.lk/search/" + link);

        System.out.println("Site Loaded");

        List<WebVehicleDto> list = new ArrayList<>();
        try {
            //driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[4]/div/div/div/form/div[2]/input")).sendKeys("civic");
            //driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[4]/div/div/div/form/div[9]/input")).click();
            if (!driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li/h2/a")).isEmpty()) {
                List<WebElement> pages = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li/h2/a"));
                System.out.println(pages.size());
                int j = pages.size();
                System.out.println(pages);
                for (int i = 0; i < j; i++) {
                    pages.get(i).click();
                    String title = null;
                    String brand = null;
                    String model = null;
                    String contact = null;
                    String year = null;
                    String price = null;
                    String fuelType = null;
                    String engineCapacity = null;
                    String mileage = null;
                    String city = null;
                    String temp = null;
                    try {

                        temp = driver.findElement(By.xpath("//*[@id=\"content\"]/h2")).getText();
                        String[] cityAr = temp.split(", ");
                        city = cityAr[1];

                        title = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
                        brand = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[5]/td[2]")).getText();
                        model = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[5]/td[4]")).getText();
                        contact = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[3]/td[2]/span")).getText();
                        year = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[6]/td[2]")).getText();
                        price = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[3]/td[4]/span")).getText();

                        fuelType = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[7]/td[4]")).getText();
                        engineCapacity = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[8]/td[4]")).getText();
                        mileage = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[6]/td[4]")).getText();


                        /*if (ValidateUtil.validatePhone(contact) && ValidateUtil.validateYear(Integer.parseInt(year)) && ValidateUtil.validatePrice(price))
                        {*/
                            System.out.println("web");
                            list.add(new WebVehicleDto(title, brand, model, contact, year, price, fuelType, engineCapacity, mileage, city));
                            System.out.print(i + "  : ");
                            System.out.println(dto);
                        //}else System.out.println("else");
                        if (list.size()==25)break;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    driver.navigate().back();
                    pages = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li/h2/a"));
                    //Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return list;
    }

    private void setPrice() {
        int min = dto.getPriceMin();
        int max = dto.getPriceMax();
        System.out.println(min);
        System.out.println(max);
        if ((min != 0) && max == 0) {
            System.out.println("im in price  1");
            link += "price-" + min + "-0/";
        } else if (min == 0 && (max != 0)) {
            System.out.println("im in price  2");
            link += "price-0-" + max + "/";
        } else if (min != 0 && max != 0) {
            System.out.println("im in price  3");
            link += "price-" + min + "-" + max + "/";
        }
    }

    private void setFuelType() {
        String fuel = dto.getFuelType();
        if (!fuel.isEmpty()) {
            System.out.println(fuel);
            link += fuel + "/";
        }
    }

    private void setYear() {
        int min = dto.getYearMin();
        int max = dto.getYearMax();
        System.out.println(min);
        System.out.println(max);
        if ((min != 0) && max == 0) {
            System.out.println("im in year  1");
            link += min + "-0/";
        } else if ((min == 0) && max != 0) {
            System.out.println("im in year  2");
            link += "0-" + max + "/";
        } else if ((min != 0) && max != 0) {
            System.out.println("im in year  3");
            link += min + "-" + max + "/";
        }
    }

    private void setModel() {
        String model = dto.getModel();
        if (!model.isEmpty()) {
            System.out.println(model);
            link += model + "/";
        }
    }

    private void setBrand() {
        String brand = dto.getBrand();
        if (!brand.isEmpty()) {
            System.out.println(brand);
            link += brand + "/";
        }
    }

    private void setType() {
        String type = dto.getType();
        if (!type.isEmpty()) {
            System.out.println(type);
            switch (type) {
                case "Car":
                    link = "cars/";
                    break;
                case "Van":
                    link = "vans/";
                    break;
                case "SUV/Jeep":
                    link = "suvs/";
                    break;
                case "Motor Cycle":
                    link = "motorcycles/";
                    break;
                case "Crew Cab":
                    link = "crew-cabs/";
                    break;
                case "Pickup / Double Cab":
                    link = "pickups/";
                    break;
                case "Bus":
                    link = "buses/";
                    break;
                case "Lorry":
                    link = "lorries/";
                    break;
                case "Three Wheel":
                    link = "three-wheels/";
                    break;
                case "Other":
                    link = "others/";
                    break;
            }


        }
    }
}
