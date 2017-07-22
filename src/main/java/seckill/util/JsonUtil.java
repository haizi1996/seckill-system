package seckill.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 操作工具类
 * @author haizi
 *
 */
public class JsonUtil {


    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将 Java 对象转为 JSON 字符串
     */
    public static <T> String toJSON(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Java 转 JSON 出错！");
        }
        return jsonStr;
    }

    /**
     * 将 JSON 字符串转为 Java 对象
     */
    public static <T> T fromJSON(String json, Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException("JSON 转 Java 出错！");
        }
        return obj;
    }
    public static <T> T fromBytes(byte[] bytes , Class<T> type) {
    	T obj = null;
    	try {
			obj = objectMapper.readValue(bytes, type);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return obj;
    }
    public static <T> byte[] toBytes(T t ) {
    	
    	byte[] data = null;
    	try {
    		data = objectMapper.writeValueAsBytes(t);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data;
    }
}
