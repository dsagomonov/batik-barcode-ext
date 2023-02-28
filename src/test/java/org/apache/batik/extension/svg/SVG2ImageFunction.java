package org.apache.batik.extension.svg;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.ext.awt.image.GraphicsUtil;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.StringReader;
import java.util.function.Function;

public final class SVG2ImageFunction implements Function<String, BufferedImage> {

    @Override
    public BufferedImage apply(String svg) {

        if (svg != null && !svg.isEmpty()) {
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());

            try {
                StringReader reader = new StringReader(svg);
                SVGDocument document = (SVGDocument) f.createDocument(null, reader);

                UserAgent agent = new UserAgentAdapter();
                DocumentLoader loader = new DocumentLoader(agent);
                BridgeContext context = new BridgeContext(agent, loader);
                context.setDynamic(true);
                GraphicsNode root = new GVTBuilder().build(context, document);

                int w = (int) document.getRootElement().getWidth().getBaseVal().getValue();
                int h = (int) document.getRootElement().getHeight().getBaseVal().getValue();

                BufferedImage bufImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
                Graphics2D graphics = GraphicsUtil.createGraphics(bufImg);
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle(0, 0, bufImg.getWidth(), bufImg.getHeight()));
                root.paint(graphics);

                return bufImg;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
