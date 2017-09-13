package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 8/8/2017.
 */

public class ViewGradesProvider {
    private String title;
    private String email;
    public ViewGradesProvider(String title, String email, String owneremail, String item, String checked)
    {
        this.title = title;
        this.email = email;
        this.owneremail = owneremail;
        this.item = item;
        this.checked = checked;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwneremail() {
        return owneremail;
    }

    public void setOwneremail(String owneremail) {
        this.owneremail = owneremail;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean getChecked() {
        if (checked.equalsIgnoreCase("true") || checked.equalsIgnoreCase("false")) {
            boolean x = Boolean.valueOf(checked);
            return x;
        } else {
            // throw some exception
            return false;
        }
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    private String owneremail;
    private String item;
    private String checked;
}
