package com.binitshah.medfieldtv;

/**
 * Created by Binit on 7/11/15.
 */
public class DrawerItem {
    String itemName;
    int imgResID;

    public DrawerItem(String titemname, int timgResID){
        super();
        itemName = titemname;
        imgResID = timgResID;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String ttitemname){
        itemName = ttitemname;
    }

    public int getImgResID(){
        return imgResID;
    }

    public void setImgResID(int ttimgResID){
        imgResID = ttimgResID;
    }

}
