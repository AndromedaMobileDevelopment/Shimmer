package com.development.mobile.andromeda.shimmer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GamesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    final String BASE_URL = "195.19.44.155";

    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private RecyclerAdapter adapter;
    private ArrayList<ModelItem> itemsList;
    private SwipeRefreshLayout refreshLayout;
    private GamesInterface client;
    private ProgressBar bar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, null);

        bar = (ProgressBar) view.findViewById(R.id.progressbar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(this);
        //Настраиваем цветовую тему значка обновления, используя наши цвета:
        refreshLayout.setColorSchemeResources
                (R.color.light_blue, R.color.middle_blue,R.color.deep_blue);

        verticalLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        horizontalLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(verticalLinearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        itemsList = new ArrayList<>();
        final RequestParams params = new RequestParams();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://195.19.44.155/anton/core/api.php?action=GetCountProjects&from=0&to=10", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject obj) {
                bar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                    JSONObject apimessage = obj.getJSONObject("apimessage");
                    JSONObject projectUser = apimessage.getJSONObject("projects");
                    for (int i = 1; i < 5; i++) {
                        JSONObject project = projectUser.getJSONObject(String.valueOf(i));
                        itemsList.add(new ModelItem(project.getString("NameOfProject"), R.drawable.background, project.getString("last_name"), project.getString("description")));
                    }
                    adapter.addAll(itemsList);

                } catch (JSONException e) {

                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // handle failure here
            }

        });
        return view;
    }
    public static GamesFragment getThis(){
        GamesFragment gamesFragment = new GamesFragment();
        return gamesFragment;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Отменяем анимацию обновления
                refreshLayout.setRefreshing(false);

            }
        }, 1000);

    }


    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        private ArrayList<ModelItem> items = new ArrayList<>();

        void addAll(List<ModelItem> fakeItems){
            int pos = getItemCount();
            this.items.addAll(fakeItems);
            notifyItemRangeInserted(pos, this.items.size());
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_news, parent, false);
            return new RecyclerViewHolder(view, 1);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.bind(items.get(position), 1);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView image;

        private TextView headerTag;
        private TextView headerDesk;
        private TextView headerAuthor;
        private ImageView imgIdHeader;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
        RecyclerViewHolder(View itemView, int idNews) {
            super(itemView);
            headerTag = (TextView) itemView.findViewById(R.id.tagHeader);
            headerDesk = (TextView) itemView.findViewById(R.id.deskHeader);
            headerAuthor = (TextView) itemView.findViewById(R.id.authorHeader);
            imgIdHeader = (ImageView) itemView.findViewById(R.id.imageHeader);
            itemView.setOnClickListener(this);
        }

        void bind(ModelItem modelItem) {
            image.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), modelItem.getImgIdHeader()));
            title.setText(modelItem.getHeaderDesk());
        }


        void bind(ModelItem modelItem, int i) {
            imgIdHeader.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), modelItem.getImgIdHeader()));
            headerTag.setText(modelItem.getHeaderTag());
            headerDesk.setText(modelItem.getHeaderDesk());
            headerAuthor.setText(modelItem.getHeaderAuthor());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), TestInfoFragment.class);
            startActivity(intent);
        }
    }

    public static Fragment newInstance() {
        return new GamesFragment();
    }



}
