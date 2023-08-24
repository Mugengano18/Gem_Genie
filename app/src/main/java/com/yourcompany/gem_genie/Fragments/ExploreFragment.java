package com.yourcompany.gem_genie.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yourcompany.gem_genie.Adapters.CourseAdapter;
import com.yourcompany.gem_genie.Models.CourseModel;
import com.yourcompany.gem_genie.R;
import com.yourcompany.gem_genie.Adapters.myAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class  ExploreFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView rcview;



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

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        rcview = view.findViewById(R.id.rclview);

        // Fetch course data and set up RecyclerView adapter
        fetchCourseData();

        return view;

    }


    // Method to set up the RecyclerView adapter
    private void setupRecyclerView(ArrayList<CourseModel> courseModelArrayList) {
        CourseAdapter courseAdapter = new CourseAdapter(courseModelArrayList, requireContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false);
        rcview.setLayoutManager(gridLayoutManager);
        rcview.setAdapter(courseAdapter);
    }
    private void fetchCourseData() {

        String url = "http://192.168.56.1:4000/courses";
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String header = jsonObject.getString("title");
                        String descr = jsonObject.getString("description");
                        String image = jsonObject.getString("course_image");
                        String videoUrl = jsonObject.getString("course_material");

                        CourseModel courseModel = new CourseModel(id, image, header, descr,videoUrl);
                        courseModelArrayList.add(courseModel);
                    }

                    setupRecyclerView(courseModelArrayList);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Log.e(TAG,response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
            }
        });

        // Add the request to the Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(request);


    }
}