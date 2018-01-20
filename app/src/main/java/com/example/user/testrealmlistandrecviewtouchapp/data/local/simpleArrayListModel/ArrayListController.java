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

        for (int i = 1; i<11; i ++){arrayList.add(" " + i);}
        for (int i = 11; i<50; i ++){arrayList2.add(" " + i);}
        for (int i = 51; i<100; i ++){arrayList3.add(" " + i);}
    }


    public ArrayList<String> getArrayList(int number) {
        if (number == 0) return arrayList;
        if (number == 1) return arrayList2;
        else return arrayList3;
    }

}

