package main.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Month
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idData;
    private String month;


    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    private List<Product> product;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getId() {
        return idData;
    }

    public void setId(int id) {
        this.idData = id;
    }

}
