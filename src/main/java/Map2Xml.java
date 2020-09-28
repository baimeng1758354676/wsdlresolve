import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/6/23 15:14
 */
public class Map2Xml {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
//        GroovyShell groovyShell = new GroovyShell();
//        Map<String, String> map = new HashMap(2);
//        map.put("name", "bob");
//        groovyShell.setProperty("map",map);
//
//        groovyShell.evaluate("String a = map.get('name');map.put('age',a+'qqq')");
//        System.out.println(1);
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class groovyClass = groovyClassLoader.parseClass(new File("F:\\kingdee\\example\\helloworld\\src\\main\\java\\TestGroovy.groovy"));
        GroovyObject o = (GroovyObject) groovyClass.newInstance();
        Exchange exchange = new DefaultExchange(new DefaultCamelContext());

        o.invokeMethod("process", exchange);
        System.out.println(1);
    }
}
