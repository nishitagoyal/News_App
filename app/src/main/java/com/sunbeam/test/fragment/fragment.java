package com.sunbeam.test.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.JSONObjectBody;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.sunbeam.test.Adapter.ItemAdapter;
import com.sunbeam.test.Models.ModelItems;
import com.sunbeam.test.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class fragment extends Fragment {

    ArrayList <ModelItems> items = new ArrayList <>();
    ItemAdapter adapter;
    int pos;

    public fragment() { }

    public fragment(int i) {
        this.pos = i;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xmlfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        String urltemp = loadURL();

            Ion.with(this).load("GET",urltemp).asJsonObject().setCallback(new FutureCallback <JsonObject>() {
                  @Override
                public void onCompleted(Exception e, JsonObject result) {
                    if(e!=null)
                    {
                        Toast.makeText(getContext(),"Error Loading",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String status = result.get("status").getAsString();
                    if (status.equals("ok")) {
                        JsonArray array = result.get("articles").getAsJsonArray();
                        for (int i = 0; i < array.size(); i++) {
                            JsonObject object = array.get(i).getAsJsonObject();
                            String author = object.get("author").toString();
                            String title = object.get("title").toString();
                            title = title.substring(1, title.length() - 1);
                            String url = object.get("url").toString();
                            url = url.substring(1, url.length() - 1);
                            String urlToImage = object.get("urlToImage").toString();
                            urlToImage = urlToImage.substring(1, urlToImage.length() - 1);
                            String date = object.get("publishedAt").toString();
                            String content = object.get("content").toString();
                            content = content.substring(1, content.length() - 1);
                            items.add(new ModelItems(title, author, date, urlToImage, url));
                        }

                        adapter = new ItemAdapter(getActivity(), items);
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        SnapHelper snapHelper = new LinearSnapHelper();
                        snapHelper.attachToRecyclerView(recyclerView);

                    }
                }
            });
    }

    public String loadURL()
    {
        String url="";
        if(pos == 0 )
            url = "http://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a4f528c02600408496a4278e9aec04df";
        else if (pos == 1)
            url = "http://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a4f528c02600408496a4278e9aec04df";
        else if (pos == 2)
            url = "http://newsapi.org/v2/everything?domains=wsj.com&apiKey=a4f528c02600408496a4278e9aec04df";
        else
            url = "http://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a4f528c02600408496a4278e9aec04df";
        return url;
    }
}
//http://newsapi.org/v2/everything?q=apple&from=2020-10-26&to=2020-10-26&sortBy=popularity&apiKey=a4f528c02600408496a4278e9aec04df
//http://newsapi.org/v2/everything?q=bitcoin&from=2020-09-27&sortBy=publishedAt&apiKey=a4f528c02600408496a4278e9aec04df