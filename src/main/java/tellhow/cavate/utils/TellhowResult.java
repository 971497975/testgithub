package tellhow.cavate.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 泰豪公司自定义响应结构,需要引入jackson相关的jar包
 */
public class TellhowResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;//例如 ：200  400  500  200正确  400错误   500异常

    // 响应消息
    private String msg;//例如：成功  失败

    // 响应中的数据
    private Object data;

    
    /**
     * 自定义方法,都需返回对应的构造方法,生成代餐的TellhowResult对象
     */
    //3个参数的build方法, 参数1:状态;    参数2:信息;  参数3:数据类型
    public static TellhowResult build(Integer status, String msg, Object data) {
        return new TellhowResult(status, msg, data);
    }
    
    //2个参数的build方法, 参数1:状态;    参数2:信息;  
    public static TellhowResult build(Integer status, String msg) {
        return new TellhowResult(status, msg, null);
    }
    
    //1个参数的ok方法, 参数1:数据类型;
    public static TellhowResult ok(Object data) {
        return new TellhowResult(data);
    }

    //0个参数的ok方法
    public static TellhowResult ok() {
        return new TellhowResult(null);
    }

   

    /**
     * 将json结果集转化为TellhowResult对象
     * 
     * @param jsonData json数据
     * @param clazz TellhowResult中的object类型
     * @return
     */
    public static TellhowResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, TellhowResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static TellhowResult format(String json) {
        try {
            return MAPPER.readValue(json, TellhowResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static TellhowResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
    
    
    //构造方法
    public TellhowResult() {

    }
    
    public TellhowResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TellhowResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    
    //参数的get,set方法
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
