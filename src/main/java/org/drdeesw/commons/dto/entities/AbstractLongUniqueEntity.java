/**
 * 
 */
package org.drdeesw.commons.dto.entities;


import javax.persistence.MappedSuperclass;

import org.drdeesw.commons.dto.base.LongUniqueObject;


/**
 * @author gary_kephart
 *
 */
@MappedSuperclass
public abstract class AbstractLongUniqueEntity extends AbstractUniqueEntity<Long>
    implements LongUniqueObject
{
  private static final long serialVersionUID = -9190810275366831598L;


  /**
   * Hibernate
   */
  protected AbstractLongUniqueEntity()
  {
  }


  /**
   * @param that
   */
  protected AbstractLongUniqueEntity(AbstractLongUniqueEntity that)
  {
    super(that);
  }


  /**
   * @param id
   */
  protected AbstractLongUniqueEntity(Long id)
  {
    super(id);
  }


  /**
   * @param that
   */
  public void update(
    AbstractLongUniqueEntity that)
  {
    super.update(that);
  }

}
