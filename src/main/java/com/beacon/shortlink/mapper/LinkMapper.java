package com.beacon.shortlink.mapper;

import com.beacon.shortlink.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author beacon
 */
@Repository
public interface LinkMapper {


    /**
     * 添加链接
     * @param link
     * @return
     */
    long addLink(@Param("link") Link link);

    /**
     * 查询链接
     * @param link 链接
     * @return
     */
    Link queryLink(@Param("link") Link link);

}
