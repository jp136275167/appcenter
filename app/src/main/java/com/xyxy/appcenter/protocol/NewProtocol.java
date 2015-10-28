package com.xyxy.appcenter.protocol;

import com.xyxy.appcenter.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class NewProtocol extends BaseProtocol<List<AppInfo>> {
    @Override
    protected String getKey() {
        return "app";
    }

    @Override
    protected List<AppInfo> parseJson(String json) {
        List<AppInfo> appInfos=new ArrayList<AppInfo>();
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    long id = jsonObject.getLong("id");
                    String name = jsonObject.getString("name");
                    String packageName = jsonObject.getString("packageName");
                    String iconUrl = jsonObject.getString("iconUrl");
                    float stars = Float.parseFloat(jsonObject.getString("stars"));
                    long size = jsonObject.getLong("size");
                    String downloadUrl = jsonObject.getString("downloadUrl");
                    String des = jsonObject.getString("des");
                    AppInfo info = new AppInfo(id, name, packageName, iconUrl, stars, size, downloadUrl, des);
                    appInfos.add(info);
                }
                return appInfos;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

    }


}
