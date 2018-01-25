package com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Артем on 25.01.2018.
 */

public class RListsContainer<R> extends RealmObject {

    public RealmList<RList> realmListContainer;

}
