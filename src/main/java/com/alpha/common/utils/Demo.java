package com.alpha.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {

    static Pattern pattern = Pattern.compile("mtmsgid=(.*?)&");

    public static String getEncoding(int dataCoding) {
        switch (dataCoding) {
            case 0:
                return "ASCII";
            case 6:
                return "UTF8";
            case 8:
                return "UnicodeBigUnmarked";
            case 15:
                return "GBK";
            default:
                return null;
        }
    }

    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    public static String decodeHexStr(int dataCoding, String hexStr) {
        String realStr = null;

        if (hexStr != null) {
            try {
                byte[] data = hexStr2ByteArr(hexStr);
                realStr = new String(data, getEncoding(dataCoding));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return realStr;
    }

    public static String extractMessageID(String resp) {
        Matcher matcher = pattern.matcher(resp);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String send(String appid, String pwd, String to, String content) {
        try {
            URL api = new URL("http://api2.santo.cc/submit?command=MT_REQUEST" + "&cpid=" + appid + "&cppwd=" + pwd
                    + "&da=" + to + "&sm=" + URLEncoder.encode(content, "UTF-8"));
            URLConnection conn = api.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine, messageID = "";
            while ((inputLine = in.readLine()) != null) {
                if (messageID.length() == 0) {
                    messageID = extractMessageID(inputLine);
                }
            }
            in.close();
            return messageID;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        // 测试发送
        String msgId = send("test1", "santo201704", "8615986797745", "测试 - from java demo");
        System.out.println(msgId);

        // 测试解码
        // System.out.println(decodeHexStr(15, "c4e3bac3"));
    }
}
