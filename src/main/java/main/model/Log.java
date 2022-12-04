package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String succes_insert;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSucces_insert() {
        return succes_insert;
    }

    public void setSucces_insert(String succes_insert) {
        this.succes_insert = succes_insert;
    }

}
