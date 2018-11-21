package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import domain.Offer;
import java.util.List;


public class MyCouponsAdapter extends RecyclerView.Adapter<MyCouponsAdapter.ViewHolder> {

    private List<Offer> mOffers;
    private LayoutInflater mInflater;
    private MyCouponsAdapter.ItemClickListener mClickListener;
    private Context context;

    public MyCouponsAdapter(Context context, List<Offer> mOffers) {
        this.mInflater = LayoutInflater.from(context);
        this.mOffers = mOffers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = mInflater.inflate(R.layout.recyclerview_coupons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Offer offer = mOffers.get(position);
        int imageResource = context.getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, context.getPackageName());
        String mOfferTitle = offer.getTitle();
        double mOfferDiscount = offer.getDiscount();
        double mOfferPrice = offer.getPrice();
        int mAmmount = offer.getAmountCounter();

        holder.img_offerImage.setImageResource(imageResource);
        holder.txt_offerTitle.setText(mOfferTitle);
        holder.txt_offerDiscount.setText("-"+ Double.toString(mOfferDiscount) + "%");
        holder.txt_offerPrice.setText(Double.toString(mOfferPrice));
        holder.txt_stock.setText(Integer.toString(mAmmount));
    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_offerImage;
        TextView txt_offerTitle;
        TextView txt_offerPrice;
        TextView txt_offerDiscount;
        TextView txt_stock;

        public ViewHolder(View view) {
            super(view);
            img_offerImage = itemView.findViewById(R.id.img_offer);
            txt_offerTitle = itemView.findViewById(R.id.txt_offerTitle);
            txt_offerPrice = itemView.findViewById(R.id.txt_offerPrice);
            txt_offerDiscount = itemView.findViewById(R.id.txt_offerDiscount);
            txt_stock = itemView.findViewById(R.id.txt_offerStock);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }


    }

    // convenience method for getting data at click position
    public Offer getItem(int id) {
        return mOffers.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(MyCouponsAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
