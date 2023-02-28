package org.apache.batik.extension.svg;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeTest {


    static String read(BufferedImage image) throws NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Result result = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }

    @Test
    public void testSvg() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<svg xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "     xmlns:bc=\"http://xml.apache.org/batik/ext\"\n" +
                "     color=\"black\" font-family=\"PT Mono\" height=\"192px\" viewBox=\"0 0 336 192\"\n" +
                "     width=\"336px\">\n" +
                "    <g>\n" +
                "        <bc:barcode cx=\"50\" cy=\"30\" format=\"code-128\" width=\"2\" height=\"56\">900000155030</bc:barcode>\n" +
                "        <text font-size=\"12pt\" font-weight=\"bold\" x=\"192\" y=\"96\"/>\n" +
                "        <text font-size=\"14pt\" font-weight=\"bold\" x=\"182\" y=\"120\"><![CDATA[900000155]]></text>\n" +
                "        <text font-size=\"14pt\" font-weight=\"bold\" x=\"294\" y=\"120\"><![CDATA[030]]></text>\n" +
                "        <text font-size=\"14pt\" font-weight=\"bold\" x=\"182\" y=\"142\"><![CDATA[M]]></text>\n" +
                "        <text font-size=\"14pt\" font-weight=\"bold\" x=\"206\" y=\"142\"><![CDATA[J Smith]]></text>\n" +
                "    </g>\n" +
                "</svg>";
        BufferedImage image = new SVG2ImageFunction().apply(xml);
        ImageIO.write(image, "png", new File("target", "test" + ".png"));
        Assertions.assertEquals("900000155030", read(image));
    }
}
