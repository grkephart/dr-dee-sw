/**
 * 
 */
package org.drdeesw.commons.dto.base;

/**
 * @author gary_kephart
 *
 */
public class AbstractLongRenameableObject extends AbstractNamedObject<Long>
    implements LongUniqueObject
{
  private static final long serialVersionUID = 6902141588206147324L;

  /**
   * 
   */
  public AbstractLongRenameableObject()
  {
    super();
  }


  /**
   * @param that
   */
  public AbstractLongRenameableObject(AbstractNamedObject<Long> that)
  {
    super(that);
  }


  /**
   * @param id
   * @param name
   */
  public AbstractLongRenameableObject(Long id, String name)
  {
    super(id, name);
  }


  /**
   * @param id
   */
  public AbstractLongRenameableObject(Long id)
  {
    super(id);
  }


  /**
   * @param name
   */
  public AbstractLongRenameableObject(String name)
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
