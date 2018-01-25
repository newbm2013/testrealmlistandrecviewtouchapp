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
import io.realm.RealmObject;

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
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(1).build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                if (realm.where(RListsContainer.class).findFirst() == null){
                    RListsContainer listsContainer = realm.createObject(RListsContainer.class);
                }




                final RList<RTask> list = realm.createObject(RList.class);
                list.setId(System.currentTimeMillis());
                list.setName("list 1");
                list.tasks //todo need setTasks

                final RList<RTask> list2 = realm.createObject(RList.class);
                list2.setId(System.currentTimeMillis());
                list2.setName("list 2");

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        realm.where(RListsContainer.class).findFirst().realmListContainer.add(list);
                        realm.where(RListsContainer.class).findFirst().realmListContainer.add(list2);

                    }
                });
            }
        });


        for (int i = 0; i<100; i++){
            final int finalI = i;

            final RListsContainer rListsContainer = realm.where(RListsContainer.class).findFirst();
//            final RList<RTask> rList = rListsContainer.realmListContainer.get(0);

            final RealmModel rList = rListsContainer.realmListContainer.get(0);
            final RealmModel rList2 = rListsContainer.realmListContainer.get(1);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RTask task = realm.createObject(RTask.class);
                    task.setId(System.currentTimeMillis());
                    task.setName("task " + finalI);

                    if (finalI/2==0)  ((RList<RTask>) rList).tasks.add(task);
                    else ((RList<RTask>) rList2).tasks.add(task);
                }
            });
        }



    }


    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
