import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

import java.sql.*;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/9/4 15:07
 */
public class TestProcejure {
    public static void main(String[] args) throws Exception {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String strUrl = "jdbc:oracle:thin:@//172.16.20.41:1521/mz";
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(strUrl, " MP ", " MP ");
            //创建执行存储过程的对象
            CallableStatement proc = null;
            //设置存储过程 call为关键字
            proc = conn.prepareCall("{ call MQS.histomobiledtr.mobiledtr_getPatientBasicInfo(?,?,?,?)}");

            //第一个参数，设置存储过程xml条件入参
            proc.setString(1, "<patientId></patientId>     <idCardNo></idCardNo>     <healthCardNo></healthCardNo>     <phone></phone>     <inpatientId>0002478</inpatientId>     <accountNo></accountNo>");

            proc.registerOutParameter(2, OracleTypes.NUMBER);
            proc.registerOutParameter(3, OracleType.VARCHAR2);
            proc.registerOutParameter(4, OracleTypes.CURSOR);
            proc.execute();

            //获得输出参数
//            String testPrint2 = proc.getString(2);
//            String testPrint3 = proc.getString(3);
//            ResultSet resultSet = (ResultSet) proc.getObject(4);
            rs = (ResultSet)proc.getObject(4);
            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(rs != null){
                    rs.close();
                    if(stmt!=null){
                        stmt.close();
                    }
                    if(conn!=null){
                        conn.close();
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
