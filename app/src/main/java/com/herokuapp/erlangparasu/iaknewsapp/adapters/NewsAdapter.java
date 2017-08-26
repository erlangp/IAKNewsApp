package com.herokuapp.erlangparasu.iaknewsapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herokuapp.erlangparasu.iaknewsapp.R;
import com.herokuapp.erlangparasu.iaknewsapp.models.Article;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final int LAYOUT = R.layout.item_news;
    private ArrayList<Article> mArticles;
    private OnItemClickListener mOnItemClickListener;

    public NewsAdapter(ArrayList<Article> articles) {
        mArticles = articles;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(LAYOUT, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.bind(mArticles.get(position));
        holder.btnReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onButtonReadMoreClicked(
                            mArticles.get(position)
                    );
                }
            }
        });
    }

    /* Custom methods -------------------------------------------------------------------------- */

    public void setData(ArrayList<Article> articles) {
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /* Inner class ----------------------------------------------------------------------------- */

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivNewsPhoto)
        ImageView ivNewsPhoto;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvNewsTitle)
        TextView tvNewsTitle;
        @BindView(R.id.btnReadMore)
        TextView btnReadMore;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Article newsItem) {
            tvNewsTitle.setText(newsItem.getTitle());
            tvDescription.setText(newsItem.getDescription());
            Glide.with(ivNewsPhoto.getContext())
                    .load(newsItem.getUrlToImage())
                    .into(ivNewsPhoto);
        }
    }

    /* Interface ------------------------------------------------------------------------------- */

    public interface OnItemClickListener {
        void onButtonReadMoreClicked(Article article);
    }
}
