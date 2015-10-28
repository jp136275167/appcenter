package com.xyxy.appcenter.protocol;

import com.xyxy.appcenter.domain.SubjectInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>> {
    @Override
    protected String getKey() {
        return "subject";
    }
    @Override
    protected List<SubjectInfo> parseJson(String json) {
        List<SubjectInfo> subjectInfos=new ArrayList<SubjectInfo>();
        try {
            JSONArray jsonArray=new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String des=jsonObject.getString("des");
                String url=jsonObject.getString("url");
                SubjectInfo subjectInfo=new SubjectInfo(des,url);
                subjectInfos.add(subjectInfo);
            }
            return subjectInfos;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;

        }
    }
}
