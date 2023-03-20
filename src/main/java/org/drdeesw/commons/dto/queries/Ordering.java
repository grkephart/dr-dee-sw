/**
 * 
 */
package org.drdeesw.commons.dto.queries;

import org.drdeesw.commons.dto.base.DataTransferObject;

/**
 * @author gary_kephart
 *
 */
public class Ordering implements DataTransferObject
{
  private static final long serialVersionUID = 4169694001533857765L;
  private String            name;
  private boolean           ascending;


  public Ordering()
  {
    super();
  }


  /**
   * @param name
   * @param ascending
   */
  public Ordering(String name, boolean ascending)
  {
    super();
    this.name = name;
    this.ascending = ascending;
  }


  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }


  /**
   * @param name the name to set
   */
  public void setName(
    String name)
  {
    this.name = name;
  }


  /**
   * @return the ascending
   */
  public boolean isAscending()
  {
    return ascending;
  }


  /**
   * @param ascending the ascending to set
   */
  public void setAscending(
    boolean ascending)
  {
    this.ascending = ascending;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "Ordering [name=" + name + ", ascending=" + ascending + "]";
  }
}
