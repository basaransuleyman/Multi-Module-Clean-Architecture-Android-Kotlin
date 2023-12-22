package com.productapp.lint.naming

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import java.util.EnumSet

class SourcesOfRules {
    companion object {
        val ISSUE_NAMING_CONVENTION = Issue.create(
            id = "NamingConventionViolation",
            briefDescription = "Classes should end with 'Activity' or 'Fragment'",
            explanation = "This app's coding standards require that Activity and Fragment classes, must end with 'Activity' or 'Fragment'.",
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.ERROR,
            implementation = Implementation(
                NamingRulesConventionDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
            )
        )
    }

}