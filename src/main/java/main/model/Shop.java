package main.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shop
{

    @Id
    private int idShop;
    private String shop;


    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Price> prices;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Price price;
//
//    @ManyToOne
//    private Price price;


    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public int getId() {return idShop;}

    public void setId(int id) {
        this.idShop = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

//    public Price getShopName() {
//        return price;
//    }
//
//    public void setShopName(Price shopName) {
//        this.price = shopName;
//    }


}
