package session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Coupon;
import domain.Offer;

/**
 * Used for saving coupons into memory on the mobile device. This will be replaced by a proper
 * data storage system.
 */
public class SharedPreferenceRepository implements ISessionRepository {
    private final Gson gson;
    private SharedPreferences prefs;

    public SharedPreferenceRepository(Context contextOfApplication) {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(contextOfApplication);
        this.gson = new Gson();
    }

    private List<Coupon> getUserCouponsInPref(String userId){
        Type couponListType = new TypeToken<ArrayList<Coupon>>(){}.getType();

        String couponsJson = prefs.getString(userId, "");

        if(couponsJson.isEmpty())
            return null;

        List<Coupon> coupons  = gson.fromJson(couponsJson, couponListType);

        for (Coupon item : coupons) {
            Log.i("CouponFromShared ", item.getOffer().getTitle());
        }

        return coupons;
    }

    public void saveOfferToCustomer(String customerId, Offer offer){

        List<Coupon> coupons = getUserCouponsInPref(customerId);

        if(coupons== null)
            coupons = new ArrayList<Coupon>();

        coupons.add(new Coupon( generateRandomInt(1, 100), new Date(), offer));

        String json = gson.toJson(coupons);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(customerId, json);

        editor.commit();


        Log.i("saveOfferToCustomer, userId: ", customerId);

    }

    public List<Coupon> getCustomerCoupons(String customerId){
        List<Coupon> coupons = getUserCouponsInPref(customerId);

        if(coupons== null){
            return new ArrayList<Coupon>();
        }

        Log.i("getCustomerCoupons, userId: ", customerId);

        return coupons;
    }

    private int generateRandomInt(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
