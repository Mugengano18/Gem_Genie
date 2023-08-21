package com.yourcompany.gem_genie;

import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {




    ArrayAdapter<String> arrayAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rcv;
    myAdapter adapter;

    private SearchView searchView;

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        rcv = view.findViewById(R.id.rclview);


        adapter = new myAdapter(data(), requireContext());
        rcv.setAdapter(adapter);

        // Set up the GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 3,GridLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

        rcv.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void filterList(String newText) {
        ArrayList<Model> filteredList = new ArrayList<>();
        for (Model model: data()) {

            if (model.getHeader().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(model);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No data Found", Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.setFilteredList(filteredList);
        }
     }

    public ArrayList<Model> data()
    {
        ArrayList<Model> holder=new ArrayList<>();

        Model ob1=new Model();
        ob1.setHeader("C Programming");
        ob1.setDesc("Desktop Programming");
        ob1.setImgname(R.drawable.cp);
        holder.add(ob1);

        Model ob2=new Model();
        ob2.setHeader("C++ Programming");
        ob2.setDesc("Desktop Progamming Language");
        ob2.setImgname(R.drawable.cpp);
        holder.add(ob2);

        Model ob3=new Model();
        ob3.setHeader("Java Programming");
        ob3.setDesc("Desktop Progamming Language");
        ob3.setImgname(R.drawable.java);
        holder.add(ob3);

        Model ob4=new Model();
        ob4.setHeader("PHP Programming");
        ob4.setDesc("Web Progamming Language");
        ob4.setImgname(R.drawable.php);
        holder.add(ob4);

        Model ob5=new Model();
        ob5.setHeader(".NET Programming");
        ob5.setDesc("Desktop/Web Progamming Language");
        ob5.setImgname(R.drawable.dotnet);
        holder.add(ob5);

        Model ob6=new Model();
        ob6.setHeader("Wordpress Framework");
        ob6.setDesc("PHP based Blogging Framework");
        ob6.setImgname(R.drawable.wordpress);
        holder.add(ob6);

        Model ob7=new Model();
        ob7.setHeader("Magento Framework");
        ob7.setDesc("PHP Based e-Comm Framework");
        ob7.setImgname(R.drawable.magento);
        holder.add(ob7);

        Model ob8=new Model();
        ob8.setHeader("Shopify Framework");
        ob8.setDesc("PHP based e-Comm Framework");
        ob8.setImgname(R.drawable.shopify);
        holder.add(ob8);

        Model ob9=new Model();
        ob9.setHeader("Angular Programming");
        ob9.setDesc("Web Programming");
        ob9.setImgname(R.drawable.angular);
        holder.add(ob9);

        Model ob10=new Model();
        ob10.setHeader("Python Programming");
        ob10.setDesc("Desktop/Web based Progamming");
        ob10.setImgname(R.drawable.python);
        holder.add(ob10);

        Model ob11=new Model();
        ob11.setHeader("Node JS Programming");
        ob11.setDesc("Web based Programming");
        ob11.setImgname(R.drawable.nodejs);
        holder.add(ob11);


        return holder;
    }

}