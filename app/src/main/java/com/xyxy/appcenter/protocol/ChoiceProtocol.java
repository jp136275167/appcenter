package com.xyxy.appcenter.protocol;

import com.xyxy.appcenter.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class ChoiceProtocol extends BaseProtocol<List<AppInfo>>{
    private List<String> pictures;

    public List<String> getPictures() {
        return pictures;
    }

    @Override
    protected String getKey() {
        return "home";
    }

    protected   List<AppInfo> parseJson(String json) {

        pictures=new ArrayList<String>();

        List<AppInfo> appInfos=new ArrayList<AppInfo>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray1 = jsonObject.getJSONArray("picture");
            for(int i=0;i<jsonArray1.length();i++){
                String str=jsonArray1.getString(i);
                pictures.add(str);
            }

            JSONArray jsonArray=jsonObject.getJSONArray("list");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                long id=jsonObject1.getLong("id");
                String name=jsonObject1.getString("name");
                String packageName=jsonObject1.getString("packageName");
                String iconUrl=jsonObject1.getString("iconUrl");
                float stars=Float.parseFloat(jsonObject1.getString("stars"));
                long size=jsonObject1.getLong("size");
                String downloadUrl=jsonObject1.getString("downloadUrl");
                String des=jsonObject1.getString("des");
                AppInfo info=new AppInfo(id,name,packageName,iconUrl,stars,size,downloadUrl,des);
                appInfos.add(info);
            }
            return appInfos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
