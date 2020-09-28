import groovy.json.JsonSlurper
import org.apache.camel.Exchange
import org.apache.camel.Message
import org.apache.camel.Processor

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/9/18 17:16
 */
class TestGroovy {
     void process (Exchange exchange) {
        Message inMessage = exchange.getIn()
         def jsonSlurper =  new JsonSlurper()
         def object = jsonSlurper.parseText("{ \"name\": \"John\", \"ID\" : \"1\"}")
         inMessage.setBody(object)


    }
}
