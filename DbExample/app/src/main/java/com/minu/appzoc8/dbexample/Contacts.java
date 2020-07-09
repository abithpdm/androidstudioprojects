package com.minu.appzoc8.dbexample;

/**
 * Created by appzoc8 on 11/1/16.
 */
public class Contacts {
    int _id;

    String _name;
    String _phoneNumber;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public Contacts(String _name, String _phoneNumber) {

        this._name = _name;
        this._phoneNumber = _phoneNumber;
    }


    public Contacts(int _id, String _name, String _phoneNumber) {
        this._id = _id;
        this._name = _name;
        this._phoneNumber = _phoneNumber;
    }
}


