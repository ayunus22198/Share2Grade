package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/11/2017.
 */

public class EnhancedEachItemHolder {
    private String title;
    private String emailofgrader;
    private String emailofowner;

    public EnhancedEachItemHolder(String title, String emailofgrader, String emailofowner, String item, boolean checked) {
        this.title = title;
        this.emailofgrader = emailofgrader;
        this.emailofowner = emailofowner;
        this.item = item;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmailofgrader() {
        return emailofgrader;
    }

    public void setEmailofgrader(String emailofgrader) {
        this.emailofgrader = emailofgrader;
    }

    public String getEmailofowner() {
        return emailofowner;
    }

    public void setEmailofowner(String emailofowner) {
        this.emailofowner = emailofowner;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private String item;
    private boolean checked;
    public EnhancedEachItemHolder()
    {

    }

}
