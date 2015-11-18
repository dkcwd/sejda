/*
 * Copyright 2015 by Edi Weissmann (edi.weissmann@gmail.com)
 * 
 * This file is part of the Sejda source code
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.core.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sejda.TestUtils;
import org.sejda.core.context.DefaultSejdaContext;
import org.sejda.core.context.SejdaContext;
import org.sejda.model.TopLeftRectangularBox;
import org.sejda.model.exception.TaskException;
import org.sejda.model.input.PdfStreamSource;
import org.sejda.model.parameter.SplitByTextContentParameters;
import org.sejda.model.pdf.PdfVersion;
import org.sejda.model.task.Task;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public abstract class SplitByTextContentTaskTest extends PdfOutEnabledTest implements TestableTask<SplitByTextContentParameters> {

    private DefaultTaskExecutionService victim = new DefaultTaskExecutionService();

    private SejdaContext context = mock(DefaultSejdaContext.class);
    private SplitByTextContentParameters parameters;

    @Before
    public void setUp() {
        TestUtils.setProperty(victim, "context", context);
    }

    private void setUpParameters(TopLeftRectangularBox area) {
        parameters = new SplitByTextContentParameters(area);
        parameters.setCompress(true);
        parameters.setVersion(PdfVersion.VERSION_1_6);
        InputStream stream = getClass().getClassLoader().getResourceAsStream("pdf/split_by_text_contents_sample.pdf");
        PdfStreamSource source = PdfStreamSource.newInstanceNoPassword(stream, "test_file.pdf");
        parameters.setSource(source);
        parameters.setOverwrite(true);
    }

    @Test
    public void testExecute() throws TaskException, IOException {
        setUpParameters(new TopLeftRectangularBox(114, 70, 41, 15));
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(3);
    }

    @Test
    public void testNoTextFoundInAreas() throws TaskException, IOException {
        setUpParameters(new TopLeftRectangularBox(1, 1, 1, 1));
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(0);
    }

    @Test
    public void testFileOutputNaming() throws TaskException, IOException {
        setUpParameters(new TopLeftRectangularBox(70, 70, 81, 15));
        parameters.setOutputPrefix("[CURRENTPAGE]-[TEXT]");
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(3);
        assertOutputContainsFilenames("1-Invoice 00001.pdf","4-Invoice 00002.pdf","5-Invoice 00003.pdf");
    }

    @Test
    public void testDoesNotSplitWhenThePageOnlyHasNonBreakingSpace() throws TaskException, IOException {
        setUpParameters(new TopLeftRectangularBox(68, 70, 73, 18));
        InputStream stream = getClass().getClassLoader().getResourceAsStream("pdf/split_by_text_newlines_sample.pdf");
        PdfStreamSource source = PdfStreamSource.newInstanceNoPassword(stream, "test_file.pdf");
        parameters.setSource(source);
        parameters.setOutputPrefix("[CURRENTPAGE]-[TEXT]");
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(2);
        assertOutputContainsFilenames("1-1234561234.pdf", "3-1234561235.pdf");
    }

    @Test
    public void testUsingPrefix() throws TaskException, IOException {
        setUpParameters(new TopLeftRectangularBox(70, 110, 92, 15));
        parameters.setStartsWith("Fax:");
        InputStream stream = getClass().getClassLoader().getResourceAsStream("pdf/split_by_text_with_prefix.pdf");
        PdfStreamSource source = PdfStreamSource.newInstanceNoPassword(stream, "test_file.pdf");
        parameters.setSource(source);
        parameters.setOutputPrefix("[CURRENTPAGE]-[TEXT]");
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(2);
        assertOutputContainsFilenames("1-1234561234.pdf", "3-4321231234.pdf");
    }

    @Test
    public void testUsingSuffix() throws TaskException, IOException {
        setUpParameters(new TopLeftRectangularBox(69, 95, 154, 16));
        parameters.setEndsWith("Amsterdam, Netherlands");
        InputStream stream = getClass().getClassLoader().getResourceAsStream("pdf/split_by_text_with_prefix.pdf");
        PdfStreamSource source = PdfStreamSource.newInstanceNoPassword(stream, "test_file.pdf");
        parameters.setSource(source);
        parameters.setOutputPrefix("[CURRENTPAGE]-[TEXT]");
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(2);
        assertOutputContainsFilenames("1-1023AB.pdf", "3-6543AB.pdf");
    }
}
