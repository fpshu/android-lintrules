import com.android.tools.lint.detector.api.Issue;

import hu.fps.lint.ShapeBackgroundDetector;
import org.junit.Test;

import java.util.List;

import hu.fps.lint.ColorDetector;
import hu.fps.lint.IssueRegistry;

import static com.google.common.truth.Truth.assertThat;

public class IssueRegistryTest {

    @Test
    public void testGetIssues() {
        IssueRegistry customIssueRegistry = new IssueRegistry();
        List<Issue> actual = customIssueRegistry.getIssues();
        assertThat(actual).containsExactly(
                ColorDetector.ISSUE,
                ShapeBackgroundDetector.ISSUE
        );
    }

}
