import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/8/31 11:50
 */
public class TestTime {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate now = LocalDate.now();

        LocalDateTime localDateTime = now.minusDays(3).atStartOfDay();
        String format1 = localDateTime.format(dateTimeFormatter);
        long milli = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println(format1);
    }
}
