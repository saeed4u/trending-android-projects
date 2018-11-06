package xapo.saeed.xapo_test.model;

import androidx.annotation.NonNull;

/**
 * Created on 06/11/2018.
 */
public interface Model {

    String ANDROID_QUERY_STRING = "android language:java";

    void getAndroidRepo(@NonNull String sortBy, String orderBy, int perPage);

}
