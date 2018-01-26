package com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel;

import io.realm.RealmObject;


public class RTask extends RealmObject {

    private String name;
    private long id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
