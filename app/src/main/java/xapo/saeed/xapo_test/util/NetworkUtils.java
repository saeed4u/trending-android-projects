package xapo.saeed.xapo_test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Observable;

/**
 * Created on 06/11/2018.
 */
public class NetworkUtils {

    private NetworkUtils() {
        // I don't want to be instantiated!
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Observable<Boolean> networkAvailable(Context context) {
        return Observable.just(NetworkUtils.isNetworkAvailable(context));
    }
}