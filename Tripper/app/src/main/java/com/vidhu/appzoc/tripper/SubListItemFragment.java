package com.vidhu.appzoc.tripper;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubListItemFragment extends Fragment {



    SubListCustomAdapter adapter;
        ListView subListItems;
    private SQLController dbController;

    ImageButton AddSubCategory;
    ImageButton AddCategory;
    CheckBox checkBox;

    ArrayList<SubCategoryList> items;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View sFragmentView= inflater.inflate(R.layout.activity_sub_list_item_fragment,container,false);


AddSubCategory=(ImageButton)getActivity().findViewById(R.id.addsubCategory);
AddCategory=(ImageButton)getActivity().findViewById(R.id.addCategory);

        checkBox=(CheckBox)getActivity().findViewById(R.id.checkBox);
        subListItems=(ListView)sFragmentView.findViewById(R.id.sub_listView);
        dbController=new SQLController(getActivity());


        try{
            dbController.open();

        }catch (SQLException e){
            e.printStackTrace();
        }


        getValuesFromDb();

        AddSubCategory.setVisibility(View.VISIBLE);

        AddCategory.setVisibility(View.GONE);

        AddSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("SubItem");
                alert.setMessage("Enter item name");
                final EditText input = new EditText(getActivity());
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name = input.getText().toString();
                        dbController.insertSubCategory(Constants.selected_category_id, name, "0");
                        items.clear();
                        items = dbController.readSubCategory(Constants.selected_category_id);
//        cursor.moveToFirst();

//                        while (!cursor.isAfterLast()) {
//                            String cItem = cursor.getString(1);
//                            items.add(cItem);
//                            cursor.moveToNext();
//                        }

                        adapter = new SubListCustomAdapter(getActivity(),
                                R.layout.list_subcategory_item_row, items);
                        subListItems.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });

        return sFragmentView;
    }

    private void getValuesFromDb() {

        items =  dbController.readSubCategory(Constants.selected_category_id);
//        cursor.moveToFirst();

        adapter=new SubListCustomAdapter(getActivity(),
                R.layout.list_subcategory_item_row,items);
        subListItems.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}