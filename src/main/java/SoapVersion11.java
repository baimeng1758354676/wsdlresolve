import javax.xml.namespace.QName;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/6/1 15:15
 */
public class SoapVersion11 {

    public final static QName envelopeQName = new QName(Constants.SOAP11_ENVELOPE_NS, "Envelope");
    public final static QName bodyQName = new QName(Constants.SOAP11_ENVELOPE_NS, "Body");
    public final static QName faultQName = new QName(Constants.SOAP11_ENVELOPE_NS, "Fault");
    public final static QName headerQName = new QName(Constants.SOAP11_ENVELOPE_NS, "Header");

}
