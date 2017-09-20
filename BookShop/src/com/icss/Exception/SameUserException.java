package com.icss.Exception;

@SuppressWarnings("serial")
public class SameUserException extends Exception {

	public SameUserException(String msg){
		super(msg);
	}
}
