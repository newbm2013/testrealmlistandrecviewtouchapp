package com.example.user.testrealmlistandrecviewtouchapp.ui.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.user.testrealmlistandrecviewtouchapp.R;
import com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel.RList;
import com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel.RListsContainer;
import com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel.RTask;
import com.example.user.testrealmlistandrecviewtouchapp.ui.util.pageadapter.MainTasksListsPagerAdapter;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;


public class MainActivity extends AppCompatActivity {

    MainTasksListsPagerAdapter mainTasksListsPagerAdapter;
    ViewPager mainTasksListsViewPager;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTasksListsViewPager = findViewById(R.id.main_tasks_lists_view_pager);
        mainTasksListsPagerAdapter = new MainTasksListsPagerAdapter(this);
        mainTasksListsViewPager.setAdapter(mainTasksListsPagerAdapter);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().schemaVersion(1).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    private void addContent(){
        if (realm.where(RListsContainer.class).findFirst() == null) {
            RListsContainer listsContainer = realm.createObject(RListsContainer.class);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    final RList<RTask> list = realm.createObject(RList.class);
                    list.setId(System.currentTimeMillis());
                    list.setName("list 1");
                    final RList<RTask> list2 = realm.createObject(RList.class);
                    list2.setId(System.currentTimeMillis());
                    list2.setName("list 2");
                    final RListsContainer rListsContainer = realm.where(RListsContainer.class).findFirst();
                    rListsContainer.realmListContainer.add(list);
                    rListsContainer.realmListContainer.add(list2);
                    final RealmModel rList = rListsContainer.realmListContainer.get(0);
                    //                final RealmModel rList2 = rListsContainer.realmListContainer.get(1)
                    for (int i = 0; i < 100; i++) {
                        RTask task = realm.createObject(RTask.class);
                        task.setId(System.currentTimeMillis());
                        task.setName("task n " + i);
                        ((RList<RTask>) rList).tasks.add(task);
                    }
                }
            });
        }
    }
}
