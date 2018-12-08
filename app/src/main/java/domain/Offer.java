package domain;

import java.util.Date;

/**
 * POJO of an Offer. Contains information such as ID, created date and so on.
 */
public class Offer {
    private final int storeId;
    private int id;
    private String title;
    private double discount;
    private double latitude;
    private double longitude;
    private double price;
    private String imagePath;
    private Date timeCounter;
    private Integer amountCounter;
    private Date createdDate;
    private Store store;

    public Offer(int id, String title, double discount, double latitude, double longitude, double price, String imagePath, Date timeCounter, Integer amountCounter, Date createdDate, int storeId) {
        this.id = id;
        this.title = title;
        this.discount = discount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.imagePath = imagePath;
        this.timeCounter = timeCounter;
        this.amountCounter = amountCounter;
        this.createdDate = createdDate;
        this.storeId = storeId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public Date getTimeCounter() {
        return timeCounter;
    }
    public void setTimeCounter(Date timeCounter) {
        this.timeCounter = timeCounter;
    }
    public Integer getAmountCounter() {
        return amountCounter;
    }
    public void setAmountCounter(Integer amountCounter) {
        this.amountCounter = amountCounter;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public domain.Store getStore() {
        return store;
    }
    public void setStore(domain.Store store) {
        this.store = store;
    }
    public int getStoreId() { return storeId; }
}
