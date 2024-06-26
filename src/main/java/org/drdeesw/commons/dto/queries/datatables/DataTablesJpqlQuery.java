/**
 * 
 */
package org.drdeesw.commons.dto.queries.datatables;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.drdeesw.commons.dto.queries.Condition;
import org.drdeesw.commons.dto.queries.JpqlQuery;
import org.drdeesw.commons.dto.queries.Match;
import org.springframework.util.MultiValueMap;


/**
 * @author gkephart
 *
 */
public class DataTablesJpqlQuery<T> extends JpqlQuery<T>
{
  private Integer draw;

  /**
   * Defaults to MATCH_ANY.
   * 
   * @param clazz
   */
  public DataTablesJpqlQuery(Class<T> clazz)
  {
    super(clazz, Match.MATCH_ANY);
  }

  /**
   * 
   * @param clazz
   * @param match
   */
  public DataTablesJpqlQuery(Class<T> clazz, Match match)
  {
    super(clazz, match);
  }

  /**
   * Defaults to MATCH_ANY.
   * 
   * @param clazz
   * @param model
   */
  public DataTablesJpqlQuery(Class<T> clazz, JQueryDataTableParamModel model)
  {
    this(clazz, model, Match.MATCH_ANY);
  }


  /**
   * @param clazz
   * @param model
   * @param match
   */
  public DataTablesJpqlQuery(Class<T> clazz, JQueryDataTableParamModel model, Match match)
  {
    super(clazz, match);
    String searchValue = model.getSearchValue();
    Boolean searchRegex = model.getSearchRegex();
    boolean[] columnSearchable = model.getColumnSearchable();
    String[] columnNames = model.getColumnNames();
    String[] columnData = model.getColumnData();
    boolean[] columnOrderables = model.getColumnOrderables();
    int[] orderColumns = model.getOrderColumns();
    boolean[] orderDirs = model.getOrderDirs();
    String[] columnSearchValues = model.getColumnSearchValues();
    boolean searchValueExists = searchValue != null && searchValue.length() > 0;
    boolean columnSearchValuesExist = columnSearchValues != null && columnSearchValues.length > 0;

    this.draw = model.getDraw();
    super.setCaseInsensitive(
      model.getSearchCaseInsensitive() == null ? DEFAULT_CASE_INSENSITIVE
                                               : model.getSearchCaseInsensitive().booleanValue());

    super.setStart(model.getStart());
    super.setMaxResults(model.getLength());
    super.setPerformCount(model.isPerformCount());

    if (searchValueExists)
    {
      // Do not escape single quotes here. This is done in JpqlQuery

      for (int x = 0; x < columnNames.length; x++)
      {
        if (columnSearchable[x])
        {
          String columnName = columnData[x];

          if (Boolean.TRUE.equals(searchRegex))
            ilike(columnName, searchValue);
          else
            iequals(columnName, searchValue);
        }
      }
    }

    // All of the columnSearchValues need to be grouped together as part of 
    // an AND condition, but separate from the global searchValue, with which
    // they will be OR'd.
    if (columnSearchValuesExist)
    {
      List<Condition> columnSearchConditions = new ArrayList<>();

      for (int x = 0; x < columnSearchValues.length; x++)
      {
        if (columnSearchable[x])
        {
          String columnSearchValue = columnSearchValues[x];
          // Do not escape single quotes here. This is done in JpqlQuery
          String trimmedValue = columnSearchValue == null ? null : columnSearchValue.trim();

          if (trimmedValue != null && trimmedValue.length() > 0)
          {
            String columnName = columnData[x];

            if (Boolean.TRUE.equals(searchRegex))
              columnSearchConditions.add(Condition.ilike(columnName, trimmedValue));
            else
              columnSearchConditions.add(Condition.iequals(columnName, trimmedValue));
          }
        } // end if columnSearchable
      } // end loop

      if (!columnSearchConditions.isEmpty())
        and(columnSearchConditions.toArray(new Condition[0]));

    } // end if columnSearchValues

    if (orderColumns != null && orderColumns.length > 0)
    {
      for (int x = 0; x < orderColumns.length; x++)
      {
        int colNumber = orderColumns[x];

        if (columnOrderables[colNumber])
        {
          String data = columnData[colNumber];
          boolean ascending = orderDirs[x];

          super.addOrdering(data, ascending);
        }
      }
    }

    if (!model.getAjaxData().isEmpty())
    {
      for (Entry<String, Object> entry : model.getAjaxData().entrySet())
      {
        addMandatoryCondition(entry.getKey(), entry.getValue());
      }
    }
  }


  /**
   * @param clazz
   * @param allRequestParams
   */
  public DataTablesJpqlQuery(Class<T> clazz, MultiValueMap<String, String> allRequestParams)
  {
    this(clazz, DataTablesParamUtility.getParamModel(allRequestParams));
  }


  /**
   * @param clazz
   * @param allRequestParams
   * @param match
   */
  public DataTablesJpqlQuery(Class<T> clazz, MultiValueMap<String, String> allRequestParams, Match match)
  {
    this(clazz, DataTablesParamUtility.getParamModel(allRequestParams), match);
  }


  /**
   * @return the draw
   */
  public Integer getDraw()
  {
    return draw;
  }
}
