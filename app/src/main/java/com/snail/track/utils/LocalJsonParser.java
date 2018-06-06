package com.snail.track.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class LocalJsonParser {

    /**
     * 得到json文件中的内容
     */
    public static String getJsonString(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串转换为对象
     */
    public static <T> T parseJsonToObject(String json, Class<T> type) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * 将Json文件转换为对象
     */
    public static <T> T parseJsonToObject(Context context, String fileName, Class<T> type) {
        return parseJsonToObject(getJsonString(context, fileName), type);
    }

    /**
     * 将字符串转换为List
     */
    public static <T> T parseJsonToList(String json, Type type) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * 将Json文件转换为对象
     */
    public static <T> T parseJsonToList(Context context, String fileName, Type type) {
        return parseJsonToList(getJsonString(context, fileName), type);
    }

}
