package com.zeusjava.cache;

import com.zeusjava.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By IntelliJ IDEA.
 * User: LittleXuan
 * Date: 2015/11/19.
 * Time: 9:17
 * Desc: To change this template use File | Settings | File Templates.
 */
public class UserCache {
    private static Map<String,User> userCache;
    private static UserCache instance = null;

    private UserCache() {
        userCache = new HashMap<String,User>();
        initOneUser();
    }

    public static Map<String,User> getUserCache() {
        if(instance==null) {
            instance = new UserCache();
        }
        return userCache;
    }

    private static void initOneUser() {
        User user = new User("001","zhaohongxuan",24);
        userCache.put(user.getUserId(),user);
    }
}
