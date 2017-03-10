/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.datatypes;

/**
 *
 * @author lovel_mimica
 */
public class User {

    private int id;
    private String name;
    private String password;
    private int category;
    private boolean admin;

   
    public User(String name, String password, int category, boolean admin) throws Exception {
        this.name = name;
        this.password = password;
        this.admin = admin;
        if (category < 0 || category > 4) {
            throw new Exception("Kategorija korisnika izvan granica 0 i 4");
        } else {
            this.category = category;
        }
    }

    public User(int id, String name, String password, int category, boolean admin) throws Exception {
        this.id = id;
        this.name = name;
        this.password = password;
        if (category < 0 || category > 4) {
            throw new Exception("Kategorija korisnika izvan granica 0 i 4");
        } else {
            this.category = category;
        }
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isAdmin() {
        return admin;
    }

}
