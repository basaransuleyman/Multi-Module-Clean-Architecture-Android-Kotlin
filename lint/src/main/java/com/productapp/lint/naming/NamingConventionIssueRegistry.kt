package com.productapp.lint.naming

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.productapp.lint.naming.SourcesOfRules.Companion.ISSUE_NAMING_CONVENTION

class NamingConventionIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(ISSUE_NAMING_CONVENTION)
}