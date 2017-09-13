package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/10/2017.
 */

public class UserInformations {
    public String getNameSaved() {
        return nameSaved;
    }

    public void setNameSaved(String nameSaved) {
        this.nameSaved = nameSaved;
    }

    public String getAddressSaved() {
        return addressSaved;
    }

    public void setAddressSaved(String addressSaved) {
        this.addressSaved = addressSaved;
    }

    public String nameSaved;
    public String addressSaved;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected;

    public UserInformations() {

    }

    public UserInformations(String nameSaved, String addressSaved) {
        this.nameSaved = nameSaved;
        this.addressSaved = addressSaved;
    }

}