/*
 * Copyright 2015, Red Hat, Inc. and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.zanata.rest.service;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.LocaleDetails;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface ProjectIterationLocalesResource {
    public static final String SERVICE_PATH = ProjectIterationResource.SERVICE_PATH
            + "/locales";

    /**
     * Returns list of active locales for a single project-version.
     *
     * This may be the list of locales inherited from the project.
     *
     * @return
     *    OK 200 containing the list of LocaleDetails
     *    NOT FOUND 404 if the project-version does not exist
     */
    @GET
    // workaround for enunciate, see note in org.zanata.rest.service.ProjectResource
    @TypeHint(LocaleDetails[].class)
    @Produces({ MediaTypes.APPLICATION_ZANATA_PROJECT_LOCALES_XML,
            MediaTypes.APPLICATION_ZANATA_PROJECT_LOCALES_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response get();

}
