import com.ibm.wsdl.BindingImpl;
import com.ibm.wsdl.BindingOperationImpl;
import com.ibm.wsdl.ServiceImpl;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.json.XML;

import javax.wsdl.*;
import javax.wsdl.extensions.schema.Schema;
import javax.wsdl.xml.WSDLReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/6/18 16:55
 */
public class GetOutPutName {
    public static void main(String[] args) {

        try {

            WSDLReader wsdlReader = WAWsdlUtil.getWsdlReader();
            wsdlReader.setFeature("javax.wsdl.verbose", true);
            wsdlReader.setFeature("javax.wsdl.importDocuments", true);
            Definition definition = WAWsdlUtil.getWsdlReader().readWSDL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl");

            Iterator iterator = definition.getServices().values().iterator();
            while (iterator.hasNext()) {
                ServiceImpl service = (ServiceImpl) iterator.next();
                Port port = service.getPort("WeatherWebServiceSoap");
                Object o = port.getExtensibilityElements().get(0);
//                Binding binding = weatherWebServiceSoap.getBinding();
//
//                PortType portType = binding.getPortType();
//                List<Operation> operations = portType.getOperations();
//                Operation operation = operations.get(0);



                System.out.println(1);
            }


            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
