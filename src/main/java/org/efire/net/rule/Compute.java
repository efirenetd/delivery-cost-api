package org.efire.net.rule;

import org.efire.net.model.Item;

import java.util.function.Consumer;

@FunctionalInterface
public interface Compute extends Consumer<Item> {
}
