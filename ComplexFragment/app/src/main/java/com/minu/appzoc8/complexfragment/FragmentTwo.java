package com.minu.appzoc8.complexfragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by appzoc8 on 25/11/15.
 */
public class FragmentTwo extends Fragment {
    GridView gridView;
//    TextView textView;
//    ImageView imageView;
    ArrayList<CustomArrayGrid> personallist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragmenttwo, container, false);
        gridView=(GridView) view.findViewById(R.id.minu);
        //mageView=(ImageView) view.findViewById(R.id.imageview);
        personallist =new ArrayList<>();
        personallist.add(new CustomArrayGrid(R.drawable.sen2, "MINU"));
        CustomArrayGrid customArrayGrid=new CustomArrayGrid();

        customArrayGrid.setImage(R.drawable.sceb3);
        customArrayGrid.setText("revathy");
        personallist.add(customArrayGrid);
        personallist.add(new CustomArrayGrid( R.drawable.scen1,"ramu"));
        personallist.add(new CustomArrayGrid(R.drawable.scen4,"somu"));
        personallist.add(new CustomArrayGrid(R.drawable.scen5,"karthik"));
        personallist.add(new CustomArrayGrid(R.drawable.scen6,"hai"));
        personallist.add(new CustomArrayGrid(R.drawable.sceb3, "hgidgoi"));
        personallist.add(new CustomArrayGrid(R.drawable.sen2, "kavi"));
        CustomGridArrayAdapter customGridArrayAdapter=new CustomGridArrayAdapter(getActivity(),
                R.layout.customgridview,personallist);
        gridView.setAdapter(customGridArrayAdapter);
//
//        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), SingleGrid.class);
//
//                intent.putExtra("NAME",personallist.get(position).getText());
//                intent.putExtra("IMAGE",personallist.get(position).getImage());
//
//                getActivity().startActivity(intent);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SingleGrid.class);
//
                intent.putExtra("NAME",personallist.get(position).getText());
                intent.putExtra("IMAGE",personallist.get(position).getImage());

                getActivity().startActivity(intent);
            }
        });

        return view;
    }

}

