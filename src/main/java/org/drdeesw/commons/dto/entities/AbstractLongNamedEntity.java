/**
 * 
 */
package org.drdeesw.commons.dto.entities;

import javax.persistence.MappedSuperclass;

import org.drdeesw.commons.dto.base.LongNamedObject;

/**
 * @author gary_kephart
 *
 */
@MappedSuperclass
public class AbstractLongNamedEntity extends AbstractNamedEntity<Long>
    implements LongNamedObject
{
  private static final long serialVersionUID = 6902141588206147324L;

  /**
   * 
   */
  public AbstractLongNamedEntity()
  {
    super();
  }


  /**
   * @param that
   */
  public AbstractLongNamedEntity(AbstractNamedEntity<Long> that)
  {
    super(that);
  }


  /**
   * @param id
   * @param name
   */
  public AbstractLongNamedEntity(Long id, String name)
  {
    super(id, name);
  }


  /**
   * @param id
   */
  public AbstractLongNamedEntity(Long id)
  {
    super(id);
  }


  /**
   * @param name
   */
  public AbstractLongNamedEntity(String name)
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
