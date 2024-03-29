/**
 * 
 */
package org.drdeesw.commons.dto.pojos;

import org.drdeesw.commons.dto.base.NamedLongUniqueObject;

/**
 * @author gary_kephart
 *
 */
public class AbstractNamedLongUniquePojo extends AbstractNamedUniquePojo<Long>
    implements NamedLongUniqueObject
{
  private static final long serialVersionUID = 6902141588206147324L;

  /**
   * 
   */
  public AbstractNamedLongUniquePojo()
  {
    super();
  }


  /**
   * @param that
   */
  public AbstractNamedLongUniquePojo(AbstractNamedLongUniquePojo that)
  {
    super(that);
  }


  /**
   * @param id
   * @param name
   */
  public AbstractNamedLongUniquePojo(Long id, String name)
  {
    super(id, name);
  }


  /**
   * @param id
   */
  public AbstractNamedLongUniquePojo(Long id)
  {
    super(id);
  }


  /**
   * @param name
   */
  public AbstractNamedLongUniquePojo(String name)
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
