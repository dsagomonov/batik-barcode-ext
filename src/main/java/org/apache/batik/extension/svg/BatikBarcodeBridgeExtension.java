package org.apache.batik.extension.svg;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.BridgeExtension;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public final class BatikBarcodeBridgeExtension implements BridgeExtension, BatikBarcodeConstants {

    @Override
    public float getPriority() {
        return 1.0f;
    }

    @Override
    public Iterator getImplementedExtensions() {
        return Collections.unmodifiableList(Arrays.asList("http://xml.apache.org/batik/ext/barcode/1.0")).iterator();
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
    public void registerTags(BridgeContext ctx) {
        ctx.putBridge(new BarcodeElementBridge());
    }

    @Override
    public boolean isDynamicElement(Element e) {
        return false;
    }
}
