package org.zanata.common;

public final class TransUnitCount extends AbstractTranslationCount
{

   private static final long serialVersionUID = 1L;

   public TransUnitCount()
   {
   }

   public TransUnitCount(int approved, int needReview, int untranslated, int rejected, int accepted)
   {
      super(approved, needReview, untranslated, rejected, accepted);
   }

   public TransUnitCount(int approved, int needReview, int untranslated)
   {
      super(approved, needReview, untranslated);
   }

   public void increment(ContentState state)
   {
      increment(state, 1);
   }

   public void decrement(ContentState state)
   {
      decrement(state, 1);
   }

   public void add(TransUnitCount other)
   {
      super.add(other);
   }

   public void set(TransUnitCount other)
   {
      super.set(other);
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == this)
         return true;
      if (obj == null)
         return false;
      if (obj instanceof TransUnitCount)
      {
         return super.equals(obj);
      }
      return false;
   }

}