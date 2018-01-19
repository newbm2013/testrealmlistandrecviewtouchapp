package com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel;

import java.util.ArrayList;

public class ArrayListController {

    ArrayList<String> arrayList;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;

    public ArrayListController(){
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();

        for (int i = 0; i<100; i ++){arrayList.add("item " + i);}
        for (int i = 100; i<200; i ++){arrayList2.add("item " + i);}
        for (int i = 200; i<300; i ++){arrayList3.add("item " + i);}
    }


    public ArrayList<String> getArrayList(int number) {
        if (number == 0) return arrayList;
        if (number == 1) return arrayList2;
        else return arrayList3;
    }

}

