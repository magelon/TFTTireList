package com.lon.tftt.tfttirelist;

public class Item {
    private String id;
    private String combo;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

    public Item() {

    }

    public Item(String id, String combo,String img) {
        this.id = id;
        this.combo = combo;
        this.img=img
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

}
