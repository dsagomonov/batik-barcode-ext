package org.apache.batik.extension.svg;

import lombok.NoArgsConstructor;
import org.apache.batik.dom.AbstractDocument;
import org.apache.batik.dom.DomExtension;
import org.apache.batik.dom.ExtensibleDOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class BatikBarcodeDomExtension implements DomExtension, BatikExtConstants, BatikBarcodeConstants {
    @Override
    public float getPriority() {
        return 1.0f;
    }

    @Override
    public String getAuthor() {
        return AUTHOR;
    }

    @Override
    public String getContactAddress() {
        return CONTACT_ADDRESS;
    }

    @Override
    public String getURL() {
        return "http://xml.apache.org/batik";
    }

    @Override
    public String getDescription() {
        return BATIK_BARCODE_DESCRIPTION;
    }

    @Override
    public void registerTags(ExtensibleDOMImplementation di) {
        di.registerCustomElementFactory(BATIK_EXT_NAMESPACE_URI,
                "barcode",
                new BatikBarcodeElementFactory());
    }

    @NoArgsConstructor
    protected static final class BatikBarcodeElementFactory
            implements ExtensibleDOMImplementation.ElementFactory {

        @Override
        public Element create(String prefix, Document doc) {
            return new BarcodeElement(prefix, (AbstractDocument) doc);
        }
    }
}
