/**
 * 
 */
package org.drdeesw.commons.dto.pojos;


import java.io.Serializable;

import org.drdeesw.commons.dto.base.UniqueObject;


/**
 * Represents a unique object.
 * 
 * @author gary_kephart
 *
 */
public abstract class AbstractUniquePojo<ID extends Serializable> implements UniquePojo<ID>
{
  private static final long serialVersionUID = 3882181757154157592L;
  private ID                id;

  /**
   * The default constructor.
   */
  protected AbstractUniquePojo()
  {
  }


  /**
   * Constructs an object with the given id.
   * 
   * @param id the ID of the object to construct
   */
  protected AbstractUniquePojo(ID id)
  {
    this.id = id;
  }


  /**
   * Constructs a copy of the given object.
   * 
   * @param that the object to copy
   */
  protected AbstractUniquePojo(UniquePojo<ID> that)
  {
    this.id = that.getId();
  }



  /**
   * @param that
   */
  public AbstractUniquePojo(UniqueObject<ID> that)
  {
    this.id = that.getId();
  }


  /**
   * @return
   */
  @SuppressWarnings("unchecked")
  protected <T extends AbstractUniquePojo<ID>> T cast()
  {
    return (T)this;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(
    Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("unchecked")
    AbstractUniquePojo<ID> other = (AbstractUniquePojo<ID>)obj;
    if (this.id == null)
    {
      if (other.id != null)
        return false;
    }
    else if (!id.equals(other.id))
      return false;
    return true;
  }


  /**
   * @param value1
   * @param value2
   * @return
   */
  public boolean equalsWithNullCheck(
    Object value1,
    Object value2)
  {
    return (value1 == null && value2 == null)
           || (value1 != null && value2 != null && value1.equals(value2));
  }


  /**
   * Returns the ID of the object.
   * 
   * @return the unique id of the object.
   */
  @Override
  public ID getId()
  {
    return this.id;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;

    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

    return result;
  }


  /* (non-Javadoc)
   * @see com.dr_dee_sw.commons.dto.UniqueObject#setId(java.io.Serializable)
   */
  @Override
  public void setId(
    ID id)
  {
    this.id = id;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "{class:" + this.getClass().getSimpleName() + ",id:" + this.id + "}";
  }


  /**
   * @param that
   */
  public void update(
    AbstractUniquePojo<ID> that)
  {
    this.id = that.id;
  }
}
