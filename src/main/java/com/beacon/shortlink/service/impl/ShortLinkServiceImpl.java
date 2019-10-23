package com.beacon.shortlink.service.impl;

import com.beacon.shortlink.common.CommonResponse;
import com.beacon.shortlink.entity.Link;
import com.beacon.shortlink.mapper.LinkMapper;
import com.beacon.shortlink.service.ShortLinkService;
import com.beacon.shortlink.util.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author beacon
 */
@Service("shortLinkService")
public class ShortLinkServiceImpl implements ShortLinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public CommonResponse generateShortLink(HttpServletRequest request,String longUrl) {
        if (!(longUrl.startsWith("http") || longUrl.startsWith("https"))) {
            return new CommonResponse("999", "illegal url", null);
        }

        Link queryLink = new Link();
        queryLink.setLongLink(longUrl);
        Link link = linkMapper.queryLink(queryLink);
        if (link == null) {
            link = new Link();
            link.setLongLink(longUrl);
            linkMapper.addLink(link);
        }
        String key = Base62.encode(link.getId());
        String shortLink = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/m/"+key;
        return new CommonResponse("0", "success", shortLink);
    }

    @Override
    public CommonResponse getLongLink(String key) {
        long id = Base62.decode(key);
        Link queryLink = new Link();
        queryLink.setId(id);
        Link link = linkMapper.queryLink(queryLink);
        if(link==null){
            return new CommonResponse("999","not exists",null);
        }
        return new CommonResponse("0","success",link.getLongLink());
    }
}
