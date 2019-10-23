package com.beacon.shortlink.controller;

import com.beacon.shortlink.common.CommonResponse;
import com.beacon.shortlink.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author beacon
 */
@RestController
public class ShortLinkController {

    @Autowired
    @Qualifier("shortLinkService")
    private ShortLinkService shortLinkService;

    @RequestMapping(path = "/create")
    public CommonResponse create(HttpServletRequest request,String url) {
        return shortLinkService.generateShortLink(request,url);
    }

    @RequestMapping(path = "/m/{key}")
    public RedirectView analysis(@PathVariable String key) {
        RedirectView redirectView = new RedirectView();
        CommonResponse result = shortLinkService.getLongLink(key);
        if ("0".equals(result.getCode())) {
            //重定向到result.getData
            String longLink = (String) result.getData();
            redirectView.setUrl(longLink);
            return redirectView;
        }
        redirectView.setUrl("/shortLink/index");
        return redirectView;
    }


}
