package com.wzc.masterblogdemo.rxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author wzc
 * @date 2018/9/5
 */
public interface Api {
    @GET("ajax.php?a=fy")
    Observable<Translation> translate(
            @Query("f") String fromType,
            @Query("t") String toType,
            @Query("w") String content
    );


}
