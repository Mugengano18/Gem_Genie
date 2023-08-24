package com.yourcompany.gem_genie.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.yourcompany.gem_genie.R;

public class CourseDetActivity extends AppCompatActivity {

    WebView webView;
    TextView title_desc;
    TextView description_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_det);
        webView = findViewById(R.id.videourl);
        title_desc = findViewById(R.id.ind_title);
        description_desc = findViewById(R.id.ind_desc);
        String video = getIntent().getStringExtra("videoUri");
        webView.loadData(video,"text/html","utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        title_desc.setText(getIntent().getStringExtra("header_title"));
        description_desc.setText(getIntent().getStringExtra("desc_item"));

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
}