package rocketBase;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	@Test
	public void testRate(){
		try {
			double rate = RateBLL.getRate(730);
			assert(4==rate);
		} catch (RateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() {
		double pmt = RateBLL.getPayment(.04/12, 360, 300000, 0, false);
		Assert.assertEquals(1432.25, pmt, 1);
	}
	@Test(expected = RateException.class)
	public void testError() throws Exception{
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		int testRate = 400;
		try {
			double rate = RateBLL.getRate(testRate);
			System.out.println("The rate is :" +rate);
		}
		catch (RateException e){
			throw e;
		}
	}
}
