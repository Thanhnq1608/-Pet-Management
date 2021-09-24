package vn.poly.sotaythucung.petsnews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.model.News;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.sqlite.NewsDAO;

import com.squareup.picasso.Picasso;

import java.util.List;

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> newsList;
    private Context context;

    public NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder holder, final int position) {
        final NewsDAO newsDAO = new NewsDAO(new SQLiteDB(context));
        newsList = newsDAO.getAllNews();
        final News news = newsList.get(position);
        holder.tvTitleTinTuc.setText(news.getTitleNews());
        Picasso.get().load(newsList.get(position).getImgHeaderNews()).into(holder.imgHinhAnhTinTuc);
        Log.d("Check","check" + news.getImgHeaderNews());
        holder.imgIconShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.imgIconSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgIconShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsList = newsDAO.getAllNews();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, newsList.get(position).getUrlNews());
                context.startActivity(Intent.createChooser(intent, "Share"));

            }
        });
        holder.tvTitleTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDAO newsDAO = new NewsDAO(new SQLiteDB(context));
                newsList = newsDAO.getAllNews();
                Intent intent = new Intent(context, NewsMainActivity.class);
                intent.putExtra("LINKBLOG", newsList.get(position).getUrlNews());
                intent.putExtra("IMAGERHEADER", newsList.get(position).getImgHeaderNews());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhAnhTinTuc, imgIconShare, imgIconSave;
        TextView tvTitleTinTuc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAnhTinTuc = itemView.findViewById(R.id.imgTinTuc);
            tvTitleTinTuc = itemView.findViewById(R.id.tvTinTuc);
            imgIconShare = itemView.findViewById(R.id.imgIconShare);
            imgIconSave = itemView.findViewById(R.id.imgIconSave);
        }
    }

}
