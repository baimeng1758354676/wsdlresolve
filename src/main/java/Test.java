import com.ibm.wsdl.PortImpl;
import com.ibm.wsdl.ServiceImpl;
import com.ibm.wsdl.extensions.soap.SOAPAddressImpl;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.xml.WSDLReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangleai on 2018/3/1.
 */
public class Test {
    public static void main(String[] args) throws WSDLException {
        String wsdluri = "wsdl/queryInfo.wsdl";
        WSDLReader wsdlReader = WAWsdlUtil.getWsdlReader();
        Definition definition = wsdlReader.readWSDL(wsdluri);
        Map services = definition.getServices();

        Object[] objects = services.values().toArray();
        ServiceImpl service = (ServiceImpl) objects[0];
        Object[] portList =  service.getPorts().values().toArray();
        PortImpl port = (PortImpl) portList[0];
        SOAPAddressImpl address = (SOAPAddressImpl) port.getExtensibilityElements().get(0);
        String locationURI = address.getLocationURI();
//        services.values().stream().forEach(t->{
//            ServiceImpl service = (ServiceImpl) t;
//            service.getPorts().values().stream().forEach(k->{
//                PortImpl port = (PortImpl) k;
//                SOAPAddressImpl address = (SOAPAddressImpl) port.getExtensibilityElements().get(0);
//                String uri = address.getLocationURI();
//
//            });
//            Element serviceElement = service.getDocumentationElement();
//            String name = serviceElement.getAttribute("name");
//            Node firstChild = service.getDocumentationElement().getFirstChild().getFirstChild();
//            System.out.println(firstChild);
//
//
//
//        });

        XmlObject object = XmlObject.Factory.newInstance();
        XmlCursor cursor = object.newCursor();
        XmlCursor.TokenType tokenType = cursor.toNextToken();
        cursor.beginElement( SoapVersion11.envelopeQName);
        cursor.toChild(0);
        cursor.beginElement(SoapVersion11.bodyQName);
        cursor.toParent();
        cursor.toChild(1);
        cursor.beginElement(SoapVersion11.headerQName);
        System.out.println(object);
        XmlCursor.TokenType tokenType9 = cursor.currentTokenType();
        XmlCursor.TokenType tokenType3 = cursor.toNextToken();
        XmlCursor.TokenType tokenType7 = cursor.toNextToken();
        XmlCursor.TokenType tokenType1 = cursor.currentTokenType();

        List<ParameterInfo> parameterInfos = WAWsdlUtil.getMethodParams(wsdluri, "queryCarInfomation");

        merge(parameterInfos, object, cursor);

        System.out.println(object);

    }

    private static void merge(List<ParameterInfo> parameterInfos, XmlObject object, XmlCursor cursor) {
        if (parameterInfos == null || parameterInfos.isEmpty()) {
            return;
        }
        for (int i = 0; i < parameterInfos.size(); i++) {
            XmlCursor.TokenType tokenType1 = cursor.currentTokenType();
            ParameterInfo parameterInfo = parameterInfos.get(i);
            cursor.insertElementWithText(parameterInfo.getName(), parameterInfo.getValue());
            XmlCursor.TokenType tokenType = cursor.toPrevToken();
            merge(parameterInfo.getChildren(), object, cursor);
            cursor.toParent();
        }
    }

    private static void printParams(List<ParameterInfo> parameterInfos, String parentName) {
        if (parameterInfos != null) {
            for (ParameterInfo parameterInfo : parameterInfos) {
                System.out.println("parentname : " + parentName + " ; name : " + parameterInfo.getName() + " ; type :" +
                        " " + parameterInfo.getType() + " ;" +
                        " childtype : " + parameterInfo.getChildType());
                printParams(parameterInfo.getChildren(), parameterInfo.getName());
            }
        }
    }
}
