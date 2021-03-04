package org.ismek.beans;

import org.ismek.db.DbOperations;
import org.ismek.domain.Rehber;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.Facelet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "rehberMB")
public class RehberManagedBean {

    private String isim;
    private String telefon;
    private List<Rehber> rehberList = new ArrayList<>();

    public RehberManagedBean() {
    }

    public void save() {
        DbOperations.rehbereEkle(isim, telefon);
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        ExternalContext externalContext = currentInstance.getExternalContext();
        try {
            externalContext.redirect("/faces/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Rehber> getRehberList() {
        rehberList = DbOperations.tumRehberiGetir();
        return rehberList;
    }

    public void setRehberList(List<Rehber> rehberList) {
        this.rehberList = rehberList;
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
}