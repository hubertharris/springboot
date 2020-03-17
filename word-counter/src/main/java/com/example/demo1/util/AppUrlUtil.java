package com.example.demo1.util;

import org.springframework.web.util.UriComponentsBuilder;

public class AppUrlUtil {

    public static String getHttpLocalUrl(int port, String path) {
        return UriComponentsBuilder.newInstance()
                .scheme(AppConstant.SCHEME_HTTP)
                .host(AppConstant.HOSTNAME_LOCAL)
                .port(port).path(path).build().toString();
    }
}
