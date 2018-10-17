package com.wangtao.tools;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author : wangtao
 * @date : 2018/10/17 17:31  星期三
 */


public class SmsUtils {
    /**
     * 时间戳接口配置
     */
    public static final String TIMESTAMP = "https://api.mysubmail.com/service/timestamp";
    /**
     * 备用接口
     * http://api.mysubmail.com/service/timestamp
     * https://api.submail.cn/service/timestamp
     * http://api.submail.cn/service/timestamp
     */

    public static final String TYPE_MD5 = "md5";
    public static final String TYPE_SHA1 = "sha1";
    public static final String SIGNATURE = "【Admin】";
    /**
     * API 请求接口配置
     */
    private static final String URL = "https://api.mysubmail.com/message/send";

    /**
     * 备用接口
     * <p>
     * http://api.mysubmail.com/message/send
     * https://api.submail.cn/message/send
     * http://api.submail.cn/message/send
     */
    public static void send(String msg) {
        System.out.println("msg = " + msg);
        TreeMap<String, Object> requestData = new TreeMap<String, Object>();
        String appid = "28434";
        String appkey = "e55b2222076ed6279feb0d6a5f815481";
        String to = "17612172512";
//        String content = "【SUBMAIL】你好，你的验证码是1234";
        String content = SIGNATURE + msg;
        String signtype = "md5";

        requestData.put("appid", appid);
        requestData.put("content", content);
        requestData.put("to", to);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        ContentType contentType = ContentType.create("text/plain", "UTF-8");
        for (Map.Entry<String, Object> entry : requestData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                builder.addTextBody(key, String.valueOf(value), contentType);
            }
        }
        if (signtype.equals(TYPE_MD5) || signtype.equals(TYPE_SHA1)) {
            String timestamp = getTimestamp();
            requestData.put("timestamp", timestamp);
            requestData.put("sign_type", signtype);
            String signStr = appid + appkey + RequestEncoder.formatRequest(requestData) + appid + appkey;
            System.out.println(signStr);
            builder.addTextBody("timestamp", timestamp);
            builder.addTextBody("sign_type", signtype);
            builder.addTextBody("signature", RequestEncoder.encode(signtype, signStr), contentType);
        } else {
            builder.addTextBody("signature", appkey, contentType);
        }
        /**
         * http post 请求接口
         * 成功返回 status: success,其中 fee 参数为短信费用 ，credits 参数为剩余短信余额
         * 详细的 API 错误日志请访问 SUBMAIL 官网 → 开发文档 → DEBUG → API 错误代码
         */
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("charset", "UTF-8");
        httpPost.setEntity(builder.build());
        try {
            CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
            HttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(jsonStr);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取时间戳
     *
     * @return
     */
    private static String getTimestamp() {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet(TIMESTAMP);
        try {
            HttpResponse response = closeableHttpClient.execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
            if (jsonStr != null) {
                JSONObject json = JSONObject.fromObject(jsonStr);
                return json.getString("timestamp");
            }
            closeableHttpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
