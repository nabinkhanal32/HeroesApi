package Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.heroesapi.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import BaseURL.url;
import models.Heroes;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder>
{       private List<Heroes> heroesList;
        private Context context;

    public HeroesAdapter(List<Heroes> heroesList, Context context) {
        this.heroesList = heroesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_heroes,viewGroup,false);

        return new ViewHolder(view);
    }
    private void StrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesAdapter.ViewHolder viewHolder, int i) {
        Heroes heroes = heroesList.get(i);
        String imgPath = url.BASE_URL + "uploads/" + heroes.getImage();
        StrictMode();

        try{
            URL url = new URL(imgPath);
            viewHolder.imgPhoto.setImageBitmap(BitmapFactory.decodeStream( (InputStream) url.getContent()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.tvName.setText(heroes.getName());
        viewHolder.tvDes.setText(heroes.getDesc());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.imgPhoto);
            tvDes  = itemView.findViewById(R.id.tvDes);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
