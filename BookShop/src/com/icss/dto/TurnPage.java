package com.icss.dto;

public class TurnPage {
	public int pageno = 1;  //当前的页号 默认从1开始
	public int rows = 10;    //每页的显示行数
	public int allRows;       //总的记录数
	public int allPages;      //总页数
}
