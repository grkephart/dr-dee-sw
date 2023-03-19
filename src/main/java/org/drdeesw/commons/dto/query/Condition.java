/**
 * 
 */
package org.drdeesw.commons.dto.query;


import java.text.DateFormat;
import java.util.Date;


/**
 * @author gkephart
 *
 */
public class Condition
{
  public static Condition equals(
    String fieldName,
    Object value)
  {
    return new Condition(fieldName, Operator.EQUALS, value);
  }


  static Condition iequals(
    String fieldName,
    Object value)
  {
    return new Condition(fieldName, Operator.IEQUALS, value);
  }


  static Condition ilike(
    String fieldName,
    String value)
  {
    return new Condition(fieldName, Operator.ILIKE, value);
  }


  public static Condition isNull(
    String fieldName)
  {
    return new Condition(fieldName, Operator.IS_NULL);
  }


  static Condition like(
    String fieldName,
    String value)
  {
    return new Condition(fieldName, Operator.LIKE, value);
  }


  public static Condition or(
    Condition[] conditions)
  {
    return new Condition(conditions, Operator.OR);
  }

  private Condition[] conditions;
  private String      fieldName;
  private Operator    operator;
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
    this.value = value;
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
    this.value = value;
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
    this.value = value;
    this.value2 = value2;
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
   * @param value the value to set
   */
  public void setValue(
    Object value)
  {
    this.value = value;
  }


  /**
   * @param value2 the value2 to set
   */
  public void setValue2(
    Object value2)
  {
    this.value2 = value2;
  }
}
