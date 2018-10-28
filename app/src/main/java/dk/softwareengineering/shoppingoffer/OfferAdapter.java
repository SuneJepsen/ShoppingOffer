package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<Integer> mViewProducts;
    private List<String> mProducts;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public OfferAdapter(Context context, List<Integer> viewProducts, List<String> products) {
        this.mInflater = LayoutInflater.from(context);
        this.mViewProducts = viewProducts;
        this.mProducts = products;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int productImage = mViewProducts.get(position);
        String productText = mProducts.get(position);
        holder.productImage.setBackgroundColor(productImage);
        holder.productText.setText(productText);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View productImage;
        TextView productText;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.img_product);
            productText = itemView.findViewById(R.id.tv_productName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public String getItem(int id) {
        return mProducts.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
