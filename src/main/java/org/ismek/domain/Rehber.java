package org.ismek.domain;

/**
 * Created by AKARTAL on 4.3.2021.
 */
public class Rehber {

    private int id;
    private String isim;
    private String telefon;

    public Rehber(int id, String isim, String telefon) {
        this.id = id;
        this.isim = isim;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Rehber{" +
                "id=" + id +
                ", isim='" + isim + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
