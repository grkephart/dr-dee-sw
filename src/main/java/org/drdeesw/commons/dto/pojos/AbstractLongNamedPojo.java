/**
 * 
 */
package org.drdeesw.commons.dto.pojos;

import org.drdeesw.commons.dto.base.LongNamedObject;

/**
 * @author gary_kephart
 *
 */
public class AbstractLongNamedPojo extends AbstractNamedPojo<Long>
    implements LongNamedObject
{
  private static final long serialVersionUID = 6902141588206147324L;

  /**
   * 
   */
  public AbstractLongNamedPojo()
  {
    super();
  }


  /**
   * @param that
   */
  public AbstractLongNamedPojo(AbstractNamedPojo<Long> that)
  {
    super(that);
  }


  /**
   * @param id
   * @param name
   */
  public AbstractLongNamedPojo(Long id, String name)
  {
    super(id, name);
  }


  /**
   * @param id
   */
  public AbstractLongNamedPojo(Long id)
  {
    super(id);
  }


  /**
   * @param name
   */
  public AbstractLongNamedPojo(String name)
  {
    super(name);
  }


  /**
   *
   */
  @Override
  public Long getId()
  {
    return super.getId();
  }


  /**
   *
   */
  @Override
  public void setId(
    Long id)
  {
    super.setId(id);
  }
}
