/*
 * Created on 27/apr/2010
 *
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
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

import org.sejda.core.manipulation.model.output.TaskOutput;

/**
 * Model for a task parameters used during the task executions
 * 
 * @author Andrea Vacondio
 * 
 */
public interface TaskParameters {

    /**
     * @return output destination where the result of the manipulation is placed
     */
    TaskOutput getOutput();

    /**
     * @return true if the tasks' outputs should overwrite any existing file, false otherwise
     */
    boolean isOverwrite();
}