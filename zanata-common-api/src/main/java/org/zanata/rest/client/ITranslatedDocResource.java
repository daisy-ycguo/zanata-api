/*
 * Copyright 2010, Red Hat, Inc. and individual contributors
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
package org.zanata.rest.client;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientResponse;
import org.zanata.common.LocaleId;
import org.zanata.rest.dto.resource.TranslationsResource;
import org.zanata.rest.service.TranslatedDocResource;

/**
 * Client Interface for the Translation Resources service. 
 */
@Produces({ MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_XML })
public interface ITranslatedDocResource extends TranslatedDocResource
{
   @GET
   @Path("{id}/translations/{locale}")
   public ClientResponse<TranslationsResource> getTranslations(@PathParam("id") String idNoSlash, @PathParam("locale") LocaleId locale, @QueryParam("ext") Set<String> extensions);

   @Override
   @GET
   @Path("{id}/translations/{locale}")
   public ClientResponse<TranslationsResource> getTranslations(
         @PathParam("id") String idNoSlash,
         @PathParam("locale") LocaleId locale,
         @QueryParam("ext") Set<String> extensions,
         @QueryParam("skeletons") boolean skeletons,
         @HeaderParam(HttpHeaders.IF_NONE_MATCH) String eTag);

   @Override
   @DELETE
   @Path("{id}/translations/{locale}")
   public ClientResponse<String> deleteTranslations(@PathParam("id") String idNoSlash, @PathParam("locale") LocaleId locale);

   @Override
   @PUT
   @Path("{id}/translations/{locale}")
   public ClientResponse<String> putTranslations(@PathParam("id") String idNoSlash, @PathParam("locale") LocaleId locale, TranslationsResource messageBody, @QueryParam("ext") Set<String> extensions, @QueryParam("merge") String merge);
   
   @PUT
   @Path("{id}/translations/{locale}")
   public ClientResponse<String> putTranslations(@PathParam("id") String idNoSlash, @PathParam("locale") LocaleId locale, TranslationsResource messageBody, @QueryParam("ext") Set<String> extensions);
}
