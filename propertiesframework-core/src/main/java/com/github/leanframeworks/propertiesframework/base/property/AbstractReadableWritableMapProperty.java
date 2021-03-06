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

package com.github.leanframeworks.propertiesframework.base.property;

import com.github.leanframeworks.propertiesframework.api.property.MapPropertyChangeListener;
import com.github.leanframeworks.propertiesframework.api.property.ReadableWritableMapProperty;

/**
 * Abstract implementation of a {@link ReadableWritableMapProperty}.
 *
 * @param <K> Type of keys maintained by this map property.
 * @param <V> Type of mapped values that can be read from and written to this map property.
 */
public abstract class AbstractReadableWritableMapProperty<K, V> extends AbstractReadableMapProperty<K, V>
        implements ReadableWritableMapProperty<K, V> {

    /**
     * {@inheritDoc}
     *
     * @see AbstractReadableWritableMapProperty#AbstractReadableWritableMapProperty()
     */
    public AbstractReadableWritableMapProperty() {
        super();
    }

    /**
     * {@inheritDoc}
     *
     * @see AbstractReadableWritableMapProperty#AbstractReadableWritableMapProperty(MapPropertyChangeListener[])
     */
    @SafeVarargs
    public AbstractReadableWritableMapProperty(MapPropertyChangeListener<? super K, ? super V>... listeners) {
        super(listeners);
    }
}
