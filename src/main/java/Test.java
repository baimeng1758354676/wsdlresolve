import com.ibm.wsdl.PortImpl;
import com.ibm.wsdl.ServiceImpl;
import com.ibm.wsdl.extensions.soap.SOAPAddressImpl;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;
import org.w3c.dom.*;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.xml.WSDLReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangleai on 2018/3/1.
 */
public class Test {
    public static void main(String[] args) throws WSDLException, TransformerException {
        String wsdluri = "http://172.16.20.166/soap/JHIPLIB.SOAP.BS.HL7V3Service.cls?wsdl";
        WSDLReader wsdlReader = WAWsdlUtil.getWsdlReader();
        Definition definition = wsdlReader.readWSDL(wsdluri);
//        Element documentationElement = definition.getDocumentationElement();
//        NodeList soap = documentationElement.getElementsByTagName("soap");
//        System.out.println(definition);
        Document definitionDocument = WAWsdlUtil.getDefinitionDocument(wsdluri);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(definitionDocument);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();
        try {
            org.dom4j.Document daf = DocumentHelper.parseText(output);
            org.dom4j.Element rootElement = daf.getRootElement();
            org.dom4j.Element element = rootElement.element("binding").element("operation");
            String soapAction = ((DefaultElement) element.content().get(1)).attribute("soapAction").getValue();
            System.out.println(soapAction);
//            ((DefaultElement) ((ArrayList) ((DefaultElement) element).content).get(1)).attribute(0).getValue()
            System.out.println(1);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        NodeList soap = definitionDocument.getElementsByTagName("soap");

//        Map services = definition.getServices();

//        Object[] objects = services.values().toArray();
//        ServiceImpl service = (ServiceImpl) objects[0];
//        Object[] portList =  service.getPorts().values().toArray();
//        PortImpl port = (PortImpl) portList[0];
//        SOAPAddressImpl address = (SOsoAPAddressImpl) port.getExtensibilityElements().get(0);
//        String locationURI = address.getLocationURI();
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
        cursor.beginElement(SoapVersion11.bodyQName);
        cursor.toParent();
        cursor.beginElement(SoapVersion11.headerQName);
        XmlCursor.TokenType tokenType1 = cursor.toNextToken();
        XmlCursor.TokenType tokenType3 = cursor.toNextToken();


        List<ParameterInfo> parameterInfos = WAWsdlUtil.getMethodParams(wsdluri, "queryCarInfomation");

        merge(parameterInfos, cursor);

        System.out.println(object);

    }

    private static void merge(List<ParameterInfo> parameterInfos, XmlCursor cursor) {
        if (parameterInfos == null || parameterInfos.isEmpty()) {
            return;
        }
        for (int i = 0; i < parameterInfos.size(); i++) {
            XmlCursor.TokenType tokenType1 = cursor.currentTokenType();
            ParameterInfo parameterInfo = parameterInfos.get(i);
            cursor.insertElementWithText(parameterInfo.getName(), parameterInfo.getValue());
            XmlCursor.TokenType tokenType = cursor.toPrevToken();
            merge(parameterInfo.getChildren(), cursor);
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
