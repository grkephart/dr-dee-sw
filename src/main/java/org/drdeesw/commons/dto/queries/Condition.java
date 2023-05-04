/**
 * 
 */
package org.drdeesw.commons.dto.queries;


import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;


/**
 * @author gkephart
 *
 */
public class Condition
{
  /**
   * @param conditions
   * @return
   */
  public static Condition and(
    Condition[] conditions)
  {
    return new Condition(conditions, Operator.AND);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  public static Condition equals(
    String fieldName,
    Object value)
  {
    return new Condition(fieldName, Operator.EQUALS, value);
  }


  /**
   * @param propertyName
   * @param value
   * @param isRef
   * @return
   */
  public static Condition equals(
    String propertyName,
    Object value,
    boolean isRef)
  {
    return new Condition(propertyName, Operator.EQUALS, value, isRef);
  }


  /**
   * @param subquery
   * @return
   */
  public static Condition exists(
    Query<?> subquery)
  {
    return new Condition(Operator.EXISTS, subquery);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  public static Condition ge(
    String fieldName,
    Object value)
  {
    return new Condition(fieldName, Operator.GE, value);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  public static Condition gt(
    String fieldName,
    Object value)
  {
    return new Condition(fieldName, Operator.GT, value);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  public static Condition iequals(
    String fieldName,
    Object value)
  {
    return new Condition(fieldName, Operator.IEQUALS, value);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  public static Condition ilike(
    String fieldName,
    String value)
  {
    return new Condition(fieldName, Operator.ILIKE, value);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  public static Condition in(
    String fieldName,
    Collection<?> value)
  {
    return new Condition(fieldName, Operator.IN, value);
  }


  /**
   * @param fieldName
   * @return
   */
  public static Condition isNotNull(
    String fieldName)
  {
    return new Condition(fieldName, Operator.IS_NOT_NULL);
  }


  /**
   * @param fieldName
   * @return
   */
  public static Condition isNull(
    String fieldName)
  {
    return new Condition(fieldName, Operator.IS_NULL);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  static Condition like(
    String fieldName,
    String value)
  {
    return new Condition(fieldName, Operator.LIKE, value);
  }


  /**
   * @param fieldName
   * @param value
   * @return
   */
  public static Condition notEquals(
    String fieldName,
    Object value)
  {
    return new Condition(fieldName, Operator.NOT_EQUALS, value);
  }


  /**
   * @param subquery
   * @return
   */
  public static Condition notExists(
    Query<?> subquery)
  {
    return new Condition(Operator.NOT_EXISTS, subquery);
  }


  /**
   * @param conditions
   * @return
   */
  public static Condition or(
    Condition[] conditions)
  {
    return new Condition(conditions, Operator.OR);
  }

  private Condition[] conditions;
  private String      fieldName;
  private Operator    operator;
  private boolean     ref;
  private Query<?>    subquery;
  private Object      value;
  private Object      value2;

  /**
   * @param conditions
   * @param operator
   */
  public Condition(Condition[] conditions, Operator operator)
  {
    this.conditions = conditions;
    this.operator = operator;
    this.ref = false;
  }


  public Condition(Operator operator, Query<?> subquery)
  {
    this.subquery = subquery;
    this.operator = operator;
    this.ref = false;
  }


  /**
   * @param fieldName
   * @param operator
   */
  public Condition(String fieldName, Operator operator)
  {
    super();
    this.fieldName = fieldName;
    this.operator = operator;
    this.ref = false;
  }


  /**
   * @param fieldName
   * @param operator
   * @param value
   * @param value2
   * @param df
   */
  public Condition(String fieldName, Operator operator, Date value, Date value2)
  {
    super();
    this.fieldName = fieldName;
    this.operator = operator;
    this.value = value;
    this.value2 = value2;
    this.ref = false;
  }


  /**
   * @param fieldName
   * @param operator
   * @param value
   * @param df
   */
  public Condition(String fieldName, Operator operator, Date value, DateFormat df)
  {
    super();
    this.fieldName = fieldName;
    this.operator = operator;
    this.value = sqlSafeString(value);
    this.ref = false;
  }


  /**
   * @param fieldName
   * @param operator
   * @param value
   */
  public Condition(String fieldName, Operator operator, Object value)
  {
    super();
    this.fieldName = fieldName;
    this.operator = operator;
    this.value = sqlSafeString(value);
    this.ref = false;
  }


  /**
   * @param fieldName
   * @param operator
   * @param value
   * @param value2
   */
  public Condition(String fieldName, Operator operator, Object value, boolean ref)
  {
    super();
    this.fieldName = fieldName;
    this.operator = operator;
    this.value = sqlSafeString(value);
    this.value2 = null;
    this.ref = ref;
  }


  /**
   * @param fieldName
   * @param operator
   * @param value
   * @param value2
   */
  public Condition(String fieldName, Operator operator, Object value, Object value2)
  {
    super();
    this.fieldName = fieldName;
    this.operator = operator;
    this.value = sqlSafeString(value);
    this.value2 = sqlSafeString(value2);
    this.ref = false;
  }


  /**
   * @return the conditions
   */
  public Condition[] getConditions()
  {
    return conditions;
  }


  /**
   * @return the fieldName
   */
  public String getFieldName()
  {
    return fieldName;
  }


  /**
   * @return the operator
   */
  public Operator getOperator()
  {
    return operator;
  }


  /**
   * @return the subquery
   */
  public Query<?> getSubquery()
  {
    return subquery;
  }


  /**
   * @return the value
   */
  public Object getValue()
  {
    return value;
  }


  /**
   * @return the value2
   */
  public Object getValue2()
  {
    return value2;
  }


  /**
   * @return the ref
   */
  public boolean isRef()
  {
    return ref;
  }


  /**
   * @param conditions the conditions to set
   */
  public void setConditions(
    Condition[] conditions)
  {
    this.conditions = conditions;
  }


  /**
   * @param fieldName the fieldName to set
   */
  public void setFieldName(
    String fieldName)
  {
    this.fieldName = fieldName;
  }


  /**
   * @param operator the operator to set
   */
  public void setOperator(
    Operator operator)
  {
    this.operator = operator;
  }


  /**
   * @param ref the ref to set
   */
  public void setRef(
    boolean ref)
  {
    this.ref = ref;
  }


  /**
   * @param subquery the subquery to set
   */
  public void setSubquery(
    Query<?> subquery)
  {
    this.subquery = subquery;
  }


  /**
   * @param value the value to set
   */
  public void setValue(
    Object value)
  {
    this.value = sqlSafeString(value);
  }


  /**
   * @param value2 the value2 to set
   */
  public void setValue2(
    Object value2)
  {
    this.value2 = sqlSafeString(value2);
  }


  /**
   * Primarily for escaping single quotes in a string value.
   * 
   * @param value
   * @return
   */
  private Object sqlSafeString(
    Object value)
  {
    if (value instanceof String)
    {
      String str = (String)value;

      return str.replace("'", "\\'");
    }
    else
      return value;
  }
}
