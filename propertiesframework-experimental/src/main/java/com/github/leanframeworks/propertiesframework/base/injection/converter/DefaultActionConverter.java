/*-
 * #%L
 * PropertiesFramework :: Experiments
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

package com.github.leanframeworks.propertiesframework.base.injection.converter;

import com.github.leanframeworks.propertiesframework.base.action.RunnableWrapper;
import com.github.leanframeworks.propertiesframework.base.injection.annotation.ExposeA;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class DefaultActionConverter extends AbstractConverter {

    public DefaultActionConverter() {
        supportedExposures.put(ExposeA.class, Stream.of(Runnable.class).collect(toSet()));
        supportedConversions.put(Runnable.class, Stream.of(Runnable.class).collect(toSet()));
    }

    @Override
    public <T> T convert(Object from, Class<T> to) {
        Object result = null;

        if ((from instanceof Runnable) && to.isAssignableFrom(Runnable.class)) {
            result = new RunnableWrapper((Runnable) from);
        }

        return (T) result;
    }
}
