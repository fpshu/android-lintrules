package hu.fps.lintrules;

import com.android.tools.lint.detector.api.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Collection;
import java.util.Collections;

public class ShapeBackgroundDetector extends ResourceXmlDetector {

    @SuppressWarnings("unchecked")
    private static final Implementation IMPLEMENTATION = new Implementation(
            ShapeBackgroundDetector.class,
            Scope.ALL_RESOURCES_SCOPE, Scope.ALL_RESOURCES_SCOPE);

    public static final Issue ISSUE = Issue.create(
            "ShapeBackgroundDetector",
            "all shapes should have background",
            "All shape drawable should contain solid background." +
                    " Otherwise manufacturer default will be used. That can be black.",
            Category.CORRECTNESS,
            7,
            Severity.ERROR,
            IMPLEMENTATION);

    public ShapeBackgroundDetector() {
    }

    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList("shape");
    }

    @Override
    public void visitElement(XmlContext context, Element element) {
        if (element.getTagName().equals("shape")) {
            NodeList nodeList = element.getChildNodes();
            boolean hasBackground = false;
            boolean isGradient = false;
            for (int i = 0, size = nodeList.getLength(); i < size; i++) {
                String nodeName = nodeList.item(i).getNodeName();
                if ("solid".equals(nodeName)) {
                    hasBackground = true;
                    break;
                } else if ("gradient".equals(nodeName)) {
                    isGradient = true;
                    break;
                }
            }
            if (!isGradient && !hasBackground) {
                context.report(ISSUE, element, context.getLocation(element),
                        "Shapes should have backgrounds otherwise manufacturer default will be used." +
                                " Set a transparent solid color.");
            }
        }
    }
}
