/*-
 * #%L
 * PropertiesFramework :: Core
 * %%
 * Copyright (C) 2017 LeanFrameworks
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package com.github.leanframeworks.propertiesframework.test;

import com.github.leanframeworks.propertiesframework.api.property.ListPropertyChange;
import com.github.leanframeworks.propertiesframework.base.utils.ValueUtils;
import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

import static com.github.leanframeworks.propertiesframework.test.TestUtils.haveEqualElements;

public class ListPropertyChangeMatcher<T> extends ArgumentMatcher<ListPropertyChange<T>> {

    private final ListPropertyChange<T> refEvent;

    public ListPropertyChangeMatcher(ListPropertyChange<T> refEvent) {
        super();
        this.refEvent = refEvent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean matches(Object actualEvent) {
        boolean match = false;

        if (actualEvent instanceof ListPropertyChange<?>) {
            match = ValueUtils.areEqual(refEvent.getSource(), ((ListPropertyChange) actualEvent).getSource()) &&
                    (refEvent.getStartIndex() == ((ListPropertyChange) actualEvent).getStartIndex()) &&
                    haveEqualElements(refEvent.getOldValues(), ((ListPropertyChange) actualEvent).getOldValues()) &&
                    haveEqualElements(refEvent.getNewValues(), ((ListPropertyChange) actualEvent).getNewValues());
        }

        return match;
    }

    @Override
    public void describeTo(Description description) {
        // Do nothing
    }
}
