package domain;

import java.util.Date;

/**
 * Created by Sune Jepsen on 19-10-2018.
 */

public class Offer {
    private int Id;
    private String Title;
    private double Discount;
    private double Latitude;
    private double Longitude;
    private double Price;
    private String ImagePath;
    private Date TimeCounter=null;
    private Integer AmountCounter=null;
    private Date CreatedDate;
    private Store Store;

    public Offer(int id, String title, double discount, double latitude, double longitude, double price, String imagePath, Date timeCounter, Integer amountCounter, Date createdDate, Store store) {
        Id = id;
        Title = title;
        Discount = discount;
        Latitude = latitude;
        Longitude = longitude;
        Price = price;
        ImagePath = imagePath;
        TimeCounter = timeCounter;
        AmountCounter = amountCounter;
        CreatedDate = createdDate;
        Store = store;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public Date getTimeCounter() {
        return TimeCounter;
    }

    public void setTimeCounter(Date timeCounter) {
        TimeCounter = timeCounter;
    }

    public Integer getAmountCounter() {
        return AmountCounter;
    }

    public void setAmountCounter(Integer amountCounter) {
        AmountCounter = amountCounter;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public domain.Store getStore() {
        return Store;
    }

    public void setStore(domain.Store store) {
        Store = store;
    }
}
