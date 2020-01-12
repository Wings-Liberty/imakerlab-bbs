package cn.imakerlab.bbs.utils;

import cn.imakerlab.bbs.model.exception.MyException;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class MyUtils {

    public static <T> T ListToOne (List<T> list){

        //不知道list长度为0是是否返回fals
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        if (list.size() == 1){
            return list.get(0);
        } else {
            throw new MyException();
        }

    }

}