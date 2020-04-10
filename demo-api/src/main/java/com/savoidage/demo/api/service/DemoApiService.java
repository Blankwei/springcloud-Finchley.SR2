package com.savoidage.demo.api.service;

import com.savoidage.demo.api.config.FeignServerConfig;
import com.savoidage.demo.api.fallback.DemoApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-16 16:25
 * Description: DemoApiService
 */
@FeignClient(name = "${demo.server.name}",configuration = FeignServerConfig.class,fallbackFactory = DemoApiFallback.class)
public interface DemoApiService {

    @RequestMapping(value = "/student/findOne")
    String findOne(@RequestParam("stuId") Integer stuId);
}
