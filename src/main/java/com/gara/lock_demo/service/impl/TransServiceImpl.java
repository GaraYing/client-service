package com.gara.lock_demo.service.impl;

import com.gara.lock_demo.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO
 * @author: GaraYing
 * @create: 2018-08-14 17:47
 **/

@Service
public class TransServiceImpl implements TransService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String invoke(String url, String orderid) {
        return restTemplate.getForEntity(url+orderid,String.class).getBody();
    }
}
