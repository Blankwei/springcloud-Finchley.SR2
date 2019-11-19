package com.savoidage.demo.config;

import com.savoidage.common.jwt.TokenUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-16 16:34
 * Description: FeignServerConfig
 */
public class FeignServerConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String feignToken = TokenUtils.getFeignToken();
        requestTemplate.header("feign-header-token",feignToken);
    }
}
