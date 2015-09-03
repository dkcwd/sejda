/*
 * Created on 07/ago/2011
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.model.outline;

/**
 * Component providing methods to retrieve outline information based on depth level.
 * 
 * @author Andrea Vacondio
 */
public interface OutlineLevelsHandler {

    /**
     * @return the max depth level in the pdf document outline associated to this handler.
     */
    int getMaxOutlineDepth();

    /**
     * @param level
     * @return page destinations found at the given outline level
     */
    OutlinePageDestinations getPageDestinationsForLevel(int level);
}