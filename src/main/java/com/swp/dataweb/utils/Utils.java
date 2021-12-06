package com.swp.dataweb.utils;

import com.swp.dataweb.entity.User;
import com.swp.dataweb.entity.response.Status;
import com.swp.dataweb.exception.SuRuntimeException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Utils {
    /**
     * 获取用户id
     */
    public static Long getUserId() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    /**
     * 获取用户名
     */
    public static String getUserName() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    /**
     * 获取用户昵称
     */
    public static String getNickName() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNickname();
    }

    public static String setPassword(String password) {
        int salt = (int) Math.floor((Math.random() * 6) + 1);
        String date = new SimpleDateFormat("HHmm").format(new Date());

        password = salt + password.substring(0, salt) + date + password.substring(salt);
        StringBuilder p = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            p.append(password.charAt(i));
            p.append('\r');
        }
        return p.toString();
    }

    /**
     * decode密码
     */
    public static String decodePassword(String password) {
        String[] chars = password.split("\\s");
        StringBuilder p = new StringBuilder();
        for (String aChar : chars) {
            p.append(String.valueOf((char) Integer.parseInt(aChar)));
        }
        return p.toString();
    }

    /**
     * 将密码加密并返回
     *
     * @param password
     * @return
     */
    public static String getPassword(String password) {
        String s = decodePassword(password);
        int salt1 = Integer.parseInt(String.valueOf(s.charAt(0)));
        String s2 = s.substring(1, salt1 + 1) + s.substring(salt1 + 5);
        return s2;
    }

    /**
     * 检查密码时效性并返回解密密码(无时效性返回""
     */
    public static String checkAndGetPassword(String password) {
        String s = decodePassword(password);
        int salt1 = Integer.parseInt(String.valueOf(s.charAt(0)));
        String date = new SimpleDateFormat("HHmm").format(new Date());
        int d = Integer.parseInt(date);
        int sub = Integer.parseInt(s.substring(salt1 + 1, salt1 + 5));
        boolean equals = d+3>=sub&&d-3<=sub;
        if (equals) {
            return s.substring(1, salt1 + 1) + s.substring(salt1 + 5);
        } else {
            return "";
        }
    }


    /**
     * 对集合进行深拷贝
     * 注意需要对泛型类进行序列化（实现serializable）
     *
     * @param src
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopyList(List<T> src) {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteOut);
        ) {
            outputStream.writeObject(src);
            try (ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                 ObjectInputStream inputStream = new ObjectInputStream(byteIn);
            ) {
                return (List<T>) inputStream.readObject();
            }
        } catch (Exception e) {
            throw new SuRuntimeException(Status.FAILURE);
        }
    }
}
