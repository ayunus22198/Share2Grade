package com.adnanyunus.share2grade;

/**
 * Created by Adnan Yunus on 9/10/2017.
 */

public class ListInformations {
    private String title;
    private String Email;
    private String whatisinsidethelist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWhatisinsidethelist() {
        return whatisinsidethelist;
    }

    public void setWhatisinsidethelist(String whatisinsidethelist) {
        this.whatisinsidethelist = whatisinsidethelist;
    }

    public String getPeoplewhocanseelist() {
        return peoplewhocanseelist;
    }

    public void setPeoplewhocanseelist(String peoplewhocanseelist) {
        this.peoplewhocanseelist = peoplewhocanseelist;
    }

    private String peoplewhocanseelist;
    public ListInformations(String id, String title, String email, String whatisinsidethelist, String peoplewhocanseelist) {
        this.id = id;
        this.title = title;
        this.Email = email;
        this.whatisinsidethelist = whatisinsidethelist;
        this.peoplewhocanseelist = peoplewhocanseelist;
    }
    public ListInformations()
    {

    }

}
