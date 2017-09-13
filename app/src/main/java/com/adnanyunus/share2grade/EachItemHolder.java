package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class EachItemHolder {
    private String item;

    public String getPersonwhograded() {
        return personwhograded;
    }

    public void setPersonwhograded(String personwhograded) {
        this.personwhograded = personwhograded;
    }

    private String personwhograded;
    public EachItemHolder(String item,String personwhograded, boolean isChecked)
    {
        this.item = item;
        this.personwhograded = personwhograded;
        this.isChecked = isChecked;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;
}
