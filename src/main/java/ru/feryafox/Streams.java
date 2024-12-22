package ru.feryafox;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams<T> {

    private final List<T> elements;

    private Streams(List<T> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public static <T> Streams<? extends T> of(List<? extends T> list) {
        Objects.requireNonNull(list, "List cannot be null");
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "Predicate cannot be null");
        List<T> filtered = elements.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return new Streams<>(filtered);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> transformer) {
        Objects.requireNonNull(transformer, "Transformer cannot be null");
        List<R> transformed = elements.stream()
                .map(transformer)
                .collect(Collectors.toList());
        return new Streams<>(transformed);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends V> valueMapper) {
        Objects.requireNonNull(keyMapper, "Key mapper cannot be null");
        Objects.requireNonNull(valueMapper, "Value mapper cannot be null");
        return elements.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public List<T> toList() {
        return new ArrayList<>(elements);
    }

    public Set<T> toSet() {
        return new HashSet<>(elements);
    }

}
