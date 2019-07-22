package io.github.iamtwang.ioc.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tao
 */
public class ToyIOC {

    public ToyIOC(String location) throws Exception {
        loadBeans(location);
    }

    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String REF = "ref";

    private static final String ID = "id";
    private static final String CLASS = "class";
    private static final String PROPERTY = "property";

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        Object bean = beanMap.get(name);
        if (bean == null) {
            throw new IllegalArgumentException("there is no bean with name " + name);
        }

        return bean;
    }

    private void loadBeans(String location) throws Exception {

        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = docBuilder.parse(Files.newInputStream(Paths.get(location)));
        NodeList nodes = doc.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute(ID);
                String className = ele.getAttribute(CLASS);

                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }


                Object bean = beanClass.newInstance();


                NodeList propertyNodes = ele.getElementsByTagName(PROPERTY);
                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Node propertyNode = propertyNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute(NAME);
                        String value = propertyElement.getAttribute(VALUE);

                        //Set accessible to true
                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);

                        if (value != null && value.length() > 0) {

                            declaredField.set(bean, value);
                        } else {
                            String ref = propertyElement.getAttribute(REF);
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }

                            declaredField.set(bean, getBean(ref));
                        }

                        registerBean(id, bean);
                    }
                }
            }
        }
    }

    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }

}
