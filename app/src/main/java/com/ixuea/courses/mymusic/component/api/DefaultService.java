package com.ixuea.courses.mymusic.component.api;

import com.ixuea.courses.mymusic.component.sheet.model.SheetWrapper;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 默认远程数据源
 */
public interface DefaultService {

    /**
     * 歌单列表
     *
     * @param category
     * @param size
     * @return
     */
    @GET("v1/sheets")
    Observable<SheetWrapper> sheets(@Query(value = "category") String category, @Query(value = "size") int size);
}
