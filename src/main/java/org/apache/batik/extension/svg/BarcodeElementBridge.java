package org.apache.batik.extension.svg;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.OneDimensionalCodeWriter;
import lombok.NoArgsConstructor;
import org.apache.batik.bridge.Bridge;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.BridgeException;
import org.apache.batik.bridge.SVGDecoratedShapeElementBridge;
import org.apache.batik.gvt.ShapeNode;
import org.apache.batik.parser.UnitProcessor;
import org.w3c.dom.Element;

import java.awt.geom.GeneralPath;
import java.util.Collections;
import java.util.function.Supplier;

@NoArgsConstructor
public final class BarcodeElementBridge extends SVGDecoratedShapeElementBridge implements BatikExtConstants {

    @Override
    public String getNamespaceURI() {
        return BATIK_EXT_NAMESPACE_URI;
    }

    @Override
    public String getLocalName() {
        return "barcode";
    }

    @Override
    public Bridge getInstance() {
        return new BarcodeElementBridge();
    }


    @Override
    protected void buildShape(BridgeContext ctx, Element e, ShapeNode shapeNode) {
        UnitProcessor.Context uctx = org.apache.batik.bridge.UnitProcessor.createContext(ctx, e);
        String s = e.getAttributeNS((String) null, BATIK_EXT_X_ATTRIBUTE);
        float x = 0.0F;
        if (s.length() != 0) {
            x = org.apache.batik.bridge.UnitProcessor.svgHorizontalCoordinateToUserSpace(s, BATIK_EXT_X_ATTRIBUTE, uctx);
        }

        s = e.getAttributeNS((String) null, BATIK_EXT_Y_ATTRIBUTE);
        float y = 0.0F;
        if (s.length() != 0) {
            y = org.apache.batik.bridge.UnitProcessor.svgVerticalCoordinateToUserSpace(s, BATIK_EXT_Y_ATTRIBUTE, uctx);
        }

        float width = 1.0F;
        s = e.getAttributeNS((String) null, BATIK_EXT_WIDTH_ATTRIBUTE);
        if (s.length() != 0) {
            width = org.apache.batik.bridge.UnitProcessor.svgHorizontalCoordinateToUserSpace(s, BATIK_EXT_WIDTH_ATTRIBUTE, uctx);
        }

        float height = 20.0F;
        s = e.getAttributeNS((String) null, BATIK_EXT_HEIGHT_ATTRIBUTE);
        if (s.length() != 0) {
            height = org.apache.batik.bridge.UnitProcessor.svgVerticalCoordinateToUserSpace(s, BATIK_EXT_HEIGHT_ATTRIBUTE, uctx);
        }


        s = e.getAttributeNS((String) null, "format");
        if (s.length() == 0) {
            throw new BridgeException(ctx, e, "attribute.missing", new Object[]{"format", s});
        } else {
            BarcodeFormat format = BarcodeFormat.valueOf(s.replace("-", "_").toUpperCase());
            if (format == null) {
                throw new BridgeException(ctx, e, "unknown", new Object[]{"format", s});
            }
            Supplier<OneDimensionalCodeWriter> writerSupplier = BatikBarcodeConstants.ONE_DIMENSIONAL_WRITER_MAP.get(format);
            if (writerSupplier == null) {
                throw new BridgeException(ctx, e, "unknown", new Object[]{"format", s});
            }

            String input = e.getTextContent();
            if (input == null || input.isEmpty()) {
                throw new BridgeException(ctx, e, "unknown", new Object[]{"format", s});
            }
            BitMatrix matrix = writerSupplier.get().encode(input, format, 0, 0, Collections.emptyMap());
            BitArray bitArray = matrix.getRow(0, null);
            GeneralPath gp = new GeneralPath();
            float y2 = y + height;
            for (int i = 0; i < bitArray.getSize(); i++) {
                if (bitArray.get(i)) {
                    float x1 = x + i * width;
                    float x2 = x1 + width;
                    gp.moveTo(x1, y);
                    gp.lineTo(x2, y);
                    gp.lineTo(x2, y2);
                    gp.lineTo(x1, y2);
                    gp.closePath();
                }
            }

            shapeNode.setShape(gp);
        }
    }
}
