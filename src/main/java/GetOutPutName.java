import com.ibm.wsdl.BindingImpl;
import com.ibm.wsdl.BindingOperationImpl;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import java.util.Collection;
import java.util.List;

/**
 * @program: cloud-integration
 * @description:
 * @Author: baimeng
 * @Date: 2020/6/18 16:55
 */
public class GetOutPutName {
    public static void main(String[] args) {
        try {
            Definition definition = WAWsdlUtil.getWsdlReader().readWSDL("wsdl/querymsg.wsdl");
            Collection values = definition.getBindings().values();
            Object[] objects = values.toArray();
            BindingImpl binding = (BindingImpl) objects[0];
            BindingOperationImpl bindingOperation = (BindingOperationImpl) binding.getBindingOperations().get(0);
            String name = bindingOperation.getBindingOutput().getName();
            System.out.println(name);
        } catch (WSDLException e) {
            e.printStackTrace();
        }

    }
}
