package com.xyxy.appcenter.fragment;
import android.support.v4.app.Fragment;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2015/10/25.
 */
public class FragmentFactory {
    private  static Map<Integer,Fragment> mFragments=new HashMap<Integer,Fragment>();
    Fragment fragment=null;
    public static Fragment CreateFragment(int position){
        Fragment fragment=null;
        fragment=mFragments.get(position);
        if(fragment==null){
            if(position ==0){
                fragment=new ChoiceFragment();
            }else if(position == 1){
                fragment=new TopFragment();
            }else if(position ==2){
                fragment=new NewFragment();
            }else if(position == 3){
                fragment=new SubjectFragment();
            }else if(position == 4){
                fragment=new CategoryFragment();
            }
            if(fragment!=null){
                mFragments.put(position,fragment);
            }
        }
        return fragment;
    }

}
