package com.yudi.wx.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WXUtils {
    /**
     * 检查签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param token
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        //1.定义数组存放tooken，timestamp,nonce
        String[] arr = {token, timestamp, nonce};
        //2.对数组进行排序
        Arrays.sort(arr);
        //3.生成字符串
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        //4.sha1加密,网上均有现成代码
        String temp = getSha1(sb.toString());
        //5.将加密后的字符串，与微信传来的加密签名比较，返回结果
        return temp.equals(signature);
    }

    /**
     * sha1加密
     *
     * @param str
     * @return
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;

        }

    }

    /**
     * 将String中的xml转换为map
     *
     * @param strXML
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        Map<String, String> data = new HashMap();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
        Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx = 0; idx < nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == 1) {
                Element element = (Element) node;
                data.put(element.getNodeName(), element.getTextContent());
            }
        }

        try {
            stream.close();
        } catch (Exception var10) {
            ;
        }

        return data;
    }

    /**
     * 将request 中的xml转换成String
     *
     * @param request
     * @return
     */

    public static String requstToXml(HttpServletRequest request) {
        String resXml = "";
        try {
            //
            InputStream is = request.getInputStream();
            //将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
        } catch (Exception e) {
        }
        return resXml;
    }

    /**
     * 将map转换为xml
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("xml");
        document.appendChild(root);
        Iterator var5 = data.keySet().iterator();

        while (var5.hasNext()) {
            String key = (String) var5.next();
            String value = (String) data.get(key);
            if (value == null) {
                value = "";
            }

            value = value.trim();
            Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty("indent", "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();

        try {
            writer.close();
        } catch (Exception e) {

        }

        return output;
    }

    public static String myrequest(String strUrl, String requestData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String UTF8 = "UTF-8";
        String reqBody = requestData;
        URL httpUrl = new URL(strUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(connectTimeoutMs);
        httpURLConnection.setReadTimeout(readTimeoutMs);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(reqBody.getBytes(UTF8));
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        String resp = stringBuffer.toString();
        if (stringBuffer != null) {
            try {
                bufferedReader.close();
            } catch (IOException var18) {
                var18.printStackTrace();
            }
        }

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException var17) {
                var17.printStackTrace();
            }
        }

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException var16) {
                var16.printStackTrace();
            }
        }

        return resp;

    }
    public static  void main(String args[]) throws Exception {
String rquest = "{" +
        "    \"button\": [" +
        "        {" +
        "            \"name\": \"扫码\", " +
        "            \"sub_button\": [" +
        "                {" +
        "                    \"type\": \"scancode_waitmsg\", " +
        "                    \"name\": \"扫码带提示\", " +
        "                    \"key\": \"rselfmenu_0_0\", " +
        "                    \"sub_button\": [ ]" +
        "                }, " +
        "                {" +
        "                    \"type\": \"scancode_push\", " +
        "                    \"name\": \"扫码推事件\", " +
        "                    \"key\": \"rselfmenu_0_1\", " +
        "                    \"sub_button\": [ ]" +
        "                }" +
        "            ]" +
        "        }, " +
        "        {" +
        "            \"name\": \"发图\", " +
        "            \"sub_button\": [" +
        "                {" +
        "                    \"type\": \"pic_sysphoto\", " +
        "                    \"name\": \"系统拍照发图\", " +
        "                    \"key\": \"rselfmenu_1_0\", " +
        "                   \"sub_button\": [ ]" +
        "                 }, " +
        "                {" +
        "                    \"type\": \"pic_photo_or_album\", " +
        "                    \"name\": \"拍照或者相册发图\", " +
        "                    \"key\": \"rselfmenu_1_1\", " +
        "                    \"sub_button\": [ ]" +
        "                }, " +
        "                {" +
        "                    \"type\": \"pic_weixin\", " +
        "                    \"name\": \"微信相册发图\", " +
        "                    \"key\": \"rselfmenu_1_2\", " +
        "                    \"sub_button\": [ ]" +
        "                }" +
        "            ]" +
        "        }, " +
        "        {" +
        "            \"name\": \"发送位置\", " +
        "            \"type\": \"location_select\", " +
        "            \"key\": \"rselfmenu_2_0\"" +
        "        }," +
        "        {" +
        "           \"type\": \"media_id\", " +
        "           \"name\": \"图片\", " +
        "           \"media_id\": \"MEDIA_ID1\"" +
        "        }, " +
        "        {" +
        "           \"type\": \"view_limited\", " +
        "           \"name\": \"图文消息\", " +
        "           \"media_id\": \"MEDIA_ID2\"" +
        "        }" +
        "    ]" +
        "}";
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=9_l0CM-lICaeNHM_9HlPbEwPOgwRWppjUra3KYgwI1YPjqSyFMbDI_4Kc7HiDRKwu28FxZd6SQOKfoaKtOZY_pLJ_w8KCU6GylsezRhgxfhUrIGDXHXxZZltDdgmz0EUFXC-tbhJ863OsYapcrQCVcADACOH";
        String str = myrequest(url,rquest,2000,10000);
        System.out.println(str);
    }
}