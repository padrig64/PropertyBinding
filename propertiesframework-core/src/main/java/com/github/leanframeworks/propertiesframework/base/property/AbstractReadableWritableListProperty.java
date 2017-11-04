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

package com.github.leanframeworks.propertiesframework.base.property;

import com.github.leanframeworks.propertiesframework.api.property.ListValueChangeListener;
import com.github.leanframeworks.propertiesframework.api.property.ReadableWritableListProperty;

/**
 * Abstract implementation of a {@link ReadableWritableListProperty}.
 *
 * @param <T> Type of values that can be read from and written to this list property.
 */
public abstract class AbstractReadableWritableListProperty<T> extends AbstractReadableListProperty<T>
        implements ReadableWritableListProperty<T> {

    /**
     * {@inheritDoc}
     *
     * @see AbstractReadableWritableListProperty#AbstractReadableWritableListProperty()
     */
    public AbstractReadableWritableListProperty() {
        super();
    }

    /**
     * {@inheritDoc}
     *
     * @see AbstractReadableWritableListProperty#AbstractReadableWritableListProperty(ListValueChangeListener[])
     */
    public AbstractReadableWritableListProperty(ListValueChangeListener<? super T>... listeners) {
        super(listeners);
    }
}
