/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/8/6 17:56
 */
public class TestRegex {
    public static void main(String[] args) {
        String regex = ".*:\\d{0,5}$";
        boolean matches = "10.0.0.1:dsfs".matches(regex);
        System.out.println(matches);
    }
}
