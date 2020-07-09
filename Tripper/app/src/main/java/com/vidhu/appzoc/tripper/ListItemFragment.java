package com.vidhu.appzoc.tripper;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;


public class ListItemFragment extends Fragment {

    CategoryListCustomAdapter adapter;
    ListView listItems;
    private SQLController dbController;
    int[] to;

    ImageButton AddCategory;
    ImageButton AddSubCategory;
    ArrayList<CategoryList> items;

//    TextView memID_tv, memName_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vFragmentView = inflater.inflate(R.layout.activity_list_item_fragment, container, false);
        AddCategory = (ImageButton) getActivity().findViewById(R.id.addCategory);
        AddSubCategory = (ImageButton) getActivity().findViewById(R.id.addsubCategory);
        listItems = (ListView) vFragmentView.findViewById(R.id.List_id);
items=new ArrayList<>();
        dbController = new SQLController(getActivity());


        try {
            dbController.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getValuesFromDb();


        AddSubCategory.setVisibility(View.GONE);
        AddCategory.setVisibility(View.VISIBLE);

        AddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Item");
                alert.setMessage("Enter item name");
                final EditText input = new EditText(getActivity());
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name = input.getText().toString();
                        dbController.insertCategory(name);

items.clear();
                        items = dbController.readCategory();
//        cursor.moveToFirst();
//
//                        while (!cursor.isAfterLast()) {
//                            String cItem = cursor.getString(1);
//                            items.add(cItem);
//                            cursor.moveToNext();
//                        }

                        adapter = new CategoryListCustomAdapter(getActivity(),
                                R.layout.list_category_item_row, items);
                        listItems.setAdapter(adapter);
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

//        Cursor cursor = dbController.readCategory();
//        String[] from = new String[]{DataBaseHelper.CATEGORY_ID, DataBaseHelper.CATEGORY_NAME};
//        to = new int[]{R.id.category_id, R.id.category_name};
//
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
//                getActivity(), R.layout.view_member_entry, cursor, from, to);
//
//        adapter.notifyDataSetChanged();
//        listItems.setAdapter(adapter);
//String items[]={"Electronics","d","x","o"};
        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                {
                    Constants.selected_category_id=items.get(position).getId() + "";
                    Fragment Fragment = new SubListItemFragment();

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, Fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        return vFragmentView;
    }

    private void getValuesFromDb() {

items.clear();
        items = dbController.readCategory();
//        cursor.moveToFirst();

        adapter = new CategoryListCustomAdapter(getActivity(),
                R.layout.list_category_item_row, items);
        listItems.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }


}



