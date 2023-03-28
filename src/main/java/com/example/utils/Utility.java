package com.example.utils;

import java.util.Map;

public class Utility {
  public static String interpolate(String url, Map<String, String> map){
    for(Map.Entry<String,String> entry: map.entrySet()){
      url = url.replaceAll(String.format(":%s", entry.getKey()), entry.getValue());
    }
    return url;
  }
}
