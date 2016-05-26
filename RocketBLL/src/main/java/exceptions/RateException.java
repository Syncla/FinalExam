package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	RateDomainModel errorRate;
	public RateException(RateDomainModel rateDomainModel) {
		errorRate=rateDomainModel;
		// TODO Auto-generated constructor stub
	}
	public RateDomainModel getErrorRate() {
		return errorRate;
	}
	
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
}
