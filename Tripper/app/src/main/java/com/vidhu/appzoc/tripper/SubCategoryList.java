package com.vidhu.appzoc.tripper;

/**
 * Created by appzoc13 on 16/12/15.
 */
public class SubCategoryList {
    public int getSubCategory_id() {
        return subCategory_id;
    }

    public void setSubCategory_id(int subCategory_id) {
        this.subCategory_id = subCategory_id;
    }

    public String getSubCategory_name() {
        return subCategory_name;
    }


    public void setSubCategory_name(String subCategory_name) {
        this.subCategory_name = subCategory_name;
    }




    int subCategory_id;
    String subCategory_name;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    int status;

    public SubCategoryList() {
    }

    public SubCategoryList(int subCategory_id, String subCategory_name,int status) {
        this.subCategory_id = subCategory_id;
        this.subCategory_name = subCategory_name;
        this.status=status;
    }


}