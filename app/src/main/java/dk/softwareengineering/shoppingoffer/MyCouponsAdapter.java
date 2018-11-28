package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import businessLayer.IFacade;
import domain.Coupon;
import domain.Offer;
import domain.Store;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class MyCouponsAdapter extends RecyclerView.Adapter<MyCouponsAdapter.ViewHolder> {

    private List<Coupon> coupons;
    private IFacade facade;
    private LayoutInflater mInflater;
    private MyCouponsAdapter.ItemClickListener mClickListener;
    private Context context;

    public MyCouponsAdapter(Context context, IFacade facade) {
        this.mInflater = LayoutInflater.from(context);
        this.facade = facade;
        this.context = context;
        this.coupons = facade.getUserCoupons("sune@student.sdu.dk");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = mInflater.inflate(R.layout.recyclerview_coupons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Coupon coupon = coupons.get(position);
        Offer offer = coupon.getOffer();

        Log.i("Imagepath", offer.getImagePath());
        int imageResource = context.getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, context.getPackageName());
        String mOfferTitle = offer.getTitle();
        double mOfferDiscount = offer.getDiscount();
        double mOfferPrice = offer.getPrice();
        int mAmount = offer.getAmountCounter();
        String mOfferStore = facade.getStoreById(offer.getStoreId()).getName();
        String mOfferTimeLimit = new SimpleDateFormat("hh:mm").format(offer.getTimeCounter());
        int mOfferStock = offer.getAmountCounter();

        holder.img_offerImage.setImageResource(imageResource);
        holder.txt_offerTitle.setText(mOfferTitle);
        holder.txt_offerDiscount.setText("-"+ Double.toString(mOfferDiscount) + "%");
        holder.txt_offerPrice.setText(new DecimalFormat("#.0#").format(mOfferPrice));
        holder.txt_stock.setText(Integer.toString(mAmount));
        holder.txt_store.setText(mOfferStore);
        holder.txt_offerStock.setText(String.valueOf(mOfferStock));
        holder.txt_offerTimer.setText("kl " + mOfferTimeLimit);
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_offerImage;
        TextView txt_offerTitle, txt_offerPrice, txt_offerDiscount, txt_stock, txt_store,
            txt_offerTimer, txt_offerStock;

        public ViewHolder(View view) {
            super(view);
            img_offerImage = itemView.findViewById(R.id.img_offer);
            txt_offerTitle = itemView.findViewById(R.id.txt_offerTitle);
            txt_offerPrice = itemView.findViewById(R.id.txt_offerPrice);
            txt_offerDiscount = itemView.findViewById(R.id.txt_offerDiscount);
            txt_store = itemView.findViewById(R.id.txt_store);
            txt_offerTimer = itemView.findViewById(R.id.txt_offerTimer);
            txt_offerStock = itemView.findViewById(R.id.txt_offerStock);
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
    public Coupon getItem(int id) {
        return coupons.get(id);
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
