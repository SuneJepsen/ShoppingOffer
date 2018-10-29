package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import domain.Offer;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<Offer> mOffers;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    public OfferAdapter(Context context, List<Offer> mOffers) {
        this.mInflater = LayoutInflater.from(context);
        this.mOffers = mOffers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Offer offer = mOffers.get(position);
        int imageResource = context.getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, context.getPackageName());

        String mOfferTitle = offer.getTitle();
        holder.img_offerImage.setImageResource(imageResource);
        holder.txt_offerTitle.setText(mOfferTitle);
    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_offerImage;
        TextView txt_offerTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            img_offerImage = itemView.findViewById(R.id.img_product);
            txt_offerTitle = itemView.findViewById(R.id.tv_productName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Offer getItem(int id) {
        return mOffers.get(id);

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
