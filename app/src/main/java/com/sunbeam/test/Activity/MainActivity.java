package com.sunbeam.test.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sunbeam.test.Adapter.MyPageAdapter;
import com.sunbeam.test.R;


public class MainActivity extends AppCompatActivity {

    private TabLayout TabLayout;
    private ViewPager viewPager;
    private MyPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        settingAdapter();
        settingActionBar();

    }

    public void initViews()
    {
        viewPager = findViewById(R.id.viewPager);
        TabLayout = findViewById(R.id.tablayout);

    }

    public void settingAdapter()
    {
        adapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout.setTabsFromPagerAdapter(adapter);
        // to keep working together of tab layout and view pager
        TabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(TabLayout));
    }

    public void settingActionBar()
    {
        ActionBar bar = getSupportActionBar();
        bar.setTitle("News");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("black")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.about:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://about.me/nishita");
                intent.setData(uri);
                startActivity(intent);
                break;

            case R.id.website:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                Uri uri1 = Uri.parse("https://www.triveous.com/");
                intent1.setData(uri1);
                startActivity(intent1);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
