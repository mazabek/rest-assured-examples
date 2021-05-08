package com.testinglaboratory.testingbasics.examples;

import org.assertj.core.api.WithAssertions;
import org.assertj.examples.AbstractAssertionsExamples;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.examples.data.Race.HOBBIT;

public class AssertJGoodness extends AbstractAssertionsExamples implements WithAssertions {

    @Test
    public void withAssertions_examples() {
        assertThat(frodo.age).isEqualTo(33);
        assertThat(frodo.getName()).isEqualTo("Frodo").isNotEqualTo("Frodon");

        assertThat(frodo).isIn(fellowshipOfTheRing);
        assertThat(frodo).isIn(sam, frodo, pippin);
        assertThat(sauron).isNotIn(fellowshipOfTheRing);

        assertThat(frodo).matches(p -> p.age > 30 && p.getRace() == HOBBIT);
        assertThat(frodo.age).matches(p -> p > 30);
    }

    @Test
    public void BDD_style() {

        // then methods come from BDDAssertions.then static
        then(frodo.age).isEqualTo(33);
        then(frodo.getName()).isEqualTo("Frodo").isNotEqualTo("Frodon");

        then(frodo).isIn(fellowshipOfTheRing);
        then(frodo).isIn(sam, frodo, pippin);
        then(sauron).isNotIn(fellowshipOfTheRing);

        then(frodo).matches(p -> p.age > 30 && p.getRace() == HOBBIT);
        then(frodo.age).matches(p -> p > 30);
    }
}
