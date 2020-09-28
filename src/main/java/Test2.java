import java.util.HashMap;
import java.util.Map;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/8/5 14:38
 */
public class Test2 {
    public static void main(String[] args) {

        Map map = new HashMap<String, Object>();
        map.put("success", true);
        Map dataMap = new HashMap<String, Object>();
        dataMap.put("patientid", "7359555");
        map.put("data", dataMap);

        String sqlValueReplace = sqlValueReplace("UPDATE MQS.P_PLATFORM_LOG p SET p.FISSENDED = 'N' WHERE p.FMSGID = '{patientid}'", map);
        System.out.println(1);
    }


    public static String sqlValueReplace(String sql, Map<String, Object> map) {
//        String sqlForExecute = sql;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                //递归调用
                sql = sqlValueReplace(sql, (Map<String, Object>) value);
            } else {
                //值替换
                if (sql.contains("{" + key + "}")) {
                    // value 为 null 时 特殊处理
                    if (null == value) {
                        sql = sql.replace("{" + key + "}", value + "");
                        sql = sql.replace("'{" + key + "}'", value + "");
                    }
                    sql = sql.replace("{" + key + "}", value + "");
                }
            }
        }
        return sql;
    }
}
