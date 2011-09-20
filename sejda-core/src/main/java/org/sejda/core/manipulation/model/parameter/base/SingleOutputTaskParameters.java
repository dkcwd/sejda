/*
 * Created on 12/ago/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.core.manipulation.model.parameter.base;

/**
 * A {@link TaskParameters} parameter whose execution result in a single output pdf document.
 * 
 * @author Andrea Vacondio
 * 
 */
public interface SingleOutputTaskParameters extends TaskParameters {

    /**
     * @return the output file name to be used when the TaskOutput is not a File output.
     */
    String getOutputName();
}