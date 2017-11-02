import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import org.junit.Test;

import java.util.List;

import hu.fps.lint.ColorDetector;
import hu.fps.lint.IssueRegistry;

public class ColorDetectorTest extends LintDetectorTest {

    @Override
    protected Detector getDetector() {
        return new ColorDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return new IssueRegistry().getIssues();
    }

    @Test
    public void testShouldDetectNoWarning() throws Exception {
        lint().files(
                manifest("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                        "    package=\"hu.fps.list\" >\n" +
                        "\n" +
                        "    <application\n" +
                        "        android:allowBackup=\"true\"\n" +
                        "        android:icon=\"@mipmap/ic_launcher\"\n" +
                        "        android:label=\"@string/app_name\"\n" +
                        "        android:theme=\"@style/AppTheme\" >\n" +
                        "        <activity\n" +
                        "            android:name=\".MainActivity\"\n" +
                        "            android:label=\"@string/app_name\" >\n" +
                        "            <intent-filter>\n" +
                        "                <action android:name=\"android.intent.action.MAIN\" />\n" +
                        "\n" +
                        "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
                        "            </intent-filter>\n" +
                        "        </activity>\n" +
                        "    </application>\n" +
                        "\n" +
                        "</manifest>")
        ).allowMissingSdk().run().expectClean();
    }

    @Test
    public void testShouldDetectWarning() throws Exception {
        lint().files(
                manifest("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                        "    package=\"hu.fps.list\" >\n" +
                        "\n" +
                        "    <application\n" +
                        "        android:allowBackup=\"true\"\n" +
                        "        android:icon=\"@android:color/white\"\n" +
                        "        android:label=\"@string/app_name\"\n" +
                        "        android:theme=\"@style/AppTheme\" >\n" +
                        "        <activity\n" +
                        "            android:name=\".MainActivity\"\n" +
                        "            android:label=\"@string/app_name\" >\n" +
                        "            <intent-filter>\n" +
                        "                <action android:name=\"android.intent.action.MAIN\" />\n" +
                        "\n" +
                        "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
                        "            </intent-filter>\n" +
                        "        </activity>\n" +
                        "    </application>\n" +
                        "\n" +
                        "</manifest>")
        ).allowMissingSdk().run().expectErrorCount(1).expect("AndroidManifest.xml:6:" +
                        " Error: Using android color resources are not recommended." +
                        " Manufacturers are overriding them with other colors. []\n" +
"        android:icon=\"@android:color/white\"\n" +
"        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "1 errors, 0 warnings\n");
    }
}
