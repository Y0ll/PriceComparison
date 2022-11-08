package main.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;

    private String nameProduct;
    private  String hrefProducts;
    private String image;



    @ManyToOne(fetch = FetchType.LAZY)
    private Month month;



    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Price> prices;

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public String getHrefProducts() {
        return hrefProducts;
    }

    public void setHrefProducts(String hrefProducts) {
        this.hrefProducts = hrefProducts;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }




}
