package com.example.kolekcijapogledanihfilmova.ui.pretraga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kolekcijapogledanihfilmova.R;
import com.example.kolekcijapogledanihfilmova.net.models.DetailSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSearchedMoviesMaster extends RecyclerView.Adapter<AdapterSearchedMoviesMaster.ViewHolder> {
    private ArrayList<DetailSearch> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context cnt;
    public AdapterSearchedMoviesMaster(Context context, ArrayList<DetailSearch> data,ItemClickListener mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.cnt = context;
        this.mClickListener = mClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_movie_pretraga, parent, false);
        return new ViewHolder(view,mClickListener);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData.get(position).getTitle();
        String godina = mData.get(position).getYear();
        String poster = mData.get(position).getPoster();
        holder.txtTitle.setText(animal);
        holder.txtGodina.setText(godina);
        Picasso.with(cnt).load(poster).into(holder.slika);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle;
        TextView txtGodina;
        ImageView slika;
        ItemClickListener mClickListener;

        ViewHolder(View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtRezNaziv);
            txtGodina = itemView.findViewById(R.id.txtRezGodina);
            slika = itemView.findViewById(R.id.imgRezSlika);
            this.mClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(mData.get(getAdapterPosition()).getImdbID());
        }
    }
    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(String id);
    }

}
