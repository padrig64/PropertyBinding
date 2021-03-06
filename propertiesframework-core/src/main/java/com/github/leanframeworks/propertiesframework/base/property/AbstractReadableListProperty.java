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

import com.github.leanframeworks.propertiesframework.api.common.Disposable;
import com.github.leanframeworks.propertiesframework.api.property.ListPropertyChange;
import com.github.leanframeworks.propertiesframework.api.property.ListPropertyChangeListener;
import com.github.leanframeworks.propertiesframework.api.property.ReadableListProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Abstract implementation of a {@link ReadableListProperty}.
 *
 * @param <R> Type of values that can be read from this list.
 */
public abstract class AbstractReadableListProperty<R> implements ReadableListProperty<R>, Disposable {

    /**
     * Listeners to changes in the list property.
     */
    private final List<ListPropertyChangeListener<? super R>> listeners = new ArrayList<>();

    /**
     * Constructor adding no listener.
     */
    public AbstractReadableListProperty() {
        // Nothing to be done
    }

    /**
     * Constructor adding the specified listeners.
     *
     * @param listeners Listeners to be added.
     */
    @SafeVarargs
    public AbstractReadableListProperty(ListPropertyChangeListener<? super R>... listeners) {
        for (ListPropertyChangeListener<? super R> listener : listeners) {
            addChangeListener(listener);
        }
    }

    /**
     * Disposes this readable list property by removing any references to any listener.
     * <p>
     * Sub-classes should call the dispose() method of their parent class.
     * <p>
     * Note that the listeners will not be disposed.
     *
     * @see Disposable#dispose()
     */
    @Override
    public void dispose() {
        listeners.clear();
    }

    /**
     * Gets the registered list item change listeners.
     * <p>
     * Note that the returned collection is not modifiable.
     *
     * @return List item change listeners.
     */
    public Collection<ListPropertyChangeListener<? super R>> getChangeListeners() {
        return Collections.unmodifiableList(listeners);
    }

    /**
     * @see ReadableListProperty#addChangeListener(ListPropertyChangeListener)
     */
    @Override
    public void addChangeListener(ListPropertyChangeListener<? super R> listener) {
        listeners.add(listener);
    }

    /**
     * @see ReadableListProperty#removeChangeListener(ListPropertyChangeListener)
     */
    @Override
    public void removeChangeListener(ListPropertyChangeListener<? super R> listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies the change listeners that items have been added.
     * <p>
     * Note that the specified list of items will be wrapped in an unmodifiable list before being passed to the
     * listeners.
     *
     * @param startIndex Index of the first added item.
     * @param newItems   Newly added items.
     */
    protected void doNotifyListenersOfAddedValues(int startIndex, List<? extends R> newItems) {
        doNotifyListeners(new ListPropertyChange<>(this, startIndex, null, newItems));
    }

    /**
     * Notifies the change listeners that items have been replaced.
     * <p>
     * Note that the specified lists of items will be wrapped in unmodifiable lists before being passed to the
     * listeners.
     *
     * @param startIndex Index of the first replaced item.
     * @param oldItems   Previous items.
     * @param newItems   New items.
     */
    protected void doNotifyListenersOfChangedValues(int startIndex,
                                                    List<? extends R> oldItems,
                                                    List<? extends R> newItems) {
        doNotifyListeners(new ListPropertyChange<>(this, startIndex, oldItems, newItems));
    }

    /**
     * Notifies the change listeners that items have been removed.
     * <p>
     * Note that the specified list of items will be wrapped in an unmodifiable list before being passed to the
     * listeners.
     *
     * @param startIndex Index of the first removed item.
     * @param oldItems   Removed items.
     */
    protected void doNotifyListenersOfRemovedValues(int startIndex, List<? extends R> oldItems) {
        doNotifyListeners(new ListPropertyChange<>(this, startIndex, oldItems, null));
    }

    /**
     * Notifies the change listeners that items have been added, replaced or removed.
     * <p>
     * Note that the specified lists of items in the event should be wrapped in unmodifiable lists before being passed
     * to the listeners.
     *
     * @param event Event to be passed to the listeners.
     */
    protected void doNotifyListeners(ListPropertyChange<? extends R> event) {
        List<ListPropertyChangeListener<? super R>> listenersCopy = new ArrayList<>(listeners);
        for (ListPropertyChangeListener<? super R> listener : listenersCopy) {
            listener.listPropertyChanged(event);
        }
    }
}
