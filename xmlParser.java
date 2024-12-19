import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.StringReader;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Main{
  public static void main(String[] args)throws Exception {
    new StAXStreamTreeViewer();
  }
}
class StAXStreamTreeViewer extends JFrame {
  private JTree jTree;

  DefaultTreeModel defaultTreeModel;

  public StAXStreamTreeViewer()throws Exception {
    
    DefaultMutableTreeNode base = new DefaultMutableTreeNode("XML Document");

    defaultTreeModel = new DefaultTreeModel(base);
    jTree = new JTree(defaultTreeModel);

    buildTree(defaultTreeModel, base);

    getContentPane().add(new JScrollPane(jTree), BorderLayout.CENTER);
    
    setVisible(true);
    setSize(800, 450);
  }
  public void buildTree(DefaultTreeModel treeModel, DefaultMutableTreeNode current)
      throws XMLStreamException, FileNotFoundException {
    StringReader sr = new StringReader("<tag src='java2s.com'></tag>");
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    XMLStreamReader reader = inputFactory.createXMLStreamReader(sr);

    addStartDocumentNodes(reader, current);

    parseRestOfDocument(reader, current);
  }

  private void addStartDocumentNodes(XMLStreamReader reader, DefaultMutableTreeNode current) {
    DefaultMutableTreeNode version = new DefaultMutableTreeNode(reader.getVersion());
    current.add(version);

    DefaultMutableTreeNode standalone = new DefaultMutableTreeNode(reader.isStandalone());
    current.add(standalone);

    DefaultMutableTreeNode standaloneSet = new DefaultMutableTreeNode(reader.standaloneSet());
    current.add(standaloneSet);

    DefaultMutableTreeNode encoding = new DefaultMutableTreeNode(reader.getEncoding());
    current.add(encoding);

    DefaultMutableTreeNode declaredEncoding = new DefaultMutableTreeNode(reader.getCharacterEncodingScheme());
    current.add(declaredEncoding);
  }

  private void parseRestOfDocument(XMLStreamReader reader, DefaultMutableTreeNode current)
      throws XMLStreamException {

    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
      case XMLStreamConstants.START_ELEMENT:

        DefaultMutableTreeNode element = new DefaultMutableTreeNode(reader.getLocalName());
        current.add(element);
        current = element;

        if (reader.getNamespaceURI() != null) {
          String prefix = reader.getPrefix();
          if (prefix == null) {
            prefix = "[None]";
          }
          DefaultMutableTreeNode namespace = new DefaultMutableTreeNode("prefix = '"
              + prefix + "', URI = '" + reader.getNamespaceURI() + "'");
          current.add(namespace);
        }

        if (reader.getAttributeCount() > 0) {
          for (int i = 0; i < reader.getAttributeCount(); i++) {
            DefaultMutableTreeNode attribute = new DefaultMutableTreeNode("Attribute (name = '"
                + reader.getAttributeLocalName(i) + "', value = '" + reader.getAttributeValue(i)
                + "')");
            String attURI = reader.getAttributeNamespace(i);
            if (attURI != null) {
              String attPrefix = reader.getAttributePrefix(i);
              if (attPrefix == null || attPrefix.equals("")) {
                attPrefix = "[None]";
              }
              DefaultMutableTreeNode attNamespace = new DefaultMutableTreeNode(
                  "prefix=" + attPrefix + ",URI=" + attURI);
              attribute.add(attNamespace);
            }
            current.add(attribute);
          }
        }

        break;
      case XMLStreamConstants.END_ELEMENT:
        current = (DefaultMutableTreeNode) current.getParent();
        break;
      case XMLStreamConstants.CHARACTERS:
        if (!reader.isWhiteSpace()) {
          DefaultMutableTreeNode data = new DefaultMutableTreeNode("CD:"
              + reader.getText());
          current.add(data);
        }
        break;
      case XMLStreamConstants.DTD:
        DefaultMutableTreeNode dtd = new DefaultMutableTreeNode("DTD:" + reader.getText());
        current.add(dtd);
        break;
      case XMLStreamConstants.SPACE:
        break;
      case XMLStreamConstants.COMMENT:
        DefaultMutableTreeNode comment = new DefaultMutableTreeNode(reader.getText());
        current.add(comment);
        break;
      default:
        System.out.println(type);
      }
    }
  }

}
