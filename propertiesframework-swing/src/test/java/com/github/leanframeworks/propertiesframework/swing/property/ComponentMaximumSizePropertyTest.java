/*-
 * #%L
 * PropertiesFramework :: Swing Support
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

package com.github.leanframeworks.propertiesframework.swing.property;

import com.github.leanframeworks.propertiesframework.api.property.PropertyChange;
import com.github.leanframeworks.propertiesframework.api.property.PropertyChangeListener;
import com.github.leanframeworks.propertiesframework.api.property.ReadableWritableProperty;
import org.junit.Test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import static com.github.leanframeworks.propertiesframework.test.TestUtils.matches;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @see ComponentMaximumSizeProperty
 */
public class ComponentMaximumSizePropertyTest {

    @SuppressWarnings("unchecked")
    @Test
    public void testNonNullFromProperty() {
        Component component = new JLabel();

        ReadableWritableProperty<Dimension> property = new ComponentMaximumSizeProperty(component);
        PropertyChangeListener<Dimension> listenerMock = (PropertyChangeListener<Dimension>) mock(PropertyChangeListener.class);
        property.addChangeListener(listenerMock);

        assertEquals(new Dimension(0, 0), property.getValue());
        assertEquals(new Dimension(0, 0), component.getMaximumSize());
        property.setValue(new Dimension(11, 12));
        assertEquals(new Dimension(11, 12), component.getMaximumSize());

        // Check exactly one event fired
        verify(listenerMock).propertyChanged(matches(new PropertyChange<>(property, new Dimension(0, 0), new Dimension(11, 12))));
        verify(listenerMock).propertyChanged(any());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testNonNullFromComponent() {
        JFrame window = new JFrame();
        Container contentPane = new JPanel(null);
        window.setContentPane(contentPane);
        Component component = new JLabel();
        contentPane.add(component);

        ReadableWritableProperty<Dimension> property = new ComponentMaximumSizeProperty(component);
        PropertyChangeListener<Dimension> listenerMock = (PropertyChangeListener<Dimension>) mock(PropertyChangeListener.class);
        property.addChangeListener(listenerMock);

        assertEquals(new Dimension(0, 0), property.getValue());
        assertEquals(new Dimension(0, 0), component.getMaximumSize());
        component.setMaximumSize(new Dimension(13, 14));
        assertEquals(new Dimension(13, 14), property.getValue());

        // Check exactly one event fired
        verify(listenerMock).propertyChanged(matches(new PropertyChange<>(property, new Dimension(0, 0), new Dimension(13, 14))));
        verify(listenerMock).propertyChanged(any());
    }
}
