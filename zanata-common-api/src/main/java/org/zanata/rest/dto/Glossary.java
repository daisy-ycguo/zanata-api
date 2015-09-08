/*
 * Copyright 2011, Red Hat, Inc. and individual contributors
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
package org.zanata.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.common.Namespaces;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.MediaTypes.Format;
import org.zanata.rest.dto.resource.LocaleList;

/**
 *
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 *
 **/

@XmlRootElement(name = "glossary")
@XmlType(name = "glossaryType", propOrder = { "glossaryEntries" })
@JsonPropertyOrder({ "glossaryEntries" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Glossary implements Serializable, HasMediaType {
    /**
    *
    */
    private static final long serialVersionUID = 2979294228147882716L;

    private List<GlossaryEntry> glossaryEntries;

    @XmlElementWrapper(name = "glossary-entries",
            namespace = Namespaces.ZANATA_OLD)
    @XmlElement(name = "glossary-entry", namespace = Namespaces.ZANATA_OLD)
    public List<GlossaryEntry> getGlossaryEntries() {
        if (glossaryEntries == null) {
            glossaryEntries = new ArrayList<GlossaryEntry>();
        }
        return glossaryEntries;
    }

    public void setGlossaryEntries(List<GlossaryEntry> glossaryEntries) {
        this.glossaryEntries = glossaryEntries;
    }

    @Override
    public String getMediaType(Format format) {
        return MediaTypes.APPLICATION_ZANATA_GLOSSARY + format;
    }

    @Override
    public String toString() {
        return DTOUtil.toXML(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result =
                prime
                        * result
                        + ((glossaryEntries == null) ? 0 : glossaryEntries
                                .hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Glossary other = (Glossary) obj;
        if (glossaryEntries == null) {
            if (other.glossaryEntries != null)
                return false;
        } else if (!glossaryEntries.equals(other.glossaryEntries))
            return false;
        return true;
    }
}
