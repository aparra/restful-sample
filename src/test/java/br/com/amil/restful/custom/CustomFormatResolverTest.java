package br.com.amil.restful.custom;

import static junit.framework.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import br.com.caelum.vraptor.view.AcceptHeaderToFormat;

public class CustomFormatResolverTest {

	protected Mockery context = new JUnit4Mockery() {{
		this.setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	@Test
	public void extractFormatAndNotExtractVendorFromParameter() {
		final HttpServletRequest request = context.mock(HttpServletRequest.class);
		final AcceptHeaderToFormat acceptHeaderToFormat = context.mock(AcceptHeaderToFormat.class);

		context.checking(new Expectations(){{
			one(request).getParameter(with(equal("_format"))); will(returnValue("json"));
			one(request).getHeader(with(equal("Accept"))); will(returnValue(null));
		}});		
		
		CustomFormatResolver resolver = new CustomFormatResolver(request, acceptHeaderToFormat);
		
		assertEquals("json", resolver.getAcceptFormat());
		assertEquals("", resolver.getVendor());
	}
	
	@Test
	public void extractFormatAndVendorFromHeader() {
		final HttpServletRequest request = context.mock(HttpServletRequest.class);
		final AcceptHeaderToFormat acceptHeaderToFormat = context.mock(AcceptHeaderToFormat.class);

		context.checking(new Expectations(){{
			one(request).getParameter(with(equal("_format"))); will(returnValue(null));
			allowing(request).getHeader(with(equal("Accept"))); will(returnValue("application/vnd.amil.unit.test+json"));
			one(acceptHeaderToFormat).getFormat(with(equal("application/json"))); will(returnValue("json"));
			
		}});		
		
		CustomFormatResolver resolver = new CustomFormatResolver(request, acceptHeaderToFormat);
		
		assertEquals("json", resolver.getAcceptFormat());
		assertEquals("vnd.amil.unit.test", resolver.getVendor());
	}

	@Test
	public void extractFormatAndNotExtractVendorFromHeader() {
		final HttpServletRequest request = context.mock(HttpServletRequest.class);
		final AcceptHeaderToFormat acceptHeaderToFormat = context.mock(AcceptHeaderToFormat.class);

		context.checking(new Expectations(){{
			one(request).getParameter(with(equal("_format"))); will(returnValue(null));
			allowing(request).getHeader(with(equal("Accept"))); will(returnValue("application/json"));
			one(acceptHeaderToFormat).getFormat(with(equal("application/json"))); will(returnValue("json"));
			
		}});		
		
		CustomFormatResolver resolver = new CustomFormatResolver(request, acceptHeaderToFormat);
		
		assertEquals("json", resolver.getAcceptFormat());
		assertEquals("", resolver.getVendor());
	}

	
}
