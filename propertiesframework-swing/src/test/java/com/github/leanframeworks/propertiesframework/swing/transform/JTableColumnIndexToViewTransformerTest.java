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

package com.github.leanframeworks.propertiesframework.swing.transform;

import com.github.leanframeworks.propertiesframework.api.transform.Transformer;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import static org.junit.Assert.assertEquals;

/**
 * @see JTableColumnIndexToViewTransformer
 */
public class JTableColumnIndexToViewTransformerTest {

    private Transformer<Integer, Integer> transformer;

    @Before
    public void setUp() {
        // Create table and table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("One");
        tableModel.addColumn("Two");
        tableModel.addColumn("Three");

        JTable table = new JTable(tableModel);

        // Shuffle columns to: "Three", "One", Two"
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column = columnModel.getColumn(0);
        columnModel.removeColumn(column);
        columnModel.addColumn(column);
        column = columnModel.getColumn(0);
        columnModel.removeColumn(column);
        columnModel.addColumn(column);

        // Create transformer
        transformer = new JTableColumnIndexToViewTransformer(table);
    }

    @Test
    public void testWithinBounds() {
        assertEquals(Integer.valueOf(1), transformer.transform(0)); // "One"
        assertEquals(Integer.valueOf(2), transformer.transform(1)); // "Two"
        assertEquals(Integer.valueOf(0), transformer.transform(2)); // "Three"
    }
}
