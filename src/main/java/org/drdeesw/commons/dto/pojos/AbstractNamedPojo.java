package org.drdeesw.commons.dto.pojos;


import java.io.Serializable;

import org.drdeesw.commons.dto.base.NamedObject;


/**
 * @author gary_kephart
 *
 * @param <ID>
 */
public abstract class AbstractNamedPojo<ID extends Serializable> extends
    AbstractUniquePojo<ID> implements Comparable<NamedObject>, NamedObject
{
  private static final long serialVersionUID = -6603247573392458671L;
  private String            name;


  /**
   * 
   */
  protected AbstractNamedPojo()
  {
  }


  /**
   * @param id
   */
  protected AbstractNamedPojo(ID id)
  {
    super(id);
  }


  /**
   * @param id
   * @param name
   */
  protected AbstractNamedPojo(ID id, String name)
  {
    super(id);
    this.name = name;
  }


  /**
   * @param name
   */
  protected AbstractNamedPojo(String name)
  {
    this.name = name;
  }


  /**
   * @param that
   */
  protected AbstractNamedPojo(AbstractNamedPojo<ID> that)
  {
    super(that);
    this.name = that.name;
  }


  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(
    NamedObject o)
  {
    NamedObject that = (NamedObject)o;

    if (this.getName() == null && that.getName() == null)
      return 0;
    else if (this.getName() == null)
      return -1;
    else if (that.getName() == null)
      return 1;
    else
    {
      return this.getName().compareTo(that.getName());
    }
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
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("unchecked")
    AbstractNamedPojo<ID> other = (AbstractNamedPojo<ID>)obj;
    if (this.name == null)
    {
      if (other.name != null)
        return false;
    }
    else if (!this.name.equals(other.name))
      return false;
    return true;
  }


  /**
   * 
   */
  @Override
  public String getName()
  {
    return this.name;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
    return result;
  }


  /* (non-Javadoc)
   * @see com.dr_dee_sw.commons.dto.RenameableObject#setName(java.lang.String)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <NO extends NamedObject> NO setName(
    String name)
  {
    this.name = name;

    return (NO)cast();
  }


  /* (non-Javadoc)
   * @see com.dr_dee_sw.commons.dto.AbstractUniqueObject#toString()
   */
  @Override
  public String toString()
  {
    return "{class:" + this.getClass().getSimpleName() + ",id:" + getId() + ",name:'" + this.name
           + "'}";
  }


  public void update(
    AbstractNamedPojo<ID> that)
  {
    super.update(that);
    this.name = that.name;
  }


}
