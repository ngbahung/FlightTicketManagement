package org.example.flightticketmanagement.Models;

import org.example.flightticketmanagement.Views.AccountType;
import org.example.flightticketmanagement.Views.ViewFactory;

public class Model {
    private static Model model;
    private  final ViewFactory viewFactory;
    private AccountType loginAccountType;
    private String loggedInUserId;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.loginAccountType = AccountType.STAFF;
    }

    public static synchronized Model getInstance(){
        if (model == null){
            model = new Model();
        }

        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public String getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }
}
