package com.yourcompany.gem_genie.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yourcompany.gem_genie.Fragments.ExploreFragment;
import com.yourcompany.gem_genie.Fragments.LearnFragment;
import com.yourcompany.gem_genie.Fragments.MoreFragment;
import com.yourcompany.gem_genie.Fragments.SearchFragment;
import com.yourcompany.gem_genie.R;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new ExploreFragment());

        ImageView leftIcon = findViewById(R.id.left_icon);
        ImageView right_icon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);



        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.add_course) {
            Intent intent = new Intent(this,AddCourseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        } else if (itemId == R.id.settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.explore) {
            fragment = new ExploreFragment();
        } else if (itemId == R.id.search_button) {
            fragment = new SearchFragment();
        } else if (itemId == R.id.Library) {
            fragment = new LearnFragment();
        } else if (itemId == R.id.profile) {
            fragment = new MoreFragment();
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, fragment).commit();
    }
}