/**
 * 
 */
package org.drdeesw.commons.dto.base;


import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


/**
 * Represents a unique object.
 * 
 * @author gary_kephart
 *
 */
@MappedSuperclass
@Access(value = AccessType.FIELD)
public abstract class AbstractUniqueObject<ID extends Serializable> implements UniqueObject<ID>
{
  private static final long serialVersionUID = 3882181757154157592L;
  @Id
  @GenericGenerator(name = "native", strategy = "native")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @Column(name="id")
  private ID                id;

  /**
   * The default constructor.
   */
  protected AbstractUniqueObject()
  {
  }


  /**
   * Constructs an object with the given id.
   * 
   * @param id the ID of the object to construct
   */
  protected AbstractUniqueObject(ID id)
  {
    this.id = id;
  }


  /**
   * Constructs a copy of the given object.
   * 
   * @param that the object to copy
   */
  protected AbstractUniqueObject(AbstractUniqueObject<ID> that)
  {
    this.id = that.id;
  }


  /**
   * @return
   */
  @SuppressWarnings("unchecked")
  protected <T extends AbstractUniqueObject<ID>> T cast()
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
    AbstractUniqueObject<ID> other = (AbstractUniqueObject<ID>)obj;
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
   * Subclasses need to specify the Column annotation.
   * The ID annotation is necessary:
   * "You cannot override the [id] non-identifier property from the [...AbstractUniqueObject] base class or MappedSuperclass and make it an identifier in the subclass!"
   * So we should not need it in the subclasses.
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
    AbstractUniqueObject<ID> that)
  {
    this.id = that.id;
  }
}
