# Barcode rendering for Apache™ Batik

[Apache™ Batik](https://github.com/apache/xmlgraphics-batik) extensions for Barcode rendering.

## Usage
Use following maven coordinates and  [jitpack.io](https://jitpack.io/docs/) repository.
```xml
 <dependency>
    <groupId>com.github.dsagomonov</groupId>
    <artifactId>batik-barcode-ext</artifactId>
    <version>1.1</version>
 </dependency>
```

## Supported elements

Use `http://xml.apache.org/batik/ext` [namespace](https://developer.mozilla.org/en-US/docs/Web/SVG/Namespaces_Crash_Course) in your SVG-documents

### &lt;barcode&gt;

1-D barcode element

```xml
<?xml version="1.0" encoding="UTF-8"?>
<svg xmlns="http://www.w3.org/2000/svg"
     xmlns:bc="http://xml.apache.org/batik/ext"
     height="192px" viewBox="0 0 336 192"
     width="336px">
    <g>
        <bc:barcode x="50" y="30" format="code-128" width="2" height="56"><![CDATA[900000155030]]></bc:barcode>
    </g>
</svg>
```

#### Attribures
| Attribute name | Description | Default value |
| --- | --- |--- |
| [x](https://developer.mozilla.org/ru/docs/Web/SVG/Attribute/x) |The x coordinate of the barcode-rect | 0 |
| [y](https://developer.mozilla.org/ru/docs/Web/SVG/Attribute/y) |The y coordinate of the barcode-rect| 0 |
| width |The width of barcode line| 1 |
| [height](https://developer.mozilla.org/ru/docs/Web/SVG/Attribute/height) |The height of the barcode-rect| 20 |
|format| Barcode format | - |

Supported formats:
1) [codeabar](https://en.wikipedia.org/wiki/Codabar)
2) [code-39](https://en.wikipedia.org/wiki/Code_39)
3) [code-128](https://en.wikipedia.org/wiki/Code_128)
4) [code-93](https://en.wikipedia.org/wiki/Code_93)
5) [EAN-8](https://en.wikipedia.org/wiki/EAN-8)
6) [EAN-13](https://en.wikipedia.org/wiki/International_Article_Number#)
7) [ITF](https://en.wikipedia.org/wiki/Interleaved_2_of_5)

