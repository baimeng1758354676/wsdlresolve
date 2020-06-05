import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import java.util.List;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/6/5 9:56
 */
public class Test1 {
    public static void main(String[] args) throws WSDLException {
        String wsdluri = "wsdl/queryInfo.wsdl";
        WSDLReader wsdlReader = WAWsdlUtil.getWsdlReader();
        Definition definition = wsdlReader.readWSDL(wsdluri);
        List<ParameterInfo> parameterInfos = WAWsdlUtil.getMethodParams(wsdluri, "queryCarInfomation");

        String targetNamespace = definition.getTargetNamespace();
        String prefix = definition.getPrefix(targetNamespace);

        XmlObject object = XmlObject.Factory.newInstance();
        XmlCursor cursor = object.newCursor();
        cursor.toNextToken();
        //构建soap 消息大体结构
        cursor.insertElement(SoapConstant.ENVELOPE_QNAME);
//        cursor.insertElement(new QName("http://schemas.xmlsoap.org/soap/envelope/","Envelope","soapenv"));
//            cursor.beginElement(SoapConstant.envelopeQName);
//            cursor.insertNamespace("soapenv","http://schemas.xmlsoap.org/soap/envelope/");
//            cursor.insertNamespace(prefix,targetNamespace);
        cursor.toPrevToken();
        cursor.beginElement(SoapConstant.BODY_QNAME);
//        cursor.beginElement(new QName("http://schemas.xmlsoap.org/soap/envelope/","Body","soapenv"));
        cursor.toParent();
//        cursor.beginElement(new QName("http://schemas.xmlsoap.org/soap/envelope/","Header","soapenv"));
        cursor.beginElement(SoapConstant.HEADER_QNAME);
        cursor.toNextToken();
        cursor.toNextToken();
        // 根据参数列表构建 soap body 消息
        buildBodyMessage(parameterInfos, cursor,targetNamespace,prefix);

        System.out.println(object);
    }

    private static void buildBodyMessage(List<ParameterInfo> params, XmlCursor cursor, String targetNamespace, String prefix) {
        if (params == null || params.isEmpty()) {
            return;
        }
        for (int i = 0; i < params.size(); i++) {
            ParameterInfo parameterInfo = params.get(i);
            if (parameterInfo.getName().equals(parameterInfo.getType())) {
                cursor.insertElementWithText(new QName(targetNamespace, parameterInfo.getName(), prefix), parameterInfo.getValue());

            } else {
                cursor.insertElementWithText(parameterInfo.getName(), parameterInfo.getValue());
            }
//            cursor.insertElementWithText(new QName(targetNamespace,parameterInfo.getName(),prefix),parameterInfo.getValue());
//            cursor.insertElementWithText(new QName(targetNamespace, parameterInfo.getName()), parameterInfo.getValue());
//            cursor.insertElementWithText(parameterInfo.getName(), parameterInfo.getValue());
            cursor.toPrevToken();
            buildBodyMessage(parameterInfo.getChildren(),cursor,targetNamespace,prefix);
            cursor.toParent();
        }
    }
}
