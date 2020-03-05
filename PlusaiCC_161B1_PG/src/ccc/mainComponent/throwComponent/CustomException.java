package ccc.mainComponent.throwComponent;

import ccc.mainComponent.globalID;

public final class CustomException extends Exception{
	
	public CustomException(String e) {
		super(e);
		globalID.addErrorCount(this);
	}
}