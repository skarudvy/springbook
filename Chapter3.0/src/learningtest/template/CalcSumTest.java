package learningtest.template;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
   
public class CalcSumTest { 
	Calculator calculator;
	String numFilepath;
	
	@Before
	public void setup() {
		this.calculator = new Calculator();
		this.numFilepath = getClass().getResource("number.txt").getPath();
	}
	
	@Test
	public void sumOfNumbers() throws IOException {
		assertThat(calculator.calcSum(this.numFilepath), is(10));
//		System.out.println(numFilepath);
	}
	
	@Test
	public void multiplyOfNumbers() throws IOException {
		assertThat(calculator.calcMultiply(this.numFilepath), is(24));
	}
	
	@Test 
	public void concatenateStrings() throws IOException {
		assertThat(calculator.concatenate(this.numFilepath), is("1234"));
	}

}
