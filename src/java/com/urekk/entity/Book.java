
package com.urekk.entity;

/**
 *
 * @author urekk
 */
public class Book {
    private int id;
    private String name;
    private String description;
    private String author;
    private String published;
    private String ISBN;
    private String length;
    private double price;
    private String summary;
    private String image;

    public Book() {
    }

    public Book(int id, String name, String discription, String author, String published, String ISBN, String length, double price, String summary, String image) {
        this.id = id;
        this.name = name;
        this.description = discription;
        this.author = author;
        this.published = published;
        this.ISBN = ISBN;
        this.length = length;
        this.price = price;
        this.summary = summary;
        this.image = image;
    }

    public Book(String name, String discription, String author, String published, String ISBN, String length, double price, String summary, String image) {
        this.name = name;
        this.description = discription;
        this.author = author;
        this.published = published;
        this.ISBN = ISBN;
        this.length = length;
        this.price = price;
        this.summary = summary;
        this.image = image;
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

    public void setDescription(String discription) {
        this.description = discription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", discription=" + description + ", author=" + author + ", published=" + published + ", ISBN=" + ISBN + ", length=" + length + ", price=" + price + ", summary=" + summary + ", image=" + image + '}';
    }
    
    
    
}
