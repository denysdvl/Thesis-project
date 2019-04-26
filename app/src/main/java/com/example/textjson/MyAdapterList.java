package com.example.textjson;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static java.lang.String.valueOf;

public class MyAdapterList extends RecyclerView.Adapter<MyAdapterList.ViewHolder>{
    private List<ListItem> listItems;
    private Context context;

    public MyAdapterList(List<ListItem> ListItems, Context context) {
        this.listItems = ListItems;
        this.context = context;
    }

    public List<ListItem> getListItems() {
        return listItems;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foto_list, parent,    false);
        return new ViewHolder(v);
    }
    public void onBindViewHolder(ViewHolder holder, final int position){
        final ListItem listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getTitle());


        holder.setBackground(listItem.getBitmap());
holder.ConsLayoutDesc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Toast.makeText(context,String.valueOf(listItem.getId()), Toast.LENGTH_SHORT).show();

      Intent i = new Intent(context, MainActivity.class);
       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       i.putExtra("id", String.valueOf(listItem.getId()));
       context.startActivity(i);

    }
});

    }


    @Override
    public int getItemCount() {
        return listItems.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead;
public ConstraintLayout ConsLayoutDesc;


        public ViewHolder(View itemView){
            super(itemView);

            textViewHead = itemView.findViewById(R.id.textViewHead);
            ConsLayoutDesc = itemView.findViewById(R.id.ConsLayoutDesc);
        }

        public void setBackground(Bitmap background){
            try {
                if (background!=null) {
                    itemView.findViewById(R.id.backgroundHolder).setBackground(new BitmapDrawable(background));

                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
