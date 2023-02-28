package org.apache.batik.extension.svg;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.batik.dom.AbstractDocument;
import org.apache.batik.extension.GraphicsExtensionElement;
import org.w3c.dom.Node;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class BarcodeElement extends GraphicsExtensionElement implements BatikExtConstants {
    /**
     * Creates a new BarcodeElement object.
     *
     * @param name  The element name, for validation purposes.
     * @param owner The owner document.
     */
    public BarcodeElement(String name, AbstractDocument owner) {
        super(name, owner);
    }

    @Override
    public String getLocalName() {
        return "barcode";
    }

    @Override
    public String getNamespaceURI() {
        return "http://xml.apache.org/batik/ext";
    }

    @Override
    protected Node newNode() {
        return new BarcodeElement();
    }
}
