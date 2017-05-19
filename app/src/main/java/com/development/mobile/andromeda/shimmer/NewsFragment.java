package com.development.mobile.andromeda.shimmer;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment{

    private final String APINEWSKEY="360d5d5a263143efb06db21d011bb235";
    private final String BASE_URL = "https://newsapi.org";
    private NewsInterface service;
    private ProgressBar prBar;
    private ArrayList<ModelItem> itemsList;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private LinearLayoutManager horizontalLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_news);

        itemsList = new ArrayList<>();
        prBar = (ProgressBar) view.findViewById(R.id.progress);

        verticalLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        horizontalLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(verticalLinearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NewsInterface.class);
        Call<ModelNews> call = service.getNews("bloomberg", APINEWSKEY);
        call.enqueue(new Callback<ModelNews>() {
            @Override
            public void onResponse(Call<ModelNews> call, Response<ModelNews> response) {
                prBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                List<Article> project = response.body().getArticles();

                for(int i =0; i<project.size(); i++) {



                    itemsList.add(new ModelItem(project.get(i).getAuthor(),project.get(i).getUrlToImage(), project.get(i).getTitle(), project.get(i).getDescription()));
                }
                adapter.addAll(itemsList);
            }

            @Override
            public void onFailure(Call<ModelNews> call, Throwable t) {
                //TODO: реализовать ошибку подключения
            }
        });
        return view;
    }
    private class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView image;

        private TextView headerTag;
        private TextView headerDesk;
        private TextView headerAuthor;
        private ImageView imgIdHeader;


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
            Picasso.with(getActivity().getApplicationContext()).load(modelItem.getUrlToImage()).into(imgIdHeader);
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

    private class RecyclerAdapter extends RecyclerView.Adapter<NewsFragment.RecyclerViewHolder> {
        private ArrayList<ModelItem> items = new ArrayList<>();

        void addAll(List<ModelItem> fakeItems){
            int pos = getItemCount();
            this.items.addAll(fakeItems);
            notifyItemRangeInserted(pos, this.items.size());
        }

        @Override
        public NewsFragment.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_news, parent, false);
            return new NewsFragment.RecyclerViewHolder(view, 1);
        }

        @Override
        public void onBindViewHolder(NewsFragment.RecyclerViewHolder holder, int position) {
            holder.bind(items.get(position), 1);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }


    public static Fragment newInstance() {
        return new NewsFragment();
    }
}
