/**
 * 
 */
package org.drdeesw.commons.dto.base;

/**
 * @author gary_kephart
 *
 */
public class AbstractLongNamedObject extends AbstractNamedObject<Long>
    implements LongUniqueObject
{
  private static final long serialVersionUID = 6902141588206147324L;

  /**
   * 
   */
  public AbstractLongNamedObject()
  {
    super();
  }


  /**
   * @param that
   */
  public AbstractLongNamedObject(AbstractNamedObject<Long> that)
  {
    super(that);
  }


  /**
   * @param id
   * @param name
   */
  public AbstractLongNamedObject(Long id, String name)
  {
    super(id, name);
  }


  /**
   * @param id
   */
  public AbstractLongNamedObject(Long id)
  {
    super(id);
  }


  /**
   * @param name
   */
  public AbstractLongNamedObject(String name)
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
