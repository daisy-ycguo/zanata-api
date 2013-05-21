/*
 * Copyright 2010, Red Hat, Inc. and individual contributors as indicated by the
 * @author tags. See the copyright.txt file in the distribution for a full
 * listing of individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.zanata.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

/**
 * Translation statistics. Contains actual numbers and other information about
 * the state of translation.
 * 
 * @author Carlos Munoz <a
 *         href="mailto:camunoz@redhat.com">camunoz@redhat.com</a>
 */
@XmlType(name = "translationStatistics", propOrder = { "total", "untranslated", "needReview", "translated", "unit", "locale", "lastTranslated" })
@XmlRootElement(name = "translationStats")
@JsonIgnoreProperties(value = { "percentTranslated", "percentNeedReview", "percentUntranslated" }, ignoreUnknown = true)
@JsonPropertyOrder({ "total", "untranslated", "needReview", "translated", "unit", "locale", "lastTranslated" })
@JsonWriteNullProperties(false)
public class TranslationStatistics implements Serializable
{

   public enum StatUnit
   {
      /** Statistics are measured in words. */
      WORD,
      /** Statistics are measured in messages (i.e. entries, text flows) */
      MESSAGE;
   }

   private long untranslated;
   private long fuzzy;
   private long translated;
   private long approved;
   private long rejected;
   
   private long total;
   private StatUnit unit;
   private String locale;
   private double remainingHours;
   private String lastTranslated;

   public TranslationStatistics()
   {
   }

   public TranslationStatistics(TransUnitCount unitCount, String locale)
   {
      this.unit = StatUnit.MESSAGE;
      this.locale = locale;
      untranslated = unitCount.getUntranslated();
      fuzzy = unitCount.getNeedReview();
//      translated = unitCount.getApproved();
      approved = unitCount.getApproved();
//      rejected = unitCount.getRejected();
      
      total = unitCount.getTotal();
   }

   public TranslationStatistics(TransUnitWords wordCount, String locale)
   {
      this.unit = StatUnit.WORD;
      this.locale = locale;
      
      untranslated = wordCount.getUntranslated();
      fuzzy = wordCount.getNeedReview();
//      translated = wordCount.getApproved();
      approved = wordCount.getApproved();
//      rejected = wordCount.getRejected();
      
      
      total = wordCount.getTotal();
   }

   /**
    * Number of untranslated elements.
    */
   @XmlAttribute
   public long getUntranslated()
   {
      return untranslated;
   }

   public void setUntranslated(long untranslated)
   {
      this.untranslated = untranslated;
   }
   
   /**
    * Number of elements that need review (i.e. Fuzzy).
    */
   @XmlAttribute
   public long getFuzzy()
   {
      return fuzzy;
   }

   public void setFuzzy(long fuzzy)
   {
      this.fuzzy = fuzzy;
   }
   
   /**
    * Number of translated elements.
    */
   @XmlAttribute
   public long getTranslated()
   {
      return translated;
   }

   public void setTranslated(long translated)
   {
      this.translated = translated;
   }
  
   /**
   * Number of approved elements.
   */
   @XmlAttribute
   public long getApproved()
   {
      return approved;
   }

   public void setApproved(long approved)
   {
      this.approved = approved;
   }
   
   @XmlAttribute
   public long getRejected()
   {
      return rejected;
   }

   public void setRejected(long rejected)
   {
      this.rejected = rejected;
   }

   /**
    * Total number of elements.
    */
   @XmlAttribute
   public long getTotal()
   {
      return total;
   }

   public void setTotal(long total)
   {
      this.total = total;
   }

   /**
    * Element unit being used to measure the translation counts.
    */
   @XmlAttribute
   public StatUnit getUnit()
   {
      return unit;
   }

   public void setUnit(StatUnit unit)
   {
      this.unit = unit;
   }

   /**
    * Locale for the translation statistics.
    */
   @XmlAttribute
   public String getLocale()
   {
      return locale;
   }

   public void setLocale(String locale)
   {
      this.locale = locale;
   }

   @XmlAttribute
   public String getLastTranslated()
   {
      return lastTranslated;
   }

   public void setLastTranslated(String lastTranslated)
   {
      this.lastTranslated = lastTranslated;
   }

   @XmlTransient
   public int getPercentTranslated()
   {
      long total = getTotal();
      if (total <= 0)
      {
         return 0;
      }
      else
      {
         double per = 100 * getTranslated() / total;
         return (int) Math.ceil(per);
      }
   }

   @XmlTransient
   public int getPercentNeedReview()
   {
      long total = getTotal();
      if (total <= 0)
      {
         return 0;
      }
      else
      {
         double per = 100 * getFuzzy() / total;
         return (int) Math.ceil(per);
      }
   }

   @XmlTransient
   public int getPercentUntranslated()
   {
      long total = getTotal();
      if (total <= 0)
      {
         return 0;
      }
      else
      {
         double per = 100 * getUntranslated() / total;
         return (int) Math.ceil(per);
      }
   }

   public void setRemainingHours(double remainingHours)
   {
      this.remainingHours = remainingHours;
   }

   @XmlTransient
   public double getRemainingHours()
   {
      return remainingHours;
   }

   public void add(TranslationStatistics other)
   {
      this.translated += other.translated;
//      this.needReview += other.needReview;
      this.untranslated += other.untranslated;
      this.rejected += other.rejected;
//      this.accepted += other.accepted;
   }

   public void increment(ContentState state, long count)
   {
      set(state, get(state) + count);
   }

   public void decrement(ContentState state, long count)
   {
      set(state, get(state) - count);
   }

   public long get(ContentState state)
   {
      switch (state)
      {
      case Approved:
         return translated;
      case NeedReview:
         return fuzzy;
      case New:
         return untranslated;
//      case Rejected:
//         return rejected;
//      case Accepted:
//         return accepted;
      default:
         throw new RuntimeException("not implemented for state " + state.name());
      }
   }

   public void set(ContentState state, long value)
   {
      switch (state)
      {
      case Approved:
         translated = value;
         break;
      case NeedReview:
         fuzzy = value;
         break;
      case New:
         untranslated = value;
         break;
//      case Rejected:
//         rejected = value;
//         break;
//      case Accepted:
//         accepted = value;
//         break;
      default:
         throw new RuntimeException("not implemented for state " + state.name());
      }
   }
}
