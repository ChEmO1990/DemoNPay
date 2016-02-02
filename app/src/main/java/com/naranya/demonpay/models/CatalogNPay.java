package com.naranya.demonpay.models;

/**
 * Created by Naranya on 30/01/2016.
 */
public class CatalogNPay {
    private String name;
    private String description;
    private String country;
    private String carrier;
    private String price;
    private String interval;
    private String currency;

    public CatalogNPay() {}

    public CatalogNPay(String name, String description, String country, String carrier, String price, String interval, String currency) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.carrier = carrier;
        this.price = price;
        this.interval = interval;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
