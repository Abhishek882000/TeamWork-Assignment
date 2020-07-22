package com.abhishek.teamworktask;

public class userProfile {
    public String userName;
    public String userEmail;
    public String userPass;
    public String confirmUserPass;

    public userProfile(String userName, String userEmail, String userPass, String confirmUserPass) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.confirmUserPass = confirmUserPass;
    }
}
