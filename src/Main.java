import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

import java.util.*;

public class Main {
    static void createXML(List<Software> systems, List<Application_Software> applications, String system) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("software-package");
            document.appendChild(root);

            int fullprice = 0;
            int fullsize = 0;

            System.out.println("Software package:");

            for (int i = 0; i < systems.size(); i++){
                if (systems.get(i).getName().equals(system)){
                    systems.get(i).show();
                    System.out.println();

                    fullprice += systems.get(i).getPrice();
                    fullsize += systems.get(i).getSize();

                    Element software = document.createElement("software");
                    root.appendChild(software);

                    Element name = document.createElement("name");
                    name.appendChild(document.createTextNode(systems.get(i).getName()));
                    software.appendChild(name);
                    Element size = document.createElement("size");
                    size.appendChild(document.createTextNode(Integer.toString(systems.get(i).getSize())));
                    software.appendChild(size);
                    Element price = document.createElement("price");
                    price.appendChild(document.createTextNode(Integer.toString(systems.get(i).getPrice())));
                    software.appendChild(price);
                    Element type = document.createElement("type");
                    type.appendChild(document.createTextNode(systems.get(i).getType()));
                    software.appendChild(type);
                }
            }

            for (int i = 0; i < applications.size(); i++){
                if (applications.get(i).getRequires().equals(system)){
                    applications.get(i).show();
                    System.out.println();

                    fullprice += applications.get(i).getPrice();
                    fullsize += applications.get(i).getSize();

                    Element software = document.createElement("software");
                    root.appendChild(software);

                    Element name = document.createElement("name");
                    name.appendChild(document.createTextNode(applications.get(i).getName()));
                    software.appendChild(name);
                    Element size = document.createElement("size");
                    size.appendChild(document.createTextNode(Integer.toString(applications.get(i).getSize())));
                    software.appendChild(size);
                    Element price = document.createElement("price");
                    price.appendChild(document.createTextNode(Integer.toString(applications.get(i).getPrice())));
                    software.appendChild(price);
                    Element type = document.createElement("type");
                    software.appendChild(document.createTextNode(applications.get(i).getType()));
                    software.appendChild(type);
                    Element requires = document.createElement("requires");
                    requires.appendChild(document.createTextNode(applications.get(i).getRequires()));
                    software.appendChild(requires);
                }
            }
            System.out.println("Full price = " + fullprice);
            System.out.println("Full size = " + fullsize);

            Element full_price = document.createElement("full-price");
            full_price.appendChild(document.createTextNode(Integer.toString(fullprice)));
            root.appendChild(full_price);

            Element full_size = document.createElement("full-size");
            full_size.appendChild(document.createTextNode(Integer.toString(fullsize)));
            root.appendChild(full_size);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult streamResult = new StreamResult(new File("D:\\Project\\Result.xml"));
            transformer.transform(domSource, streamResult);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            List<Application_Software> applications = new ArrayList<Application_Software>();
            List<Software> systems = new ArrayList<Software>();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("programs.xml");

            Node root = document.getDocumentElement();
            NodeList programs = root.getChildNodes();
            for (int i = 0; i < programs.getLength(); i++) {
                Node program = programs.item(i);
                if (program.getNodeType() == Node.ELEMENT_NODE) {
                    Element first = (Element) program;

                    String name = first.getElementsByTagName("name").item(0).getTextContent();
                    int size = Integer.parseInt(first.getElementsByTagName("size").item(0).getTextContent());
                    int price = Integer.parseInt(first.getElementsByTagName("price").item(0).getTextContent());
                    String type = first.getElementsByTagName("type").item(0).getTextContent();

                    if(type.equals("Operating system")) {
                        systems.add(new Software(name,size,price,type));
                    }
                    else {
                        String requires = first.getElementsByTagName("requires").item(0).getTextContent();
                        applications.add(new Application_Software(name,size,price,type,requires));
                    }
                    }
                }
            createXML(systems, applications, "Microsoft Windows 10");
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
