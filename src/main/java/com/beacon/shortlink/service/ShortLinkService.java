package com.beacon.shortlink.service;

import com.beacon.shortlink.common.CommonResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author beacon
 */
public interface ShortLinkService {

    /**
     * 生成短链
     * param request 请求
     * @param longUrl 长链接
     * @return
     */
    CommonResponse generateShortLink(HttpServletRequest request,String longUrl);


    /**
     * 获取长链
     * @param key 短链对应的base62key值
     * @return
     */
    CommonResponse getLongLink(String key);
}
