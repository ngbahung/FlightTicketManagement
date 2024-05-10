package org.example.flightticketmanagement.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {
    private final StringProperty owner;
    private final StringProperty accountID;

    public Account(String owner, String accountID) {
        this.owner = new SimpleStringProperty(owner);
        this.accountID = new SimpleStringProperty(accountID);
    }

    public StringProperty getOwner() {
        return owner;
    }

    public StringProperty getAccountID() {
        return accountID;
    }
}
