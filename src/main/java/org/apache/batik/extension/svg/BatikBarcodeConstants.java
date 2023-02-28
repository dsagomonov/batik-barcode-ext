package org.apache.batik.extension.svg;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.oned.CodaBarWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.Code93Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.oned.OneDimensionalCodeWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Constants.
 */
@SuppressWarnings("checkstyle:InterfaceIsType")
public interface BatikBarcodeConstants {
    /**
     * Description.
     */
    String BATIK_BARCODE_DESCRIPTION = "Batik barcode extension";

    /**
     * Contact address.
     */
    String CONTACT_ADDRESS = "darius.sagomonov@gmail.com";

    /**
     * Author.
     */
    String AUTHOR = "Darius Sagomonov";

    /**
     * Coder supplier map.
     */
    Map<BarcodeFormat, Supplier<OneDimensionalCodeWriter>> ONE_DIMENSIONAL_WRITER_MAP =
            new HashMap<BarcodeFormat, Supplier<OneDimensionalCodeWriter>>() {{
                put(BarcodeFormat.CODABAR, CodaBarWriter::new);
                put(BarcodeFormat.CODE_39, Code39Writer::new);
                put(BarcodeFormat.CODE_128, Code128Writer::new);
                put(BarcodeFormat.CODE_93, Code93Writer::new);
                put(BarcodeFormat.EAN_8, EAN8Writer::new);
                put(BarcodeFormat.EAN_13, EAN13Writer::new);
                put(BarcodeFormat.ITF, ITFWriter::new);
            }};
}
