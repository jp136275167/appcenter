package com.xyxy.appcenter.protocol;

import com.xyxy.appcenter.http.HttpHelper;
import com.xyxy.appcenter.tool.FileUtils;
import com.xyxy.appcenter.tool.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Administrator on 2015/10/26.
 */
public abstract class BaseProtocol<T> {
    public T load(int index){
        //读取本地缓存
        String json=loadLocal(index);
        if(json ==null){
            //请求服务器
            json=loadServer(index);
            if(json !=null){
                //保存缓存
                saveLocal(json,index);
            }
        }
        if(json != null){
            return parseJson(json);
        }else {
            return null;
        }
    }

    private void saveLocal(String json, int index) {
        BufferedWriter bw=null;
        File dir= FileUtils.getCacheDir(); //创建appcenter/cache
        File file=new File(dir,getKey()+"_"+index);  //创建 cache/"home_"+index
        try {
            FileWriter fw=new FileWriter(file);
            bw=new BufferedWriter(fw);
            //不能为long 类型 + ""  将long 转换为String
            bw.write(System.currentTimeMillis()+1000*10+"");
            bw.newLine(); //换行
            bw.write(json);//把json  保存起来
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(bw);
        }
    }

    private String loadServer(int index) {
        HttpHelper.HttpResult httpResult=HttpHelper.get(HttpHelper.URL+getKey()+"?index="+index);
        String json=httpResult.getString();
        System.out.println(json);
        return json;
    }



    private String loadLocal(int index) {
        File dir=FileUtils.getCacheDir(); //获取缓存文件夹
        File file=new File(dir,getKey()+"_"+index);
        try {
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            long outofDate=Long.parseLong(br.readLine());
            if(System.currentTimeMillis()>outofDate){
                return null;
            }else {
                String str=null;
                StringWriter sw=new StringWriter();
                while ((str=br.readLine())!=null){
                    sw.write(str);
                }
                return sw.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    protected abstract String getKey();
    protected abstract T parseJson(String json);
}
