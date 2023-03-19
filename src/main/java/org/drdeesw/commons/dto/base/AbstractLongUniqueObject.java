/**
 * 
 */
package org.drdeesw.commons.dto.base;


import javax.persistence.MappedSuperclass;


/**
 * @author gary_kephart
 *
 */
@MappedSuperclass
public abstract class AbstractLongUniqueObject extends AbstractUniqueObject<Long>
    implements LongUniqueObject
{
  private static final long serialVersionUID = -9190810275366831598L;


  protected AbstractLongUniqueObject()
  {
  }


  /**
   * @param that
   */
  protected AbstractLongUniqueObject(AbstractLongUniqueObject that)
  {
    super(that);
  }


  protected AbstractLongUniqueObject(Long id)
  {
    super(id);
  }


  /**
   * @param that
   */
  public void update(
    AbstractLongUniqueObject that)
  {
    super.update(that);
  }

}
