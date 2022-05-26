package com.lance.yunlive.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LiveRoomMapper {
    /**
     * 判断当前用户是否已关注
     */
    Integer checkFollowed(@Param("uid") String uid, @Param("p") String platform, @Param("rid") String roomId);
}
