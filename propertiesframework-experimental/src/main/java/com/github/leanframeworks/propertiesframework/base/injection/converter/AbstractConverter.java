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

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractConverter implements Converter {

    /**
     * Key: ExposedXXX annotation
     * Value: Classes/interfaces of field needed to be exposed with this annotation (AND)
     */
    protected final Map<Class<? extends Annotation>, Set<Class<?>>> supportedExposures = new HashMap<>();

    /**
     * Key: Class of exposed field
     * Value: Possible classes of field that can receive the converted value (OR)
     */
    protected final Map<Class<?>, Set<Class<?>>> supportedConversions = new HashMap<>();

    @Override
    public boolean canExpose(Class<?> from, Class<? extends Annotation> as) {
        Set<Class<?>> expectedTypes = supportedExposures.get(as);
        return (expectedTypes != null) && expectedTypes.stream().allMatch(t -> t.isAssignableFrom(from));
    }

    @Override
    public boolean canConvert(Class<?> from, Class<?> to) {
        Set<Class<?>> tos = supportedConversions.get(from);
        return (tos != null) && tos.contains(to);
    }
}
