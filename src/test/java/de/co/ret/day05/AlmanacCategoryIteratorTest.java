package de.co.ret.day05;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

class AlmanacCategoryIteratorTest {
    @Test
    void shoud_map_correctly_in_range() {
        var source = LongStream.rangeClosed(0, 6).iterator();
        var iterator = new AlmanacCategoryIterator(source, new AlmanacCategory("name", List.of(
                new AlmanacEntry(10, 0, 6)
        )));

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(10);
        assertThat(iterator.next()).isEqualTo(11);
        assertThat(iterator.next()).isEqualTo(12);
        assertThat(iterator.next()).isEqualTo(13);
        assertThat(iterator.next()).isEqualTo(14);
        assertThat(iterator.next()).isEqualTo(15);
        assertThat(iterator.next()).isEqualTo(6);
    }

    @Test
    void shoud_map_correctly_in_multiple_ranges() {
        var source = LongStream.rangeClosed(0, 15).iterator();
        var iterator = new AlmanacCategoryIterator(source, new AlmanacCategory("name", List.of(
                new AlmanacEntry(10, 3, 3),
                new AlmanacEntry(90, 7, 2),
                new AlmanacEntry(5, 10, 2)
        )));

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(0);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(10);
        assertThat(iterator.next()).isEqualTo(11);
        assertThat(iterator.next()).isEqualTo(12);
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.next()).isEqualTo(90);
        assertThat(iterator.next()).isEqualTo(91);
        assertThat(iterator.next()).isEqualTo(9);
        assertThat(iterator.next()).isEqualTo(5);
        assertThat(iterator.next()).isEqualTo(6);
        assertThat(iterator.next()).isEqualTo(12);
        assertThat(iterator.next()).isEqualTo(13);
        assertThat(iterator.next()).isEqualTo(14);
        assertThat(iterator.next()).isEqualTo(15);
    }

    @Test
    void shoud_map_correctly_in_multiple_ranges_sterting_in_the_middle() {
        var source = LongStream.rangeClosed(8, 9).iterator();
        var iterator = new AlmanacCategoryIterator(source, new AlmanacCategory("name", List.of(
                new AlmanacEntry(10, 3, 3),
                new AlmanacEntry(90, 7, 2),
                new AlmanacEntry(5, 10, 2)
        )));

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(91);
        assertThat(iterator.next()).isEqualTo(9);
    }
}