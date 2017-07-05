package com.zendaimoney.Dokodemo.html;

import java.util.ArrayList;

import com.zendaimoney.Dokodemo.engine.Engine;
import com.zendaimoney.Dokodemo.engine.Selector.Scope;

public class Table extends Element {


	public Table(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}

	/**
	 * 从一个table的单元格中得到单元格元素. 参数row，column行列从0开始.
	 * 
	 * @param row
	 *            ,单元格的行，从0开始.
	 * @param column
	 *            ，单元格的列，从0开始.
	 * @return 该单元格元素
	 * @throws Exception 
	 */

	public Engine getCell(int row, int column) throws Exception {
		ArrayList<Engine> rows = getTableRows();
		// 列里面有"<th>"、"<td>"两种标签，所以分开处理。
		Engine engine = null;
		if (row > 0) {
			engine = rows.get(row)
					.get_element_by_control_name("td", Scope.Descendant)
					.get(column);
			if (null == engine) {
				engine = rows.get(row)
						.get_element_by_control_name("th", Scope.Descendant)
						.get(column);
			}
		} else {
			engine = rows.get(row)
					.get_element_by_control_name("th", Scope.Descendant)
					.get(column);
			if (null == engine) {
				engine = rows.get(row)
						.get_element_by_control_name("td", Scope.Descendant)
						.get(column);
			}
		}
		return engine;
	}

	/**
	 * 得到表格所有行元素
	 * 
	 * @return 该表格行元素
	 * @throws Exception 
	 */
	public ArrayList<Engine> getTableRows() throws Exception {
		if (isExist("getTableRows()")) {
			ArrayList<Engine> rows = engine.get_element_by_control_name("tr",
					Scope.Descendant);
			return rows;
		}
		return new ArrayList<Engine>();

	}

	/**
	 * 得到表格所有行数
	 * @throws Exception 
	 */
	public int getTableRowsNum() throws Exception {
		ArrayList<Engine> rows = getTableRows();
		return rows.size();
	}

	/**
	 * 得到表格某一行元素
	 * @throws Exception 
	 */
	public Engine getRow(int num) throws Exception {
		ArrayList<Engine> rows = getTableRows();
		return rows.get(num);
	}

	/**
	 * 得到表格所有列数
	 * @throws Exception 
	 */
	public int getTableColumnNum() throws Exception {
		ArrayList<Engine> rows = getTableRows();
		ArrayList<Engine> columns = rows.get(0).get_element_by_control_name(
				"td", Scope.Descendant);
		if (columns.size() > 0) {
			return columns.size();
		} else {
			return rows.get(0)
					.get_element_by_control_name("th", Scope.Descendant).size();
		}
	}

	/**
	 * 得到表格某一列元素
	 * @throws Exception 
	 */
	public ArrayList<Engine> getTableColumns(int num) throws Exception {
		ArrayList<Engine> columnData = new ArrayList<Engine>();
		ArrayList<Engine> rows = getTableRows();
		for (int i = 0; i < rows.size(); i++) {
			ArrayList<Engine> columns = rows.get(i)
					.get_element_by_control_name("td", Scope.Descendant);
			if (columns.size() > 0) {
				columnData.add(columns.get(num));
			} else {
				columnData.add(rows.get(i)
						.get_element_by_control_name("th", Scope.Descendant)
						.get(num));
			}
		}
		return columnData;
	}

	/**
	 * 得到表格某个单元格文本
	 * @throws Exception 
	 */
	public String getCellText(int row, int column) throws Exception {
		return getCell(row, column).text();
	}
}

