package com.example.testapp.Model;

public class Product {

    private int id;
    private String name;
    private String description;
    private int price;
    private int artwork_link;


    public Product (int id, String name, String description, int price, int artwork_link){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.artwork_link = artwork_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getArtwork_link() {
        return artwork_link;
    }

    public void setArtwork_link(int artwork_link) {
        this.artwork_link = artwork_link;
    }
}
