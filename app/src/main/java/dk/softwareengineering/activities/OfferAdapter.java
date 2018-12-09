package dk.softwareengineering.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import businessLayer.IFacade;
import domain.Offer;

/**
 * Used for creating coupon fragment elements that display coupons in {@link MyCouponsActivity}.
 *
 * Inspiration from @Link {https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example}
 */
public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<Offer> mOffers;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private IFacade facade;

    public OfferAdapter(Context context, IFacade facade) {
        this.mInflater = LayoutInflater.from(context);
        this.mOffers = new ArrayList<>();
        this.context = context;
        this.facade = facade;
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
        String mOfferStore = facade.getStoreById(offer.getStoreId()).getName();
        double mOfferDiscount = offer.getDiscount();
        double mOfferPrice = offer.getPrice();

        holder.img_offerImage.setImageResource(imageResource);
        holder.txt_offerTitle.setText(mOfferTitle);
        holder.txt_offerDiscount.setText("-"+ Double.toString(mOfferDiscount) + "%");
        holder.txt_offerPrice.setText(new DecimalFormat("#.0#").format(mOfferPrice));
        holder.txt_offer_Store.setText(mOfferStore);
    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }

    /**
     * Stores and recycles views as they are scrolled off screen.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_offerImage;
        TextView txt_offerTitle, txt_offer_Store, txt_offerPrice, txt_offerDiscount;


        public ViewHolder(View itemView) {
            super(itemView);
            img_offerImage = itemView.findViewById(R.id.img_offer);
            txt_offerTitle = itemView.findViewById(R.id.txt_offerTitle);
            txt_offer_Store = itemView.findViewById(R.id.txt_offerStore);
            txt_offerPrice = itemView.findViewById(R.id.txt_offerPrice);
            txt_offerDiscount = itemView.findViewById(R.id.txt_offerDiscount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    /**
     * Convenience method for getting data at click position.
     *
     * @param id The item ID that was clicked on.
     *
     * @return Returns the items that was
     */
    public Offer getItem(int id) {
        return mOffers.get(id);
    }

    /**
     * Allows clicks events to be caught.
     *
     * @param itemClickListener The item click listener.
     */
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    /**
     * Parent activity will implement this method to respond to click events.
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * Adds offers.
     *
     * @param offers Offers to be added.
     */
    public void addOffers(List<Offer> offers) {
        mOffers.addAll(offers);
    }

    /**
     * Removes offers.
     *
     * @param offers Offers to be removed.
     */
    public void removeOffers(List<Offer> offers) {
        mOffers.removeAll(offers);
    }
}
