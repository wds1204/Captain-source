package com.captain.wds;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;

public interface APIService {

    Call<ResponseBody> login(@FieldMap Map<String,  String> map);//登陆
    
    Call<ResponseBody> testFormUrl(String name,  int pwd);//登陆



}
