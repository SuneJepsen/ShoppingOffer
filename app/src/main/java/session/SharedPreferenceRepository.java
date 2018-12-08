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

public class SharedPreferenceRepository implements ISessionRepository {
    private final Gson gson;
    private Context contextOfApplication;
    private SharedPreferences prefs;


    public SharedPreferenceRepository(Context contextOfApplication) {
        this.contextOfApplication = contextOfApplication;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(contextOfApplication);
        this.gson = new Gson();
    }

    private List<Coupon> GetUserCouponsInPref(String userId){
        Type couponListType = new TypeToken<ArrayList<Coupon>>(){}.getType();

        String couponsJson = prefs.getString(userId, "");

        if(couponsJson == null || couponsJson.isEmpty())
            return null;

        List<Coupon> coupons  = gson.fromJson(couponsJson, couponListType);

        for (Coupon item : coupons) {
            Log.i("CouponFromShared ", item.getOffer().getTitle());
        }

        return coupons;
    }

    public void SaveOfferToUser(String userId, Offer offer){

        List<Coupon> coupons = GetUserCouponsInPref(userId);

        if(coupons== null)
            coupons = new ArrayList<Coupon>();

        coupons.add(new Coupon( GenerateRandomInt (1, 100), new Date(), offer));

        String json = gson.toJson(coupons);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(userId, json);

        editor.commit();


        Log.i("saveOfferToUser, userId: ", userId);

    }

    public List<Coupon> GetUserCoupons(String userId){
        List<Coupon> coupons = GetUserCouponsInPref(userId);

        if(coupons== null){
            return new ArrayList<Coupon>();
        }

        Log.i("getUserCoupons, userId: ", userId);

        return coupons;
    }

    private int GenerateRandomInt(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
