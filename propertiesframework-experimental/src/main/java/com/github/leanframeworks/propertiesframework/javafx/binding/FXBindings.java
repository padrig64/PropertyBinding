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

package com.github.leanframeworks.propertiesframework.javafx.binding;

import com.github.leanframeworks.propertiesframework.api.property.ListValueChangeListener;
import com.github.leanframeworks.propertiesframework.api.property.ReadableListProperty;
import com.github.leanframeworks.propertiesframework.api.property.ReadableProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public final class FXBindings {

    /**
     * Private constructor for utility class.
     */
    private FXBindings() {
        // Nothing to be done
    }

    public static <R> ObservableValue<R> fx(ReadableProperty<R> vfProperty) {
        final SimpleObjectProperty<R> fxObservableValue = new SimpleObjectProperty<R>();
        vfProperty.addValueChangeListener((p, o, n) -> fxObservableValue.set(n));
        fxObservableValue.set(vfProperty.getValue());
        return fxObservableValue;
    }

    public static <R> ObservableList<R> fx(ReadableListProperty<R> vfListProperty) {
        final ObservableList<R> fxObservableList = FXCollections.observableArrayList();

        vfListProperty.addValueChangeListener(new ListValueChangeListener<R>() {

            @Override
            public void valuesAdded(ReadableListProperty<? extends R> p, int i, List<? extends R> n) {
                int offset = 0;
                for (R item : n) {
                    fxObservableList.add(i + offset++, item);
                }
            }

            @Override
            public void valuesChanged(ReadableListProperty<? extends R> p, int i, List<? extends R> o, List<? extends R> n) {
                int offset = 0;
                for (R item : n) {
                    fxObservableList.set(i + offset++, item);
                }
            }

            @Override
            public void valuesRemoved(ReadableListProperty<? extends R> p, int i, List<? extends R> o) {
                for (int j = 0; j < o.size(); j++) {
                    fxObservableList.remove(i);
                }
            }
        });
        fxObservableList.addAll(vfListProperty.asUnmodifiableList());

        return fxObservableList;
    }
}
