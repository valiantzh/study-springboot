package com.study.upms.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * @author valiantzh
 * @version 1.0
 */
public class PageUtils {

    /**
     * 获取分页参数
     *
     * @param json
     * @return
     */
    public static Page getPageParam(JSONObject json) {
        int current = json.getIntValue("current");
        int size = json.getIntValue("size");
        if (current == 0) current = 1;
        if (size == 0) size = 10;
        return new Page(current, size);
    }

}
