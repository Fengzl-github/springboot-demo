package com.cn.demo.config.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 *@Author fengzhilong
 *@Date 2021/1/7 17:03
 *@Desc
 **/
public class RequestBodyWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = null;
    private JSONObject jsonObject = null;
    private String jsonText = null;

    public boolean isEmpty() {
        return requestBody == null || requestBody.length == 0;
    }

    public byte[] getRequestBody() {
        return requestBody;
    }

    // 传入是JSON格式 转换成JSONObject
    public JSONObject getJSONObject() throws UnsupportedEncodingException {
        if (jsonObject != null) {
            return jsonObject;
        }
        System.out.println("-------------------------------------------------");
        return jsonObject = JSON.parseObject(jsonText);
    }

    public void setRequestBody(JSONObject jsonObject) throws UnsupportedEncodingException {
        this.requestBody = jsonObject.toJSONString().getBytes("UTF-8");
    }

    public RequestBodyWrapper(HttpServletRequest request) {
        super(request);
        try {
            System.out.println("-------------------------------------------------");
            System.out.println("访问地址: " + request.getRequestURI());
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
            jsonText = new String(requestBody, "UTF-8");
            System.out.println(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public String getJsonText() {
        return jsonText;
    }
}
