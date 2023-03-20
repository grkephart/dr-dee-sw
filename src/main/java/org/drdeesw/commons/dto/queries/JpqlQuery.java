/**
 * 
 */
package org.drdeesw.commons.dto.queries;


import java.text.DateFormat;
import java.util.Date;


/**
 * @author gkephart
 *
 */
public class JpqlQuery<T> extends Query<T>
{
  private static final String LOWER_FUNC = "LOWER"; // database-dependent
  private DateFormat          df;

  /**
   * 
   */
  public JpqlQuery(Class<T> clazz)
  {
    super(clazz);
  }


  /**
   * 
   */
  public JpqlQuery(Class<T> clazz, DateFormat df)
  {
    super(clazz);
    this.df = df;
  }


  /**
   * @param clazz
   * @param matchAny
   */
  public JpqlQuery(Class<T> clazz, Match match)
  {
    super(clazz, match);
  }


  /**
   * @param clazz
   * @param matchAny
   */
  public JpqlQuery(Class<T> clazz, Match match, DateFormat df)
  {
    super(clazz, match);
    this.df = df;
  }


  /**
   * @param value3
   * @return
   */
  private String formatValue(
    Object value,
    Operator operator)
  {
    String formattedValue;

    if (value instanceof String)
    {
      if (operator.isLike())
        formattedValue = "'%" + value + "%'";
      else
        formattedValue = "'" + value + "'";

      if (operator.isCaseInsensitive())
        formattedValue = LOWER_FUNC + "(" + formattedValue + ")";
    }
    else if (value instanceof Date)
      formattedValue = this.df.format((Date)value);
    else
      formattedValue = String.valueOf(value);

    return formattedValue;

  }


  /**
   * @return
   */
  private String getOrderClause()
  {
    StringBuffer buffer = new StringBuffer();

    if (!super.getOrderings().isEmpty())
    {
      boolean first = true;
      buffer.append(" ORDER BY");

      for (Ordering ordering : super.getOrderings())
      {
        String name = getAlias() + "." + ordering.getName();

        buffer.append(" ");

        if (first)
          first = false;
        else
          buffer.append(",");

        buffer.append(name);
        buffer.append(" ");
        buffer.append(ordering.isAscending() ? "ASC" : "DESC");
      }
    }

    return buffer.toString();
  }


  /**
   * @return
   */
  private String getWhereClause()
  {
    StringBuffer buffer = new StringBuffer();

    if (super.conditionsPresent() || super.hasMandatoryConditions())
    {
      buffer.append(" WHERE ");

      if (super.hasMandatoryConditions())
      {
        boolean first = true;

        for (Condition condition : getMandatoryConditions())
        {
          if (first)
            first = false;
          else
          {
            buffer.append(" ");
            buffer.append(Match.MATCH_ALL.getSql()); // do NOT use getMatch()
          }

          buffer.append(toJpql(condition));
        }
      }

      if (super.conditionsPresent())
      {
        boolean first = true;

        if (hasMandatoryConditions())
        {
          buffer.append(" ");
          buffer.append(Match.MATCH_ALL.getSql() + " (");
        }

        for (Condition condition : super.getConditions())
        {
          if (first)
            first = false;
          else
            buffer.append(" " + super.getMatch().getSql());

          buffer.append(" " + toJpql(condition));
        }

        if (hasMandatoryConditions())
          buffer.append(")");
      }
    }

    return buffer.toString();
  }


  /**
   * @param df the df to set
   */
  public void setDf(
    DateFormat df)
  {
    this.df = df;
  }


  /**
   * @return
   */
  public String toCountJpql()
  {
    return "SELECT COUNT(" + getAlias()//
           + ") FROM " + super.getClassSimpleName() + " " + getAlias() + " " + getWhereClause();
  }


  /**
   * @return
   */
  public String toJpql()
  {
    return "SELECT " + getAlias()//
           + " FROM " + super.getClassSimpleName() + " " + getAlias() + " " + getWhereClause()
           + getOrderClause();
  }


  /**
   * @param condition
   * @return
   */
  public String toJpql(
    Condition condition)
  {
    String jpql = null;
    String fieldName = condition.getFieldName();
    Operator operator = condition.getOperator();
    Object value = condition.getValue();
    Object value2 = condition.getValue2();
    boolean caseInsensitive = operator.isCaseInsensitive();

    switch (operator.getType())
    {
      case NO_VALUES:
        jpql = fieldName + " " + operator.getSql();
        break;
      case UNARY:
        if (caseInsensitive)
          fieldName = LOWER_FUNC + "(" + fieldName + ")";

        jpql = fieldName + " " + operator.getSql() + " " + formatValue(value, operator);
        break;
      case BINARY:
        jpql = fieldName + " " + operator.getSql() + " " + formatValue(value, operator) + " AND "
               + formatValue(value2, operator);
        break;
      case MULTI:
        boolean first = true;

        jpql = "(";

        for (Condition cond : condition.getConditions())
        {
          if (first)
            first = false;
          else
            jpql += " " + operator.getSql() + " ";

          jpql += toJpql(cond);
        }

        jpql += ")";
        break;
    }

    return jpql;
  }

}
