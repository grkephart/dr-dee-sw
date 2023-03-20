/**
 * 
 */
package org.drdeesw.commons.dto.queries.datatables;


import java.util.Map.Entry;

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
   * @param clazz
   * @param allRequestParams
   * @throws Exception
   */
  public DataTablesJpqlQuery(Class<T> clazz, MultiValueMap<String, String> allRequestParams)
      throws Exception
  {
    this(clazz, DataTablesParamUtility.getParamModel(allRequestParams));
  }


  /**
   * @param clazz
   * @param model
   * @throws Exception
   */
  public DataTablesJpqlQuery(Class<T> clazz, JQueryDataTableParamModel model) throws Exception
  {
    super(clazz, Match.MATCH_ANY);
    String searchValue = model.getSearchValue();
    Boolean searchRegex = model.getSearchRegex();
    boolean[] columnSearchable = model.getColumnSearchable();
    String[] columnNames = model.getColumnNames();
    String[] columnData = model.getColumnData();
    boolean[] columnOrderables = model.getColumnOrderables();
    int[] orderColumns = model.getOrderColumns();
    boolean[] orderDirs = model.getOrderDirs();
    String[] columnSearchValues = model.getColumnSearchValues();

    this.draw = model.getDraw();
    super.setCaseInsensitive(
      model.getSearchCaseInsensitive() == null ? DEFAULT_CASE_INSENSITIVE
                                               : model.getSearchCaseInsensitive().booleanValue());

    super.setStart(model.getStart());
    super.setMaxResults(model.getLength());
    super.setPerformCount(model.isPerformCount());

    if (searchValue != null && searchValue.length() > 0)
    {
      searchValue = searchValue.replace("'", "''");
      
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

    if (columnSearchValues != null && columnSearchValues.length > 0)
    {
      super.setMatch(Match.MATCH_ALL);
      
      for (int x = 0; x < columnSearchValues.length; x++)
      {
        if (columnSearchable[x])
        {
          String columnSearchValue = columnSearchValues[x];
          String trimmedValue = columnSearchValue == null ? null : columnSearchValue.trim().replace("'", "''");

          if (trimmedValue != null && trimmedValue.length() > 0)
          {
            String columnName = columnData[x];
            
            if (Boolean.TRUE.equals(searchRegex))
              ilike(columnName, trimmedValue);
            else
              iequals(columnName, trimmedValue);
          }
        }
      }
    }

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
   * @return the draw
   */
  public Integer getDraw()
  {
    return draw;
  }
}
