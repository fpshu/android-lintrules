package hu.fps.lint;

import com.android.SdkConstants;
import com.android.tools.lint.detector.api.*;
import org.w3c.dom.Attr;

import java.util.Collection;

public class ColorDetector extends ResourceXmlDetector {

    /**
     * Attribute for overriding a resource
     */

    @SuppressWarnings("unchecked")
    private static final Implementation IMPLEMENTATION = new Implementation(
            ColorDetector.class,
            Scope.MANIFEST_AND_RESOURCE_SCOPE,
            Scope.MANIFEST_SCOPE, Scope.RESOURCE_FILE_SCOPE);
    /**
     * The main issue discovered by this detector
     */
    public static final Issue ISSUE = Issue.create(
            "AndroidColorDetector",
            "Using Android color resources",
            "Android color resources like android:color/white should not be used." +
                    " Manufacturers tend to overwrite them.",
            Category.CORRECTNESS,
            7,
            Severity.ERROR,
            IMPLEMENTATION);

    /**
     * Constructs a new detector
     */
    public ColorDetector() {
    }

    @Override
    public Collection<String> getApplicableAttributes() {
        return ALL;
    }

    /**
     * Not called:
     * ALL_RESOURCE
     * RESOURCE
     * <p>
     * Called:
     * MANIFEST
     */
    @Override
    public void visitAttribute(XmlContext context, Attr attribute) {
        if (attribute.getValue().contains(SdkConstants.ANDROID_COLOR_RESOURCE_PREFIX)) {
            context.report(ISSUE, attribute, context.getLocation(attribute), "Using Android color resources are not recommended. Manufacturers are overriding them with other colors.");
        }
    }
}
