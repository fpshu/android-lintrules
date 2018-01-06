import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import hu.fps.lintrules.IssueRegistry;
import hu.fps.lintrules.ShapeBackgroundDetector;

import java.util.List;

public class ShapeBackgroundDetectorTest extends LintDetectorTest {

    @Override
    protected Detector getDetector() {
        return new ShapeBackgroundDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return new IssueRegistry().getIssues();
    }

    public void testShouldDetectNoWarning() {
        lint().files(
                xml("res/drawable/testShape.xml",
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                                "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                                "<solid android:color=\"@color/gray_style_14\" />" +
                                "</shape>")
        ).allowMissingSdk().run().expectClean();
    }

    public void testShouldDetectNoWarningWithGradient() {
        lint().files(
                xml("res/drawable/testShape.xml",
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                                "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                                "<gradient\n" +
                                "        android:angle=\"90\"\n" +
                                "        android:endColor=\"#0D000000\"\n" +
                                "        android:startColor=\"@color/transparent\" />\n" +
                                "</shape>")
        ).allowMissingSdk().run().expectClean();
    }

    public void testShouldDetectWarning() {
        lint().files(
                xml("res/drawable/testShape.xml",
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                                "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                                "</shape>")
        ).allowMissingSdk().run().expectErrorCount(1).expect("res/drawable/testShape.xml:2: Error: Shapes should have backgrounds otherwise manufacturer default will be used. Set a transparent solid color. [ShapeBackgroundDetector]\n" +
                "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                "^\n" +
                "1 errors, 0 warnings");
    }
}
