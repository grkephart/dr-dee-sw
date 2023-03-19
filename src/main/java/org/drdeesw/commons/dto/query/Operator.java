/**
 * 
 */
package org.drdeesw.commons.dto.query;


/**
 * @author gkephart
 *
 */
public class Operator
{
  public enum Type {
    BINARY, NO_VALUES, UNARY, MULTI
  }
  public static final Operator   BETWEEN     = new Operator("BETWEEN", Type.BINARY, "[betw]");
  public static final Operator   EQUALS      = new Operator("=", Type.UNARY, "[eq]");
  public static final Operator   GE          = new Operator(">=", Type.UNARY, "[ge]");
  public static final Operator   GT          = new Operator(">", Type.UNARY, "[gt]");
  public static final Operator   IEQUALS     = new Operator("=", Type.UNARY, "[ieq]");
  public static final Operator   ILIKE       = new Operator("LIKE", Type.UNARY, true, true,
      "[ilike]");
  public static final Operator   IS_NOT_NULL = new Operator("IS NOT NULL", Type.NO_VALUES, "[nn]");
  public static final Operator   IS_NULL     = new Operator("IS NULL", Type.NO_VALUES, "[null]");
  public static final Operator   LE          = new Operator("<=", Type.UNARY, "[le]");
  public static final Operator   LIKE        = new Operator("LIKE", Type.UNARY, false, true,
      "[like]");
  public static final Operator   LT          = new Operator("<", Type.UNARY, "[lt]");
  public static final Operator   NOT_EQUALS  = new Operator("<>", Type.UNARY, "[ne]");
  public static final Operator   AND         = new Operator("AND", Type.MULTI, "[and]");
  public static final Operator   OR          = new Operator("OR", Type.MULTI, "[or]");
  public static final Operator[] ALL         = { BETWEEN, EQUALS, GE, GT, IEQUALS, ILIKE,
                                                 IS_NOT_NULL, IS_NULL, LE, LIKE, LT, NOT_EQUALS,
                                                 AND, OR };
  private boolean                caseInsensitive;
  private boolean                like;
  private String                 lhs;
  private String                 sql;
  private Type                   type;

  /**
   * @param propertyName
   * @return
   */
  public static Operator byLhs(
    String propertyName)
  {
    Operator match = null;

    for (Operator operator : Operator.ALL)
    {
      if (propertyName.endsWith(operator.getLhs()))
      {
        match = operator;
        break;
      }
    }

    return match;
  }


  /**
   * @param string
   */
  Operator(String sql, Type type, String lhs)
  {
    this(sql, type, false, false, lhs);
  }


  /**
   * @param string
   */
  Operator(String sql, Type type, boolean caseInsensitive, boolean like, String lhs)
  {
    this.sql = sql;
    this.type = type;
    this.caseInsensitive = caseInsensitive;
    this.like = like;
    this.lhs = lhs;
  }


  /**
   * @return the sql
   */
  public String getSql()
  {
    return sql;
  }


  /**
   * @return the type
   */
  public Type getType()
  {
    return type;
  }


  /**
   * @return the caseInsensitive
   */
  public boolean isCaseInsensitive()
  {
    return caseInsensitive;
  }


  /**
   * @return the like
   */
  public boolean isLike()
  {
    return like;
  }


  /**
   * @return the lhs
   */
  public String getLhs()
  {
    return lhs;
  }

}
