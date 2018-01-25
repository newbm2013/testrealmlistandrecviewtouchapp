package com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel;


import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Артем on 25.01.2018.
 */

public class RList<R extends RealmObject> extends RealmObject {


    public String name;
    public io.realm.RealmList<RTask> tasks;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<RTask> getTasks() {
        return tasks;
    }

    public void setTasks(RealmList<RTask> tasks) {
        this.tasks = tasks;
    }

}
