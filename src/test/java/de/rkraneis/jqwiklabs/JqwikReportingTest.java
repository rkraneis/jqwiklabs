package de.rkraneis.jqwiklabs;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import static net.jqwik.api.Reporting.FALSIFIED;
import net.jqwik.api.Statistics;
import net.jqwik.api.constraints.Positive;

public class JqwikReportingTest {

    @Property
    @Report(FALSIFIED)
    @Label("Test 1: boolean")
    boolean Test_1_boolean(@ForAll @Positive int i) {
        Statistics.collect(i % 2 == 0 ? "positive" : "negative");
        return i < 5;
    }

    @Property
    @Report(FALSIFIED)
    @Label("Test 2a: assert")
    void Test_2a_assert(@ForAll @Positive int i) {
        Statistics.collect(i % 2 == 0 ? "positive" : "negative");
        assert i < 5;
    }

    @Property
    @Report(FALSIFIED)
    @Label("Test 2b: AssertionError")
    void Test_2b_AssertionError(@ForAll @Positive int i) {
        Statistics.collect(i % 2 == 0 ? "positive" : "negative");
        if (!(i < 5)) {
            throw new AssertionError();
        }
    }

    @Property
    @Report(FALSIFIED)
    @Label("Test 3: org.junit.Assert")
    void Test_3_JunitAssert(@ForAll @Positive int i) {
        Statistics.collect(i % 2 == 0 ? "positive" : "negative");
        org.junit.jupiter.api.Assertions.assertTrue(i < 5);
    }

    @Property
    @Report(FALSIFIED)
    @Label("Test 4a: org.assertj.core.api.Assertions")
    void Test_4a_AssertjAssert(@ForAll @Positive int i) {
        Statistics.collect(i % 2 == 0 ? "positive" : "negative");
        org.assertj.core.api.Assertions.assertThat(i).isLessThan(5);
    }

    @Property
    @Report(FALSIFIED)
    @Label("Test 4b: org.assertj.core.api.SoftAssert")
    void Test_4b_AssertjSoftAssert(@ForAll @Positive int i) {
        Statistics.collect(i % 2 == 0 ? "positive" : "negative");
        org.assertj.core.api.SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(i).isLessThan(5);
        });
    }
}
