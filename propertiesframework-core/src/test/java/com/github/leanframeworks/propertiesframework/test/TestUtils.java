/*
 * Copyright (c) 2017, LeanFrameworks
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.leanframeworks.propertiesframework.test;

import com.github.leanframeworks.propertiesframework.api.property.ListPropertyChange;
import com.github.leanframeworks.propertiesframework.api.property.PropertyChange;
import com.github.leanframeworks.propertiesframework.api.property.SetPropertyChange;
import com.github.leanframeworks.propertiesframework.base.utils.ValueUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.argThat;

/**
 * Test utilities provided for convenience.
 */
public final class TestUtils {

    /**
     * Private constructor for utility class.
     */
    private TestUtils() {
        // Nothing to be done
    }

    /**
     * Returns a property change event that can be used when verifying mocks.
     *
     * @param ref Property change event to compare to.
     * @param <T> Type of items in the corresponding property.
     * @return Property change event to be used when verifying mocks.
     */
    public static <T> PropertyChange<T> matches(PropertyChange<T> ref) {
        return argThat(new PropertyChangeMatcher<>(ref));
    }

    /**
     * Returns a set property change event that can be used when verifying mocks.
     *
     * @param ref Set property change event to compare to.
     * @param <T> Type of items in the corresponding set property.
     * @return Set property change event to be used when verifying mocks.
     */
    public static <T> SetPropertyChange<T> matches(SetPropertyChange<T> ref) {
        return argThat(new SetPropertyChangeMatcher<>(ref));
    }

    /**
     * Returns a list property change event that can be used when verifying mocks.
     *
     * @param ref List property change event to compare to.
     * @param <T> Type of items in the corresponding list property.
     * @return List property change event to be used when verifying mocks.
     */
    public static <T> ListPropertyChange<T> matches(ListPropertyChange<T> ref) {
        return argThat(new ListPropertyChangeMatcher<>(ref));
    }

    /**
     * Returns a set that can be used when verifying mocks.
     *
     * @param ref Set to compare to.
     * @param <T> Type of items in the set.
     * @return Set to be used when verifying mocks.
     */
    public static <T> Set<T> matches(Set<T> ref) {
        return argThat(new SetMatcher<>(ref));
    }

    /**
     * Returns a list that can be used when verifying mocks.
     *
     * @param ref List to compare to.
     * @param <T> Type of items in the list.
     * @return List to be used when verifying mocks.
     */
    public static <T> List<T> matches(List<T> ref) {
        return argThat(new ListMatcher<>(ref));
    }

    /**
     * Returns true if both lists contain the same elements in the same order, false otherwise.
     *
     * @param first  First list to compare the elements of.
     * @param second Second list to compare the elements of.
     * @param <T>    Type of items in the lists.
     * @return True if both lists contain the same elements and in the same order, false otherwise.
     */
    public static <T> boolean haveEqualElements(List<T> first, List<T> second) {
        boolean match = false;

        // First, check size
        if (first.size() == second.size()) {

            // Then, check each element
            match = true;
            for (int i = 0; (i < first.size()) && match; i++) {
                match = first.get(i).equals(second.get(i));
            }
        }

        return match;
    }

    /**
     * Returns true if both sets contain the same elements, false otherwise.
     *
     * @param first  First set to compare the elements of.
     * @param second Second set to compare the elements of.
     * @param <T>    Type of items in the sets.
     * @return True if both sets contain the same elements, false otherwise.
     */
    public static <T> boolean haveEqualElements(Set<T> first, Set<T> second) {
        return haveEqualElements(first, second, false);
    }

    /**
     * Returns true if both collections contain the same elements, false otherwise.
     *
     * @param first  First collection to compare the elements of.
     * @param second Second collection to compare the elements of.
     * @return True if both collections contain the same elements, false otherwise.
     */
    public static boolean haveEqualElements(Collection<?> first, Collection<?> second, boolean sameOrder) {
        boolean match = false;

        // First, check size
        if (first.size() == second.size()) {

            // Then, check each element
            match = true;
            Iterator<?> firstIterator = first.iterator();

            if (sameOrder) {
                Iterator<?> secondIterator = second.iterator();
                while (firstIterator.hasNext() && match) {
                    match = ValueUtils.areEqual(firstIterator.next(), secondIterator.next());
                }
            } else {
                while (firstIterator.hasNext() && match) {
                    match = second.contains(firstIterator.next());
                }
            }
        }

        return match;
    }
}
