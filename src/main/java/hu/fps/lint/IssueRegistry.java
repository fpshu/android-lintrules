package hu.fps.lint;

import com.android.tools.lint.detector.api.Issue;

import java.util.Collections;
import java.util.List;

public class IssueRegistry extends com.android.tools.lint.client.api.IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(ColorDetector.ISSUE);
    }
}
